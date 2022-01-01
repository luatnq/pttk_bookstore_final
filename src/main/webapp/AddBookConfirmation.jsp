<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.io.*,java.util.*, javax.servlet.*, java.text.SimpleDateFormat, java.util.Date"%>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>


<%@ include file="header.jsp" %>
<jsp:useBean id="book" scope="request" class="com.example.bookstore.model.book.Book"></jsp:useBean>

<!-- Have to set each property manually since using multipart form submission -->

<h1>Book confirmation. Success: <%=request.getParameter("success") %></h1>


<%@ include file="footer.jsp" %>