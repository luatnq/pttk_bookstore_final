<%@ include file="header.jsp" %>
<%@ page import="com.example.bookstore.controller.UserServlet" %>
<style>
    .form-signin {
        max-width: 330px;
        padding: 15px;
        margin: 0 auto;
    }
    .form-signin .form-signin-heading,
    .form-signin .checkbox {
        margin-bottom: 10px;
    }
    .form-signin .checkbox {
        font-weight: normal;
    }
    .form-signin .form-control {
        position: relative;
        height: auto;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
        padding: 10px;
        font-size: 16px;
    }
    .form-signin .form-control:focus {
        z-index: 2;
    }
    .form-signin input[type="email"] {
        margin-bottom: -1px;
        border-bottom-right-radius: 0;
        border-bottom-left-radius: 0;
    }
    .form-signin input[type="password"] {
        margin-bottom: 10px;
        border-top-left-radius: 0;
        border-top-right-radius: 0;
    }
</style>
<%
    if (request.getParameter("checkErr") != null) {
%>
<%
    }
%>
<form action="SignUpConfirmation.jsp" method="post" class="form-signin">
    <h2 class="form-signin-heading">Not registered yet?</h2>
    <label for="username" class="sr-only">Username:</label>
    <input type="text" class="form-control" name="username" id="username" required placeholder="Username" />

    <label for="firstName" class="sr-only">First Name:</label>
    <input type="text" class="form-control" name="firstName" id="firstName" placeholder="First Name" required />

    <label for="middleName" class="sr-only">Middle Name:</label>
    <input type="text" class="form-control" name="middleName" id="middleName" required placeholder="Middle Name" />

    <label for="lastName" class="sr-only">Last Name:</label>
    <input type="text" class="form-control" name="lastName" id="lastName" required placeholder="Last Name" />

    <label for="password" class="sr-only">Password:</label>
    <input type="password" class="form-control" name="password" id="password" required placeholder="Password" />

    <label for="email" class="sr-only">Email:</label>
    <input type="email" class="form-control" name="email" id="email" required placeholder="Email" />

    <label for="phone" class="sr-only">Phone Number:</label>
    <input type="tel" class="form-control" name="phone" id="phone" placeholder="Phone Number" /></br>

    <label for="dob" class="sr-only">Date of birth:</label>
    <input type="date" class="form-control" name="dob" id="dob" placeholder="Date of birth" /></br>

    <input type="submit" class="btn btn-lg btn-primary btn-block" value="Sign Up" />
</form>
<%@ include file="footer.jsp" %>