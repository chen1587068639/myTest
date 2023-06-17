package com.example.test.util;

import lombok.Data;

/**
 * 1. @description:
 * 2. @author: xh
 * 3. @time: 2022/7/14
 */
@Data
public class SMKeyPair {

    //私钥
    private String priKey;
    //公钥
    private String pubKey;

    public SMKeyPair(String priKey, String pubKey) {
        this.priKey = priKey;
        this.pubKey = pubKey;
    }
}


