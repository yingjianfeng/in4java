package com.in4java.ai.pojo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author: yingjf
 * @date: 2024/11/15 11:00
 * @description:
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TingwuResult implements Serializable {
    // 翻译
    private JSONObject transcription ;
    // 待办
    private JSONArray meetingAssistance ;
    // 总结
    private JSONObject summarization ;
    // 自定义
    private JSONArray customPrompt ;

}