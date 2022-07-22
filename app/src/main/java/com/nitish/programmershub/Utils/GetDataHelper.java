package com.nitish.programmershub.Utils;

public class GetDataHelper {
    public static String courseAssetUrl(String courseName , String courseListItemName)
    {

        String   courseAssetUrl = "file:///android_asset/"+ courseName +"/"+ courseListItemName+".html";

        return  courseAssetUrl;

    }
}
