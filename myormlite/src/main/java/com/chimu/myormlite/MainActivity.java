package com.example.chimu.myormlite;

import android.app.Dialog;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OrmliteHelper ormliteHelper = new OrmliteHelper(this);
        Dao testDao = ormliteHelper.getTestDao();
        AlertDialog.Builder builder  = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("确认" ) ;
        builder.setMessage("这是一个简单消息框" ) ;
        builder.setPositiveButton("是" ,  null );
        builder.show();

        try {
            testDao.create(new TestBean(18,"张三",true,"170")); //添加

            List<TestBean> list= testDao.queryForAll();
            Log.i("msggg",list.size()+"");
            for(TestBean test:list){
                Log.i("msggg",test.toString());
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            Log.e("msggg",Log.getStackTraceString(e));
        }



    }

}
class OrmliteHelper extends OrmLiteSqliteOpenHelper{
    private Dao<TestBean, Integer> testDao;

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
    public Dao<TestBean,Integer> getTestDao() throws SQLException {
        if (testDao == null) {
            try {
                testDao = getDao(TestBean.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return testDao;
    }
}
