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

    protected static void newExcuteJsonPost(String url, final Context context, JSONObject  s, final OnRequestDataListener listener){
        final String netError = context.getString(R.string.net_error);
        try {
            s.put("terminal",Contacts.terminal);
        } catch (JSONException e) {

        }
        OkGo.<String>post(url)
                .tag(context)
                .upJson(s.toString())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(response.body()!=null){
                            try {
                                JSONObject jsonObject=new JSONObject(response.body());

                                int error_code = jsonObject.getInt("error_code");
                                if(error_code==0){
                                    JSONObject data = jsonObject.getJSONObject("data");
                                    listener.requestSuccess(0, data);
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
