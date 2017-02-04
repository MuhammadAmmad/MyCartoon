package mycartoon.yangqian.com.mycartoon.tv.entity;

import java.io.Serializable;

/**
 * "name": "迪丽热巴",
 "url": "http://baike.so.com/doc/2156196-2281474.html",
 "image": "http://p4.qhmsg.com/dmsmty/120_110_100/t0189f8f1084abc6406.jpg"
 * Created by Administrator on 2017/1/6.
 */

public class Act_s implements Serializable {
    private String name;
    private String url;
    private String image;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getImage() {
        return image;
    }
}
