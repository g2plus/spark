package cn.tanhua.model.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user_location")
/**
 * location_index为location字段建立的索引
 */
@CompoundIndex(name = "location_index", def = "{'location': '2dsphere'}")
public class UserLocation implements Serializable {

    private static final long serialVersionUID = 4508868382007529970L;

    private ObjectId id;
    @Indexed
    private Long userId;
    /**
     * x:经度 longitude
     * y:纬度 latitude
     * GeoJsonPoint 不支持序列化
     */
    private GeoJsonPoint location;
    private String address;
    private Long created;
    private Long updated;
    private Long lastUpdated;
}