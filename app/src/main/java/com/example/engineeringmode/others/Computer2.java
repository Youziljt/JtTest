package com.example.engineeringmode.others;

/**
 * @anthor ljt
 * Date: 2/1/21
 * Time: 1:55 PM
 * Description:
 */
public class Computer2 {


    private String cpu;//必须
    private String ram;//必须
    private int usbCount;//可选
    private String keyboard;//可选
    private String display;//可选

    public String getCpu() {
        return cpu;
    }
    public void setCpu(String cpu) {
        this.cpu = cpu;
    }
    public String getRam() {
        return ram;
    }
    public void setRam(String ram) {
        this.ram = ram;
    }
    public int getUsbCount() {
        return usbCount;
    }
}
