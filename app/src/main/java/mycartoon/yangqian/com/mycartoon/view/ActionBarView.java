package mycartoon.yangqian.com.mycartoon.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import mycartoon.yangqian.com.mycartoon.R;

/**
 * Created by Administrator on 2016/11/22.
 */

public class ActionBarView extends LinearLayout {
    private static final int NULL_ID = -1;
    ImageView iv_left,iv_right;
    TextView tv_action_title;
    public ActionBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.layout_actionbar,this);
        iv_left = (ImageView) findViewById(R.id.iv_left);
        iv_right = (ImageView) findViewById(R.id.iv_right);
        tv_action_title = (TextView) findViewById(R.id.tv_action_title);
    }

    /**
     * 加载ActionBar的方法
     * @param title
     * @param leftId
     * @param rightId
     * @param listener
     */
    public void iniiActionBarView(String title, int leftId, int rightId, View.OnClickListener listener){
        if (title == null){
            tv_action_title.setVisibility(INVISIBLE);
        }else {
            tv_action_title.setText(title);
        }
        if (leftId == -1){
            iv_left.setVisibility(INVISIBLE);
        }else {
            iv_left.setImageResource(leftId);
        }
        if (rightId == -1){
            iv_right.setVisibility(INVISIBLE);
        }else {
            iv_right.setImageResource(rightId);
        }
    }
}
