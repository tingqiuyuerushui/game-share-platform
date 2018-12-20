package com.mine.shortvideo.application;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import com.umeng.socialize.Config;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import io.rong.imkit.RongIM;
import timber.log.BuildConfig;
import timber.log.Timber;

public class MyApplication extends Application {
    private static MyApplication app;
    public static Context getAppContext() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        UMConfigure.setLogEnabled(true);
        UMConfigure.init(app,"5b3dc7f3f29d9821150000a1","umeng",UMConfigure.DEVICE_TYPE_PHONE,"");
//        Config.DEBUG = true;
        initPlatformConfig();
        /**
         * 仅在Debug时初始化Timber
         */
//        if (BuildConfig.DEBUG) {
//            Timber.plant(new Timber.DebugTree());
//        }else{
//            Timber.plant(new Timber.DebugTree());
//        }
        RongIM.init(this);
    }
    private void initPlatformConfig(){
        PlatformConfig.setWeixin("wxdf23590fa50d364c", "f6647b234ecae0b88de92a6cedf2a8b6");
        //豆瓣RENREN平台目前只能在服务器端配置
        PlatformConfig.setSinaWeibo("556912100", "49c810e08c4f8292392412b9720bceed", "http://www.51pepe.com");
        PlatformConfig.setYixin("yxc0614e80c9304c11b0391514d09f13bf");
        PlatformConfig.setQQZone("101511523", "03ab383d9eff82180199a23849be1618");
        PlatformConfig.setTwitter("3aIN7fuF685MuZ7jtXkQxalyi", "MK6FEYG63eWcpDFgRYw4w9puJhzDl0tyuqWjZ3M7XJuuG7mMbO");
        PlatformConfig.setAlipay("2015111700822536");
        PlatformConfig.setLaiwang("laiwangd497e70d4", "d497e70d4c3e4efeab1381476bac4c5e");
        PlatformConfig.setPinterest("1439206");
        PlatformConfig.setKakao("e4f60e065048eb031e235c806b31c70f");
        PlatformConfig.setDing("dingoalmlnohc0wggfedpk");
        PlatformConfig.setVKontakte("5764965", "5My6SNliAaLxEm3Lyd9J");
        PlatformConfig.setDropbox("oz8v5apet3arcdy", "h7p2pjbzkkxt02a");
    }

    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

}
