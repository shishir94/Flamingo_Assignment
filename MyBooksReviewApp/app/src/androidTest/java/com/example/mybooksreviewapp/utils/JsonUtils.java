// app/src/main/java/com/example/bookreviewapp/utils/JsonUtils.java
package com.example.bookreviewapp.utils;

import android.content.Context;

import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Helper method to read a file from assets into a String.
 * (Optional; Gson can also parse from InputStreamReader directly.)
 */
public class JsonUtils {
    public static String loadJSONFromAsset(Context context, String filename) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
