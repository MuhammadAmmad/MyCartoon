package mycartoon.yangqian.com.mycartoon.internetclient;

import mycartoon.yangqian.com.mycartoon.tv.entity.TVResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/1/6.
 */

public interface TVApi {
    //影视剧集接口
    @GET("video?key=9f9248f47d8654db7019ed1d42574e13")
    Call<TVResult> getTvResult(@Query("q") String q);
}
