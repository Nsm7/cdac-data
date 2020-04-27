package com.sunbeaminfo.application4.constants;

import android.net.Uri;

public class Constants {

    // create Uri
    public static final String SCHEME = "content://";
    public static final String AUTHORITY = "com.sunbeaminfo.application4";
    public static final Uri CONTENT_URI = Uri.parse(SCHEME + AUTHORITY);

    // car
    // content://com.sunbeaminfo.application4/car
    public static final String PATH_CAR = "car";
    public static final Uri CONTENT_URI_CAR = Uri.parse(SCHEME + AUTHORITY + "/" + PATH_CAR);

    // mobile
    // content://com.sunbeaminfo.application4/mobile
    public static final String PATH_MOBILE = "mobile";
    public static final Uri CONTENT_URI_MOBILE = Uri.parse(SCHEME + AUTHORITY + "/" + PATH_MOBILE);

    // user
    // content://com.sunbeaminfo.application4/user
    public static final String PATH_USER = "user";
    public static final Uri CONTENT_URI_USER = Uri.parse(SCHEME + AUTHORITY + "/" + PATH_USER);

}
