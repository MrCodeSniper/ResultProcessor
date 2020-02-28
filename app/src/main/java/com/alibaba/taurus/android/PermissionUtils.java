package com.alibaba.taurus.android;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class PermissionUtils {


    public static void requestPermissions(Activity activity,String[] permissions,int requestCode,IPermissionResult result){
        if(Build.VERSION.SDK_INT< Build.VERSION_CODES.M){
            if(result!=null){
                result.onGranted();
            }
        }else {

            List<String> unGrantedPermissions=new ArrayList<>();
            for(String permission:permissions){
                if(ContextCompat.checkSelfPermission(activity,permission)!= PackageManager.PERMISSION_GRANTED){
                    unGrantedPermissions.add(permission);
                }
            }


            if(unGrantedPermissions.size()>0){
                ActivityCompat.requestPermissions(activity,permissions,requestCode);
            }else {
                if(result!=null){
                    result.onGranted();
                }
            }
        }








    }


}
