package mycartoon.yangqian.com.mycartoon.internetclient.entity;

//      {
//        "content":评论内容,
//        "author":{
    //        "__type":"Pointer",
    //        "className":"_User",
    //        "objectId":用户Id
//              }
//        "news":{
    //        "__type":"Pointer",
    //        "className":"News",
    //        "objectId":新闻Id
//              }
//     }


import mycartoon.yangqian.com.mycartoon.internetclient.others.AuthorPointer;
import mycartoon.yangqian.com.mycartoon.internetclient.others.NewsPointer;

public class PublishEntity {
    private String content;
    private AuthorPointer author;
    private NewsPointer news;

    public PublishEntity(String content, String userId, String newsId) {
        this.content = content;
        this.author = new AuthorPointer(userId);
        this.news = new NewsPointer(newsId);
    }
}
