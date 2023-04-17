package com.example.devops.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@WebServlet("/bookAppointment")
public class AppointmentServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/devops";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "123456";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String checkin = request.getParameter("checkin");
        String checkout = request.getParameter("checkout");
        int adults = Integer.parseInt(request.getParameter("adults"));
        int children = Integer.parseInt(request.getParameter("children"));
        String roomType = request.getParameter("roomType");

        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String sql = "INSERT INTO appointments (checkin, checkout, adults, children, roomType) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, checkin);
            statement.setString(2, checkout);
            statement.setInt(3, adults);
            statement.setInt(4, children);
            statement.setString(5, roomType);
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Database access error", e);
        }

        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver");

        // Create a new instance of the Chrome driver
        WebDriver driver = new ChromeDriver();

        // Navigate to a web page
        driver.get("https://www.google.com");

        // Find an element and perform an action
        driver.findElement(By.linkText("About")).click();

        // Close the browser window
        driver.quit();

        response.sendRedirect("confirmation.jsp");
    }
}
