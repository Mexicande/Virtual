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
    public  static  String GET_BANNER="banner/getBanner";
    //注册
    public  static  String GET_REGISTER="register/register";
    //登录
    public  static  String GET_LOGIN="login/login";
    //发送验证码
    public  static  String SEED_MESS="sms/getPwdCode";
    //忘记密码
    public  static  String GET_FORGET="person/testCode";
    //领取糖果
    public  static  String GET_SWEET="candy/addCandy";
    //评级列表
    public  static  String GET_GRADELSIT="candy/getCandy";
    //糖果列表
    public  static  String GET_SWEETLSIT="grade/getGrade";
    //当前价格
    public  static  String GET_PRICE="coin/getCoin";
    //我的糖果
    public  static  String GET_MYSWEET="candy/myCandy";
    //add收藏
    public  static  String GET_COLLECTION="grade/addGrade";
    //
    public static void getReQuest(String url,final Context context, String s, final OnRequestDataListener listener) {
        QuestionPost.newExcuteJsonPost(url, context, s,listener);
    }



}
