package com.udemy.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class CreateUserServlet
 */
//@WebServlet("/addServlet")    //name is given in adduser.html
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private Connection conn;
    //there are various in built method. I have erased them and created init and destroy method
	
	public void init()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");  //need to add this to use tomcat, add class not found exception
			 conn=DriverManager.getConnection("jdbc:mysql://localhost/mydb1", "root", "root");
		}
		catch (SQLException e)
		{
			
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
		System.out.println("doPost()");
			
		try
		{
			Statement stmt= conn.createStatement();
			ResultSet resultset = stmt.executeQuery("select * from user");
			PrintWriter out=response.getWriter();
			out.print("<table>");
			out.print("<tr>");
			out.print("<th>");
			out.print("First Name");
			out.print("</th>");
			out.print("<th>");
			out.print("Last Name");
			out.print("</th>");
			out.print("<th>");
			out.print("Email");
			out.print("</th>");
			out.print("</tr>");
			out.print("</table>");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void destroy()
	{
		try {
			conn.close();
		}
		catch(SQLException e)
		{
			
		}
	}

}
