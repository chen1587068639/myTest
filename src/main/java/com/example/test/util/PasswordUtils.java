package com.example.test.util;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @Author: chengw
 * @Date: 2023/5/4 下午2:45
 */

public class PasswordUtils {

    private static final String SYMBOLS = "@#$%^&+=";
    private static final String DIGITS = "0123456789";
    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 生成符合要求的密码。
     *
     * @return 符合要求的密码
     */
    public static String generatePassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();
        password.append(generateRandomCharacter(DIGITS));
        password.append(generateRandomCharacter(LETTERS));
        password.append(generateRandomCharacter(SYMBOLS));
        for (int i = 0; i < 3; i++) {
            password.append(generateRandomCharacter(DIGITS + LETTERS + SYMBOLS));
        }
        return password.toString();
    }

    /**
     * 从指定的字符集合中生成一个随机字符。
     *
     * @param characters 字符集合
     * @return 生成的随机字符
     */
    private static char generateRandomCharacter(String characters) {
        SecureRandom random = new SecureRandom();
        int index = random.nextInt(characters.length());
        return characters.charAt(index);
    }
//
//    // 重置历史密码的方法
//    public static void resetPasswords(User[] users) {
//        for (User user : users) {
//            String newPassword = generateRandomPassword(6);
//            user.setPassword(newPassword);
//            sendPasswordBySMS(user.getPhoneNumber(), newPassword);
//        }
//        notifyResetPassword();
//    }

    // 发送密码到手机的方法
    public static void sendPasswordBySMS(String phoneNumber, String password) {
        // 调用短信接口，将密码发送到指定手机号码
    }

    /**
     * 密码长度至少为6位。
     * 必须包含至少一个数字（0-9）。
     * 必须包含至少一个字母（不区分大小写）。
     * 必须包含至少一个特殊字符@#$%^&,.+=!
     * @param password
     * @return
     */
    public static boolean validatePassword(String password) {
        String pattern = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{6,}$";
        return password.matches(pattern);
    }





    // 通知密码重置的方法
    public static void notifyResetPassword() {
        // 在企业微信群进行通知
    }
}
