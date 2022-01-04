<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@ page import=" com.example.bookstore.util.HashHelper" %>
<%@ page import="com.example.bookstore.model.customer.Account" %>
<%@ page import="com.example.bookstore.model.customer.FullName" %>
<%@ page import="com.example.bookstore.model.customer.Customer" %>
<%@ page import="com.example.bookstore.dao.customer.impl.CustomerDAOImpl" %>
<%@ page import="com.example.bookstore.dao.customer.AccountDAO" %>
<%@ page import="com.example.bookstore.dao.customer.impl.AccountDAOImpl" %>
<%@ page import="com.example.bookstore.dao.customer.FullNameDAO" %>
<%@ page import="com.example.bookstore.dao.customer.impl.FullNamDAOImpl" %>
<jsp:useBean id="user" scope="request" class="com.example.bookstore.model.dto.RegisterDTO"></jsp:useBean>
<jsp:setProperty name="user" property="*"></jsp:setProperty>


<h1>Sign Up results: ${user.username}</h1>
<%

	String hashed = HashHelper.getHash(user.getPassword());
	user.setPassword(hashed);

	Account account = new Account(user.getUsername(), user.getPassword());
	FullName fullName = new FullName(user.getFirstName(), user.getMiddleName(), user.getLastName());
	AccountDAO accountDAO = new AccountDAOImpl();
	FullNameDAO fullNameDAO = new FullNamDAOImpl();

	accountDAO.saveAccount(account);
	fullNameDAO.saveFullName(fullName);

	Customer customer = new Customer(user, account, fullName);

	CustomerDAOImpl customerDAOImpl = new CustomerDAOImpl();
	customerDAOImpl.register(customer);
%>

<%@ include file="footer.jsp" %>