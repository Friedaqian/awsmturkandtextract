package com.amazonaws.samples;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.ByteBuffer;

import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;

import com.amazonaws.services.textract.AmazonTextract;
import com.amazonaws.services.textract.AmazonTextractClient;
import com.amazonaws.services.textract.AmazonTextractClientBuilder;

import com.amazonaws.services.textract.model.DetectDocumentTextRequest;
import com.amazonaws.services.textract.model.DetectDocumentTextResult;
import com.amazonaws.services.textract.model.Document;

import com.amazonaws.util.IOUtils;

import com.amazonaws.auth.profile.ProfileCredentialsProvider
;



public class DetectLocalFile {
	
	
    public static void main(String[] args) throws Exception {
    	
    	File folder = new File("/Users/yinjueqian/eclipse-workspace/TextractTest/src/main/resources");
    	File[] listOfFiles = folder.listFiles();

    	for (File file : listOfFiles) {
    	    if (file.isFile() && (file.getName().endsWith("jpg")|| file.getName().endsWith("png")) ){
    	        System.out.println(file.getName());
    	        
    	        String document= "/Users/yinjueqian/eclipse-workspace/TextractTest/src/main/resources/" + file.getName();


    	    	ByteBuffer imageBytes;
    	    	try (InputStream inputStream = new FileInputStream(new File(document))) {
    	    	    imageBytes = ByteBuffer.wrap(IOUtils.toByteArray(inputStream));
    	    	}
    	    	
    	    	EndpointConfiguration endpoint = new EndpointConfiguration(
    	                "https://textract.us-east-2.amazonaws.com", "us-east-2");
    	    	
    	        
    	    	AmazonTextract client = AmazonTextractClientBuilder.standard()
    	                .withEndpointConfiguration(endpoint).withCredentials(new ProfileCredentialsProvider("profile2")).build();
    	    	
    	        
    	        DetectDocumentTextRequest request = new DetectDocumentTextRequest()
    	                .withDocument(new Document()
    	                        .withBytes(imageBytes));

    	       

    	        	DetectDocumentTextResult result = client.detectDocumentText(request);
    	        	
    	            System.out.println(result);
    	            
    	            String fname ="/Users/yinjueqian/Documents/Beekepper/output/";
    	            String out_filename = fname + file.getName() + ".json";
    	            File output = new File(out_filename);
    	            FileOutputStream fos = new FileOutputStream(output);
    	            PrintStream ps = new PrintStream(fos);
    	            System.setOut(ps);
    	            System.out.println(result);
    	            
    	    }
    	} 
    	
    	
            

       

    }
}
