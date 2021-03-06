package com.app.gaolonglong.gankgirl.adapter.gank;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.gaolonglong.gankgirl.R;
import com.app.gaolonglong.gankgirl.model.gank.GanHuo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

public class MeiZhiViewHolder extends BaseViewHolder<GanHuo.Result> {
    private ImageView image;

    public MeiZhiViewHolder(ViewGroup parent) {
        super(parent, R.layout.meizhi_item);
        image = $(R.id.image);
    }

    @Override
    public void setData(GanHuo.Result data) {
        super.setData(data);
        Glide.with(getContext())
                .load(data.getUrl())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(image);
    }
}
