package mycartoon.yangqian.com.mycartoon.internetclient;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/1/5.
 */

public class TVClient {
    private OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private Retrofit mRetrofitTV;
    private TVApi mTVApi;
    private MovieResultApi mResultApi;
    private static TVClient sTVClient;
    private Retrofit mRetrofitFp;
    private UseFprApi mUseFprApi;

    public static TVClient getInstance(){
        if ( sTVClient == null){
            sTVClient = new TVClient();
        }
        return sTVClient;
    }

    public TVClient() {
        //日志拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //构建OkHttp
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)//添加日志拦截器
                .build();

        //让Gson能够将Bomb返回的时间戳自动转换为Date对象
        //构建Retrofit
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                //bomb服务器的BaseUrl
                .baseUrl("http://v.juhe.cn/boxoffice/")
                //添加Gson转换器
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mRetrofitTV = new Retrofit.Builder()
                .client(okHttpClient)
                //bomb服务器的BaseUrl
                .baseUrl("http://op.juhe.cn/onebox/movie/")
                //添加Gson转换器
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //忘记密码
        mRetrofitFp = new Retrofit.Builder()
                .client(okHttpClient)
                //bomb服务器的BaseUrl
                .baseUrl("http://118.244.212.82:9092/newsClient/")
                //添加Gson转换器
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }



    //拿到ResultApi
    public MovieResultApi getResultApi(){
        if (mResultApi == null){
            mResultApi = retrofit.create(MovieResultApi.class);
        }
        return mResultApi;
    }
    //拿到TVApi
    public TVApi getTVApi(){
        if (mTVApi == null){
            mTVApi = mRetrofitTV.create(TVApi.class);
        }
        return mTVApi;
    }
    //拿到UseFprApi
    public UseFprApi getUserApi(){
        if (mUseFprApi == null){
            mUseFprApi = mRetrofitFp.create(UseFprApi.class);
        }
        return mUseFprApi;
    }
}
