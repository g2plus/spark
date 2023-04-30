package cn.fiesacyum.domain.system;

public class Dept {
    private String id;
    private String deptName;
    private String parentId;
    private Integer state;
    private Dept parent;

    public Dept() {
    }

    public Dept(String id, String deptName, String parentId, Integer state, Dept parent) {
        this.id = id;
        this.deptName = deptName;
        this.parentId = parentId;
        this.state = state;
        this.parent = parent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Dept getParent() {
        return parent;
    }

    public void setParent(Dept parent) {
        this.parent = parent;
    }

    //用于测试
    @Override
    public String toString() {
        return "Dept{" +
                "id='" + id + '\'' +
                ", deptName='" + deptName + '\'' +
                ", parentId='" + parentId + '\'' +
                ", state=" + state +
                ", parent=" + parent +
                '}';
    }
}
