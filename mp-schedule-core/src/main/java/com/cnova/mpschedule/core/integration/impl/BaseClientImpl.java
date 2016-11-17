package com.cnova.mpschedule.core.integration.impl;

import com.cnova.mpschedule.core.exception.MpScheduleException;
import com.cnova.mpschedule.core.integration.BaseClient;
import com.cnova.mpschedule.core.util.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class BaseClientImpl implements BaseClient {

    private HttpGet httpGet;
    private HttpClient httpClient;

    @Autowired
    private Message message;

    @Override
    public void call(String url) {
        httpClient = HttpClients.createDefault();
        httpGet = new HttpGet(url);

        try {
            HttpResponse response = httpClient.execute(httpGet);
        } catch (IOException e) {
            String erro = message.getMessage("url.call.error", url);
            throw new MpScheduleException(erro);
        }

    }
}
