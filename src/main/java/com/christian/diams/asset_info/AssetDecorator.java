package com.christian.diams.asset_info;

import com.christian.diams.model.Asset;

public abstract class AssetDecorator implements IAsset{
    protected Asset tempAsset;

    public AssetDecorator(Asset newAsset) {
        this.tempAsset = newAsset;
    }

    @Override
    public Asset getAsset() {
        return tempAsset;
    }
}

