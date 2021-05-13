package com.christian.diams.dao;

import com.christian.diams.dao.base.AbstractGenericDAO;
import com.christian.diams.model.AssetInventory;

public class AssetInventoryDAO extends AbstractGenericDAO<AssetInventory> {
    public AssetInventoryDAO() {
        super(AssetInventory.class);
    }
}