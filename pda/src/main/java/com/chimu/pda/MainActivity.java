package com.example.chimu.pda;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.landicorp.android.eptapi.DeviceService;
import com.landicorp.android.eptapi.exception.ReloginException;
import com.landicorp.android.eptapi.exception.RequestException;
import com.landicorp.android.eptapi.exception.ServiceOccupiedException;
import com.landicorp.android.eptapi.exception.UnsupportMultiProcess;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.login)
    Button login;
    @BindView(R.id.login_out)
    Button loginOut;
    @BindView(R.id.pf_start)
    Button pfStart;
    @BindView(R.id.pf_stop)
    Button pfStop;
    RFCardReaderSample rFCardReaderSample = new RFCardReaderSample(this) {
        @Override
        protected void displayRFCardInfo(String cardInfo) {
            Log.i("msgggggg","卡识别："+ cardInfo);
        }

        @Override
        protected void onDeviceServiceCrash() {
            Log.i("msgggggg","识别错误");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.login, R.id.login_out, R.id.pf_start, R.id.pf_stop})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                login();
                break;
            case R.id.login_out:
                loginOut();
                break;
            case R.id.pf_start:
                rFCardReaderSample.searchCard();
                break;
            case R.id.pf_stop:
                rFCardReaderSample.stopSearch();
                break;
        }
    }

    private void login() {
        try {
            DeviceService.logout();
            DeviceService.login(this);
        } catch (RequestException e) {
            e.printStackTrace();
        } catch (ServiceOccupiedException e) {
            e.printStackTrace();
        } catch (ReloginException e) {
            e.printStackTrace();
        } catch (UnsupportMultiProcess e) {
            e.printStackTrace();
        }catch (Exception e){
            Log.i("msgggggg",Log.getStackTraceString(e));
        }
    }

    private void loginOut() {
        try {
            DeviceService.logout();
        }catch (Exception e){
            Log.i("msgggggg",Log.getStackTraceString(e));
        }
    }
}
