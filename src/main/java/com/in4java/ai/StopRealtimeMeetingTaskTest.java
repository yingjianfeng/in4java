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
 * @desc 演示了通过OpenAPI 结束实时会议 的调用方式。
 */
public class StopRealtimeMeetingTaskTest {

    @Test
    public void stopTask() throws ClientException {
        CommonRequest request = createCommonRequest("tingwu.cn-beijing.aliyuncs.com", "2023-09-30", ProtocolType.HTTPS, MethodType.PUT, "/openapi/tingwu/v2/tasks");
        request.putQueryParameter("type", "realtime");
        // 必须设置 operation=stop
        request.putQueryParameter("operation", "stop");

        JSONObject root = new JSONObject();
        JSONObject input = new JSONObject();
        input.put("TaskId", "e59d3c47e936427282f74923378c546a");
        root.put("Input", input);
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
        System.out.println(response.getData());
    }

    public static CommonRequest createCommonRequest(String domain, String version, ProtocolType protocolType, MethodType method, String uri) {
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
