package edu.fudan.onlinehotelbooking.Service;

import edu.fudan.onlinehotelbooking.Tester;
import edu.fudan.onlinehotelbooking.service.ExampleService;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * created by 姜向阳
 * on 2020/11/27
 */
public class ExampleServiceTest extends Tester {

    @Resource
    private ExampleService exampleService;
    @Test
    public void test(){
        System.out.println("开始测试");
        System.out.println(exampleService.findAll());
    }
}
