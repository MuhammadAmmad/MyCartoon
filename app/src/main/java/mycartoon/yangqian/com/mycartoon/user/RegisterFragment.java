package mycartoon.yangqian.com.mycartoon.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mycartoon.yangqian.com.mycartoon.R;
import mycartoon.yangqian.com.mycartoon.comment.ToastUtils;
import mycartoon.yangqian.com.mycartoon.internetclient.BombClient;
import mycartoon.yangqian.com.mycartoon.internetclient.UserApi;
import mycartoon.yangqian.com.mycartoon.internetclient.entity.UserEntity;
import mycartoon.yangqian.com.mycartoon.internetclient.result.ErrorResult;
import mycartoon.yangqian.com.mycartoon.internetclient.result.UserResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Administrator on 2016/12/21 0021.
 */

public class RegisterFragment extends DialogFragment {

    @BindView(R.id.etUsername)
    EditText mEtUsername;
    @BindView(R.id.etPassword)
    EditText mEtPassword;
    @BindView(R.id.btnRegister)
    Button mBtnRegister;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //取消标题栏
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_register,container,false);
        Log.e("RegisterFragment", "mEtUsername: " + mEtUsername);
        ButterKnife.bind(this,view);
//        mBtnRegister = (Button) view.findViewById(R.id.btnRegister);
//        mBtnRegister.setOnClickListener(this);
        return view;
    }

    @OnClick(R.id.btnRegister)
    public void onClick(){

        final String username = mEtUsername.getText().toString();
        String password = mEtPassword.getText().toString();

        //用户名和密码不能为空
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            ToastUtils.showShort(R.string.username_or_password_can_not_be_null);
            return;
        }
        Log.e("RegisterFragment", "onClick: "+password);
        //显示进度条
        mBtnRegister.setVisibility(View.GONE);

        //网络模块，注册请求
        //注册api
        UserApi userApi = BombClient.getInstance().getUserApi();
        //构建用户实体类
        UserEntity userEntity = new UserEntity(username,password);
        Log.e("RegisterFragment", "userEntity: "+userEntity);
        //拿到call模型
        Call<UserResult> call = userApi.register(userEntity);
        Log.e("RegisterFragment", "call: "+call);
        //执行网络请求
        call.enqueue(new Callback<UserResult>() {
            @Override
            public void onResponse(Call<UserResult> call, Response<UserResult> response) {
                //隐藏加载圈圈
                mBtnRegister.setVisibility(View.VISIBLE);
                //注册失败
                if (!response.isSuccessful()){
                    try {
                        //拿到失败的json
                        String error = response.errorBody().string();
                        //通过gson将拿到的json数据解析成失败结果类
                        ErrorResult errorResult = new Gson().fromJson(error,ErrorResult.class);
                        //提示用户注册失败
                        ToastUtils.showShort(errorResult.getError());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                //注册成功
                UserResult userResult = response.body();
                listener.registerSuccess(username,userResult.getObjectId());
                //提示注册成功
                ToastUtils.showShort(R.string.register_success);
            }

            @Override
            public void onFailure(Call<UserResult> call, Throwable t) {
                //隐藏圈圈
                mBtnRegister.setVisibility(View.VISIBLE);
                //提示失败原因
                ToastUtils.showShort(t.getMessage());
            }
        });
        listener.outMenu();
    }

//    @Override
//    public void onClick(View v) {
//
//    }

    //当注册成功会触发的方法
    public interface OnRegisterSuccessListener{
        /** 当注册成功时，来调用*/
        void registerSuccess(String username, String objectId);
        void outMenu();
    }

    private OnRegisterSuccessListener listener;

    public void setListener(OnRegisterSuccessListener listener){
        this.listener = listener;
    }


}
