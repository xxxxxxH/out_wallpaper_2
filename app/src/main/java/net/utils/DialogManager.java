package net.utils;

import android.content.Context;

import net.widget.LoadingDialog;

/**
 * Copyright (C) 2021,2021/8/25, a Tencent company. All rights reserved.
 * <p>
 * User : v_xhangxie
 * <p>
 * Desc :
 */
public class DialogManager {
    private static DialogManager instance = null;
    private LoadingDialog loadingDialog;

    private DialogManager() {

    }

    public static DialogManager getInstance() {
        if (instance == null) {
            instance = new DialogManager();
        }
        return instance;
    }

    public void showLoadingDlg(Context context) {
        loadingDialog = new LoadingDialog(context);
        loadingDialog.show();
    }

    public void closeLoadingDlg() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

}
