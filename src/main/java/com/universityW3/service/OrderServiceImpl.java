package com.universityW3.service;

import com.universityW3.model.Orders;
import com.universityW3.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service("ordenService")
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;

    @Override
    public void deleteOrder (Orders order) {
        orderRepository.delete(order);
    }

    @Override
    public void createOrder (Orders order) {
        orderRepository.save(order);
    }

    @Override
    public void updateOrder (Orders order) {
        orderRepository.save(order);
    }

    @Override
    public Orders findOrder (long id) {
        List<Orders> allOrders = orderRepository.findAll();
        try {
            List<Orders> listAndFind = allOrders
                    .stream()
                    .filter(a -> ((a.getId()) == id))
                    .collect(Collectors.toList());
            if (listAndFind.size() == 0) {
                return null;
            }
            return listAndFind.get(0);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace(System.out);
        }
        return null;
    }
}

