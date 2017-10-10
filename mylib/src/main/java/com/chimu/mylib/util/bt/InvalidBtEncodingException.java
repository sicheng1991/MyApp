package com.chimu.mylib.util.bt;

import java.io.IOException;

/**
 * Created by Longwj on 2017/10/10.
 */

public class InvalidBtEncodingException extends IOException {
    public static final long serialVersionUID = -1;

    public InvalidBtEncodingException(String message) {
        super(message);
    }
}
