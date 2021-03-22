package com.sainsburys.psr.spike.services;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.http.HttpStatusCode;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.SnsClientBuilder;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SnsException;

@Service
public class PublishService {
    private final String topicArn = "arn:aws:sns:eu-west-1:000000000000:foo";
    private final SnsClient snsClient;

    public PublishService() throws URISyntaxException {
        snsClient = SnsClient.builder().endpointOverride(new URI("http://localstack:4566")).build();
    }

    public boolean sendMessage(String message) {
        try {
            PublishRequest request = PublishRequest.builder()
                .message(message)
                .topicArn(topicArn)
                .build();

            PublishResponse result = snsClient.publish(request);
            int status = result.sdkHttpResponse().statusCode();
            System.out.println("Message Sent - ID: " + result.messageId() + " Code: " + status);
            return status == HttpStatusCode.OK;
        } catch (SnsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            return false;
        }
    }
}
