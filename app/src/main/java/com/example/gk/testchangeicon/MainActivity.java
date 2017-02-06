package com.example.gk.testchangeicon;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * 主要测试动态修改应用的图标以及应用名称
 */
public class MainActivity extends AppCompatActivity {

    private Button btn_change_1;
    private Button btn_change_2;
    private Button btn_change_default;

    private ComponentName componentNameDefault;
    private ComponentName componentName1;
    private ComponentName componentName2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initEvent();
    }

    private void initEvent() {
        btn_change_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeIconTo1();
                showTst("修改成功.返回桌面等待几秒即可看到");
            }
        });

        btn_change_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeIconTo2();
                showTst("修改成功.返回桌面等待几秒即可看到");
            }
        });

        btn_change_default.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeIconToDefault();
                showTst("修改成功.返回桌面等待几秒即可看到");
            }
        });
    }

    private void initViews() {
        btn_change_1 = (Button) findViewById(R.id.btn_change_1);
        btn_change_2 = (Button) findViewById(R.id.btn_change_2);
        btn_change_default = (Button) findViewById(R.id.btn_change_default);
//        componentNameDefault = getComponentName();//奇怪.这么写的话 恢复默认图标的时候.竟然会从launcher上消失(测试机型:zuk z2,三星note4),而下面这种写法却不会
        componentNameDefault = new ComponentName(getApplicationContext(), "com.example.gk.testchangeicon.MainActivity");
        componentName1 = new ComponentName(getApplicationContext(), "com.example.gk.testchangeicon.Change1");
        componentName2 = new ComponentName(getApplicationContext(), "com.example.gk.testchangeicon.Change2");
    }

    private void showTst(String content) {
        Toast.makeText(getApplicationContext(), content, Toast.LENGTH_SHORT).show();
    }


    /**
     * 修改图标为1
     */
    private void changeIconTo1() {
        disableComponent(componentNameDefault);
        enableComponent(componentName1);
        disableComponent(componentName2);
    }

    /**
     * 修改图标为2
     */
    private void changeIconTo2() {
        disableComponent(componentNameDefault);
        disableComponent(componentName1);
        enableComponent(componentName2);
    }

    /**
     * 修改图标为默认
     */
    private void changeIconToDefault() {
        enableComponent(componentNameDefault);
        disableComponent(componentName1);
        disableComponent(componentName2);
    }

    /**
     * 以不杀死app的方式启用组件
     *
     * @param componentName 组件名称
     */
    private void enableComponent(ComponentName componentName) {
        PackageManager packageManager = getPackageManager();
        packageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
    }

    /**
     * 以不杀死app的方式禁用组件
     *
     * @param componentName 组件名称
     */
    private void disableComponent(ComponentName componentName) {
        PackageManager packageManager = getPackageManager();
        packageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    }
}
