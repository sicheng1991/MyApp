package qinshi.mylibrary.rx;

/**
 * Created by Administrator on 2016/11/24.
 */

public class ServerException extends Exception {

    public String code;

    public ServerException(String mS, String mCode) {
        super(mS);
        code = mCode;

    }
}
