package qinshi.mylibrary.utils;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;

/**
 * Created by Administrator on 2016/11/28.
 */

public class AlertDialogUitls {


    public static AlertDialog.Builder getDefaultDialog(Context mContext, String title,String message) {


        AlertDialog.Builder mAlertDialog = new AlertDialog.Builder(mContext).setTitle(title).setMessage(message);

        return mAlertDialog;

    }


    public static AlertDialog.Builder getDefaultDialog(Context mContext,String message) {


        AlertDialog.Builder mAlertDialog = new AlertDialog.Builder(mContext).setTitle("提示").setMessage(message);

        return mAlertDialog;

    }

    public static AlertDialog.Builder getDefaultDialog(Context mContext, String title,View mView) {


        AlertDialog.Builder mAlertDialog = new AlertDialog.Builder(mContext).setTitle(title);
        mAlertDialog.setView(mView);

        return mAlertDialog;

    }



}
