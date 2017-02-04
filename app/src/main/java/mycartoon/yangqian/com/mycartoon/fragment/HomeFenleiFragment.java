package mycartoon.yangqian.com.mycartoon.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mycartoon.yangqian.com.mycartoon.R;

/**
 * Created by Administrator on 2016/11/23.
 */

public class HomeFenleiFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_fenlei,container,false);
    }
}
