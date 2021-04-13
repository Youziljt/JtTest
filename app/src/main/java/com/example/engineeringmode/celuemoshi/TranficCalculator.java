package com.example.engineeringmode.celuemoshi;

/**
 * @anthor ljt
 * Date: 2/23/21
 * Time: 3:12 PM
 * Description: 公交出行价格计算器
 */
class TranficCalculator {

    public static void main(String[] args) {

        TranficCalculator tranficCalculator = new TranficCalculator();
        tranficCalculator.setStrategy(new SubwayStrategy());
        System.out.println("price =="+tranficCalculator.calculatePrice(16));

    }

    CalculateStrategy calculateStrategy;
    public void setStrategy(CalculateStrategy mStrategy){
        this.calculateStrategy = mStrategy;
    }

    public int calculatePrice(int km){
        return calculateStrategy.calculatePrice(km);
    }


}
