package com.alumniHelper.util;


import java.util.Date;
import java.util.Random;

/**
 * Created by eno on 15/11/11.
 */
public class GetNonce {

    public static String getKey() {
        return getKey(12);
    }

    public static String getKey(Integer size) {

        //获得当前时间的毫秒时间
        String timeSeed = new Date().getTime() + "";
        int length = timeSeed.length();
        //如果需要的长度小于时间戳长度
        int start = 0;
        if(size < length) {
            start = length - size;
        }
        timeSeed = timeSeed.substring(start, timeSeed.length());
        char[] items = timeSeed.toCharArray();
        StringBuffer result = new StringBuffer("");
        for(char item: items) {
            int itemNum = Integer.parseInt(item + "");
            result.append(numToLetter(itemNum));
        }
        if(size > length) {
            for (int i=length; i<size; i++) {
                result.append(numToLetter(null));
            }
        }
        return result.toString();
    }
//
//    public static void main(String[] args) {
//        System.out.println(getKey());
//    }

    //将传入的一个0-9的数字转换成映射的字母
    private static char numToLetter(Integer num) {
        Random random = new Random();
        if(null == num || num<0 || num >9) {
            num = random.nextInt(10);
        }
        //输出是大写字母还是小写字母
        int rm = random.nextInt(2) % 2 == 0 ? 65 : 97;
        //字母的范围 把0-9控制映射到0-25
        int letterSize = 3 * (num + 1) - 3 + random.nextInt(3) % 3;
        if(letterSize == 25 || letterSize ==26)
            letterSize = 24;
        else if(letterSize > 26)
            letterSize = 25;
        letterSize = letterSize>25?25:letterSize;
        num = rm + letterSize;
        return (char)num.intValue();
    }

}


