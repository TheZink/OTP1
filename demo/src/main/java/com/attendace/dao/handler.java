package com.attendace.dao;

public abstract class Handler {

    protected Handler next;

    public void setNextHandler(Handler handler){
        this.next = handler;
    }

    public abstract boolean canProcess(Request request);
    public abstract Object process(Request request);
    
    public Object handle(Request request){
        
        // If handler can process the request, pass it to the handler
        if (canProcess(request)) {
            return process(request);
        }
        
        // Move to the next handler, if previous handler return false
        else if (next != null){
            return next.handle(request);
        }
        
        else {
            return false;
        }      
    }
}
