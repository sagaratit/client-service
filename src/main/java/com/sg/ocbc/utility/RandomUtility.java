package com.sg.ocbc.utility;

import java.util.Base64;
import java.util.UUID;

public class RandomUtility {

    public static String getUUID(){
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
        return uuidAsString;
    }

    public static String decodePassword(String password){
        byte[] actualByte= Base64.getDecoder().decode(password);
        return new String(actualByte);
    }

    public static String encodePassword(String password){
        return Base64.getEncoder()
                .encodeToString(password.getBytes());
    }
}
