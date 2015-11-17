package cc.wudoumi.framework.thread;

import android.os.AsyncTask;

import cc.wudoumi.framework.interfaces.RequestListner;
import cc.wudoumi.framework.interfaces.TaskWork;

/**
 * author: qianjujun on 2015/11/17 17.
 * email:  qianjujun@imcoming.cn
 */
public class Task extends AsyncTask<Void,Void,Exception> {

    TaskWork<String> taskWork = null;

    RequestListner requestListner;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Exception doInBackground(Void... params) {
       String str =  taskWork.doWork();

        return null;
    }

    @Override
    protected void onPostExecute(Exception e) {
        super.onPostExecute(e);
    }
}
