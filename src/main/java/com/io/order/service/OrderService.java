package com.io.order.service;

import java.util.List;

import com.io.order.pojo.ResponseOrder;

public interface OrderService {

    ResponseOrder genrateOneOrder(Long p_id);
    List<ResponseOrder> genrateOrder(List<Long> p_id);
    ResponseOrder showOrder(Long order_id);
    List<ResponseOrder> showListOrders(List<Long> order_id);

}
