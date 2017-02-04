package mycartoon.yangqian.com.mycartoon.user;

/**
 * 有关注册和登录的 entity
 * Created by Administrator on 2017/1/1.
 */

public class Register {
    //服务器返回结果
    String result;
    //用户令牌，用于验证用户。每次请求都传递给服务器。具有时效期。
    String token;
    //服务器返回结果说明
    String explain;

    public Register() {
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}