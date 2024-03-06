package lab.hang.Order.repository;

import lab.hang.Order.DTO.OrderResponse;
import lab.hang.Order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<OrderResponse> findOrderByOrderTrackingNumber(String tracking);
}
