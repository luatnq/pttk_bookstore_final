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
<form class="form-signin" action="./submitOrder" method="post">
    <h2 class="form-signin-heading">Please enter information transaction</h2>
    <label for="fullName" class="sr-only">Name</label>
    <input type="text" class="form-control" name="fullName" id="fullName" placeholder="Full name"/>

    <label for="bankId" class="sr-only">Bank id</label>
    <input type="text" name="bankId" id="bankId" class="form-control" placeholder="Bank Id">

    <button type="submit" class="btn btn-lg btn-primary btn-block">Sign in</button>
</form>

<%@ include file="footer.jsp" %>