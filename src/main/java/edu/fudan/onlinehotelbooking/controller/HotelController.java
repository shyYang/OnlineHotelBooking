package edu.fudan.onlinehotelbooking.controller;
import edu.fudan.onlinehotelbooking.core.Result;
import edu.fudan.onlinehotelbooking.core.ResultGenerator;
import edu.fudan.onlinehotelbooking.entity.Hotel;
import edu.fudan.onlinehotelbooking.entity.HotelType;
import edu.fudan.onlinehotelbooking.service.HotelService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2020/12/04.
*/
@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Resource
    private HotelService hotelService;

    @PostMapping("/sign-up")
    public Result signUp(HotelType hotel) {
        int id = hotelService.sellerSignUp(hotel);
        return ResultGenerator.genSuccessResult(id);
    }
}
