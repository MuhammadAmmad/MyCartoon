package mycartoon.yangqian.com.mycartoon.internetclient.others;


//        "__type":"Pointer",
//        "className":"News",
//        "objectId":新闻Id

import com.google.gson.annotations.SerializedName;

public class NewsPointer {
    @SerializedName("__type")
    private String type;
    private String className;
    private String objectId;

    public NewsPointer(String newsId) {
        type = "Pointer";
        className = "News";
        this.objectId = newsId;
    }
}
