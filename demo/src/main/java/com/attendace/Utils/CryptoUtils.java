package com.attendace.Utils;

import org.mindrot.jbcrypt.*;

public class CryptoUtils {

    // This method hashes data with a randomly generated salt with cost factor of 12. Return hashed data
    public String hash(String data){
        String hashedData = BCrypt.hashpw(data, BCrypt.gensalt(12));
        return hashedData;
    }

    // This method compares data with hashed data. Returns boolean value
    public Boolean verify(String data, String hashedData){
        return BCrypt.checkpw(data, hashedData);
    }
}
