<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@ page import=" com.example.bookstore.util.HashHelper" %>
<%@ page import="com.example.bookstore.model.customer.Account" %>
<%@ page import="com.example.bookstore.model.customer.FullName" %>
<%@ page import="com.example.bookstore.model.customer.Customer" %>
<%@ page import="com.example.bookstore.dao.customer.CustomerDAO" %>
<%@ page import="com.example.bookstore.dao.staff.StaffDAO" %>
<%@ page import="com.example.bookstore.dao.staff.StaffDAOImpl" %>
<jsp:useBean id="user" scope="request" class="com.example.bookstore.model.dto.RegisterDTO"></jsp:useBean>
<jsp:setProperty name="user" property="*"></jsp:setProperty>


<h1>Sign Up results: ${user.username}</h1>
<%

	String hashed = HashHelper.getHash(user.getPassword());
	user.setPassword(hashed);

	Account account = new Account(user.getUsername(), user.getPassword());
	FullName fullName = new FullName(user.getFirstName(), user.getMiddleName(), user.getLastName());
	StaffDAO staffDAO = new StaffDAOImpl();
	staffDAO.saveAccount(account);
	staffDAO.saveFullName(fullName);

	Customer customer = new Customer(user, account, fullName);

	CustomerDAO customerDAO = new CustomerDAO();
	customerDAO.register(customer);
//	int result = new UserDB().registerUser(user);
//	if (result == 1) {
//		out.println("Sign up successful!");
//	} else {
//		out.println("Sign up failure");
//	}
%>

<%@ include file="footer.jsp" %>