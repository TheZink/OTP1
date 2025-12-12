package com.attendace.utils;

import org.mindrot.jbcrypt.*;

/**
 * Utility class for cryptographic operations such as hashing and verifying data using BCrypt.
 */
public class CryptoUtils {

    /**
     * Hashes the provided data using BCrypt with a randomly generated salt and a cost factor of 12.
     *
     * @param data the plain text data to hash
     * @return the hashed data as a String
     */
    public String hash(String data){
        return BCrypt.hashpw(data, BCrypt.gensalt(12));
    }

    /**
     * Verifies that the provided plain text data matches the given hashed data using BCrypt.
     *
     * @param data the plain text data to verify
     * @param hashedData the previously hashed data to compare against
     * @return true if the data matches the hash, false otherwise
     */
    public Boolean verify(String data, String hashedData){
        return BCrypt.checkpw(data, hashedData);
    }
}
