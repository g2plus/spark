package top.arhi.thread;

import org.springframework.jdbc.core.JdbcTemplate;
import top.arhi.domain.Lottery;
import top.arhi.util.DruidUtil;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class CreateLottery implements Runnable{
    @Override
    public void run() {
        int i = 0;
        JdbcTemplate jdbcTemplate = new JdbcTemplate(DruidUtil.getDataSource());
        String sql="insert into lottery (red1,red2,red3,red4,red5,red6,blue) values (?,?,?,?,?,?,?)";
        while (i < 50000) {
            Lottery lottery = new Lottery();
            Set<Integer> red = new HashSet<>();
            while (red.size() < 6) {
                Random random = new Random();
                int randomNum = random.nextInt(33) + 1;
                red.add(randomNum);
            }
            Set<Integer> blue = new HashSet<>();
            while (blue.size() < 1) {
                Random random = new Random();
                int randomNum = random.nextInt(16) + 1;
                blue.add(randomNum);
            }
            int redIndex = -1;
            for (Integer integer : red) {
                redIndex++;
                if(redIndex==0){
                    lottery.setRed1(integer);
                }
                if(redIndex==1){
                    lottery.setRed2(integer);
                }
                if(redIndex==2){
                    lottery.setRed3(integer);
                }
                if(redIndex==3){
                    lottery.setRed4(integer);
                }
                if(redIndex==4){
                    lottery.setRed5(integer);
                }
                if(redIndex==5){
                    lottery.setRed6(integer);
                }
            }
            int blueIndex = -1;
            for(Integer integer : blue){
                blueIndex++;
                if(blueIndex==0){
                    lottery.setBlue(integer);
                }
            }
            jdbcTemplate.update(sql,lottery.getRed1(),lottery.getRed2(),lottery.getRed3(),
                    lottery.getRed4(),lottery.getRed5(),lottery.getRed6(),lottery.getBlue());
        }
    }
}
