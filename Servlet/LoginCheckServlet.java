package com.example.bo;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginCheckServlet
 */
@WebServlet("/LoginCheckServlet")
public class LoginCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		String uName=request.getParameter("uName");
		String uPassword = request.getParameter("uPassword");
		PrintWriter writer=response.getWriter();
		System.out.println("等待反馈...");
		System.out.println(uName+" "+uPassword);
		if("mike".equals(uName) && "123456".equals(uPassword))
		{
	//将用户名保存到会话中，后面页面通过检查此对象的存在与否判断用户是否登录，并将用户名显示到页面 
			session.setAttribute("uName",uName);
//			Cookie ck = new Cookie("uName",uName);
			
			writer.print("OK");
			System.out.println("已回复正确");
	  }
		else{
			writer.print("Error");}
			System.out.println("已回复2");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
