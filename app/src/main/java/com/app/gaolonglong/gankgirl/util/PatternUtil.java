package com.app.gaolonglong.gankgirl.util;

import com.app.gaolonglong.gankgirl.model.home.comment.Comments;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/6/1 0001.
 */
public class PatternUtil {
    //正则表达式去掉html标签
/*    public static List<Comments> nohtml(Comments comments){
        Pattern pattern = Pattern.compile("<a[^>]*>([^<]*)</a>");
        Matcher matcher = pattern.matcher(comments);
        List<Comments> result=new ArrayList<>();
        while(matcher.find()){
            result.add(matcher.group());
        }
        return result;
    }*/
}
