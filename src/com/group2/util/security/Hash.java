package com.group2.util.security;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
    public static String sha_512(String text) throws NoSuchAlgorithmException{
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        messageDigest.update(text.getBytes());
        byte[] digestedByte = messageDigest.digest();
        return DatatypeConverter.printHexBinary(digestedByte).toLowerCase();
    }
    public static String sha_256(String text) throws NoSuchAlgorithmException{
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(text.getBytes());
        byte[] digestedByte = messageDigest.digest();
        return DatatypeConverter.printHexBinary(digestedByte).toLowerCase();
    }
    public static String sha_1(String text) throws NoSuchAlgorithmException{
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
        messageDigest.update(text.getBytes());
        byte[] digestedByte = messageDigest.digest();
        return DatatypeConverter.printHexBinary(digestedByte).toLowerCase();
    }
}
