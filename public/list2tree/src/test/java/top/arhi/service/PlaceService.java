package top.arhi.service;

import top.arhi.pojo.Place;

import java.util.List;

public interface PlaceService {
    List<Place> load(Integer pid);
}
