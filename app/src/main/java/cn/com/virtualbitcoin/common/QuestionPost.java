package cn.com.virtualbitcoin.common;

import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.intr.OnRequestDataListener;

/**
 * Created by admin on 2018/3/19.
 */

public class QuestionPost {

    protected static void newExcuteJsonPost(String url, final Context context, String  s, final OnRequestDataListener listener){
        final String netError = context.getString(R.string.net_error);

        OkGo.<String>post(url)
                .tag(context)
                .upJson(s)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(response.body()!=null){
                            try {
                                JSONObject jsonObject=new JSONObject(response.body());

                                int error_code = jsonObject.getInt("error_code");
                                if(error_code==200){
                                    listener.requestSuccess(0, jsonObject);
                                }else {
                                    listener.requestFailure(-1, jsonObject.getString("error_message"));
                                }

                            } catch (JSONException ignored) {

                            }

                        }else {
                            listener.requestFailure(-1, netError);

                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        listener.requestFailure(-1, netError);
                    }
                });


    }
}
