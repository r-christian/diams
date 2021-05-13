package com.christian.diams.controller;

import com.christian.diams.controller.interfaces.IAuthorize;
import com.christian.diams.dao.UserDAO;
import com.christian.diams.dao.base.IGenericDAO;
import com.christian.diams.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login/","/"})
public class LoginServlet extends HttpServlet implements IAuthorize {
    private IGenericDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(isAuthorized(request, response)){
            response.sendRedirect("http://localhost:8080/company/");
            return;
        }
        String action = request.getServletPath();
        if(action.equalsIgnoreCase("/login/validate")){
            validateUser(request, response);
        }else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void validateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if(email.length() != 0 && password.length() != 0){
            User user = ((UserDAO)dao).getAuthenticateUser(email, password);
            if(user != null){
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect("http://localhost:8080/company/");
            }else{
                response.sendRedirect("http://localhost:8080/login/");
            }
        }
    }

    @Override
    public boolean isAuthorized(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        return user != null;
    }
}
