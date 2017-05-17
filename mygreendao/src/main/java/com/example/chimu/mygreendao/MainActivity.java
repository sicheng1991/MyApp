package com.example.chimu.mygreendao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private DaoMaster.DevOpenHelper devOpenHelper;
    private LinearLayout ll;
    private UserDao userDao;
    private int age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Greendao

        //创建初始化类DBmanager
        devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(),"User.DB",null);
        daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        daoSession = daoMaster.newSession();
        //获取dao
        userDao = daoSession.getUserDao();

        //控件监听
        ll = (LinearLayout) findViewById(R.id.ll);
        ll.getChildAt(0).setOnClickListener(this);
        ll.getChildAt(1).setOnClickListener(this);
        ll.getChildAt(2).setOnClickListener(this);
        ll.getChildAt(3).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == ll.getChildAt(0)){  //添加数据
            User user = new User(null,"张三","男",age,1.5 + (double) age / 10);
            age++;
            userDao.insert(user);
            Toast.makeText(this,"添加成功",Toast.LENGTH_SHORT).show();
        }
        if(v == ll.getChildAt(1)){  //查询数据
          //  List<User> userList = (List<User>)userDao.queryBuilder().where(UserDao.Properties.Id.between(2,5)).build().list();

            List<User> userList = (List<User>)userDao.queryBuilder().build().list();
            for (int i = 0; i < userList.size(); i++) {
                Log.d("user", "search: " + userList.get(i).toString());
            }
        }
        if(v == ll.getChildAt(2)){  //删除数据
            User user = userDao.queryBuilder().where(UserDao.Properties.Id.eq(10)).build().unique();
            if (user == null) {
                Toast.makeText(MainActivity.this, "用户不存在", Toast.LENGTH_SHORT).show();
            }else{
                userDao.deleteByKey(user.getId());
            }
        }
        if(v == ll.getChildAt(3)){  //修改数据
            User user = userDao.queryBuilder()
                    .where(UserDao.Properties.Id.ge(10), UserDao.Properties.Age.like("%9%")).build().unique();
            if (user == null) {
                Toast.makeText(MainActivity.this, "用户不存在!", Toast.LENGTH_SHORT).show();
            }else{
                user.setAge(100);
                userDao.update(user);
            }

        }
    }
}
