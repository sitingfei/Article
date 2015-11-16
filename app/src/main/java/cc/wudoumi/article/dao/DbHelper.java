package cc.wudoumi.article.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * author: qianjujun on 2015/11/12 14.
 * email:  qianjujun@imcoming.cn
 */
public class DbHelper extends DaoMaster.OpenHelper{

    private static final String DB_NAME = "jiujiu";


    private static DbHelper dbHelper;

    private DaoMaster daoMaster;

    private DaoSession daoSession;


    private DbHelper(Context context) {
        super(context, DB_NAME, null);
        daoMaster = new DaoMaster(getWritableDatabase());
        daoSession = daoMaster.newSession();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        DaoMaster.dropAllTables(db, true);
        onCreate(db);
    }



    public static void init(Context context){
        if(dbHelper==null){
            dbHelper = new DbHelper(context);
        }
    }


    public static DbHelper getInstance(){
        if(dbHelper==null){
            throw new RuntimeException("请在Application的onCreat（）里执行 init");
        }
        return dbHelper;
    }


    public DaoMaster getDaoMaster() {
        return daoMaster;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
