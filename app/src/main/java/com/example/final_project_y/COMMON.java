package com.example.final_project_y;

public class COMMON {
    public static  String BASE_URL = "http://10.0.2.2/finally/public/api/";
    public static  String FILES_LINK = "http://10.0.2.2/final-project-php-master/sources/files/";
    public static  String AVATAR_LINK = "http://10.0.2.2/final-project-php-master/sources/image/";

    public static  String GROUPS_URL = BASE_URL + "groups";
    public static  String ASSIGNMENTS_URL = BASE_URL + "assignments";
    public static  String MATERIALS_URL = BASE_URL + "materials";
    public static  String LOGIN_URL = BASE_URL + "login";
    public static  String PROFILE_URL = BASE_URL + "profile";



    public static  String CURRENT_USER_EMAIL;
    public static  String CURRENT_USER_PASSWORD;
    public static  String USER_TOKEN ;

    // Shared pref mode
    public static final String PREF_NAME = "file_share_app";
    public static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    public static final String KEY_TOKEN = "accessToken";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_STATUS = "stat";
    public static final String KEY_EMAIL = "email";

}
