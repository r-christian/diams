package com.christian.diams.builder;

import com.christian.diams.model.Asset;
import com.christian.diams.model.AssetInventory;
import com.christian.diams.model.AssetType;
import com.christian.diams.model.Company;

import java.util.Set;

public class AssetBuilder {
    public Company company;
    public AssetType assetType;
    public String name;
    public int quantity;
    public String manufacturer;

    public Set<AssetInventory> inventories;

    public AssetBuilder(Company company, AssetType assetType, String name, int quantity, String manufacturer) {
        this.company = company;
        this.assetType = assetType;
        this.name = name;
        this.quantity = quantity;
        this.manufacturer = manufacturer;
    }

    public AssetBuilder setInventories(Set<AssetInventory> additionalList) {
        this.inventories = inventories;
        return this;
    }

    public Asset build(){
        return new Asset(this);
    }
}
