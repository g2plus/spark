package top.arhi.dao.impl;

import org.springframework.stereotype.Component;
import top.arhi.dao.Room;

@Component("room2")
public class RoomImpl2 implements Room {
    public void add() {
       System.out.println("use room obj(room2)");
    }
}
