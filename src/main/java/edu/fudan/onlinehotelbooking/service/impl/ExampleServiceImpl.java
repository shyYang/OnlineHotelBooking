package edu.fudan.onlinehotelbooking.service.impl;

import edu.fudan.onlinehotelbooking.mapper.ExampleMapper;
import edu.fudan.onlinehotelbooking.entity.Example;
import edu.fudan.onlinehotelbooking.service.ExampleService;
import edu.fudan.onlinehotelbooking.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/11/28.
 */
@Service
@Transactional
public class ExampleServiceImpl extends AbstractService<Example> implements ExampleService {
    @Resource
    private ExampleMapper exampleMapper;

}
