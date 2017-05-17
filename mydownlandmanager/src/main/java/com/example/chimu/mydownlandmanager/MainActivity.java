package com.example.chimu.mydownlandmanager;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

/**
 *  参见：http://mp.weixin.qq.com/s?src=3&timestamp=1488162458&ver=1&signature=Tey0m7hC6dRXLHqd2lEFegSNJh0ghG4zitry8vAQEngNHiqftPwzzwQQETbr5B3lZEFGcgHJQQ9yvLm*-zJKu-ccS2Z*742sf2bxJhzIUUkR9l83-uJgb*i*0yNaJVD9VqcCs3-YOFpzuvn4gFxCYfNOMkt5hknZDby3NjDIsIo=
 * 2017.2.27    longwenjiang
 */

public class MainActivity extends AppCompatActivity {
    private String url = "https://nbct01.baidupcs.com/file/509b57a9f39e3d9be91ec3924437ae62?bkt=p3-1400509b57a9f39e3d9be91ec3924437ae629d79014c0000007c9696&fid=338218130-250528-784988059669337&time=1488159752&sign=FDTAXGERLBHS-DCb740ccc5511e5e8fedcff06b081203-WOvWKdLB89a24YvIAhB8oGfLw44%3D&to=67&size=8165014&sta_dx=8165014&sta_cs=19&sta_ft=zip&sta_ct=5&sta_mt=5&fm2=MH,Ningbo,Netizen-anywhere,,sichuanct&newver=1&newfm=1&secfm=1&flow_ver=3&pkey=1400509b57a9f39e3d9be91ec3924437ae629d79014c0000007c9696&sl=80412750&expires=8h&rt=pr&r=815507426&mlogid=1331373288807866540&vuk=338218130&vbdid=3676235229&fin=%E7%BD%91%E7%BB%9C%E5%97%85%E6%8E%A2%E5%99%A85.5.zip&fn=%E7%BD%91%E7%BB%9C%E5%97%85%E6%8E%A2%E5%99%A85.5.zip&rtype=1&iv=0&dp-logid=1331373288807866540&dp-callid=0.1.1&hps=1&csl=20&csign=lG0pLxhvUuna9Jk%2BZ9rvrVJG3lM%3D&by=themis";
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        Long id = downloadManager.enqueue(request);  //制指定下载任务的id
        //downloadManager.remove(REFERENCE_1, REFERENCE_2, REFERENCE_3);    //取消下载
        request.setTitle("网络嗅探器5.5");      //标题
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);   //wifi下载
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);//通知
        //创建目录
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).mkdirs();
        //设置文件存放路劲
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"网络嗅探器5.5.zip");

        Toast.makeText(getApplicationContext(), "开始下载", Toast.LENGTH_SHORT).show();
    }
}
