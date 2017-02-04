package mycartoon.yangqian.com.mycartoon.tv.entity;

import java.io.Serializable;

/**
 * "cover": "http://p6.qhimg.com/t0127f261b254074d12.jpg",
 "detail_url": "http://www.360kan.com/tv/PrJrbX7kSmbpN3.html",
 "title": "漂亮的李慧珍"
 * Created by Administrator on 2017/1/6.
 */

public class Video_rec implements Serializable{
    private String cover;
    private String detail_url;
    private String title;

    public String getCover() {
        return cover;
    }

    public String getDetail_url() {
        return detail_url;
    }

    public String getTitle() {
        return title;
    }
}
