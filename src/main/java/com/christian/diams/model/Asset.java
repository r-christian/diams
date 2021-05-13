package com.christian.diams.model;

import com.christian.diams.builder.AssetBuilder;
import com.christian.diams.utility.Constants;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table
@Entity
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long AssetID;

    @ManyToOne
    @JoinColumn(name="company_id", nullable=true)
    private Company company;

    @ManyToOne
    @JoinColumn(name="asset_type_id", nullable=true)
    private AssetType assetType;

    @Column(length = Constants.NAME_LENGTH)
    private String name;

    @Column(length = Constants.NAME_LENGTH)
    private String manufacturer;

    @OneToMany(mappedBy = "asset")
    private Set<AssetInventory> inventories = new HashSet<>();

    private int quantity;

    public Asset() {
    }

    public Asset(Long assetID, Company company, AssetType assetType, String name, String manufacturer, Set<AssetInventory> inventories, int quantity) {
        AssetID = assetID;
        this.company = company;
        this.assetType = assetType;
        this.name = name;
        this.manufacturer = manufacturer;
        this.inventories = inventories;
        this.quantity = quantity;
    }

    public Asset(Company company, AssetType assetType, String name, String manufacturer, Set<AssetInventory> inventories, int quantity) {
        this.company = company;
        this.assetType = assetType;
        this.name = name;
        this.manufacturer = manufacturer;
        this.inventories = inventories;
        this.quantity = quantity;
    }

    public Asset(AssetBuilder assetBuilder) {
        this.company = assetBuilder.company;
        this.assetType = assetBuilder.assetType;
        this.name = assetBuilder.name;
        this.manufacturer = assetBuilder.manufacturer;
        this.inventories = assetBuilder.inventories;
        this.quantity = assetBuilder.quantity;
    }

    public Long getAssetID() {
        return AssetID;
    }

    public void setAssetID(Long assetID) {
        AssetID = assetID;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public AssetType getAssetType() {
        return assetType;
    }

    public void setAssetType(AssetType assetType) {
        this.assetType = assetType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Set<AssetInventory> getInventories() {
        return inventories;
    }

    public void setInventories(Set<AssetInventory> inventories) {
        this.inventories = inventories;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String companyName(){
        if(company == null)
            return "";
        else
            return company.getName();
    }

    public String assetTypeName(){
        if(assetType == null)
            return "";
        else
            return assetType.getName();
    }
}
