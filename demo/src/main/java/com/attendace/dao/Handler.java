package com.attendace.dao;

/**
 * Abstract base class for handling requests in a chain of responsibility pattern.
 * Each handler can process a request or pass it to the next handler in the chain.
 */
public abstract class Handler {

    /**
     * The next handler in the chain.
     */
    protected Handler next;

    /**
     * Sets the next handler in the chain.
     *
     * @param handler the next handler to be set
     */
    public void setNextHandler(Handler handler){
        this.next = handler;
    }

    /**
     * Determines if this handler can process the given request.
     *
     * @param request the request to check
     * @return true if this handler can process the request, false otherwise
     */
    public abstract boolean canProcess(Request request);

    /**
     * Processes the given request.
     *
     * @param request the request to process
     * @return the result of processing the request
     */
    public abstract Object process(Request request);

    /**
     * Handles the request by either processing it or passing it to the next handler in the chain.
     * If no handler can process the request, returns false.
     *
     * @param request the request to handle
     * @return the result of processing the request, or false if not handled
     */
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