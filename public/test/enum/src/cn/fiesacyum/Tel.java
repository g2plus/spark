package cn.fiesacyum;

public class Tel {
    public static void main(String[] args) {
        int[] arr=new int[]{0,1,3,5,7,9};
        int[] index=new int[]{1,3,3,4,3,5,0,5,3,2,1};
        String tel="";
        for (int i : index) {
            tel+=arr[i];
        }
        System.out.println("contact me:"+tel);
    }
}
