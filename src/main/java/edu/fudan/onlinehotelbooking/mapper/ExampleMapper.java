package edu.fudan.onlinehotelbooking.mapper;

import edu.fudan.onlinehotelbooking.core.Mapper;
import edu.fudan.onlinehotelbooking.entity.Example;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * created by 姜向阳
 * on 2020/11/27
 */
public interface ExampleMapper extends Mapper<Example> {
}
