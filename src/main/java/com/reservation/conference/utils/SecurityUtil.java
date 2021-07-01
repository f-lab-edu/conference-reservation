package com.reservation.conference.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtil {

    /**
     * SHA-256 암호화
     * */
    public static String changePw(String str) throws Exception {

        String shaPw = "";

        try {
            //입력된 암호를 SHA256으로 암호화함.
            shaPw = getHex(genSHA256(str.getBytes()));
        } catch (Exception e) {
            shaPw = "암호화에 에러가 발생하였습니다." + e;
        }

        return shaPw;
    }

    /**
     * 바이트 배열을 SHA256 암호화하여 리턴
     */
    public static byte[] genSHA256(byte[] password) throws Exception {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // key-stretching
            for(int i = 0; i < 10000; i++) {
                String temp = getHex(password);
                md.update(temp.getBytes());	// temp 의 문자열을 해싱하여 md에 저장
                password = md.digest();		// md 객체의 다이제스트를 얻어 password를 갱신 (리턴 타입_byte)
            }

        } catch (NoSuchAlgorithmException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }

        return password;
    }

    /**
     * 바이트 값을 16진수로 변경
     */
    private static String getHex(byte[] temp) {
        StringBuilder sb = new StringBuilder();

        for(byte a : temp) {
            sb.append(String.format("%02x", a));
        }

        return sb.toString();
    }

}
