package com.example.engineeringmode.celuemoshi;

/**
 * @anthor ljt
 * Date: 2/23/21
 * Time: 2:57 PM
 * Description: 公交车计算价格
 */
class BusStrategy implements CalculateStrategy {


    @Override
    public int calculatePrice(int km) {

        int extraTotal = km - 10;
        int extraFactor = extraTotal / 5;
        int fraction = extraTotal % 5;
        int price = 1 + extraFactor * 1;

        return fraction > 0 ? ++price : price;
    }
}
