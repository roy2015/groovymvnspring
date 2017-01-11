/**
 * Created by BG244210 on 1/11/2017.
 */

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 新建java test 20170111 15:35
 */
public class TestMain {

    /**
     * 丢失精度
     */
    public static void test1(){
        double k = 18.2 + 0.18 *30;
        BigDecimal bd = new BigDecimal(k);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        double d = bd.doubleValue();
        System.out.println(k);
        System.out.println(d);
    }

    public static void main(String[] args) {
        test1();
    }
}
