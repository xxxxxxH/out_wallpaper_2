package net.basicmodel;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import net.adapter.MyPagerAdapter;
import net.entity.DataEntity;
import net.utils.DialogManager;
import net.utils.ImgUtils;
import net.widget.GalleryItemView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C) 2021,2021/8/25, a Tencent company. All rights reserved.
 * <p>
 * User : v_xhangxie
 * <p>
 * Desc :
 */
public class GalleryActivity extends AppCompatActivity implements View.OnClickListener {

    ViewPager viewpager;
    TextView galleryTv;
    TextView save;
    TextView set;

    List<DataEntity> data;
    int position = 0;
    MyPagerAdapter adapter;
    ArrayList<View> views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_gallery);
        initView();
        initData();
        initViewPager();
    }

    private void initView() {
        viewpager = findViewById(R.id.viewpager);
        galleryTv = findViewById(R.id.gallery_tv);
        save = findViewById(R.id.gallery_save);
        set = findViewById(R.id.gallery_set_wallpaper);
        save.setOnClickListener(this);
        set.setOnClickListener(this);
    }

    private void initData() {
        Intent intent = getIntent();
        data = (List<DataEntity>) intent.getSerializableExtra("data");
        position = intent.getIntExtra("position", 0);
    }

    private void initViewPager() {
        views = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            GalleryItemView itemView = new GalleryItemView(this);
            DataEntity entity = data.get(i);
            itemView.setImg(this, entity.getImg_url());
            views.add(itemView);
        }
        adapter = new MyPagerAdapter(views);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(position);
        galleryTv.setText((position + 1) + " / " + data.size());
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                galleryTv.setText((position + 1) + " / " + data.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.gallery_save:
                DialogManager.getInstance().showLoadingDlg(GalleryActivity.this);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1500);
                            String url = data.get(viewpager.getCurrentItem()).getImg_url();
                            ImgUtils.getInstance().downloadImg(GalleryActivity.this,url,false);
                        }catch (Exception e){
                            Log.i("xxxxxxH",e.toString());
                        }
                    }
                }).start();
                break;
            case R.id.gallery_set_wallpaper:
                DialogManager.getInstance().showLoadingDlg(this);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1500);
                            String url = data.get(viewpager.getCurrentItem()).getImg_url();
                            ImgUtils.getInstance().downloadImg(GalleryActivity.this,url,true);
                        }catch (Exception e){
                            Log.i("xxxxxxH",e.toString());
                        }
                    }
                }).start();
                break;
        }
    }

}
