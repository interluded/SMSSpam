package org.example;

import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SnsException;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 5; i++) {
            String message = "Hey from AWS #" + (i + 1);
            String phoneNumber = "+1##########"; // Replace with your actual phone number, including country code

            // Create an SNS client
            SnsClient snsClient = SnsClient.builder().build();
            try {
                // Send an SMS message
                PublishRequest request = PublishRequest.builder()
                        .message(message)
                        .phoneNumber(phoneNumber)
                        .build();

                PublishResponse result = snsClient.publish(request);
                System.out.println("Message ID: " + result.messageId());

            } catch (SnsException e) {
                System.err.println("SNS Error: " + e.awsErrorDetails().errorMessage());
            } finally {
                snsClient.close();
            }
            Thread.sleep(1000);
        }
    }
}
