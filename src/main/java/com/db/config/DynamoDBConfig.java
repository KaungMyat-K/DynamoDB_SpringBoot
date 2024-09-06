package com.db.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Configuration
public class DynamoDBConfig {

    @Value("${amazon.access.id}")
    private String awsAccessKey;

    @Value("${amazon.access.secret-key}")
    private String awsAccessSecretKey;

    @Value("${amazon.access.region}")
    private String awsAccessRegion;

    @Value("${amazon.access.endpoint-point.url}")
    private String awsAccessEndPoint;
    
    @Bean
    DynamoDBMapper dynamoDBMapper(){
        return new DynamoDBMapper(buildAmazonDynamoDb());
    }

    @Bean
    AWSCredentials awsCredentials(){
        return new BasicAWSCredentials(awsAccessKey, awsAccessSecretKey);
    }

    private AWSCredentialsProvider awsCredentialsProvider(){
        return new AWSStaticCredentialsProvider(awsCredentials());
    }

    private AmazonDynamoDB buildAmazonDynamoDb(){
        return AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(
                    new AwsClientBuilder.EndpointConfiguration(awsAccessEndPoint,awsAccessRegion)
                )
                .withCredentials(awsCredentialsProvider())
                .build();
    }



}
