package lab.hang.Order.controller;

import lab.hang.Order.DTO.OrderRequests;
import lab.hang.Order.DTO.OrderResponse;
import lab.hang.Order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> placeOrder (OrderRequests orderRequests){
        return  ResponseEntity.ok(orderService.placeOrder(orderRequests));
    }
}
