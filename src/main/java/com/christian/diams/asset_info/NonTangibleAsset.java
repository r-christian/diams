package com.christian.diams.asset_info;

import com.christian.diams.model.Asset;

public class NonTangibleAsset extends AssetDecorator{
    public NonTangibleAsset(Asset newAsset) {
        super(newAsset);
    }

    @Override
    public String getName() {
        return tempAsset.getName() + " - Non-Tangible";
    }
}
