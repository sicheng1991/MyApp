package com.chimu.mylib.util;


import android.util.Base64;

import org.bouncycastle.util.Arrays;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES 256加密，替换：US_export_policy.jar和local_policy.jar  让jdk支持256
 *
 * Created by yangzteL on 2018/5/17 0017.
 */

public class AESUtil {

    private static byte[] iv = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'g', 'k', 'l', 'm', 'n', 'o', 'p'};

    /**
     * Encodes a String in AES-256 with a given key
     *
     * @param
     * @param
     * @param
     * @return String BBase64 and AES encoded String
     */

    public static String encode(String keyString, String stringToEncode) throws NullPointerException {

        if (keyString.length() == 0 || keyString == null) {

            throw new NullPointerException("Please give Password");

        }


        if (stringToEncode.length() == 0 || stringToEncode == null) {

            throw new NullPointerException("Please give text");

        }


        try {

            SecretKeySpec skeySpec = getKey(keyString);

            byte[] clearText = stringToEncode.getBytes("UTF8");


// IMPORTANT TO GET SAME RESULTS ON iOS and ANDROID

//            final byte[] iv = new byte[16];
//            Arrays.fill(iv, (byte) 0x00);
//
//            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);


/**

 * 这个地方调用BouncyCastleProvider

 *让java支持PKCS7Padding

 */
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

// Cipher is not thread safe

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");

            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(iv));


            String encrypedValue = Base64.encodeToString(cipher.doFinal(clearText), Base64.DEFAULT);

// Log.d("jacek", "Encrypted: " + stringToEncode + " -> " + encrypedValue);

            return encrypedValue;


        } catch (InvalidKeyException e) {

            e.printStackTrace();

        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();

        } catch (BadPaddingException e) {

            e.printStackTrace();

        } catch (NoSuchPaddingException e) {

            e.printStackTrace();

        } catch (IllegalBlockSizeException e) {

            e.printStackTrace();

        } catch (InvalidAlgorithmParameterException e) {

            e.printStackTrace();

        }

        return "";

    }


    /**
     * Decodes a String using AES-256 and BBase64
     *
     * @param
     * @param password
     * @param text
     * @return desoded String
     */

    public static String decode(String password, String text) throws NullPointerException {


        if (password.length() == 0 || password == null) {

            throw new NullPointerException("Please give Password");

        }


        if (text.length() == 0 || text == null) {

            throw new NullPointerException("Please give text");

        }


        try {

            SecretKey key = getKey(password);


// IMPORTANT TO GET SAME RESULTS ON iOS and ANDROID

//            final byte[] iv = new byte[16];

//            Arrays.fill(iv, (byte) 0x00);
//
//            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);


            byte[] encrypedPwdBytes = Base64.decode(text, Base64.DEFAULT);

// cipher is not thread safe


/**

 * 这个地方调用BouncyCastleProvider

 *让java支持PKCS7Padding

 */
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");


            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));

            byte[] decrypedValueBytes = (cipher.doFinal(encrypedPwdBytes));


            String decrypedValue = new String(decrypedValueBytes);

//Log.d(LOG_TAG, "Decrypted: " + text + " -> " + decrypedValue);

            return decrypedValue;


        } catch (InvalidKeyException e) {

            e.printStackTrace();

        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();

        } catch (BadPaddingException e) {

            e.printStackTrace();

        } catch (NoSuchPaddingException e) {

            e.printStackTrace();

        } catch (IllegalBlockSizeException e) {

            e.printStackTrace();

        } catch (InvalidAlgorithmParameterException e) {

            e.printStackTrace();

        }

        return "";

    }


    /**
     * Generates a SecretKeySpec for given password
     *
     * @param password
     * @return SecretKeySpec
     * @throws UnsupportedEncodingException
     */

    private static SecretKeySpec getKey(String password) throws UnsupportedEncodingException {


// You can change it to 128 if you wish

        int keyLength = 256;

        byte[] keyBytes = new byte[keyLength / 8];

// explicitly fill with zeros

        Arrays.fill(keyBytes, (byte) 0x0);


// if password is shorter then key length, it will be zero-padded

// to key length

        byte[] passwordBytes = password.getBytes("UTF-8");

        int length = passwordBytes.length < keyBytes.length ? passwordBytes.length : keyBytes.length;

        System.arraycopy(passwordBytes, 0, keyBytes, 0, length);

        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");

        return key;

    }
}