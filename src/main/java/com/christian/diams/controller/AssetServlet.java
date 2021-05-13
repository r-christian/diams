package com.christian.diams.controller;

import com.christian.diams.builder.AssetBuilder;
import com.christian.diams.controller.interfaces.IAuthorize;
import com.christian.diams.dao.AssetDAO;
import com.christian.diams.dao.AssetTypeDAO;
import com.christian.diams.dao.CompanyDAO;
import com.christian.diams.dao.base.IGenericDAO;
import com.christian.diams.model.Asset;
import com.christian.diams.model.AssetType;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AssetServlet", urlPatterns = {"/asset/", "/asset/edit", "/asset/update", "/asset/delete", "/asset/save"})
public class AssetServlet extends HttpServlet implements IAuthorize {
    private IGenericDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new AssetDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!isAuthorized(request, response)){
            response.sendRedirect("http://localhost:8080/login/");
            return;
        }
        String action = request.getServletPath();
        try{
            switch (action){
                case "/asset/":
                    showMainForm(request, response);
                    break;
                case "/asset/edit":
                    showEditForm(request, response);
                    break;
                case "/asset/update":
                    updateAsset(request, response);
                    break;
                case "/asset/delete":
                    deleteAsset(request, response);
                    break;
                case "/asset/save":
                    saveAsset(request, response);
                    break;
                default:
                    showMainForm(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveAsset(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String companyName = request.getParameter("companies");
        String assetTypeName = request.getParameter("assetTypes");
        CompanyDAO companyDAO = new CompanyDAO();
        Company selectedCompany = companyDAO.getByName(companyName);
        AssetTypeDAO assetTypeDAO = new AssetTypeDAO();
        AssetType selectedAssetType = assetTypeDAO.getByName(assetTypeName);
        //create object
        Asset asset = new Asset();
        AssetBuilder builder = new AssetBuilder(selectedCompany, selectedAssetType,
                request.getParameter("name"), Integer.parseInt(request.getParameter("quantity")),
                request.getParameter("manufacturer"));
        dao.insert(builder.build());
        response.sendRedirect("/asset/");
    }

    private void updateAsset(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String companyName = request.getParameter("companies");
        String assetTypeName = request.getParameter("assetTypes");
        CompanyDAO companyDAO = new CompanyDAO();
        Company selectedCompany = companyDAO.getByName(companyName);
        AssetTypeDAO assetTypeDAO = new AssetTypeDAO();
        AssetType selectedAssetType = assetTypeDAO.getByName(assetTypeName);
        //create object
        Asset selectedAsset = (Asset) dao.get((long)id);
        selectedAsset.setName(request.getParameter("name"));
        selectedAsset.setCompany(selectedCompany);
        selectedAsset.setAssetType(selectedAssetType);
        selectedAsset.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        selectedAsset.setManufacturer(request.getParameter("manufacturer"));
        dao.update(selectedAsset);
        response.sendRedirect("/asset/");
    }

    private void deleteAsset(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Asset selectedAsset = (Asset) dao.get((long) id);
        dao.delete(selectedAsset);
        response.sendRedirect("/asset/");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Asset selectedAsset = (Asset) dao.get((long)id);
        List<Asset> assetList = dao.findAll();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/asset.jsp");
        request.setAttribute("selectedAsset", selectedAsset);
        request.setAttribute("assetList", assetList == null ? new ArrayList<Asset>() : assetList);
        dispatcher.forward(request, response);
    }

    private void showMainForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Asset> assetList = ((AssetDAO)dao).findAll();
        request.setAttribute("assetList", assetList == null ? new ArrayList<Asset>() : assetList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/asset.jsp");
        dispatcher.forward(request, response);
    }



    @Override
    public boolean isAuthorized(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        return user != null;
    }
}