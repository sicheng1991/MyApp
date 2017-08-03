package com.chimu.mylib.db.dbmanager;


//import com.lekusi.wubopda.common.DataBaseHelper;

import com.chimu.mylib.LibApplication;
import com.chimu.mylib.db.OrmliteHelper;

import java.sql.SQLException;

/**
 * Created by luorucai on 2017/5/5.
 */
public class DaoImp extends OrmDaoUtils {

    public DaoImp(Class cls) throws SQLException {
        super(cls);
    }

    @Override
    protected OrmliteHelper getHelper() {
        return OrmliteHelper.getInstance(LibApplication.application);
    }
}
