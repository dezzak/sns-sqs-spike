package com.sainsburys.psr.spike.services;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.SqsResponse;

@Service
public class ListenService {

    private final SqsClient sqsClient;
    private final String queueUrlStart = "http://localhost:4566/000000000000/";

    public ListenService() throws URISyntaxException {
        sqsClient = SqsClient.builder().endpointOverride(new URI("http://localstack:4566")).build();
    }

    public void listen(String queueName) {
        ReceiveMessageRequest request = ReceiveMessageRequest.builder()
            .queueUrl(getQueueUrl(queueName))
            .waitTimeSeconds(20)
            .build();
        sqsClient.receiveMessage(request).messages().forEach(m -> handleMessage(m, queueName));

    }

    public void handleMessage(Message message, String queueName) {
        System.out.println("From: " + queueName + " " + message.messageId() + " - " + message.body());

        DeleteMessageRequest request = DeleteMessageRequest.builder()
            .queueUrl(getQueueUrl(queueName))
            .receiptHandle(message.receiptHandle())
            .build();
        sqsClient.deleteMessage(request);
    }

    private String getQueueUrl(String queueName) {
        return queueUrlStart + queueName;
    }
}
