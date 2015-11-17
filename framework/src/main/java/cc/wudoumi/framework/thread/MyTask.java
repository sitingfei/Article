package cc.wudoumi.framework.thread;

import android.os.AsyncTask;

import cc.wudoumi.framework.interfaces.RequestListner;
import cc.wudoumi.framework.net.ErrorMessage;

/**
 * author: qianjujun on 2015/11/17 16.
 * email:  qianjujun@imcoming.cn
 */
public class MyTask<T> extends AsyncTask<Void,Void,T> {

    private MyTaskItem taskItem;
    private RequestListner requestListner;
    public MyTask(MyTaskItem taskItem){
        super();
        this.taskItem = taskItem;
        this.requestListner = taskItem.getRequestListner();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(requestListner!=null){
            requestListner.onStart();
        }
    }

    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p/>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param params The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected T doInBackground(Void... params) {

        try{
            return (T) taskItem.getTaskWork().doWork();
        }catch (Exception e){

        }

        return null;
    }


    @Override
    protected void onPostExecute(T t) {
        super.onPostExecute(t);

        if(t==null){
            end(false,new ErrorMessage(ErrorMessage.TYPE_NO_DATA));
        }else{
            try {
                taskItem.getTaskWork().onSuccess(t);
                end(true,null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void end(boolean result,ErrorMessage e){
        if(requestListner!=null){
            requestListner.onEnd(result,e);
        }
    }
}
