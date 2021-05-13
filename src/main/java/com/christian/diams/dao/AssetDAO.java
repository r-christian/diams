package com.christian.diams.dao;

import com.christian.diams.dao.base.AbstractGenericDAO;
import com.christian.diams.model.Asset;

public class AssetDAO extends AbstractGenericDAO<Asset> {
    public AssetDAO() {
        super(Asset.class);
    }
}
