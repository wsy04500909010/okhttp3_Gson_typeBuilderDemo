package com.italkbb.imetistest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.italkbb.imetis.SDKManager;
import com.italkbb.imetis.bean.KuaiDiReal;
import com.italkbb.imetis.bean.KuaidiInfo;
import com.italkbb.imetis.bean.Province;
import com.italkbb.imetis.constant.ConstantUrls;
import com.italkbb.imetis.okhttp3.client.RequestManager;
import com.italkbb.imetis.okhttp3.exception.OkHttpException;
import com.italkbb.imetis.okhttp3.listener.ProcessorListener;
import com.italkbb.imetis.okhttp3.model.Response;
import com.italkbb.imetis.util.AppUtil;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btn_version, btn_http1, btn_http2;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SDKManager.init(this);
        RequestManager.init(new HashMap<String, String>(), true);

        btn_version = findViewById(R.id.btn_version);
        btn_http1 = findViewById(R.id.btn_http1);
        btn_http2 = findViewById(R.id.btn_http2);
        tv = findViewById(R.id.tv);


        btn_version.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText(AppUtil.getAppVersionName(MainActivity.this));
            }
        });

        btn_http1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData1();
            }
        });
        btn_http2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData2();
            }
        });
    }

    public void getData1() {

        RequestManager.get(ConstantUrls.DISTRICT, null, new ProcessorListener() {
            @Override
            public void onSuccess(Object responseObj) {
                tv.setText(((List<Province>) responseObj).get(0).getId() + "---" + ((List<Province>) responseObj).get(0).getName());
            }

            @Override
            public void onFailure(OkHttpException e) {
                Log.e("error", e.getEMsg().toString());

            }
        }, Province.class);

    }

    public void getData2() {
        RequestManager.get(ConstantUrls.KUAIDI2, null, new ProcessorListener() {
            @Override
            public void onSuccess(Object responseObj) {
                List<KuaiDiReal> dataList = (List<KuaiDiReal>) ((Response) responseObj).data;
                if (dataList.size() > 0) {

                    tv.setText(dataList.get(0).getContext());
                } else {
                    tv.setText("暂无物流数据");

                }
            }

            @Override
            public void onFailure(OkHttpException e) {
                Log.e("error", e.getEMsg().toString());

            }
        }, KuaiDiReal.class);
    }

}
