package com.example.shopping.adapters;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shopping.R;
import com.example.shopping.base.BaseListAdapter;
import com.example.shopping.simp.CategoryHome;

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
        @BindView(R.id.grid_image) ImageGrid imageGrid;

        private ImageView[] mImageViews;

        private CategoryHome mItem;

        ViewHolder(View itemView) {
            super(itemView);
            imageGrid.shuffle();
            mImageViews = imageGrid.getImageViews();

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

            ImageView[] imageViews = imageGrid.getImageViews();
            List<SimpleGoods> goodsList = mItem.getHotGoodsList();

            for (int i = 0; i < imageViews.length; i++) {
                Picture picture = goodsList.get(i).getImg();
                GlideUtils.loadPicture(picture, imageViews[i]);
            }
        }

        @OnClick(R.id.text_category) void navigateToSeach() {
            Filter filter = new Filter();
            filter.setCategoryId(mItem.getId());
            Intent intent = SearchGoodsActivity.getStartIntent(getContext(), filter);
            getContext().startActivity(intent);
        }

        private void navigateToGoodsActivity(int index) {

            if (mItem.getHotGoodsList().size() <= index) return;

            int goodsId = mItem.getHotGoodsList().get(index).getId();
            Intent intent = GoodsActivity.getStartIntent(getContext(), goodsId);
            getContext().startActivity(intent);
        }
    }
}
