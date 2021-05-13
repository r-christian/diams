package com.christian.diams.dao;

import com.christian.diams.dao.base.AbstractGenericDAO;
import com.christian.diams.model.Asset;
import com.christian.diams.model.AssetType;
import com.christian.diams.model.Company;

import javax.persistence.TypedQuery;
import java.util.List;

public class AssetTypeDAO extends AbstractGenericDAO<AssetType> {
    public AssetTypeDAO() {
        super(AssetType.class);
    }

    public AssetType getByName(String name){
        AssetType assetType = null;
        entityManager.getTransaction().begin();
        String jpql = "select at from AssetType at where at.name = :name";
        TypedQuery<AssetType> query = entityManager.createQuery(jpql, AssetType.class);
        query.setParameter("name", name);
        try {
            assetType = query.getSingleResult();
        } finally {
            entityManager.getTransaction().commit();
            return assetType;
        }
    }

    public List<AssetType> getTypeNames(){
        String jpql = "select at.name from AssetType at";
        List<AssetType> assetTypeList = (List<AssetType>)entityManager.createQuery(jpql) .getResultList();
        return assetTypeList;
    }
}
