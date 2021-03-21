package com.amazonaws.samples;

import java.io.IOException;

import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.mturk.AmazonMTurk;
import com.amazonaws.services.mturk.AmazonMTurkClientBuilder;

import com.amazonaws.services.mturk.model.ListHITsRequest;
import com.amazonaws.services.mturk.model.ListHITsResult;


public class ListHITs {
	
	private static final String SANDBOX_ENDPOINT = "mturk-requester-sandbox.us-east-1.amazonaws.com";
	private static final String SIGNING_REGION = "us-east-1";

	public static void main(final String[] argv) throws IOException {
		
		final ListHITs hits = new ListHITs(getSandboxClient());
		hits.listHITs();
		
	}
	
	   private final AmazonMTurk client;
	
	   private ListHITs (final AmazonMTurk client) {
		this.client = client;
	   }

		private static AmazonMTurk getSandboxClient() {
			AmazonMTurkClientBuilder builder = AmazonMTurkClientBuilder.standard();
			builder.setEndpointConfiguration(new EndpointConfiguration(SANDBOX_ENDPOINT, SIGNING_REGION));
			return builder.build();
		}
		
		private void listHITs() {
			ListHITsRequest listHITsRequest = new ListHITsRequest();
			ListHITsResult listHITsResult = client.listHITs(listHITsRequest);
			System.out.println(listHITsResult.getHITs());
			
			
		}
			
	}
	


