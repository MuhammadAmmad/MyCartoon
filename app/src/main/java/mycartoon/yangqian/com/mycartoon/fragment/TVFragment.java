package mycartoon.yangqian.com.mycartoon.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mycartoon.yangqian.com.mycartoon.R;
import mycartoon.yangqian.com.mycartoon.comment.ToastUtils;
import mycartoon.yangqian.com.mycartoon.tv.activity.ShowActivity;

/**
 * Created by Administrator on 2016/11/23.
 */

public class TVFragment extends Fragment {
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.tv_search)
    TextView mTvSearch;
    @BindView(R.id.iv_search)
    ImageView mIvSearch;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_search, container, false);
        ButterKnife.bind(this,view);
        return view;
    }
    @OnClick(R.id.iv_search)
    public void onClick() {
        String tvname = mEtSearch.getText().toString().trim();
        if (tvname.equals("")){
            ToastUtils.showShort("抱歉，片名不能为空~");
            return;
        }
        ShowActivity.openWeb(getContext(),tvname);

    }

    @Override
    public void onStop() {
        super.onStop();
        mEtSearch.setText("");
    }
}
