package top.arhi.domain;

/**
 * @author Milo
 * @description
 * @date 2021/5/18 14:37
 */

public class Student {

    private String id;
    private String name;
    private Integer age;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Student() {

    }

    public Student(String name, Integer age) {

        this.name = name;
        this.age = age;
    }

    public Student(String id, String name, Integer age) {
        this.id = id;

        this.name = name;
        this.age = age;
    }
}
