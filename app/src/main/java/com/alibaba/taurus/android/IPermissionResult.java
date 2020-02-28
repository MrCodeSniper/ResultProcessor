package com.alibaba.taurus.android;

import java.util.List;

public interface IPermissionResult {


    void onGranted();

    void onDenied(int requestCode, List<String> denyList);

    void cancelPermissions(int requestCode);


}
