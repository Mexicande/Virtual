package cn.com.virtualbitcoin.common;

import cn.com.virtualbitcoin.utils.AppUtils;

/**
 * Created by apple on 2018/3/15.
 */

public class Contacts {
    public static String app_version= AppUtils.getAppVersionName();
    public static String app_Name= AppUtils.getAppName();
    public static String app_Terminal= "android";
    public static String token= "token";
    public static String phone= "phone";
    public static String terminal= "1";


    public static  final  int RESULT_CODE=100;
    public static  final  int REQUESTION_CODE=1000;
    public static int ERROR_CODE= 2;




    public static String WEB_TITLE= "title";
    public static String WEB_URL= "url";






        /** 启动页显示时间 **/
        public static final int LAUCHER_DIPLAY_MILLIS = 2000;
        /** 倒计时时间 **/
        public static final int MILLIS_IN_TOTAL = 60000;
        /** 时间间隔 **/
        public static final int COUNT_DOWN_INTERVAL = 1000;

}
