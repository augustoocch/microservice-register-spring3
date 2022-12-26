package com.universityW3.service;

import com.universityW3.model.Orders;
import org.springframework.stereotype.Service;
import java.util.Optional;

public interface OrderService {


    public void deleteOrder(Orders orders);
    public void createOrder(Orders orders);
    public void updateOrder(Orders orders);
    public Orders findOrder(long id);
}
