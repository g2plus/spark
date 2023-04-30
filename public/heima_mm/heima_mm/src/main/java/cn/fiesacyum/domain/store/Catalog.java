package cn.fiesacyum.domain.store;

import java.util.Date;

public class Catalog {
    /*
    *  <id column="id" jdbcType="VARCHAR" property="id"/>
        <result column="name" jdbcType="VARCHAR" property="name"/>
        <result column="remark" jdbcType="VARCHAR" property="remark"/>
        <result column="state" jdbcType="VARCHAR" property="state"/>
        <result column="course_id" jdbcType="VARCHAR" property="courseId"/>
        <result column="create_time" jdbcType="TIMESTAMP" property="createTime"/>
        <association
                property="course"
                column="course_id"
                javaType="cn.fiesacyum.domain.store.Course"
                select="cn.fiesacyum.dao.store.CourseDao.findById"
        />
    * */
    private String id;
    private String name;
    private String remark;
    private String state;
    private String courseId;
    private Date createTime;

    private Course course;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    //用于测试
    @Override
    public String toString() {
        return "Catalog{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", state='" + state + '\'' +
                ", courseId='" + courseId + '\'' +
                ", createTime=" + createTime +
                ", course=" + course +
                '}';
    }
}
