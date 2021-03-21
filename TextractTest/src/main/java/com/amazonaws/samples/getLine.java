package com.amazonaws.samples;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.textract.AmazonTextract;
import com.amazonaws.services.textract.AmazonTextractClientBuilder;

import software.amazon.awssdk.utils.CollectionUtils;

import java.nio.file.Files;
import com.amazonaws.services.textract.model.*;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.*;



public class getLine {

public static void getLineText(String localFile) throws IOException {

    File file = new File(localFile);
    byte[] fileContent = Files.readAllBytes(file.toPath());
    EndpointConfiguration endpoint = new EndpointConfiguration(
            "https://textract.us-east-2.amazonaws.com", "us-east-2");
	
    
	AmazonTextract client = AmazonTextractClientBuilder.standard()
            .withEndpointConfiguration(endpoint).withCredentials(new ProfileCredentialsProvider("profile2")).build();
    

    AnalyzeDocumentRequest request = new AnalyzeDocumentRequest()
        .withDocument(new Document()
            .withBytes(ByteBuffer.wrap(fileContent))).withFeatureTypes(FeatureType.FORMS);


    AnalyzeDocumentResult result = client.analyzeDocument(request);


    //Get the text blocks
    List<Block> blocks = result.getBlocks();
    
  
    

    for (Block block : blocks) {
    	
    	if ((block.getBlockType()).equals("LINE")){
           
    		
    		System.out.println(block.getText());
        
    		
          }
    }
        

  


}

//public static HashMap<String, String> getKVMapRelationship(List<Block> key_map, List<Block> value_map, List<Block> block_map) throws IOException {
//    HashMap<String, String> kvs = new HashMap<>();
//    ;
//    Block value_block;
//    String key, val = "";
//    for (Block key_block : key_map) {
//        value_block = Find_value_block(key_block, value_map);
//        key = Get_text(key_block, block_map);
//        val = Get_text(value_block, block_map);
//
//        kvs.put(key, val);
//    }
//
//    return kvs;
//
//}
//
//
//public static Block Find_value_block(Block block, List<Block> value_map) {
//    Block value_block = new Block();
//    for (Relationship relationship : block.getRelationships()) {
//        if (relationship.getType().equals("VALUE")) {
//            for (String value_id : relationship.getIds()) {
//
//                for (Block value : value_map) {
//                    if (value.getId().equals(value_id)) {
//                        value_block = value;
//                    }
//
//                }
//
//            }
//
//        }
//
//    }
//    return value_block;
//
//}


//public static void Get_text(Block result, List<Block> blocklist) throws IOException {
//    String text = "";
//    Block word2= new Block();
//    try {
//
//        if (result != null
//            && !CollectionUtils.isNullOrEmpty(result.getRelationships())) {
//
//            for (Relationship relationship : result.getRelationships()) {
//
//                if (relationship.getType().equals("CHILD")) {
//
//                    for (String id : relationship.getIds()) {
//
//                        Block word= (blocklist.stream().filter(x-> x.getId().equals(id)).findFirst().orElse(word2));
//                        
//
//                        if (word.getBlockType().equals("LINE")) {
//                            text += word.getText() + " ";
//                      } else if (word.getBlockType().equals("SELECTION_ELEMENT")) {
//
//                            if (word.getSelectionStatus().equals("NOT_SELECTED")) {
//                                text += " ";
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//    } catch (Exception e) {
//        System.out.println(e);
//    }
//    System.out.println(text);
//}

public static void main (String[]args) throws IOException {

    String fileStr = "/Users/yinjueqian/Documents/Beekepper/Sample Forms/time-off-request-form-template-30.jpg";

    getLine.getLineText(fileStr);

    System.out.println("Done!");
}
}
