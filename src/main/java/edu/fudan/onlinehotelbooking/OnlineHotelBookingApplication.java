package edu.fudan.onlinehotelbooking;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("edu.fudan.onlinehotelbooking.mapper")
public class OnlineHotelBookingApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineHotelBookingApplication.class, args);
    }

}
