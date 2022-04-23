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
            configMap.put("api_key", "961751873561697");
            configMap.put("api_secret", "Rz-BAAmqPCLvrDHmrw59xCvMChA");
            cloudinary = new Cloudinary(configMap);
        }

        return cloudinary;
    }
}
