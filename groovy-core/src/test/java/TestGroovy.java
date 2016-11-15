import com.roy.v8.bill.MyBasicScript;
import com.roy.v8.bill.ScriptService;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.runtime.powerassert.SourceText;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.BufferedReader;
import java.io.IOException;
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

    /*@Test
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
    }*/

    @Test
    //测试远程grovvy脚本注入spring
    public void test2() throws InterruptedException {
        while(true){
            scriptService.run();
            TimeUnit.SECONDS.sleep(3);
        }
    }

    @Test
    //测试java调用groovy脚本文件
    public void test3() throws InterruptedException {
        try {
            String[] paths = {"D:/test/"};
            GroovyScriptEngine gse = new GroovyScriptEngine(paths);

        Binding binding = new Binding();
        Object[] path = {"E:/downloadpkgs"};
        binding.setVariable("args",path);
        gse.run("ListFile.groovy", binding);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ResourceException e) {
            e.printStackTrace();
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4(){
        CompilerConfiguration cfg = new CompilerConfiguration();
//        cfg.setScriptBaseClass();
        cfg.setScriptBaseClass(MyBasicScript.class.getName());
        GroovyShell shell = new GroovyShell(cfg);

//        Script script = shell.parse("(pow(3,2)+8 )% 3");
        Script script1 = shell.parse("  500/(8-3)-2*2-1-custOp1(3.0,2)+8%3");
        Script script = shell.parse("  (3+5) >2 || (1==1)");
        System.out.println(script.run());
        System.out.println(script1.run().getClass());
    }

    @Test
    public void test5(){
        long begin =0;
        CompilerConfiguration cfg = new CompilerConfiguration();
        GroovyShell shell = new GroovyShell(cfg);
        Script script;
        for (int i=0; i<1; i++) {
            begin =System.currentTimeMillis();

            script = shell.parse("  0.34 * 120.0");
            System.out.println(script.run());
            System.out.println(script.run().getClass());
            System.out.println(System.currentTimeMillis()  - begin);
        }
    }

    @Test
    public void test6() throws javax.script.ScriptException {
        long begin =0;
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine nashorn = scriptEngineManager.getEngineByName("JavaScript");
        for (int i=0; i<1; i++) {
            begin =System.currentTimeMillis();

            System.out.println("\n"+nashorn.eval("  0.34 * 120.0"));
            System.out.println(nashorn.eval("  0.34 * 120.0").getClass());
            System.out.println(System.currentTimeMillis()  - begin);
        }
    }

    @Test
    public void test7() throws javax.script.ScriptException {
        test5();
        test6();
    }

}
