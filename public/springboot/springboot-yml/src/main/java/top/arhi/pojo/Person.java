package top.arhi.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope("prototype")
@ConfigurationProperties(prefix="person")
public class Person {
    private String name;
    private Integer age;
    private String[] address;

    public Person() {
    }

    public Person(String name, Integer age, String[] address) {
        this.name = name;
        this.age = age;
        this.address = address;
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

    public String[] getAddress() {
        return address;
    }

    public void setAddress(String[] address) {
        this.address = address;
    }

    //@Override
    //public String toString() {
    //    return "Person{" +
    //            "name='" + name + '\'' +
    //            ", age=" + age +
    //            '}';
    //}
}
