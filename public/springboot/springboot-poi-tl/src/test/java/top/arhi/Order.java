package top.arhi;

import lombok.Data;

import java.util.List;

@Data
public class Order {
    private List<OrderDetail> orderDetailList;
    private String totalPrice;
}
