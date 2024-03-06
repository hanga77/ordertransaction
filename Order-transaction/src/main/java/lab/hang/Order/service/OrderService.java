package lab.hang.Order.service;

import lab.hang.Order.DTO.OrderRequests;
import lab.hang.Order.DTO.OrderResponse;


public interface OrderService {
    OrderResponse placeOrder(OrderRequests orderRequests);
}
