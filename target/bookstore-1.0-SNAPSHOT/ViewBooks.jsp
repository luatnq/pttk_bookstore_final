<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page import="com.example.bookstore.dao.book.BookDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.bookstore.model.book.Book" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Objects" %>
<%@ page import="com.example.bookstore.model.FileDb" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>
<jsp:useBean id="cart" scope="session" class="com.example.bookstore.model.order.Cart"></jsp:useBean>
<h1>List book</h1>
<%
    if (request.getParameter("quanErr") != null) {
%>
<%
    }
%>
<div>
    <table class="table table-striped">
        <thead>
        <th>STT</th>
        <th>Title</th>
        <th>Cover</th>
        <th>Action</th>
        <th></th>
        </thead>
        <tbody>

        <%
            int stt = 1;
            BookDAO bookDAO = new BookDAO();
            List<Book> books = bookDAO.getBooks();
            for (Book book : books) {
                List<FileDb> fileDbs = new ArrayList<>(book.getFileDbs());
        %>

        <form action="./BookServlet" method="post">
            <tr style="text-align: left">
                <td><% out.println(stt++); %></td>
                <td><% out.println(book.getTitle()); %></td>
                <td><img src="<%= fileDbs.size() > 0 ?
                    new ArrayList<>(book.getFileDbs()).get(0).getPath() : null %>"
                         alt="<%=book.getTitle()%> cover" style="width: 75px; height: 50px"></td>
                <td>${item.price}</td>

                <input type="hidden" name="isbn" value="<%out.println(book.getIsbn()); %>">
                <td><input type="submit" name="remove" value="Remove"></td>
                <% if (!book.isStatus()) {%>
                    <td><input type="submit" name="push" value="Push to shelf"></td>
                <% } %>
            </tr>
        </form>
        <%}%>
        </tbody>
    </table>

</div>


<%@ include file="footer.jsp" %>