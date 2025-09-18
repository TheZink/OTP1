package com.attendace.dao.handlers;

import com.attendace.dao.Handler;
import com.attendace.dao.Request;

public class DefaultHandler extends Handler {

    /**
     * StarterChain acts as the entry point for the Chain of Responsibility.
     * This handler does not process any requests and always returns false.
     * Its main purpose is to provide a starting node for the chain and to delegate
     * all requests to the next handler in the chain.
     */

    @Override
    public boolean canProcess(Request request){
        setNextHandler(new Dao_user());
        return false;

    }

    @Override
    public Object process(Request request){
        return null;
    }

}
