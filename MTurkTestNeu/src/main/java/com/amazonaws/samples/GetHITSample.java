package com.amazonaws.samples;

import java.io.IOException;

import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.mturk.AmazonMTurk;
import com.amazonaws.services.mturk.AmazonMTurkClientBuilder;

import com.amazonaws.services.mturk.model.GetHITRequest;
import com.amazonaws.services.mturk.model.GetHITResult;


public class GetHITSample {
	
	private static final String HIT_ID = "39KV3A5D2EUE8VGOEJFVDVOI3GPS77";
	private static final String SANDBOX_ENDPOINT = "mturk-requester-sandbox.us-east-1.amazonaws.com";
	private static final String SIGNING_REGION = "us-east-1";

	public static void main(final String[] argv) throws IOException {
		
		final GetHITSample hit = new GetHITSample(getSandboxClient());
		hit.getHIT(HIT_ID);
		
	}
	
	   private final AmazonMTurk client;
	
	   private GetHITSample(final AmazonMTurk client) {
		this.client = client;
	   }

		private static AmazonMTurk getSandboxClient() {
			AmazonMTurkClientBuilder builder = AmazonMTurkClientBuilder.standard();
			builder.setEndpointConfiguration(new EndpointConfiguration(SANDBOX_ENDPOINT, SIGNING_REGION));
			return builder.build();
		}
		
		private void getHIT(final String hitId) {
			GetHITRequest getHITRequest = new GetHITRequest();
			getHITRequest.setHITId(hitId);
			GetHITResult getHITResult = client.getHIT(getHITRequest);
			System.out.println(getHITResult.getHIT().getHITStatus());
		}
			
	}
	

