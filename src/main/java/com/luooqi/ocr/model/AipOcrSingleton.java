package com.luooqi.ocr.model;

import com.baidu.aip.ocr.AipOcr;
import com.luooqi.ocr.config.BdOrcConfig;

/**
 * @author tuhao
 * @date 2021/2/19 2:12 下午
 * @desc
 */
public class AipOcrSingleton {

    private static AipOcr client;

    private AipOcrSingleton() {}

    public static AipOcr getClient() {
        if (null == client) {
            synchronized(AipOcrSingleton.class) {
                if (null == client) {
                    // 初始化一个AipOcr
                    client = new AipOcr(BdOrcConfig.getAppId(),BdOrcConfig.getApiKey(),BdOrcConfig.getSecretKey());
                    // 可选：设置网络连接参数
                    client.setConnectionTimeoutInMillis(2000);
                    client.setSocketTimeoutInMillis(60000);
                    return client;
                }
            }
        }
        return client;
    }
}
