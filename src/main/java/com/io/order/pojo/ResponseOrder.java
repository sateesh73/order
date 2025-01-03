package com.io.order.pojo;

import com.io.order.util.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseOrder {

    private Long order_id;
    private Status status;
    private String message; 
}
