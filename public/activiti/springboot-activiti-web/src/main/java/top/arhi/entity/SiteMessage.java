package top.arhi.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 站内消息
 */
@Data
public class SiteMessage implements Serializable {
    private Long id;
    private Long userid;
    /**
     * 消息类型  1-代办任务
     */
    private Integer type;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 是否已读  0- 已读  1-未读
     */
    private Integer is_read;
    private Date create_time;
    private Date update_time;
}
