package com.example.khanh.foody4.upload_image;

import com.example.khanh.foody4.get_set.user_member;
import com.google.gson.JsonObject;

/**
 * Created by Khanh on 5/13/2017.
 */

public class StaticData
{

    public static final JsonObject GET_ERROR_OBJECT() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("success", false);
        jsonObject.addProperty("message", "Something went wrong");
        return jsonObject;
    }
    static user_member objectInfoUser;

    public static user_member getObjectInfoUser() {
        return objectInfoUser;
    }

    public static void setObjectInfoUser(user_member objectInfoUser) {
        StaticData.objectInfoUser = objectInfoUser;
    }
}
