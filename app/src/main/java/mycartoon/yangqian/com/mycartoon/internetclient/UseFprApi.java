package mycartoon.yangqian.com.mycartoon.internetclient;


import mycartoon.yangqian.com.mycartoon.user.BaseEntity;
import mycartoon.yangqian.com.mycartoon.user.Register;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/1/9.
 */

public interface UseFprApi {
//    /user_forgetpass?ver=" + args[0] + "&email=" + args[1]

    @GET("user_forgetpass?ver=1.0")
    Call<BaseEntity<Register>> getFp(@Query("email") String email);
}
