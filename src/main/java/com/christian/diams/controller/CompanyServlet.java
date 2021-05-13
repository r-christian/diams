package com.christian.diams.controller;

import com.christian.diams.controller.interfaces.IAuthorize;
import com.christian.diams.dao.CompanyDAO;
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

@WebServlet(name = "CompanyServlet", urlPatterns = {"/company/", "/company/edit", "/company/update", "/company/delete", "/company/save"})
public class CompanyServlet extends HttpServlet implements IAuthorize {
    IGenericDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new CompanyDAO();
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
                case "/company/":
                    showMainForm(request, response);
                    break;
                case "/company/edit":
                    showEditForm(request, response);
                    break;
                case "/company/update":
                    updateCompany(request, response);
                    break;
                case "/company/delete":
                    deleteCompany(request, response);
                    break;
                case "/company/save":
                    saveCompany(request, response);
                    break;
                default:
                    showMainForm(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteCompany(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Company selectedCompany = (Company) dao.get((long) id);
        dao.delete(selectedCompany);
        response.sendRedirect("/company/");
    }

    private void saveCompany(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        Company company = new Company(name);
        dao.insert(company);
        response.sendRedirect("/company/");
    }

    protected void showMainForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Company> companyList = ((CompanyDAO)dao).findAll();
        request.setAttribute("companyList", companyList == null ? new ArrayList<Company>() : companyList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/company.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Company selectedCompany = (Company) dao.get((long) id);
        List<Company> companyList = ((CompanyDAO)dao).findAll();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/company.jsp");
        request.setAttribute("selectedCompany", selectedCompany);
        request.setAttribute("companyList", companyList == null ? new ArrayList<Company>() : companyList);
        dispatcher.forward(request, response);
    }


    private void updateCompany(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Company selectedCompany = (Company) dao.get((long) id);
        selectedCompany.setName(request.getParameter("name"));
        dao.update(selectedCompany);
        response.sendRedirect("/company/");
    }

    @Override
    public boolean isAuthorized(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        return user != null;
    }
}
