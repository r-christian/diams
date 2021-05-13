package com.christian.diams.model;

import javax.persistence.*;
import java.time.LocalDate;

@Table
@Entity
public class AssetInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assetInventorID;

    @ManyToOne
    @JoinColumn(name="asset_id", nullable=true)
    private Asset asset;

    @Column(length = 25)
    private String model;

    @Column(length = 25)
    private String serialNumber;

    private LocalDate dateAcquired;

    private int numberInStock;

    public AssetInventory() {
    }

    public AssetInventory(Long assetInventorID, Asset asset, String model, String serialNumber, LocalDate dateAcquired, int numberInStock) {
        this.assetInventorID = assetInventorID;
        this.asset = asset;
        this.model = model;
        this.serialNumber = serialNumber;
        this.dateAcquired = dateAcquired;
        this.numberInStock = numberInStock;
    }

    public AssetInventory(Asset asset, String model, String serialNumber, LocalDate dateAcquired, int numberInStock) {
        this.asset = asset;
        this.model = model;
        this.serialNumber = serialNumber;
        this.dateAcquired = dateAcquired;
        this.numberInStock = numberInStock;
    }

    public Long getAssetInventorID() {
        return assetInventorID;
    }

    public void setAssetInventorID(Long assetInventorID) {
        this.assetInventorID = assetInventorID;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public LocalDate getDateAcquired() {
        return dateAcquired;
    }

    public void setDateAcquired(LocalDate dateAcquired) {
        this.dateAcquired = dateAcquired;
    }

    public int getNumberInStock() {
        return numberInStock;
    }

    public void setNumberInStock(int numberInStock) {
        this.numberInStock = numberInStock;
    }
}
