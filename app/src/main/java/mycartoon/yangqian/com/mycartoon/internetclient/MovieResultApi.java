package mycartoon.yangqian.com.mycartoon.internetclient;


import mycartoon.yangqian.com.mycartoon.entity.DTResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/1/5.
 */

public interface MovieResultApi {
//    @GET("rank.php?key=a27e6f19654e149adfa8e63483170fee&area=CN")
//    Call<DTResult> getDType();
    //电影票房接口
    @GET("rank.php?key=a27e6f19654e149adfa8e63483170fee")
    Call<DTResult> getDType(@Query("area") String area);
}
