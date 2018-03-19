package cn.com.virtualbitcoin.common;

import android.content.Context;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.intr.OnRequestDataListener;

/**
 * Created by apple on 2018/3/15.
 */

public class Api {
    public  static  String HOST="http://test.api.bi.anwenqianbao.com/v1/";
    //banner图
    public  static  String GET_BANNER=HOST+"banner/getBanner";
    //注册
    public  static  String GET_REGISTER=HOST+"register/register";
    //登录
    public  static  String GET_LOGIN=HOST+"login/login";
    //注册发送验证码
    public  static  String SEED_MESS=HOST+"sms/getcode";
     //忘记密码发送验证码
    public  static  String SEED_FORGET_MESS=HOST+"sms/getPwdCode";
    //忘记密码
    public  static  String GET_FORGET=HOST+"person/testCode";
    //领取糖果
    public  static  String GET_SWEET=HOST+"candy/addCandy";
    //未登录评级列表
    public  static  String GET_GRADELSIT=HOST+"grade/getGrade";
    //已登录评级列表
    public  static  String GET_USERGRADELSIT=HOST+"grade/getGradeUser";
    //未登录糖果列表
    public  static  String GET_SWEETLSIT=HOST+"candy/getCandy";
    //已登录
    public  static  String GET_USUERSWEETLSIT=HOST+"candy/getCandyUser";
    //当前价格
    public  static  String GET_PRICE=HOST+"coin/getCoin";
    //我的糖果
    public  static  String GET_MYSWEET=HOST+"candy/myCandy";
    //add收藏
    public  static  String GET_COLLECTION=HOST+"grade/addGrade";
    //my收藏
    public  static  String GET_MY_COLLECTION=HOST+"grade/myGrade";
    //版本检测
    public  static  String GET_UPDATE="http://test.api.anwenqianbao.com/v1/version/version";
    //
    public static void getReQuest(String url,final Context context, JSONObject s, final OnRequestDataListener listener) {
        QuestionPost.newExcuteJsonPost(url, context, s,listener);
    }



}
