package top.arhi;

import lombok.Data;

import java.util.List;

@Data
public class Document {
    private String yearMonth;
    private String companyCount;
    private String projectCount;
    private List<Order> orderList;
//    private List<Good> goodList;
}
