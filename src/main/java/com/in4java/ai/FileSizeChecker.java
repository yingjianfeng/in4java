package com.in4java.ai;

import java.net.URL;
import java.net.URLConnection;

/**
 * @author: yingjf
 * @date: 2024/11/20 09:43
 * @description:
 */
public class FileSizeChecker {

    public static void main(String[] args) throws Exception {
        Integer fileLength = getFileLength("http://lippi-space-sh.cn-shanghai.oss.aliyuncs.com/yundisk0/iAEIAqRmaWxlA6h5dW5kaXNrMATOIQTNFwXNEsoGzSbRB85nPHKZCM0Chg.file?Expires=1732070606&OSSAccessKeyId=LTAIjmWpzHta71rc&Signature=ugdDFvo6XW%2Fu9zuA8Rh7mCQ%2FwJQ%3D");
        System.out.println( fileLength/ 1000 +"kb");
    }

    /**
     * 获取文件大小
     * @param urlString
     * @return
     */
    public static Integer getFileLength(String urlString) {
        int size = 0;
        try {
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            // 连接到URL
            connection.connect();
            // 获取内容长度，即文件大小
            size = connection.getContentLength();
        } catch (Exception e) {

        }
        return size;
    }
}