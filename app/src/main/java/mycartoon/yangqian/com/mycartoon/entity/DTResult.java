package mycartoon.yangqian.com.mycartoon.entity;

import java.util.ArrayList;

/**
 * resultcode": "200",
 "reason": "success",
 "result":
 * Created by Administrator on 2017/1/6.
 */

public class DTResult {
    private String resultcode;
    private String reason;
    private ArrayList<Result> result;
    private String error_code;

    public String getResultcode() {
        return resultcode;
    }

    public String getReason() {
        return reason;
    }


    public String getError_code() {
        return error_code;
    }
    public ArrayList<Result> getResult() {
        return result;
    }
}
