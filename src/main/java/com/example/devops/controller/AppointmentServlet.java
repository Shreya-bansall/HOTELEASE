package com.example.devops.controller;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

public class AppointmentServlet extends HttpServlet {

    private Connection conn;

    // Initialize the database connection
    public void init() throws ServletException {
        String url = "jdbc:mysql://localhost:3306/devops"; // Replace with your database URL
        String user = "root"; // Replace with your database user
        String password = "123456"; // Replace with your database password

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            throw new ServletException("JDBC driver not found.", e);
        } catch (SQLException e) {
            throw new ServletException("Unable to connect to the database.", e);
        }
    }

    // Process the form submission
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve the form data
            String checkin = request.getParameter("checkin");
            String checkout = request.getParameter("checkout");
            int adults = Integer.parseInt(request.getParameter("adults"));
            int children = Integer.parseInt(request.getParameter("children"));
            String roomType = request.getParameter("roomType");

            // Create the SQL INSERT statement
            String sql = "INSERT INTO bookings (checkin, checkout, adults, children, room_type) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, checkin);
            statement.setString(2, checkout);
            statement.setInt(3, adults);
            statement.setInt(4, children);
            statement.setString(5, roomType);

            // Execute the SQL INSERT statement
            statement.executeUpdate();

            // Redirect the user to a confirmation page
            response.sendRedirect("confirmation.jsp");
        } catch (SQLException e) {
            throw new ServletException("Unable to store the form data in the database.", e);
        }
    }

    // Close the database connection
    public void destroy() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}