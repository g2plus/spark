package top.arhi.test;

import java.util.ArrayList;

public class testFormat {
    private static int max = 100;
    private static ArrayList<String> list=new ArrayList<>();
    public static void main(String[] args) {
        int[] ints=new int[Integer.valueOf(max).toString().length()];
        for (int i=1;i<=max;i++){

           if(Integer.valueOf(i).toString().length()<=Integer.valueOf(max).toString().length()){

               if(Integer.valueOf(i).toString().length()==1){
                   ints = new int[Integer.valueOf(max).toString().length()];
                   for(int j=0;j<ints.length;j++){
                       ints[j]=0;
                       if(j==ints.length-1){
                           ints[j]=i;
                       }

                   }
               }
               if(Integer.valueOf(i).toString().length()==2){
                   ints = new int[Integer.valueOf(max).toString().length()];
                   for(int j=0;j<ints.length;j++){
                       if(j==0){
                           ints[j]=0;
                       }else{
                           if(j==1){
                               ints[j]=i/10;
                           }else{
                               ints[j]=i%10;
                           }
                       }
                   }
               }
               if(Integer.valueOf(i+1).toString().length()==3){
                   ints = new int[Integer.valueOf(max).toString().length()];
                   for(int j=0;j<ints.length;j++){
                       if(j==0){
                           ints[j]=i/100%10;
                       }else{
                           if(j==1){
                               ints[j]=i/10%10;
                           }else{
                               ints[j]=i%10;
                           }
                       }
                   }
               }
               String s="";
               for (int k=0;k<ints.length;k++){

                   s+=ints[k];
               }
               list.add(s);
           }
        }
        for (String s : list) {
            System.out.println(s);
        }
    }
}
