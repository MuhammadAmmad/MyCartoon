package mycartoon.yangqian.com.mycartoon.user;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mycartoon.yangqian.com.mycartoon.R;
import mycartoon.yangqian.com.mycartoon.comment.CommonUtil;
import mycartoon.yangqian.com.mycartoon.comment.ToastUtils;
import mycartoon.yangqian.com.mycartoon.internetclient.TVClient;
import mycartoon.yangqian.com.mycartoon.internetclient.UseFprApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Administrator on 2016/1/1.
 */

public class FragmentForgetPassWord extends DialogFragment {

    @BindView(R.id.etEmail)
    TextInputEditText mEtEmail;
    @BindView(R.id.btnLogin)
    Button mBtnLogin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //无标题栏
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_fp, container, false);
        ButterKnife.bind(this, view);
        return view;
    }



    @Override
    public void onStop() {
        super.onStop();
        mEtEmail.setText(null);
    }

    @OnClick(R.id.btnLogin)
    public void onClick() {
        String email = mEtEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getActivity(), "邮箱地址不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!CommonUtil.verifyEmail(email)) {
            Toast.makeText(getActivity(), "邮箱格式不正确，请重新输入！", Toast.LENGTH_SHORT).show();
            return;
        }
        //显示进度条
        mBtnLogin.setVisibility(View.GONE);

        //登录网络请求
        UseFprApi userApi = TVClient.getInstance().getUserApi();
        Call<BaseEntity<Register>> call = userApi.getFp(email);
        call.enqueue(new Callback<BaseEntity<Register>>() {
            @Override
            public void onResponse(Call<BaseEntity<Register>> call, Response<BaseEntity<Register>> response) {
                mBtnLogin.setVisibility(View.VISIBLE);
                BaseEntity<Register> body = response.body();
                String status = body.getStatus();
                Register data = body.getData();
                String result = "";
                if (status.equals("0")){
                    result = data.explain;
                    if (data.getResult().trim().equals("0")){
                        mFragmentForgetPassWordListener.sucess();
                        mFragmentForgetPassWordListener.outMenu();
                    }else if (data.getResult().trim().equals("-1")){
                        mEtEmail.requestFocus();
                    }else if (data.getResult().trim().equals("-2")){
                        mEtEmail.requestFocus();
                    }
                    ToastUtils.showShort(result);
                }
            }

            @Override
            public void onFailure(Call<BaseEntity<Register>> call, Throwable t) {

                mBtnLogin.setVisibility(View.VISIBLE);
                ToastUtils.showShort("网络异常~");
            }
        });

    }
    public void setFragmentForgetPassWordListener(@NonNull FragmentForgetPassWordListener fragmentForgetPassWordListener){
        this.mFragmentForgetPassWordListener = fragmentForgetPassWordListener;
    }

    private FragmentForgetPassWordListener mFragmentForgetPassWordListener;
    public interface FragmentForgetPassWordListener{
        void sucess();
        void outMenu();
    }
}
