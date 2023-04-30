package cn.fiesacyum.service.store;

import cn.fiesacyum.domain.store.Course;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CourseService {
    /**
     *
     * @param course
     */
    void save(Course course);

    /**
     *
     * @param course
     */
    void delete(Course course);

    /**
     *
     * @param course
     */
    void update(Course course);

    /**
     *
     * @param id
     * @return
     */
    Course findById(String id);

    /**
     *
     * @return
     */
    List<Course> findAll();

    /**
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo findAll(int pageNum, int pageSize);
}
