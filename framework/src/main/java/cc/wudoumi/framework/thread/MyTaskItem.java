package cc.wudoumi.framework.thread;

import cc.wudoumi.framework.interfaces.RequestListner;
import cc.wudoumi.framework.interfaces.TaskWork;

/**
 * author: qianjujun on 2015/11/17 16.
 * email:  qianjujun@imcoming.cn
 */
public class MyTaskItem<T> {
    private RequestListner requestListner;

    private TaskWork<T> taskWork;

    public RequestListner getRequestListner() {
        return requestListner;
    }

    public void setRequestListner(RequestListner requestListner) {
        this.requestListner = requestListner;
    }

    public TaskWork<T> getTaskWork() {
        return taskWork;
    }

    public void setTaskWork(TaskWork<T> taskWork) {
        this.taskWork = taskWork;
    }


    public MyTaskItem(RequestListner requestListner, TaskWork<T> taskWork) {
        this.requestListner = requestListner;
        this.taskWork = taskWork;
    }

    public MyTaskItem(TaskWork<T> taskWork) {
        this.taskWork = taskWork;
    }
}
