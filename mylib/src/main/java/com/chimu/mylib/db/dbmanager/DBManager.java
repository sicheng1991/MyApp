package com.chimu.mylib.db.dbmanager;

/**
 * Created by luorucai on 2017/5/4.
 */
public class DBManager {

    public static DBManager getDbManager;
    private DBManager(){}

    public static DBManager getAppManager() {
        if (getDbManager == null) {
            getDbManager = new DBManager();
        }
        return getDbManager;
    }

}
