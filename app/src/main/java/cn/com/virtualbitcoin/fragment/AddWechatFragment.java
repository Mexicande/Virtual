package cn.com.virtualbitcoin.fragment;


import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.virtualbitcoin.R;
import cn.com.virtualbitcoin.utils.ToastUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddWechatFragment extends DialogFragment {


    @Bind(R.id.cancel)
    TextView cancel;
    @Bind(R.id.copy)
    TextView copy;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.str)
    TextView str;

    private String mTitle;
    private String tv_str;

    public AddWechatFragment() {
        // Required empty public constructor
    }

    public static AddWechatFragment newInstance(String title, String str) {
        AddWechatFragment instance = new AddWechatFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("str", str);
        instance.setArguments(args);
        return instance;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Base_AlertDialog);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_wechat, container, false);
        final Window window = getDialog().getWindow();
        if (window != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams wlp = window.getAttributes();
            wlp.gravity = Gravity.CENTER;
            wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
            wlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(wlp);
        }
        ButterKnife.bind(this, view);
        initDialog();

        return view;
    }

    private void initDialog() {
        mTitle = getArguments().getString("title");
        tv_str = getArguments().getString("str");
        if (mTitle != null) {
            title.setText(mTitle);
        }
        if (tv_str != null) {
            str.setText(tv_str);
        }
        if("加QQ群".equals(mTitle)){
            copy.setText("立即加群");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public boolean joinQQGroup(String key) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + key));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            startActivity(intent);
            return true;
        } catch (Exception e) {
            // 未安装手Q或安装的版本不支持
            return false;
        }

    }

    @OnClick({R.id.cancel, R.id.copy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                dismiss();
                break;
            case R.id.copy:

                if("加QQ群".equals(mTitle)){
                    joinQQGroup("ln83JO-AUn64PcssZPDA7V4xK2VSTRZJ");
                    dismiss();

                }else {
                    ClipboardManager cmb = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                    if (cmb != null) {
                        cmb.setText("TangGuoDaBaoBao");
                    }
                    ToastUtils.showShort("复制成功");
                    dismiss();
                }
                break;
            default:
                break;

        }

    }

}
