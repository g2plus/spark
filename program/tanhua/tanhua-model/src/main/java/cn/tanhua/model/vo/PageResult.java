package cn.tanhua.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/***
 * 页面数据封装
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult implements Serializable {

    /**
     * 总记录数
     */
    private Long counts = 0L;
    /**
     * 页大小(前端控制)
     */
    private Integer pagesize;
    /**
     * 总页数
     */
    private Long pages = 0L;
    /**
     * 当前页码（前端控制）
     */
    private Integer page;
    /**
     * 列表
     */
    private List<?> items = Collections.emptyList();

    public PageResult(Integer page,Integer pagesize,
                      Long counts,List list) {
        this.page = page;
        this.pagesize = pagesize;
        this.counts = counts;
        this.items = list;
        this.pages =  counts % pagesize == 0 ? counts / pagesize : counts / pagesize + 1;
    }
}
