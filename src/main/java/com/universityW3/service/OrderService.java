package com.universityW3.service;

import com.universityW3.model.Order;

import java.util.Optional;

public interface OrderService {


    public void deleteOrder(Order course);
    public void createOrder(Order course);
    public void updateOrder(Order course);
    public Optional<Order> findOrder(long id);
}
