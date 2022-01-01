package com.example.bookstore.controller;

import com.example.bookstore.dao.order.CartDAO;
import com.example.bookstore.dao.order.OrderDAO;
import com.example.bookstore.dao.order.PaymentDAO;
import com.example.bookstore.dao.order.ShipmentDAO;
import com.example.bookstore.dao.order.impl.PaymentDAOImpl;

import com.example.bookstore.dao.order.impl.ShipmentDAOImpl;
import com.example.bookstore.model.book.BookItem;
import com.example.bookstore.model.order.Cart;
import com.example.bookstore.model.order.Order;
import com.example.bookstore.model.order.Shipment;
import com.example.bookstore.model.order.payment.Cash;
import com.example.bookstore.model.order.payment.Check;
import com.example.bookstore.model.order.payment.Credit;
import com.example.bookstore.model.order.payment.Payment;
import org.apache.commons.lang3.RandomStringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderServlet extends HttpServlet{

    private static final long serialVersionUID = 1L;

    public OrderServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        String usernameLogin = (String) session.getAttribute("user");

        String paymentType = request.getParameter("payment");
        String address = request.getParameter("address");
        float totalPrice = Float.valueOf((String) session.getAttribute("totalPrice"));


        // Check if logged in
        if (Objects.isNull(usernameLogin)) {
            response.sendRedirect("./SignUp.jsp?checkErr");
            return;
        }

        if (Objects.isNull(cart)) {
            response.sendError(404);
            return;
        }
        Order order = new Order(cart);
        CartDAO cartDAO = new CartDAO();
        OrderDAO orderDAO = new OrderDAO();
        ShipmentDAO shipmentDAO = new ShipmentDAOImpl();

        cartDAO.saveCart(cart);
        orderDAO.saveOrder(order);

        String shipCode = RandomStringUtils.randomAlphabetic(10);
        Shipment shipment = new Shipment(order, address, shipCode);
        shipmentDAO.saveShipment(shipment);
        this.paymentHandle(paymentType, totalPrice, shipment, order);




        request.setAttribute("orderId", order.getId());
        request.removeAttribute("cart");

        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/OrderConfirmation.jsp");
        dispatcher.forward(request, response);
    }

    public Payment paymentHandle(String paymentHandle, float totalPrice, Shipment shipment, Order order){

        PaymentDAO paymentDAO = new PaymentDAOImpl();
        if (paymentHandle.equals("0")){
            Cash cash = new Cash();
            cash.setShipment(shipment);
            cash.setAmount(totalPrice);
            cash.setOrder(order);

            paymentDAO.savePayment(cash);

        }else if (paymentHandle.equals("1")){
            Check check = new Check();
            check.setShipment(shipment);
            check.setAmount(totalPrice);
            check.setOrder(order);

            paymentDAO.savePayment(check);

        }else if (paymentHandle.equals("2")){
            Credit credit = new Credit();
            credit.setShipment(shipment);
            credit.setAmount(totalPrice);
            credit.setOrder(order);

            paymentDAO.savePayment(credit);
        }
        return null;
    }

}
