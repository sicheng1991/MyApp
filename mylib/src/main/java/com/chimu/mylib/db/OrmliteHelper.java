package com.chimu.mylib.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.chimu.mylib.bean.TestBean;
import com.chimu.mylib.db.dbmanager.BaseDao;
import com.chimu.mylib.db.dbmanager.BaseDaoImp;
import com.chimu.mylib.db.dbmanager.DaoImp;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OrmliteHelper extends OrmLiteSqliteOpenHelper{
//    private Dao<TestBean, Integer> testDao;
    private DaoImp testDao;
    static OrmliteHelper mOrmliteHelper;

    private Map<String,Dao> daoMap = new ConcurrentHashMap<>();
    private Map<String,BaseDaoImp> helperMap = new ConcurrentHashMap<>();


    public static OrmliteHelper getInstance(Context context) {
        if (mOrmliteHelper == null) {
            synchronized (OrmliteHelper.class) {
                if (mOrmliteHelper == null) {
                    mOrmliteHelper = new OrmliteHelper(context);
                }
            }
        }
        return mOrmliteHelper;
    }
    

    public OrmliteHelper(Context context) {
        super(context, "myText.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        Log.e("msggg","创建数据库");
        try {
            TableUtils.createTable(connectionSource, TestBean.class);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        Log.e("msggg","升级数据库");
        try {
            TableUtils.dropTable(connectionSource, TestBean.class, true);
            onCreate(sqLiteDatabase, connectionSource);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 获得carInfoDao
     *
     * @return
     * @throws SQLException
     */
    public DaoImp getTestDao() throws SQLException {
        if (testDao == null) {
            try {
                testDao =new DaoImp(TestBean.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return testDao;
    }


    /**
     * 释放资源
     */
    @Override
    public void close() {
        super.close();
        testDao = null;

        try {
            daoMap.clear();
            helperMap.clear();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public <T> Dao<T,Integer> fetchDao(Class<T> clazz) {
        Dao<T,Integer> dao = null;
        try {
            String className = clazz.getSimpleName();
            if (!daoMap.containsKey(className)) {
                dao = super.getDao(clazz);
                if(null != dao)
                    daoMap.put(className, dao);
            }
            dao = daoMap.get(className);
        }catch (Exception e){
            e.printStackTrace();
        }
        return dao;
    }

    public <T> BaseDao<T> dao(Class<T> clazz){
        String className = clazz.getSimpleName();
        if(!helperMap.containsKey(className)){
            helperMap.put(className,new BaseDaoImp<>(this,clazz));
        }
        return helperMap.get(className);
    }
}
