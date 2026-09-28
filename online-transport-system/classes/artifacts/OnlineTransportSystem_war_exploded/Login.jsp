<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Login</title>
        <style nonce="${cspNonce}">
            /* CSS styles */
            body {
                font-family: 'Poppins', Arial, sans-serif;
                background-image: url('https://cdn.pixabay.com/photo/2017/08/07/05/11/architecture-2600144_1280.jpg');
                background-repeat: no-repeat;
                background-size: cover;
                margin: 0;
                padding: 0;
                height: 100vh;
                display: flex;
                justify-content: center;
                align-items: center;
                color: #333;
            }

            #loginbox {
                width: 400px;
                background-color: rgba(255, 255, 255, 0.9);
                border-radius: 12px;
                padding: 40px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }

            h1 {
                text-align: center;
                font-size: 36px;
                font-weight: 700;
                color: #1426CF;
                background: linear-gradient(to right, #1426CF, #CF1512);
                -webkit-background-clip: text;
                -webkit-text-fill-color: transparent;
                margin-bottom: 30px;
            }

            .input {
                display: flex;
                flex-direction: column;
                margin-bottom: 20px;
                position: relative;
            }

            label {
                margin-bottom: 8px;
                font-weight: bold;
                font-size: 14px;
                color: #555;
            }

            input[type="text"],
            input[type="email"],
            input[type="password"] {
                padding: 10px;
                border-radius: 8px;
                border: 1px solid #ccc;
                font-size: 16px;
                transition: border-color 0.3s;
            }

            input:focus {
                border-color: #48c6a0;
                outline: none;
            }

            .error {
                color: #ff4d4d;
                font-size: 13px;
                margin-top: 5px;
                position: absolute;
                bottom: -20px;
            }

            button {
                background-color: #5adbb5;
                color: #fff;
                padding: 12px;
                font-size: 16px;
                margin-top: 10px;
                border: none;
                border-radius: 25px;
                cursor: pointer;
                transition: background-color 0.3s;
                text-transform: uppercase;
                letter-spacing: 1px;
                box-shadow: 0 3px 6px rgba(0, 0, 0, 0.1);
            }

            button:hover {
                background-color: #48c6a0;
            }

            .google-login {
                display: flex;
                align-items: center;
                justify-content: center;
                gap: 10px;
                width: 100%;
                box-sizing: border-box;
                min-height: 44px;
                margin-top: 14px;
                padding: 10px 16px;
                text-align: center;
                background-color: #fff;
                color: #333;
                border: 1px solid #b9b9b9;
                border-radius: 6px;
                font-size: 15px;
                font-weight: 600;
                text-transform: none;
                letter-spacing: 0;
                text-decoration: none;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.08);
                transition: background-color 0.2s, border-color 0.2s, box-shadow 0.2s;
            }

            .google-login::before {
                content: "G";
                color: #4285f4;
                font-size: 20px;
                font-weight: 700;
                line-height: 1;
            }

            .google-login:hover {
                color: #222;
                background-color: #f8f9fa;
                border-color: #8a8a8a;
                box-shadow: 0 3px 7px rgba(0, 0, 0, 0.12);
            }

            .login-divider {
                display: flex;
                align-items: center;
                gap: 12px;
                margin-top: 18px;
                color: #777;
                font-size: 12px;
                text-transform: uppercase;
                letter-spacing: 1px;
            }

            .login-divider::before,
            .login-divider::after {
                content: "";
                flex: 1;
                height: 1px;
                background-color: #d1d1d1;
            }

            a {
                color: #0066cc;
                text-decoration: none;
                font-weight: 500;
            }

            a:hover {
                color: #8a2be2;
            }

            #Forgot {
                text-align: right;
                font-size: 14px;
                margin-top: 10px;
            }

            #Forgot:hover {
                color: #8a2be2;
            }

            /* Responsive design for smaller screens */
            @media (max-width: 600px) {
                #loginbox {
                    width: 90%;
                }

                #Forgot {
                    margin-left: 0;
                    text-align: center;
                }
            }
            
            .error-message
            { 
               color: red;
            }
        </style>
    </head>

    <body>
        <div id="loginbox">
            <form action="Login" method="Post" id="loginform">
                <input type="hidden" name="csrfToken" value="${sessionScope.csrfToken}" />
                <h1>Login</h1>
                <div class="input">
                    <label for="Username">User name:</label>
                    <input type="text" id="Username" name="Username" required>
                    <div class="error"></div>
                </div>

                

                <div class="input">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                    <div class="error"></div>
                    <a id="Forgot" href="ForgotLogin.jsp">Forgot password?</a>
                </div>

                <div>
                    New member? <a href="signupOption.jsp">Sign up here</a>
                </div>
                <div>
                    <button type="submit"><b>Login</b></button>
                </div>
                <div class="login-divider">or</div>
                <div>
                    <a class="google-login" href="google-login">Continue with Google</a>
                </div>
                
                 <% 
                     String errorMessage = (String) request.getAttribute("errorMessage"); 
                     if (errorMessage != null) { 
                 %>
                 
                    <div class="error-message">
                        <h2><%= errorMessage %></h2>
                   </div>
                <% 
                   } 
                 %>

            </form>
        </div>

       
    </body>
</html>