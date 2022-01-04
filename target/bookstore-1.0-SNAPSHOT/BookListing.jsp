<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
<%@ page import="com.example.bookstore.dao.book.impl.BookItemDAOImpl, java.text.*" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ArrayList" %>
<jsp:useBean id="bookItem" scope="request" class="com.example.bookstore.model.book.BookItem"></jsp:useBean>
<jsp:setProperty name="bookItem" property="*"></jsp:setProperty>
<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">${bookItem.book.title}</h3>
    </div>
    <div class="panel-body">
        <img src="<%= new ArrayList<>(bookItem.getFileDbs()).get(0).getPath()%>" alt="${bookItem.book.title} cover"
             width="200">
        <form action="./cartUpdate" method="post">
            <h4>Price: <%=NumberFormat.getCurrencyInstance(new Locale("vi", "VN")).format(bookItem.getPrice()) %>
            </h4>
            <h4>ISBN: ${bookItem.book.isbn}</h4>
            <h4>Author(s): ${bookItem.book.author.name}
                <h4>
                    <h4>Publisher: ${bookItem.book.publisher.name}</h4>
                    <%--		<h4>Genre(s): ${book.genres}</h4>--%>
                    <h4>Summary:</h4>
                    <p>${bookItem.book.summary}</p>
                    <input type="hidden" name="isbn" value="${bookItem.book.isbn}"/>
                    <input type="hidden" name="title" value="${bookItem.book.title}"/>
                    <input type="hidden" name="quantity" value="1"/>
                    <input type="submit" name="add" value="Add to Cart"/>
        </form>

    </div>
</div>

<div class="content">

</div>

<%@ include file="footer.jsp" %>