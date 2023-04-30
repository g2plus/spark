package cn.fiesacyum.domain.store;

import java.util.Date;

public class Course {
    /*
    * <id column="id" jdbcType="VARCHAR" property="id"/>
      <result column="name" jdbcType="VARCHAR" property="name"/>
      <result column="remark" jdbcType="VARCHAR" property="remark"/>
      <result column="state" jdbcType="VARCHAR" property="state"/>
      <result column="create_time" jdbcType="TIMESTAMP" property="createTime"/>
      TimeStamp 类型在java中可使用TimeStamp接收,也可使用Date类型接收,多态. Date
      实际存储为TimeStamp,数据库的接收类型为TimeStamp,向下转型不会出错.
    * */
    private String id;
    private String name;
    private String remark;
    private String state;
    private Date createTime;

    public Course() {
    }

    public Course(String id, String name, String remark, String state, Date createTime) {
        this.id = id;
        this.name = name;
        this.remark = remark;
        this.state = state;
        this.createTime = createTime;
    }

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    //用于测试
    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", state='" + state + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
