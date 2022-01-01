<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
<jsp:useBean id="cart" scope="session" class="com.example.bookstore.model.order.Cart"></jsp:useBean>
<h1>Bookstore Checkout/Cart Page</h1>
<%
    if (request.getParameter("quanErr") != null) {
%>
<div class="alert alert-danger" style="max-width: 330px; margin: 0 auto" role="alert">There wasn't enough inventory to
    process your order. Please review your updated cart and proceed.
</div>
<%
    }
%>
<c:if test="${not empty cart.bookItems}">
    <table class="table table-striped">
        <thead>
        <th>STT</th>
        <th>Title</th>
        <th>Price</th>
        <th>Action</th>
        <th></th>
        </thead>
        <tbody>

        <%
            int stt = 1;
            Locale localeVN = new Locale("vi", "VN");
            NumberFormat nf = NumberFormat.getCurrencyInstance(localeVN);
            Double totalPrice = Double.valueOf((String) session.getAttribute("totalPrice"));

            totalPrice = totalPrice + 30000D;
            session.removeAttribute("totalPrice");
            session.setAttribute("totalPrice", String.valueOf(totalPrice));
        %>
        <c:forEach var="item" items="${cart.bookItems}">

            <form action="./cartUpdate" method="post">
                <tr style="text-align: left">
                    <td><% out.println(stt++); %></td>
                    <td>${item.book.title}</td>
                    <td>${item.price}</td>

                    <input type="hidden" name="id" value="${item.id}">
                    <td><input type="submit" name="remove" value="Remove"></td>
                </tr>
            </form>
        </c:forEach>
        </tbody>
    </table>

    <div>
        <label>Tổng tiền: <% out.println(totalPrice - 30000D);%></label>
    </div>


    <form action="./submitOrder" method="post" accept-charset="utf-8">
        <div class="row mt-3">
            <div class="col-md-4">
                <label for=>Phương thức thanh toán: </label>
            </div>
            <div class="col-md-4">
                <select name="payment" style="width:145px; height: 25px;">
                    <option value="0">Cash</option>
                    <option value="1">Check</option>
                    <option value="2">Credit</option>
                </select>
            </div>
        </div>

        <div class="row mt-3">
            <input type="hidden" id="totalPrice" value="<% out.println(totalPrice); %>">
            <div class="col-md-4">
                <label for=>Địa chỉ ship: </label>
            </div>
            <div class="col-md-4">
                <input type="text" name="address" onchange="myFunction()" >
            </div>
            <div>
                <label id="price"></label>
            </div>
        </div>
        <div class="row mt-3" >
            <input type="submit" class="btn btn-default" value="Checkout" style="width: 145px; height: 25px;">
        </div>
    </form>
</c:if>
<c:if test="${empty cart.bookItems}">
    <h2>Cart is empty</h2>
</c:if>

<script>
    function myFunction() {
        var x = document.getElementById("totalPrice").value;
        document.getElementById("price").innerHTML = "Thành tiền: " + x;
    }
</script>
<form action="index.jsp" method="post">
    <input type="submit" class="btn btn-default" value="Continue Shopping">
</form>
<%@ include file="footer.jsp" %>