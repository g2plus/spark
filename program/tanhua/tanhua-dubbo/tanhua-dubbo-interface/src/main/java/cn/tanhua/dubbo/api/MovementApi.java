package cn.tanhua.dubbo.api;

import cn.tanhua.model.mongo.Movement;

import java.util.List;

public interface MovementApi {

    void publish(Movement movement);

    List<Movement> getMyMovements(Long userId, int page, int pagesize);

    List<Movement> getFriendMoment(Long userId, int page, int pagesize);

    List<Movement> getMovementsRandomly(int pagesize);

    List<Movement> findMovementByPids(List<Long> pids);

    Movement findByMovementId(String movementId);
}
