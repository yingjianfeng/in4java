package com.in4java.ai;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.in4java.ai.pojo.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yingjf
 * @date: 2024/11/14 13:48
 * @description:
 */

public class CustomPromptTest {


    String taskId = "ac0c05c3fe9d40f9bc746bd066b08eb0";
    @Test
    // https://nls-portal.console.aliyun.com/tingwu/overview
    // https://help.aliyun.com/zh/tingwu/custom-prompt     单次最多三个
    public void test() throws Exception {
        String url = "http://lippi-space-sh.cn-shanghai.oss.aliyuncs.com/yundisk0/iAEIAqRmaWxlA6h5dW5kaXNrMATOIT_LigXNEs0GzRKhB85nNb9XCM0CnQ.file?Expires=1731644954&OSSAccessKeyId=LTAIjmWpzHta71rc&Signature=s269AVOOyoD7ApHdbrsOzi%2FdU%2Bo%3D";
        JSONArray contents = new JSONArray()

                //.fluentAdd(prompt("projectBackground","总结此次录音,客户方提及的使用目的或项目背景，尽量20字之内"))
                .fluentAdd(prompt("currentIssues","{Transcription} 总结此次录音,客户方提及的目前问题，尽量50字之内"))
                .fluentAdd(prompt("preferences","{Transcription} 总结此次录音,客户方提及的客户偏好，尽量50字之内"))
                //.fluentAdd(prompt("competitorSituation","{Transcription} 总结此次录音,客户方提及的其他提供商的情况，尽量50字之内"))
                //.fluentAdd(prompt("budget","{Transcription} 总结此次录音,客户方提及的预算情况，尽量50字之内"))
                //.fluentAdd(prompt("priceSensitivity","{Transcription} 总结此次录音,客户方提及的价格敏感信息，尽量50字之内"))
                .fluentAdd(prompt("deliveryTime","{Transcription} 提及的有关交付时间信息，尽量50字之内"))

                ;
        String data = testFiletrans(url, contents);
        System.out.println(data);
    }

    @Test
    public void test_all() throws Exception {
        //String taskId = "3dad97fda0ba46d180cba0525b7145a6";
        String transcription = transcription(taskId);
        JSONArray meetingAssistance = meetingAssistance(taskId);
        JSONObject summarization = summarization(taskId);
        JSONArray customPrompt = customPrompt(taskId);
        TingwuResult result = new TingwuResult();
        result.setTranscription(transcription.substring(0,100)+"....");
        result.setMeetingAssistance(meetingAssistance);
        result.setSummarization(summarization);
        result.setCustomPrompt(customPrompt);

        System.out.println(JSONObject.toJSONString(result));
    }


    @Test
    public void test2() throws Exception {
       // String taskId = "61cc11b18cd64d6cb2932d9306bb52ad";
        String data = getTaskInfo(taskId);
        System.out.println(data);
    }

    @Test
    public void test3() throws Exception {
        //String taskId = "61cc11b18cd64d6cb2932d9306bb52ad";
        System.out.println(transcription(taskId));
    }


    /**
     * 生成待办
     * @date 2024/11/15 10:28
     * @author yingjf
     * @param
     */
    @Test
    public void test4() throws Exception {
        //String taskId = "3dad97fda0ba46d180cba0525b7145a6";
        System.out.println(meetingAssistance(taskId).toJSONString());
    }

    @Test
    public void test5()throws Exception{
        System.out.println(summarization(taskId));
    }
    @Test
    public void test6()throws Exception{
        System.out.println(customPrompt(taskId));
    }

    // 自定义
    public JSONArray customPrompt(String taskId)throws Exception{
        String data = getTaskInfo(taskId);
        String url = JSONObject.parseObject(data).getJSONObject("Data")
                .getJSONObject("Result").getString("CustomPrompt");
        String str = OkHttpUtil.get(url);
        JSONObject jsonObject = JSONObject.parseObject(str);
        String customPrompt = jsonObject.getString("CustomPrompt");

        List<CustomPromptItem> customPromptItems = JSONArray.parseArray(customPrompt, CustomPromptItem.class);
        JSONArray ans = new JSONArray();
        ans.addAll(customPromptItems);
        return ans;
    }

    // 总结
    public JSONObject summarization(String taskId) throws Exception{
        String data = getTaskInfo(taskId);
        String url = JSONObject.parseObject(data).getJSONObject("Data")
                .getJSONObject("Result").getString("Summarization");
        String str = OkHttpUtil.get(url);
        JSONObject jsonObject = JSONObject.parseObject(str);
        JSONObject summarization = jsonObject.getJSONObject("Summarization");
        // 摘要
        String paragraphSummary = summarization.getString("ParagraphSummary");
        // 标题
        String paragraphTitle = summarization.getString("ParagraphTitle");
        JSONArray conversationalSummary = summarization.getJSONArray("ConversationalSummary");
        List<ConversationalSummaryItem> conversationalSummaryItems = JSONArray.parseArray(conversationalSummary.toJSONString(), ConversationalSummaryItem.class);

        JSONArray questionsAnsweringSummary = summarization.getJSONArray("QuestionsAnsweringSummary");
        List<QuestionsAnsweringSummaryItem> questionsAnsweringSummaryItem = JSONArray.parseArray(questionsAnsweringSummary.toJSONString(), QuestionsAnsweringSummaryItem.class);


        JSONObject ans = new JSONObject();
        ans.put("paragraphSummary", paragraphSummary);
        ans.put("paragraphTitle", paragraphTitle);
        ans.put("conversationalSummary", conversationalSummaryItems);
        ans.put("questionsAnsweringSummary", questionsAnsweringSummaryItem);
        return ans;
    }

    // 翻译
    public String transcription(String taskId ) throws Exception{
        String data = getTaskInfo(taskId);
        System.out.println(data);
        String url = JSONObject.parseObject(data).getJSONObject("Data")
                .getJSONObject("Result").getString("Transcription");
        String str = OkHttpUtil.get(url);
        JSONObject jsonObject = JSONObject.parseObject(str);
        JSONArray jsonArray = jsonObject.getJSONObject("Transcription").getJSONArray("Paragraphs");
        JSONArray ans = new JSONArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject parsedObject = JSONObject.parseObject(jsonArray.get(i).toString());
            StringBuilder sb = new StringBuilder();
//            sb.append("SpeakerId" +parsedObject.getString("SpeakerId")+"     ");
            JSONArray words = parsedObject.getJSONArray("Words");
            for (int j = 0; j < words.size(); j++) {
                String word = JSONObject.parseObject(words.get(j).toString()).getString("Text");
                sb.append(word);
            }
            ans.add(sb.toString());
        }
        return ans.toJSONString();
    }

    // 待办
    public JSONArray meetingAssistance(String taskId)throws Exception{
        String data = getTaskInfo(taskId);
        String url = JSONObject.parseObject(data).getJSONObject("Data")
                .getJSONObject("Result").getString("MeetingAssistance");
        String str = OkHttpUtil.get(url);
        JSONObject jsonObject = JSONObject.parseObject(str);
        JSONArray jsonArray = jsonObject.getJSONObject("MeetingAssistance").getJSONArray("Actions");
        JSONArray ans = new JSONArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject parsedObject = JSONObject.parseObject(jsonArray.get(i).toString());
            String word = parsedObject.getString("Text");
            ans.add(word);
        }
        return ans;
    }

    public String testFiletrans(String url, JSONArray contents) throws ClientException {
        CommonRequest request = createCommonRequest("tingwu.cn-beijing.aliyuncs.com", "2023-09-30", ProtocolType.HTTPS, MethodType.PUT, "/openapi/tingwu/v2/tasks");
        request.putQueryParameter("type", "offline");
        JSONObject root = new JSONObject();
        root.put("AppKey", APPKEY);

        JSONObject input = new JSONObject();
        input.fluentPut("FileUrl", url).fluentPut("SourceLanguage", "cn").fluentPut("TaskKey", "task" + System.currentTimeMillis());
        root.put("Input", input);

        JSONObject parameters = new JSONObject();

        JSONObject transcription = new JSONObject();
        transcription.put("DiarizationEnabled", true);
        JSONObject speakerCount = new JSONObject();
        speakerCount.put("SpeakerCount", 0);
        transcription.put("Diarization", speakerCount);
        parameters.put("Transcription", transcription);

        // 是否启用智能纪要功能，开启后会生成关键词、重点内容、待办等结果。
        parameters.put("MeetingAssistanceEnabled", true);
        JSONObject meetingAssistance = new JSONObject();
        List list = new ArrayList();
        list.add("Actions");   // 待办
        //list.add("KeyInformation");
        meetingAssistance.put("Types",list);
        parameters.put("MeetingAssistance", meetingAssistance);

        // 是否启用摘要功能，开启后会可以生成全文摘要、发言人总结等结果
        parameters.put("SummarizationEnabled", true);
        JSONObject summarization = new JSONObject();
        List types = new ArrayList();
        types.add("Paragraph");
        types.add("Conversational");
        types.add("QuestionsAnswering");
        summarization.put("Types",types);
        parameters.put("Summarization", summarization);

        // 是否启用自定义 Prompt 功能。
        if (contents != null) {
            parameters.put("CustomPromptEnabled", true);
            JSONObject customPrompt = new JSONObject();
            customPrompt.put("Contents", contents);
            parameters.put("CustomPrompt", customPrompt);
        }

        root.put("Parameters", parameters);

        request.setHttpContent(root.toJSONString().getBytes(), "utf-8", FormatType.JSON);
        DefaultProfile profile = DefaultProfile.getProfile("cn-beijing", ALIBABA_CLOUD_ACCESS_KEY_ID, ALIBABA_CLOUD_ACCESS_KEY_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonResponse response = client.getCommonResponse(request);
        return response.getData();
    }

    public String getTaskInfo(String taskId) throws ClientException {
        String queryUrl = String.format("/openapi/tingwu/v2/tasks" + "/%s", taskId);
        CommonRequest request = createCommonRequest("tingwu.cn-beijing.aliyuncs.com", "2023-09-30", ProtocolType.HTTPS, MethodType.GET, queryUrl);
        DefaultProfile profile = DefaultProfile.getProfile("cn-beijing", ALIBABA_CLOUD_ACCESS_KEY_ID, ALIBABA_CLOUD_ACCESS_KEY_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonResponse response = client.getCommonResponse(request);
        return response.getData();
    }

    public static CommonRequest createCommonRequest(String domain, String version, ProtocolType protocolType, MethodType method, String uri) {
        // 创建API请求并设置参数
        CommonRequest request = new CommonRequest();
        request.setSysDomain(domain);
        request.setSysVersion(version);
        request.setSysProtocol(protocolType);
        request.setSysMethod(method);
        request.setSysUriPattern(uri);
        request.setHttpContentType(FormatType.JSON);
        return request;
    }

    public static JSONObject prompt(String name,String prompt){
        return new JSONObject().fluentPut("Name", name).fluentPut("Prompt", prompt)
                .fluentPut("Model","tingwu-turbo") .fluentPut("TransType","chat");
    }



}



