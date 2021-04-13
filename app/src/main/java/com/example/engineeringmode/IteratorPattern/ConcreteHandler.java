package com.example.engineeringmode.IteratorPattern;

/**
 * @anthor ljt
 * Date: 2/24/21
 * Time: 2:28 PM
 * Description:
 */
class ConcreteHandler extends Handler {


    @Override
    void handlerRequest(String condition) {
        if (condition.equals("ConcreteHandler")){
            System.out.println("ConcreteHandler");
        }else{
            //
            successor.handlerRequest(condition);
        }
    }
}
