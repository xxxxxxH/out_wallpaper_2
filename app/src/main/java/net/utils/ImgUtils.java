package net.utils;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Copyright (C) 2021,2021/8/25, a Tencent company. All rights reserved.
 * <p>
 * User : v_xhangxie
 * <p>
 * Desc :
 */
public class ImgUtils {
    private static ImgUtils instance = null;

    private ImgUtils() {
    }

    public static ImgUtils getInstance() {
        if (instance == null) {
            instance = new ImgUtils();
        }
        return instance;
    }

    @SuppressLint("CheckResult")
    public void downloadImg(Context context, String url, boolean justSave) {
        Glide.with(context)
                .downloadOnly()
                .load(url)
                .listener(new RequestListener<File>() {
                    @Override
                    public boolean onLoadFailed(GlideException e, Object model, Target<File> target, boolean isFirstResource) {
                        Toast.makeText(context, "download failed", Toast.LENGTH_SHORT).show();
                        DialogManager.getInstance().closeLoadingDlg();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(File resource, Object model, Target<File> target, DataSource dataSource, boolean isFirstResource) {
                        DialogManager.getInstance().closeLoadingDlg();
                        try {
                            handleImgAfterDownload(context,resource,justSave);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return false;
                    }
                }).preload();
    }

    public void handleImgAfterDownload(Context context, File scrPath, boolean set) throws IOException {
        File file = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".png");
        boolean isCopySuccess = copyFile(scrPath, file.getAbsolutePath());
        if (isCopySuccess) {
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                    Uri.parse("file://" + file.getAbsolutePath())));

            if (set) {
                WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
                Bitmap bitmap = BitmapFactory.decodeFile(file.toString());
                wallpaperManager.setBitmap(bitmap);
                bitmap.recycle();
            }

            Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
        }

        DialogManager.getInstance().closeLoadingDlg();
    }


    public boolean copyFile(File src, String destPath) {
        boolean result = false;
        if ((src == null) || (destPath == null)) {
            return result;
        }
        File dest = new File(destPath);
        if (dest != null && dest.exists()) {
            dest.delete(); // delete file
        }
        try {
            dest.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileChannel srcChannel = null;
        FileChannel dstChannel = null;

        try {
            srcChannel = new FileInputStream(src).getChannel();
            dstChannel = new FileOutputStream(dest).getChannel();
            srcChannel.transferTo(0, srcChannel.size(), dstChannel);
            result = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return result;
        }
        try {
            srcChannel.close();
            dstChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
