package com.game2048.number.puzzle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.game2048.number.BuildConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppUtility {
    public static void showAlertDialog(Activity activity, IAppExit iAppExitListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        // builder.setCancelable(false);
        builder.setTitle("Rate us if you like 2048");
        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            iAppExitListener.onAppExit();
            activity.finish();
        });
        builder.setNegativeButton("No", (dialog, which) -> dialog.cancel());
        builder.setNeutralButton("Rate Us", (dialog, which) -> {
            try {
                activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + BuildConfig.APPLICATION_ID)));
            } catch (android.content.ActivityNotFoundException anfe) {
                activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID)));
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    static boolean verifyInstallerId(Context context) {
        // A list with valid installers package name
        List<String> validInstallers = new ArrayList<>(Arrays.asList("com.android.vending", "com.google.android.feedback"));

        // The package name of the app that has installed your app
        final String installer = context.getPackageManager().getInstallerPackageName(context.getPackageName());

        // true if your app has been downloaded from Play Store
        return installer != null && validInstallers.contains(installer);
    }

    interface IAppExit {
        void onAppExit();
    }
}
