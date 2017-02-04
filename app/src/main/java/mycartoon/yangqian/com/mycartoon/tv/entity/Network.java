package mycartoon.yangqian.com.mycartoon.tv.entity;

import java.io.Serializable;

/**
 * "leshi": "http://www.le.com/ptv/vplay/27421450.html?ch=360_kan",
 "pptv": "http://v.pptv.com/show/s2NJxia6UBEKlI68.html",
 "qiyi": "http://www.iqiyi.com/v_19rr9yohfg.html?vfm=f_191_360y",
 "qq": "http://v.qq.com/x/cover/9qikfq28xqb01y9/r0022u0tnkm.html?ptag=360kan.tv.free",
 "sohu": "http://tv.sohu.com/20170105/n477910357.shtml?txid=4e4df35dda9d8ed32c874b1ad590ef59"
 * Created by Administrator on 2017/1/6.
 */

public class Network implements Serializable {
    private String leshi;
    private String pptv;
    private String qiyi;
    private String qq;
    private String sohu;
    private String youku;

    public String getLeshi() {
        return leshi;
    }

    public String getPptv() {
        return pptv;
    }

    public String getQiyi() {
        return qiyi;
    }

    public String getQq() {
        return qq;
    }

    public String getSohu() {
        return sohu;
    }

    public String getYouku() {
        return youku;
    }
}
