package com.util;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
// import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * Centralized password hashing utility using Argon2id with a server-side pepper.
 *
 * <p><strong>Design decisions:</strong></p>
 * <ul>
 *   <li><strong>Argon2id</strong> – hybrid variant resistant to both side-channel
 *       and GPU/ASIC attacks (OWASP recommended).</li>
 *   <li><strong>Unique salt</strong> – generated automatically by the Argon2 library
 *       (16 bytes of {@code SecureRandom}) for every hash operation.</li>
 *   <li><strong>Server-side pepper</strong> – a secret prepended to the password before
 *       hashing. Loaded from an environment variable ({@code PEPPER_SECRET}) with
 *       a file-based fallback ({@code pepper.properties}). The pepper is never
 *       stored in the database.</li>
 * </ul>
 *
 * <p>Argon2 parameters follow OWASP 2024 recommendations for Argon2id:</p>
 * <ul>
 *   <li>Iterations: 3</li>
 *   <li>Memory: 65536 KiB (64 MiB)</li>
 *   <li>Parallelism: 1</li>
 *   <li>Salt length: 16 bytes (default)</li>
 *   <li>Hash length: 32 bytes (default)</li>
 * </ul>
 */
public final class PasswordUtils {

    // --- Argon2id parameters (OWASP 2024 recommended) ---
    private static final int ITERATIONS  = 3;
    private static final int MEMORY_KB   = 65536;  // 64 MiB
    private static final int PARALLELISM = 1;

    // --- Pepper (loaded once at class-load time) ---
    private static final String PEPPER = loadPepper();

    // --- Argon2 instance (Argon2id variant) ---
    private static final Argon2 ARGON2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

    private PasswordUtils() {
        // Utility class — prevent instantiation
    }

    /**
     * Hashes a plaintext password with Argon2id.
     * <p>A unique 16-byte salt is generated automatically by the library for each call.</p>
     *
     * @param plainTextPassword the raw password supplied by the user
     * @return the encoded Argon2id hash string (includes algorithm, parameters, salt, and hash)
     */
    public static String hashPassword(String plainTextPassword) {
        char[] peppered = applyPepper(plainTextPassword);
        try {
            return ARGON2.hash(ITERATIONS, MEMORY_KB, PARALLELISM, peppered);
        } finally {
            // Wipe the peppered password from memory
            java.util.Arrays.fill(peppered, '\0');
        }
    }

    /**
     * Verifies a plaintext password against a stored Argon2id hash.
     *
     * @param storedHash         the encoded hash retrieved from the database
     * @param plainTextPassword  the raw password supplied by the user
     * @return {@code true} if the password matches the hash; {@code false} otherwise
     */
    public static boolean verifyPassword(String storedHash, String plainTextPassword) {
        char[] peppered = applyPepper(plainTextPassword);
        try {
            return ARGON2.verify(storedHash, peppered);
        } finally {
            java.util.Arrays.fill(peppered, '\0');
        }
    }

    // ---------------------------------------------------------------
    // Internal helpers
    // ---------------------------------------------------------------

    /**
     * Prepends the server-side pepper to the plaintext password.
     */
    private static char[] applyPepper(String plainTextPassword) {
        String combined = PEPPER + plainTextPassword;
        return combined.toCharArray();
    }

    /**
     * Loads the pepper from the environment variable {@code PEPPER_SECRET}.
     * Falls back to {@code pepper.properties} on the classpath or filesystem.
     *
     * @throws ExceptionInInitializerError if no pepper can be found
     */
    private static String loadPepper() {
        // 1. Try environment variable first (recommended for production)
        String envPepper = System.getenv("PEPPER_SECRET");
        if (envPepper != null && !envPepper.trim().isEmpty()) {
            return envPepper.trim();
        }

        // 2. Fall back to pepper.properties file
        Properties props = new Properties();

        // 2a. Classpath lookup
        try (InputStream in = PasswordUtils.class.getClassLoader()
                .getResourceAsStream("pepper.properties")) {
            if (in != null) {
                props.load(in);
                String value = props.getProperty("pepper.secret");
                if (value != null && !value.trim().isEmpty()) {
                    return value.trim();
                }
            }
        } catch (IOException ignored) {
            // fall through to filesystem attempt
        }

        // 2b. Filesystem lookup (project root)
        try (InputStream in = new FileInputStream("pepper.properties")) {
            props.load(in);
            String value = props.getProperty("pepper.secret");
            if (value != null && !value.trim().isEmpty()) {
                return value.trim();
            }
        } catch (IOException ignored) {
            // fall through
        }

        throw new ExceptionInInitializerError(
                "No pepper found. Set the PEPPER_SECRET environment variable "
                + "or provide a pepper.properties file with 'pepper.secret' key.");
    }
}
