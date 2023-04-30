package top.arhi.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.arhi.pojo.Place;
import top.arhi.mapper.PlaceMapper;
import top.arhi.service.PlaceService;
import top.arhi.util.TreeUtil;

import java.util.List;

@Service
@Slf4j
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceMapper placeMapper;

    public List<Place> load(Integer pid){
        return TreeUtil.toTree(placeMapper.load(),pid,1,1);
    }


}
