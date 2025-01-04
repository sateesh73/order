package com.io.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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

    private final ThreadPoolExecutor executorService;

    

    public OrderServiceImpl() {
        this.executorService = new ThreadPoolExecutor(
            2, 2, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>()
        );
    }

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
        List<Future<OrderDetails>> futureList = new ArrayList<>();
        List<ResponseOrder> responseOrders = new ArrayList<>();
        for (Long id : p_id) {
            futureList.add(executorService.submit(() -> {
                OrderDetails orderDetails = OrderDetails.builder()
                    .p_Id(id)
                    .status(Status.GENRATED.toString())
                    .message("Order generated with product Id: " + id + " waiting for payment.")
                    .build();
                return orderRepo.save(orderDetails);
            }));
        }
        for (Future<OrderDetails> future : futureList) {
            try {
                OrderDetails orderDetails = future.get();  // Wait for the task to complete 
                responseOrders.add(ResponseOrder.builder()
                .order_id(orderDetails.getOrder_id())
                .message(orderDetails.getMessage())
                .status(Status.valueOf(orderDetails.getStatus()))
                .build());
            } catch (InterruptedException | ExecutionException e) {
                // Handle exceptions
                e.printStackTrace();
            }
        }
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
