package com.example.engineeringmode.others;

/**
 * @anthor ljt
 * Date: 2/1/21
 * Time: 1:52 PM
 * Description:
 */
public class Computer1 {
    private String cpu;//必须
    private String ram;//必须
    private int usbCount;//可选
    private String keyboard;//可选
    private String display;//可选

    public Computer1(String cpu, String ram) {
        this(cpu, ram, 0);
    }
    public Computer1(String cpu, String ram, int usbCount) {
        this(cpu, ram, usbCount, "罗技键盘");
    }
    public Computer1(String cpu, String ram, int usbCount, String keyboard) {
        this(cpu, ram, usbCount, keyboard, "三星显示器");
    }
    public Computer1(String cpu, String ram, int usbCount, String keyboard, String display) {
        this.cpu = cpu;
        this.ram = ram;
        this.usbCount = usbCount;
        this.keyboard = keyboard;
        this.display = display;
    }

}
