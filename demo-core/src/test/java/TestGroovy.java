import com.roy.v8.bill.ScriptService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

/**
 * Created by BG244210 on 2016/7/7.
 */
@ContextConfiguration({
        "classpath*:spring-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestGroovy {

    @Autowired
    private ScriptService scriptService;

    @Test
    public void test1() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    System.in));
            while (true) {
                // 循环获取输入，每次有输入即进行一次调用
                String line = reader.readLine();
                if ("q".equals(line)) {
                    break;
                }
                scriptService.run();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() throws InterruptedException {
        while(true){
            scriptService.run();
            TimeUnit.SECONDS.sleep(3);
        }




    }
}
