package mycartoon.yangqian.com.mycartoon.tv.entity;

import java.util.ArrayList;

/**
 *  "title": "漂亮的她",
 "tag": "偶像 / 言情 / 都市 / 喜剧",
 "act": "迪丽热巴 盛一伦 李溪芮 张彬彬 王骁 王一楠",
 "year": "2017",
 "rating": null,
 "area": "内地剧",
 "dir": "赵晨阳",
 "desc": "从小生活完美的公主李慧珍因家道中落沦为“龙套女”，成了初入职场处处碰壁的菜鸟。而小时候的“矮挫胖”白皓宇却变身高富帅海归。重逢时，一个是初入职场的菜鸟，拼尽全力克服职场上种种困难，怀抱着对初恋的期待而努力；一个是事业有成的时尚精英，念念不忘想寻回记忆中的初恋，却遍寻不得，二人身份的逆转倒错，让这个爱情故事有了一丝苦涩却也不乏搞笑欢乐的暖萌情节，是一部涵盖了友情、爱情、梦想、职场的轻喜剧。",
 "cover": "http://p6.qhimg.com/t0127f261b254074d12.jpg",
 "vdo_status": "play",
 "playlinks": {
 "leshi": "http://www.le.com/ptv/vplay/27421450.html?ch=360_kan",
 "pptv": "http://v.pptv.com/show/s2NJxia6UBEKlI68.html",
 "qiyi": "http://www.iqiyi.com/v_19rr9yohfg.html?vfm=f_191_360y",
 "qq": "http://v.qq.com/x/cover/9qikfq28xqb01y9/r0022u0tnkm.html?ptag=360kan.tv.free",
 "sohu": "http://tv.sohu.com/20170105/n477910357.shtml?txid=4e4df35dda9d8ed32c874b1ad590ef59"
 * Created by Administrator on 2017/1/6.
 */

public class TVRes {
    private String title;
    private String tag;
    private String act;
    private String year;
    private String rating;
    private String area;
    private String dir;
    private String cover;
    private String vdo_status;
    private Network playlinks;
    private String desc;

    public String getDesc() {
        return desc;
    }
    private ArrayList<Video_rec> video_rec;
    private ArrayList<Act_s> act_s;
    public ArrayList<Video_rec> getVideo_rec() {
        return video_rec;
    }

    public ArrayList<Act_s> getAct_s() {
        return act_s;
    }

    public String getTitle() {
        return title;
    }

    public String getTag() {
        return tag;
    }

    public String getAct() {
        return act;
    }

    public String getYear() {
        return year;
    }

    public String getRating() {
        return rating;
    }

    public String getArea() {
        return area;
    }

    public String getDir() {
        return dir;
    }

    public String getCover() {
        return cover;
    }

    public String getVdo_status() {
        return vdo_status;
    }

    public Network getPlaylinks() {
        return playlinks;
    }
}
