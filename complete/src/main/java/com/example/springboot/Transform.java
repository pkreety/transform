package com.example.springboot;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.schibsted.spt.data.jslt.Parser;
import com.schibsted.spt.data.jslt.Expression;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URL;
import java.util.HashMap;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.core.JsonParseException;

@RestController
public class Transform {

	@PostMapping(value = "/transform", produces="application/json")
	public ResponseEntity<TransformOutput> transformData1(@RequestBody String inputString) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			// transform string for jslt
			String transform = "{ \"eventId\":.eventId, \"device_os\":.device.osType}";
			// create JsonNode from String request
			JsonNode rootNode = mapper.readTree(inputString);
			Expression jslt = Parser.compileString(transform);
			JsonNode output = jslt.apply(rootNode);
			TransformOutput transformOutput = mapper.treeToValue(output, TransformOutput.class);
			return new ResponseEntity<>(transformOutput, HttpStatus.OK);
		}
		catch (JsonParseException e) { e.printStackTrace(); }
		catch (JsonMappingException e) { e.printStackTrace(); }
		catch (IOException e) { e.printStackTrace(); }

		return new ResponseEntity<>(new TransformOutput(), HttpStatus.BAD_REQUEST);
	}

}




