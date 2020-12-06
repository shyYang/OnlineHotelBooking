package edu.fudan.onlinehotelbooking.controller;
import edu.fudan.onlinehotelbooking.core.Result;
import edu.fudan.onlinehotelbooking.core.ResultGenerator;
import edu.fudan.onlinehotelbooking.entity.Example;
import edu.fudan.onlinehotelbooking.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2020/11/27.
*/
@RestController
@RequestMapping("/example")
public class ExampleController {
    @Resource
    private ExampleService exampleService;

    @PostMapping("/add")
    public Result add(Example example) {
        exampleService.save(example);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        exampleService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(Example example) {
        exampleService.update(example);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Example example = exampleService.findById(id);
        return ResultGenerator.genSuccessResult(example);
    }

    @PostMapping("/list")
    public Result list() {
        List<Example> list = exampleService.findAll();
        return ResultGenerator.genSuccessResult(list);
    }
}



