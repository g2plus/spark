package top.arhi.dao;

import top.arhi.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderSettingDao {
    long findCountByOrderDate(Date orderDate);

    void editNumberByOrderDate(OrderSetting orderSetting);

    void add(OrderSetting orderSetting);

    List<OrderSetting> getOrderSettingByMonth(Map map);

    //根据预约日期查询预约设置信息
    OrderSetting findByOrderDate(Date orderDate);

    void editReservationsByOrderDate(OrderSetting orderSetting);
}
