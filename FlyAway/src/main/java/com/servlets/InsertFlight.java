package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Database.Dao;


     @WebServlet("/InsertFlight")
     public class InsertFlight extends HttpServlet 
     {
	   private static final long serialVersionUID = 1L;
       protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
       {
    	   response.setContentType("Text/Html");
    	   PrintWriter out = response.getWriter();
    	   String name=request.getParameter("name");
		   String from=request.getParameter("from");
		   String to=request.getParameter("to");
		   String departure=request.getParameter("departure");
		   String time=request.getParameter("time");
		   String price=request.getParameter("price");

		   HashMap<String,String> flight=new HashMap<>();
		   flight.put("name", name);
		   flight.put("from", from);
		   flight.put("to", to);
		   flight.put("date", departure);
		   flight.put("time", time);
		   flight.put("price", price);

		   try 
		   {
			Dao dao=new Dao();
			HttpSession session=request.getSession();
			if(dao.insertFlight(flight)) 
			{

				session.setAttribute("message", "Flight Added Successfully");
			}
			
			else 
			{
				session.setAttribute("message", "Invalid Details");
			}
		} 
		   catch (ClassNotFoundException | SQLException e) 
		   {
			
			System.out.print("error");
			e.printStackTrace();
		   }
		response.sendRedirect("AdminHome.jsp");

	}

}

