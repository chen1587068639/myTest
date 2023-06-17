package com.example.test.string;

import com.example.test.util.PasswordUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @Author: chengw
 * @Date: 2023/5/4 下午2:47
 */
@SpringBootTest
public class PasswordTest {

    @Test
    public void testPassword(){
        String s = PasswordUtils.generatePassword();
        System.out.println(s);
    }

    @Test
    public void testPassword222(){
        for (int i = 0;i<100;i++){
            String s = generatePassword();
            System.out.println(s);
        }
    }


    private static final String SYMBOLS = "@#$%&+=";
    private static final String NUMBER_CHAR = "123456789";
    private static final String LETTER_STR = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";

    /**
     * 生成六位符合要求的密码。
     * 1. 密码长度至少为6位
     * 2. 必须包含至少一个数字
     * 3. 必须包含至少一个字母，不区分大小写
     * 4. 必须包含至少一个特殊字符（@#$%^&+=!）
     * 5. 特殊字符不能在密码开头和密码结尾
     *
     * @return 符合要求的密码
     */
    public static String generatePassword() {
        SecureRandom random = new SecureRandom();
        //生成中间四位的密码
        StringBuilder middlePassword = new StringBuilder(6);
        for (int i = 3; i < 6; i++) {
            String source = NUMBER_CHAR + LETTER_STR + SYMBOLS;
            middlePassword.append(source.charAt(random.nextInt(source.length())));
        }
        middlePassword.append(SYMBOLS.charAt(random.nextInt(SYMBOLS.length())));
        //生成开头结尾的密码
        String password = String.valueOf(NUMBER_CHAR.charAt(random.nextInt(NUMBER_CHAR.length()))) +
                LETTER_STR.charAt(random.nextInt(LETTER_STR.length()));
        String fp = shuffleString(password);
        String mp = shuffleString(middlePassword.toString());
        return fp.charAt(0) + mp + fp.charAt(1);
    }


    /**
     * 打乱字符串并返回新字符串。
     *
     * @param string 要打乱的字符串
     * @return 打乱后的新字符串
     */
    public static String shuffleString(String string) {
        char[] charArray = string.toCharArray();
        Random random = new Random();
        for (int i = charArray.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            char temp = charArray[index];
            charArray[index] = charArray[i];
            charArray[i] = temp;
        }
        return new String(charArray);
    }

    @Test
    public void test123(){
        boolean b = PasswordUtils.validatePassword("as11243");
        System.out.println(b);

    }


}
