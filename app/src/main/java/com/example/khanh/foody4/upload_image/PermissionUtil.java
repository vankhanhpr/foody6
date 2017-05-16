package com.example.khanh.foody4.upload_image;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;

import com.example.khanh.foody4.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Khanh on 5/16/2017.
 */

public class PermissionUtil {

    public static boolean isReadWritePermission(Context context) {
        return Build.VERSION.SDK_INT < 23 || (Build.VERSION.SDK_INT >= 23 &&
                context.checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }

    public static void marshmallowReadWritePermissionCheck(Activity context) {
        if (Build.VERSION.SDK_INT >= 23 && !Settings.System.canWrite(context)) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(context, "android.permission.READ_EXTERNAL_STORAGE")
                    && ActivityCompat.shouldShowRequestPermissionRationale(context,"android.permission.WRITE_EXTERNAL_STORAGE")) {
                showPopupPermission(context,
                        new ArrayList(Arrays.asList(
                                new String[]{context.getString(R.string.TEXT_PERMISSION_STORAGE)})));
                return;
            }
            ActivityCompat.requestPermissions(context,
                    new String[]{
                            "android.permission.WRITE_EXTERNAL_STORAGE",
                            "android.permission.READ_EXTERNAL_STORAGE"},
                    11);
        }
    }

    private static void showPopupPermission(final Activity context, ArrayList<String> listPermissions) {
        String message = context.getString(R.string.TEXT_REQUEST_PERMISSION);
        if (listPermissions != null && listPermissions.size() > 0) {
            if (listPermissions.size() == 1) {
                message = message + "\n";
            }
            for (String i : listPermissions) {
                message += "\n  - " + i;

            }
        }
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton(context.getString(R.string.TEXT_YES), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                        intent.setData(Uri.fromParts("package", context.getPackageName(), null));
                        context.startActivityForResult(intent, 11);
                        dialog.dismiss();

                    }
                })
                .setNegativeButton(context.getString(R.string.TEXT_NO), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(true)
                .show();
    }
}
