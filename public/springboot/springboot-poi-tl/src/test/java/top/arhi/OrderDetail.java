package top.arhi;

import lombok.Data;

@Data
public class OrderDetail {
    private String orderId;
    private String orderDeptId;
    private String price;
}
