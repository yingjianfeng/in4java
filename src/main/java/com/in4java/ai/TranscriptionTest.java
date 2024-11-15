package com.in4java.ai;

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
import org.junit.Test;

/**
 * @author tingwu2023
 */
public class TranscriptionTest {

    @Test
    public void testFiletrans() throws ClientException {
        CommonRequest request = createCommonRequest("tingwu.cn-beijing.aliyuncs.com", "2023-09-30", ProtocolType.HTTPS, MethodType.PUT, "/openapi/tingwu/v2/tasks");
        request.putQueryParameter("type", "offline");

        JSONObject root = new JSONObject();
        root.put("AppKey", "Z8XnzpvelfMRFQSm");

        JSONObject input = new JSONObject();
        input.fluentPut("FileUrl", "http://lippi-space-zjk.cn-zhangjiakou.oss.aliyuncs.com/yundisk/hwEHAqRmaWxlA6d5dW5kaXNrBM4hNnT-Bc0F1AbN5rgHzmclRVQ.file?Expires=1731293524&OSSAccessKeyId=LTAIjmWpzHta71rc&Signature=7LaSjSOfs7LqBHHe%2F4wgzfWiWlo%3D")
                .fluentPut("SourceLanguage", "cn")
                .fluentPut("TaskKey", "task" + System.currentTimeMillis());
        root.put("Input", input);

        JSONObject parameters = new JSONObject();
        JSONObject transcription = new JSONObject();
        transcription.put("DiarizationEnabled", true);
        JSONObject speakerCount = new JSONObject();
        speakerCount.put("SpeakerCount", 2);
        transcription.put("Diarization", speakerCount);
        parameters.put("Transcription", transcription);
        root.put("Parameters", parameters);
        System.out.println(root.toJSONString());
        request.setHttpContent(root.toJSONString().getBytes(), "utf-8", FormatType.JSON);

        String ALIBABA_CLOUD_ACCESS_KEY_ID = "LTAI5t7brj3VVuVL2dMCVA91";
        String ALIBABA_CLOUD_ACCESS_KEY_SECRET = "tM6qbgJ3nU6L5WZRgBhQvuXQY2JUSq";
        // LTAI5t7brj3VVuVL2dMCVA91
        // tM6qbgJ3nU6L5WZRgBhQvuXQY2JUSq
        // TODO 请通过环境变量设置您的AccessKeyId、AccessKeySecret
        DefaultProfile profile = DefaultProfile.getProfile("cn-beijing",
                ALIBABA_CLOUD_ACCESS_KEY_ID,
                ALIBABA_CLOUD_ACCESS_KEY_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonResponse response = client.getCommonResponse(request);
        String data = response.getData();
        System.out.println(data);
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
}
