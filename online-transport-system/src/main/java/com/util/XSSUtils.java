package com.util;

/**
 * XSS (Cross-Site Scripting) Sanitization Utility.
 *
 * <p>Use {@link #sanitize(String)} to escape user-supplied input <strong>before</strong>
 * storing it in the database or setting it as a request attribute that will be
 * rendered in HTML.
 *
 * <p>Use {@link #sanitizeForHtmlAttribute(String)} when the value is placed
 * inside an HTML attribute (e.g., {@code value="..."}).
 *
 * <p><strong>Rule of thumb:</strong>
 * <ul>
 *   <li>Sanitize at the servlet layer (on the way <em>in</em>).</li>
 *   <li>Re-sanitize at the JSP layer (on the way <em>out</em>) for any data
 *       read from the database that may already contain stored XSS.</li>
 * </ul>
 */
public class XSSUtils {

    private XSSUtils() {
        // Utility class – no instantiation
    }

    /**
     * Escapes HTML special characters in the given string so that it is safe to
     * render inside an HTML element (e.g., {@code <td>}, {@code <p>}).
     *
     * <p>Replaces:
     * <ul>
     *   <li>{@code &}  → {@code &amp;}</li>
     *   <li>{@code <}  → {@code &lt;}</li>
     *   <li>{@code >}  → {@code &gt;}</li>
     *   <li>{@code "}  → {@code &quot;}</li>
     *   <li>{@code '}  → {@code &#x27;}</li>
     *   <li>{@code /}  → {@code &#x2F;}</li>
     * </ul>
     *
     * @param input the raw (potentially malicious) string
     * @return the HTML-escaped string, or an empty string if {@code input} is {@code null}
     */
    public static String sanitize(String input) {
        if (input == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder(input.length());
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            switch (c) {
                case '&':  sb.append("&amp;");   break;
                case '<':  sb.append("&lt;");    break;
                case '>':  sb.append("&gt;");    break;
                case '"':  sb.append("&quot;");  break;
                case '\'': sb.append("&#x27;");  break;
                case '/':  sb.append("&#x2F;");  break;
                default:   sb.append(c);         break;
            }
        }
        return sb.toString();
    }

    /**
     * Alias for {@link #sanitize(String)}.
     * Use this when embedding a value inside an HTML attribute string.
     *
     * @param input the raw string
     * @return the HTML-escaped string
     */
    public static String sanitizeForHtmlAttribute(String input) {
        return sanitize(input);
    }
}
