package com.example.engineeringmode.celuemoshi;

/**
 * @anthor ljt
 * Date: 2/23/21
 * Time: 3:04 PM
 * Description:
 */
class SubwayStrategy implements CalculateStrategy {

    @Override
    public int calculatePrice(int km) {
        if (km <= 6) {
            return 3;
        }
        return 8;
    }
}
