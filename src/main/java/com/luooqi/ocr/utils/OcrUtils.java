package com.luooqi.ocr.utils;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.StaticLog;
import com.luooqi.ocr.model.AipOcrSingleton;
import com.luooqi.ocr.model.TextBlock;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * tools-ocr
 * Created by 何志龙 on 2019-03-22.
 */
public class OcrUtils {

    public static String ocrImg(byte[] imgData) {
        StaticLog.info("-------ocrImg------");
        return bdGeneralOcr(imgData);
    }

    private static String bdGeneralOcr(byte[] imgData){
        HashMap<String, String> options = new HashMap<>();
        options.put("language_type", "CHN_ENG");
        options.put("paragraph", "true");
        org.json.JSONObject jsonObject = AipOcrSingleton.getClient().basicGeneral(imgData, options);
        JSONObject data = JSONUtil.parseFromMap(jsonObject.toMap());
        return extractBdGeneralOcrResult(data);
    }


    private static String extractBdGeneralOcrResult(JSONObject data) {
        JSONArray jsonArray = data.getJSONArray("words_result");
        List<TextBlock> textBlocks = new ArrayList<>();
        boolean isEng = false;
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jObj = jsonArray.getJSONObject(i);
            TextBlock textBlock = new TextBlock();
            textBlock.setText(jObj.getStr("words") + '\n');
            //noinspection SuspiciousToArrayCall
            int top = 100;
            int left = 100;
            int width = 100;
            int height = 100;
            textBlock.setTopLeft(new Point(top, left));
            textBlock.setTopRight(new Point(top, left + width));
            textBlock.setBottomLeft(new Point(top + height, left));
            textBlock.setBottomRight(new Point(top + height, left + width));
            textBlocks.add(textBlock);
        }
        return CommUtils.combineTextBlocks(textBlocks, isEng);
    }
}
