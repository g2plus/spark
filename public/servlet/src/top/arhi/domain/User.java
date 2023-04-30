package top.arhi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private Integer age;
    private Date time;

    public String getTime(Date dob){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年mm月dd日 HH:mm:ss");
        return sdf.format(dob);
    }
}
