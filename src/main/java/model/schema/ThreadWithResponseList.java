package model.schema;

import java.util.ArrayList;

public class ThreadWithResponseList {
    private Thread thread;
    private ArrayList<Response> responseList;
    
    public ThreadWithResponseList(Thread thread, ArrayList<Response> responseList) {
        this.thread = thread;
        this.responseList = responseList;
    }
    
    public Thread getThread() {
        return thread;
    }
    public ArrayList<Response> getResponseList() {
        return responseList;
    }

}
