package lab.hang.Order.service.impl;

import lab.hang.Order.DTO.OrderRequests;
import lab.hang.Order.DTO.OrderResponse;
import lab.hang.Order.entity.Order;
import lab.hang.Order.entity.Payment;
import lab.hang.Order.exception.PaymentException;
import lab.hang.Order.repository.OrderRepository;
import lab.hang.Order.repository.PaymentRepository;
import lab.hang.Order.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private PaymentRepository paymentRepository;

    public OrderServiceImpl(OrderRepository orderRepository, PaymentRepository paymentRepository) {
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional(rollbackFor = PaymentException.class)
    public OrderResponse placeOrder(OrderRequests orderRequests) {
        Order order =orderRequests.getOrder();
        order.setStatus("IN PROGRESS");
        order.setOrderTrackingNumber(UUID.randomUUID().toString());
        orderRepository.save(order);

        Payment payment = orderRequests.getPayment();
        if (!payment.getType().equals("DEBIT")){
          throw new PaymentException("Payment card type do not support");
        }

        payment.setOrderId(order.getId());
        paymentRepository.save(payment);

        order.setStatus("DONE");
        orderRepository.findOrderByOrderTrackingNumber(order.getOrderTrackingNumber());
        orderRepository.saveAndFlush(order);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderTrackingNumber(order.getOrderTrackingNumber());

        orderResponse.setStatus(order.getStatus());
        orderResponse.setMessage("SUCCESS");
        return orderResponse;
    }
}
