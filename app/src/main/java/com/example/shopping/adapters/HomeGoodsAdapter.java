package com.example.shopping.adapters;


import android.content.Intent;
import android.graphics.Picture;
import android.view.View;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shopping.GoodsActivity;
import com.example.shopping.R;
import com.example.shopping.base.BaseListAdapter;
import com.example.shopping.simp.CategoryHome;
import com.example.shopping.simp.SimpleGoods;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeGoodsAdapter extends BaseListAdapter<CategoryHome, HomeGoodsAdapter.ViewHolder> {


    @Override protected int getItemViewLayout() {
        return R.layout.item_home_goods;
    }

    @Override protected ViewHolder getItemViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    class ViewHolder extends BaseListAdapter.ViewHolder {

        @BindView(R.id.text_category) TextView tvCategory;
        @BindView(R.id.grid_image) ImageView imageGrid;

        private ImageView[] mImageViews;

        private CategoryHome mItem;

        ViewHolder(View itemView) {
            super(itemView);
//            imageGrid.shuffle();
//            mImageViews = imageGrid.getImageViews();

            for (int i = 0; i < mImageViews.length; i++) {
                final int index = i;
                mImageViews[i].setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        navigateToGoodsActivity(index);
                    }
                });
            }
        }

        @Override protected void bind(int position) {
            mItem = getItem(position);
            tvCategory.setText(mItem.getName());

//            ImageView[] imageViews = imageGrid.getimage();
//            List<SimpleGoods> goodsList = mItem.getHotGoodsList();
//
//            for (int i = 0; i < imageViews.length; i++) {
//                Picture picture = goodsList.get(i).getImg();
//                GlideUtils.loadPicture(picture, imageViews[i]);
//            }
        }



        private void navigateToGoodsActivity(int index) {

            if (mItem.getHotGoodsList().size() <= index) return;

            int goodsId = mItem.getHotGoodsList().get(index).getId();
            Intent intent = GoodsActivity.getStartIntent(getContext(), goodsId);
            getContext().startActivity(intent);
        }
    }
}
