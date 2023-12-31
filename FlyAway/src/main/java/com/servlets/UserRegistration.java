package com.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Database.Dao;


@WebServlet("/UserRegistration")
public class UserRegistration extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String name=request.getParameter("name");
		String phoneno=request.getParameter("phoneno");
		String aadhaarno=request.getParameter("aadhaarno");

		HashMap<String,String> user=new HashMap<>();
		user.put("email", email);
		user.put("password", password);
		user.put("name", name);
		user.put("phoneno", phoneno);
		user.put("aadhaarno", aadhaarno);

		try 
		{
			Dao dao=new Dao();
			boolean result=dao.insertUser(user);
			HttpSession session=request.getSession();
			
			if(result) 
			{
				session.setAttribute("message", "User Added Successfully");
			}
			
			else 
			{
				session.setAttribute("message","Invalid Details");
			}
			
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			
			e.printStackTrace();
		}
		response.sendRedirect("UserPage.jsp");
	}

}