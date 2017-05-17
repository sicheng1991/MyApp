package qinshi.mylibrary.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/7.
 */
public class BaseModel<T> implements Serializable {


    public String status;
    public String errMsg;
    public T data;
    public String errCode;

    public BaseModel(String mStatus, String mErrMsg, T mData,String mErrCode) {
        status = mStatus;
        errMsg = mErrMsg;
        data = mData;
        errCode=mErrCode;
    }
}
