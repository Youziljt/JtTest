package com.example.engineeringmode.IteratorPattern;

/**
 * @anthor ljt
 * Date: 2/24/21
 * Time: 2:22 PM
 * Description: 抽象处理者
 *
 *
 * //责任链模式的定义，使多个对象都有机会处理请求，从而避免了请求的发送者和接受者之间的耦合关系，
 * //场景：多个对象可以处理同一请求，但具体由哪个对象处理则在运行时动态决定。
 *
 * //小明出差 5万 报销单，需要逐级审批，个领导没权限问题。
 *
 *
 *
 */
abstract class Handler {

    //下一节点处理者
    protected Handler successor;


    /**
     * 请求处理条件：
     */
    abstract void handlerRequest(String condition);
}
