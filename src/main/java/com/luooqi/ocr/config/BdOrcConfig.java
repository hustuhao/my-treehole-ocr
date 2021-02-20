package com.luooqi.ocr.config;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author tuhao
 * @date 2021/2/19 3:43 下午
 * @desc
 */
public class BdOrcConfig {
    static {
        Properties properties = getProperties("config/config.properties");
        APP_ID = (String)properties.get("bd.ocr.config.app.id");
        API_KEY = (String)properties.get("bd.ocr.config.app.key");
        SECRET_KEY = (String)properties.get("bd.ocr.config.app.secret");
    }

    /**
     * 设置APP ID/AK/SK
     */
    private static String APP_ID;
    private static String API_KEY;
    private static String SECRET_KEY;

    public static String getApiKey() {
        return API_KEY;
    }

    public static String getAppId() {
        return APP_ID;
    }

    public static String getSecretKey() {
        return SECRET_KEY;
    }

    /**
       * 读取配置文件所有信息
       *
       * @Title: getProperties_2
       * @Description: 第二种方式：使用缓冲输入流读取配置文件，然后将其加载，再按需操作
       *                    绝对路径或相对路径， 如果是相对路径，则从当前项目下的目录开始计算，
       *                  如：当前项目路径/config/config.properties,
       *                  相对路径就是config/config.properties
       *
       * @param filePath
       * @return void
       * @throws
       */
    public static Properties getProperties(String filePath){
        Properties prop = new Properties();
        try {
            // 通过输入缓冲流进行读取配置文件
            InputStream InputStream = BdOrcConfig.class.getClassLoader().getResourceAsStream("config/config.properties");
             // 加载输入流
            prop.load(InputStream);
            return prop;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop;
    }
}
