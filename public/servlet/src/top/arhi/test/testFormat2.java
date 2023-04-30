package top.arhi.test;

public class testFormat2 {
    public static void main(String[] args) {
        for(int i = 0 ; i < 1010 ; i++){
            String format="%0"+Integer.valueOf(1010).toString().length()+"d";
            String str=String.format(format,i+1);
            //0代表前面补零，3代表输出3位，根据需要修改即可。
            System.out.println(str);
        }
    }
}
