package com.example.engineeringmode.IteratorPattern;

/**
 * @anthor ljt
 * Date: 2/24/21
 * Time: 2:30 PM
 * Description:
 */
class ConcreteHandler2 extends Handler {

    @Override
    void handlerRequest(String condition) {
        if (condition.equals("ConcreteHandler2")){
            System.out.println("ConcreteHandler2");
        }else{
            //
            successor.handlerRequest(condition);
        }
    }

}
