package top.arhi.util;

public abstract class StringUtils {
    public static boolean isNotBlank(String str){
        if(str==null||str.length()==0){
            return false;
        }
        for(int i=0;i<str.length();i++){
            if(!" ".equals(str.charAt(i))){
                return true;
            }
        }
        return false;
    }
}
