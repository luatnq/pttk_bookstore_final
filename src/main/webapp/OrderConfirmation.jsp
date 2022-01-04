<%@ include file="header.jsp" %>
<div class="jumbotron">
	<h1>Order Success</h1>
	<p>Your order created successful. Your order ship code is:</p>

	<p><strong><%= request.getAttribute("orderId") %></strong>
</div>

<%@ include file="footer.jsp" %>