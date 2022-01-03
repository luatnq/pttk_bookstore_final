<%--<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>JSP - Hello World</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<h1><%= "Hello World!" %>--%>
<%--</h1>--%>
<%--<br/>--%>
<%--<a href="hello-servlet">Hello Servlet</a>--%>
<%--</body>--%>
<%--</html>--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="header.jsp" %>

<%@ page import="com.example.bookstore.dao.book.impl.BookDAOImpl,
                 com.example.bookstore.model.book.Book,
                 java.util.*,
                 java.text.NumberFormat" %>
<%@ page import="com.example.bookstore.dao.book.BookItemDAO" %>
<%@ page import="com.example.bookstore.model.book.BookItem" %>
<head>
    <title>Awesome Book Store!</title>
</head>
<h1>PTTKHT</h1>


<%
    Locale localeVN = new Locale("vi", "VN");
    NumberFormat nf = NumberFormat.getCurrencyInstance(localeVN);
    BookItemDAO bk = new BookItemDAO();
    List<BookItem> books = bk.getBookItems();

%>
<div class="row">

    <%
        for (BookItem book : books) {
    %>
    <div class="col-md-4" style="padding: 5px;">
        <div style="margin:3px; padding:22px; background-color: #eee;">
            <div class="row">
                <div class="col-md-4">
                    <a href="./BookServlet?isbn=<%= book.getBook().getIsbn() %>"
                       style="max-height: 130px; max-width: 110px;">
                        <img src="<%= new ArrayList<>(book.getFileDbs()).get(0).getPath()%>"
                             alt="<%=book.getBook().getTitle()%> cover" style="max-width: inherit; max-height: inherit">
                    </a>
                </div>
                <div class="col-md-8" style="text-align: left; padding-left:10px; height: 160px">
                    <h4><%=book.getBook().getTitle() %>
                    </h4>
                    <h5><%=nf.format(book.getPrice()) %>
                    </h5>
                    <h5><%=book.getBook().getAuthor().getName() %>
                    </h5>
                    <div>
                        <form action="./cartUpdate" method="post">
                            <input type="hidden" name="isbn" value="<%=book.getBook().getIsbn()%>"/>
                            <input type="hidden" name="id" value="<%=book.getId()%>"/>
                            <input type="hidden" name="title" value="<%=book.getBook().getTitle()%>"/>
                            <input type="hidden" name="quantity" value="1"/>
                            <input type="submit" name="add" value="Add to Cart"/>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%
        }
    %>
</div>

<%@ include file="footer.jsp" %>