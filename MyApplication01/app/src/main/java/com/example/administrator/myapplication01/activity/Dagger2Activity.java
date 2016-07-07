package com.example.administrator.myapplication01.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.administrator.myapplication01.Bean.Liu;
import com.example.administrator.myapplication01.R;

import javax.inject.Inject;

public class Dagger2Activity extends Activity {



    @Inject Liu liu;

    /**
     * dagger2 依赖注解
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2);

        initInject();


    }

    private void initInject() {
        if(liu==null){
            System.out.println("inject sucess");
        }
        // 构建Component并注入

//        this.getActivityComponent().inject(this);
//        loginPresenter.attachView(this);
//        validCodePresenter.attachView(this);
    }

    //  建议写在基类Activity里
//    protect ActivityComponent getActivityComponent(){
//        return  DaggerActivityComponent.builder()
//                .appComponent(getAppComponent())
//                .activityModule(getActivityModule())
//                .build();
//    }
//
//    //  建议写在基类Activity里
//    protect ActivityModule getActivityModule(){
//        return new ActivityModule(this);
//    }
//
//    // 建议写在MyApplication类里
//    public AppComponent getAppComponent(){
//        return DaggerAppComponent.builder()
//                .appModule(new AppModule((MyApplication)getApplicationContext()))
//                .build();
//    }

}
