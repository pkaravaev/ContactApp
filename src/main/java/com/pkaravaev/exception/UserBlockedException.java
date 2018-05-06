package com.pkaravaev.exception;

public class UserBlockedException  extends Exception{


    /**
     * Creates User object without error description
     */
    public UserBlockedException(){
    }

    public UserBlockedException(String errDesc){
     super(errDesc);
    }
}
