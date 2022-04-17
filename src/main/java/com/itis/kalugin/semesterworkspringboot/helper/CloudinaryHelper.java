package com.itis.kalugin.semesterworkspringboot.helper;

import com.cloudinary.Cloudinary;

import java.util.HashMap;
import java.util.Map;

public class CloudinaryHelper {

    private static Cloudinary cloudinary;

    public static Cloudinary getInstance() {
        if (cloudinary == null){
            Map configMap = new HashMap();

            configMap.put("cloud_name", "dwzcudur6");
            configMap.put("api_key", "586295443946344");
            configMap.put("api_secret", "WUfk9-waoo6rXuG5O9hls9zmO3U");
            cloudinary = new Cloudinary(configMap);
        }

        return cloudinary;
    }
}
