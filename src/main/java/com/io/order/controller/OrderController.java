package com.io.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.io.order.pojo.ResponseOrder;
import com.io.order.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/save")
    public ResponseEntity<ResponseOrder> saveOne(@RequestBody Long p_id) {
        return new ResponseEntity<ResponseOrder>(orderService.genrateOneOrder(p_id), HttpStatus.OK);
    }

    @PostMapping("/saveAll")
    public ResponseEntity<List<ResponseOrder>> saveAll(@RequestBody List<Long> p_id) {
        return new ResponseEntity<List<ResponseOrder>>(orderService.genrateOrder(p_id), HttpStatus.OK);
    }
    

}
