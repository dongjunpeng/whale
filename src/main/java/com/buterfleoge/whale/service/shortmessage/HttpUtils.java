package com.buterfleoge.whale.service.shortmessage;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.MapUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.springframework.util.StringUtils;

public class HttpUtils {

    public static HttpResponse doGet(String host, String path, String method, Map<String, String> headers, Map<String, String> querys)
            throws Exception {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }
        return httpClient.execute(request);
    }

    private static String buildUrl(String host, String path, Map<String, String> querys) throws UnsupportedEncodingException {
        StringBuilder urlBuilder = new StringBuilder(host);
        if (StringUtils.hasText(path)) {
            urlBuilder.append(path);
        }
        if (MapUtils.isNotEmpty(querys)) {
            StringBuilder queryBuilder = new StringBuilder();
            for (Entry<String, String> query : querys.entrySet()) {
                if (StringUtils.hasText(query.getKey()) && StringUtils.hasText(query.getValue())) {
                    queryBuilder.append(query.getKey());
                    queryBuilder.append("=");
                    queryBuilder.append(URLEncoder.encode(query.getValue(), "utf-8"));
                    queryBuilder.append("&");
                }
            }
            if (queryBuilder.length() > 0) {
                queryBuilder.deleteCharAt(queryBuilder.length() - 1);
                urlBuilder.append("?").append(queryBuilder);
            }
        }
        return urlBuilder.toString();
    }
}
