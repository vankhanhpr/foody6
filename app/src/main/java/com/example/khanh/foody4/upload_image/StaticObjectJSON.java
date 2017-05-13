package com.example.khanh.foody4.upload_image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.google.gson.JsonObject;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

/**
 * Created by Khanh on 5/13/2017.
 */

public class StaticObjectJSON {
    public static JsonObject createImageInputObject(String path) {
        JsonObject outputObject = null;

        Bitmap myBitmap = BitmapFactory.decodeFile(path);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
        byte[] byteArray = bos.toByteArray();


        String str = Base64.encodeToString(byteArray, Base64.NO_WRAP);


        outputObject = new JsonObject();

        outputObject.addProperty("userid", StaticData.getObjectInfoUser().getUser_Name());
        outputObject.addProperty("id", UUID.randomUUID().toString());
        outputObject.addProperty("image", str);

        return outputObject;
    }
}