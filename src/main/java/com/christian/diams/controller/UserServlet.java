package com.christian.diams.controller;

import com.christian.diams.controller.interfaces.IAuthorize;
import com.christian.diams.dao.UserDAO;
import com.christian.diams.dao.base.IGenericDAO;
import com.christian.diams.model.Company;
import com.christian.diams.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserServlet", urlPatterns = {"/user/", "/user/edit", "/user/update", "/user/delete", "/user/save"})
public class UserServlet extends HttpServlet implements IAuthorize {
    IGenericDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!isAuthorized(request, response)){
            response.sendRedirect("http://localhost:8080/login/");
            return;
        }
        String action = request.getServletPath();
        try{
            switch (action){
                case "/user/":
                    showMainForm(request, response);
                    break;
                case "/user/edit":
                    showEditForm(request, response);
                    break;
                case "/user/update":
                    updateUser(request, response);
                    break;
                case "/user/delete":
                    deleteUser(request, response);
                    break;
                case "/user/save":
                    saveUser(request, response);
                    break;
                default:
                    showMainForm(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User selectedUser = (User) dao.get((long) id);
        dao.delete(selectedUser);
        response.sendRedirect("/user/");
    }

    private void saveUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = new User(firstName, lastName, email, password);
        dao.insert(user);
        response.sendRedirect("/user/");
    }

    protected void showMainForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> userList = ((UserDAO)dao).findAll();
        request.setAttribute("userList", userList == null ? new ArrayList<User>() : userList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User selectedUser = (User) dao.get((long) id);
        List<User> userList = ((UserDAO)dao).findAll();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user.jsp");
        request.setAttribute("selectedUser", selectedUser);
        request.setAttribute("userList", userList == null ? new ArrayList<Company>() : userList);
        dispatcher.forward(request, response);
    }


    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User selectedUser = (User) dao.get((long) id);
        selectedUser.setFirstName(request.getParameter("firstName"));
        selectedUser.setLastName(request.getParameter("lastName"));
        selectedUser.setEmail(request.getParameter("email"));
        selectedUser.setPassword(request.getParameter("password"));
        dao.update(selectedUser);
        response.sendRedirect("/user/");
    }

    @Override
    public boolean isAuthorized(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        return user != null;
    }
}

