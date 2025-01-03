package com.io.order.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.io.order.entity.OrderDetails;
import com.io.order.pojo.ResponseOrder;
import com.io.order.repository.OrderRepo;
import com.io.order.util.Status;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Override
    public ResponseOrder genrateOneOrder(Long p_id) {
        OrderDetails orderDetails = OrderDetails.builder()
        .p_Id(p_id)
        .status(Status.GENRATED.toString())
        .message("order genrated with product Id: "+ p_id +" waiting for payment.")
        .build();
        OrderDetails save = orderRepo.save(orderDetails);
        return ResponseOrder.builder()
        .order_id(save.getOrder_id())
        .message(save.getMessage())
        .status(Status.valueOf(save.getStatus()))
        .build();
    }

    @Override
    public List<ResponseOrder> genrateOrder(List<Long> p_id) {
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        List<ResponseOrder> responseOrders = new ArrayList<>();
        p_id.parallelStream().forEach(id->{
            OrderDetails orderDetails = OrderDetails.builder()
            .p_Id(id)
            .status(Status.GENRATED.toString())
            .message("order genrated with product Id: "+ p_id +" waiting for payment.")
            .build();
            OrderDetails save = orderRepo.save(orderDetails);
            orderDetailsList.add(save);
        });
        orderDetailsList.forEach(order->{
            ResponseOrder responseOrder = ResponseOrder.builder()
            .order_id(order.getOrder_id())
            .message(order.getMessage())
            .status(Status.valueOf(order.getStatus()))
            .build();
            responseOrders.add(responseOrder);
        });
        return responseOrders;
    }

    @Override
    public ResponseOrder showOrder(Long order_id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showOrder'");
    }

    @Override
    public List<ResponseOrder> showListOrders(List<Long> order_id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showListOrders'");
    }

}
