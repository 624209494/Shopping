package com.example.shopping.simp;


import com.example.shopping.base.CategoryBase;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryHome extends CategoryBase {

    @SerializedName("goods")
    private List<SimpleGoods> mHotGoodsList;

    public List<SimpleGoods> getHotGoodsList() {
        return mHotGoodsList;
    }
}
