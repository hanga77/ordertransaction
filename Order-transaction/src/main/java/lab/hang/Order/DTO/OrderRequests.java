package lab.hang.Order.DTO;

import lab.hang.Order.entity.Order;
import lab.hang.Order.entity.Payment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
public class OrderRequests {
    private Order order;
    private Payment payment;
}
