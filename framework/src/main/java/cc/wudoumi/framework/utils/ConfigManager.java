package cc.wudoumi.framework.utils;

import android.app.Application;

/**
 * author: qianjujun on 2015/11/17 14.
 * email:  qianjujun@imcoming.cn
 */
public class ConfigManager {
    private static ConfigManager instance;
    private Application app;

    private ConfigManager(Application app){
        this.app = app;
    }


    public static final void init(Application app){
        if(instance==null){
            synchronized (ConfigManager.class){
                if(instance==null){
                    instance = new ConfigManager(app);
                }
            }
        }
    }


    public static final ConfigManager get(){
        if(instance==null){
            throw new RuntimeException("请先执行init");
        }
        return instance;
    }




}
