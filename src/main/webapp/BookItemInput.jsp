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
<form class="form-signin" action="./BookServlet" method="post">
    <div>
        <h2 class="form-signin-heading">Enter book item info</h2>
    </div>
    <label for="price" class="sr-only">Price</label>
    <input type="text" class="form-control" name="price" id="price" placeholder="Price"/>

    <label for="discount" class="sr-only">Discount</label>
    <input type="text" name="discount" id="discount" class="form-control" placeholder="Discount">

    <label for="barCode" class="sr-only">Bar code</label>
    <input type="text" name="barCode" id="barCode" class="form-control" placeholder="Bar code">

    <input type="hidden" name="pushConfirm" value="pushConfirm"></td>

    <button type="submit" class="btn btn-lg btn-primary btn-block">Push</button>
</form>

<%@ include file="footer.jsp" %>