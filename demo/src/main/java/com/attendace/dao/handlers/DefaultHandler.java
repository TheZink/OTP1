package com.attendace.dao.handlers;

import com.attendace.dao.Handler;
import com.attendace.dao.Request;

/**
 * The DefaultHandler class acts as the entry point for the Chain of Responsibility pattern.
 * This handler does not process any requests and always returns false in canProcess.
 * Its main purpose is to provide a starting node for the chain and to delegate
 * all requests to the next handler in the chain.
 */
public class DefaultHandler extends Handler {

    /**
     * Constructs a DefaultHandler.
     * No special initialization is required.
     */
    public DefaultHandler() {
        // Default constructor
    }

    /**
     * Determines if this handler can process the given request.
     * This implementation always returns false, indicating that
     * DefaultHandler does not process any requests.
     *
     * @param request the request to check
     * @return false, as this handler does not process requests
     */
    @Override
    public boolean canProcess(Request request){
        setNextHandler(new DaoUser());
        return false;
    }

    /**
     * Processes the given request.
     * This implementation always returns null, as this handler does not process requests.
     *
     * @param request the request to process
     * @return null, as this handler does not process requests
     */
    @Override
    public Object process(Request request){
        return null;
    }
}