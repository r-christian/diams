package com.christian.diams.controller;

import com.christian.diams.dao.AssetTypeDAO;
import com.christian.diams.dao.base.IGenericDAO;
import com.christian.diams.model.AssetType;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetAssetTypesController", urlPatterns = {"/GetAssetTypes/"})
public class GetAssetTypesController extends HttpServlet {
    AssetTypeDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new AssetTypeDAO();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String json = getAssetTypes();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    private String getAssetTypes() throws IOException {
        String jsonResult = "";
        List<AssetType> assetTypeList = dao.getTypeNames();
        JSONArray JSONArray = new JSONArray(assetTypeList);
        jsonResult = JSONArray.toString();
        return jsonResult;
    }
}