package com.christian.diams.controller;

import com.christian.diams.dao.CompanyDAO;
import com.christian.diams.dao.base.IGenericDAO;
import com.christian.diams.model.Company;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@WebServlet(name = "GetCompaniesController", urlPatterns = {"/GetCompanies/"})
public class GetCompaniesController extends HttpServlet {
    CompanyDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new CompanyDAO();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String json = getCountries();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private String getCountries() throws IOException {
        String jsonResult = "";
        List<Company> companyList = dao.getCompanyNames();
        JSONArray JSONArray = new JSONArray(companyList);
        jsonResult = JSONArray.toString();
        return jsonResult;
    }
}
