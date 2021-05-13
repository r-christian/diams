package com.christian.diams.controller;

import com.christian.diams.controller.interfaces.IAuthorize;
import com.christian.diams.dao.AssetTypeDAO;
import com.christian.diams.dao.base.IGenericDAO;
import com.christian.diams.model.AssetType;
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

@WebServlet(name = "AssetTypeServlet", urlPatterns = {"/assetType/", "/assetType/edit", "/assetType/update", "/assetType/delete", "/assetType/save"})
public class AssetTypeServlet extends HttpServlet implements IAuthorize {
    IGenericDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new AssetTypeDAO();
    }

    @Override
    public boolean isAuthorized(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        return user != null;
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
                case "/assetType/":
                    showMainForm(request, response);
                    break;
                case "/assetType/edit":
                    showEditForm(request, response);
                    break;
                case "/assetType/update":
                    updateAssetType(request, response);
                    break;
                case "/assetType/delete":
                    deleteAssetType(request, response);
                    break;
                case "/assetType/save":
                    saveAssetType(request, response);
                    break;
                default:
                    showMainForm(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteAssetType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        AssetType selectedAssetType = (AssetType) dao.get((long) id);
        dao.delete(selectedAssetType);
        response.sendRedirect("/assetType/");
    }

    private void saveAssetType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        AssetType assetType = new AssetType(name);
        dao.insert(assetType);
        response.sendRedirect("/assetType/");
    }

    protected void showMainForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<AssetType> assetTypeList = ((AssetTypeDAO)dao).findAll();
        request.setAttribute("assetTypeList", assetTypeList == null ? new ArrayList<AssetType>() : assetTypeList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/assetType.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        AssetType selectedAssetType = (AssetType) dao.get((long) id);
        List<AssetType> assetTypeList = ((AssetTypeDAO)dao).findAll();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/assetType.jsp");
        request.setAttribute("selectedAssetType", selectedAssetType);
        request.setAttribute("assetTypeList", assetTypeList == null ? new ArrayList<AssetType>() : assetTypeList);
        dispatcher.forward(request, response);
    }


    private void updateAssetType(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        AssetType selectedAssetType = (AssetType) dao.get((long) id);
        selectedAssetType.setName(request.getParameter("name"));
        dao.update(selectedAssetType);
        response.sendRedirect("/assetType/");
    }
}