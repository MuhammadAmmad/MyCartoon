package mycartoon.yangqian.com.mycartoon.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import mycartoon.yangqian.com.mycartoon.R;
import mycartoon.yangqian.com.mycartoon.base.BaseActivity;

public class LogoActivity extends BaseActivity {
    ImageView iv_logo;
    Animation alpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        Log.e("LogoActivity", "onCreate: " );
        iv_logo = (ImageView) findViewById(R.id.iv_logo);
        alpha = AnimationUtils.loadAnimation(this,R.anim.logo_anim);
        alpha.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(HomeActivity.class);
                LogoActivity.this.finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        iv_logo.setAnimation(alpha);
    }
}
