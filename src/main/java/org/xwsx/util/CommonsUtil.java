package org.xwsx.util;

public class CommonsUtil {
    public static boolean isEmpty(String s){
        if(s == null || "".equals(s)){
            return true;
        }
        return false;
    }
}
