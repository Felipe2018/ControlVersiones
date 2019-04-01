package com.microdat.wasdev.proyecto.util;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.microdat.wasdev.proyecto.util.model.ErrorDefinition;

@Service("businessServiceException")
public class BusinessServiceException {

    public ResponseEntity<?> getError (String alias, String... messageParameters){
    	
    	ErrorDefinition getErrorDefinition = getErrorDefinition(alias); 
    	String mensaje = getErrorDefinition.getMessage();
    	
    	for(int i=0; i < messageParameters.length; i++) {
    		mensaje = mensaje.replace("{"+i+"}", messageParameters[i]);
    	}
    	
    	return new ResponseEntity<>(new CustomErrorType(mensaje),getErrorDefinition.getHttpStatus());
    }
    
    public ErrorDefinition getErrorDefinition(String alias) {
    	
    	ErrorDefinition errorDefinition = new ErrorDefinition();
    	
    	try {
			File fXmlFile = new ClassPathResource("errors.xml").getFile();

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("error-definition");
			
			for (int temp = 0; temp < nList.getLength(); temp++) {
				
				Node nNode = nList.item(temp);
				
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					
					Element eElement = (Element) nNode;
					
					if(alias.equals(eElement.getAttribute("alias"))) {
						errorDefinition.setAlias(eElement.getAttribute("alias"));
						errorDefinition.setSeverity(eElement.getAttribute("severity"));
						errorDefinition.setHttpStatus(HttpStatus.valueOf(Integer.parseInt(eElement.getAttribute("http-status"))));
						errorDefinition.setMessage(eElement.getElementsByTagName("message").item(0).getTextContent());
						break;
						
					}	
				}
			}
			
		} catch (IOException | ParserConfigurationException | SAXException e) {
			errorDefinition.setSeverity("ERROR");
			errorDefinition.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			errorDefinition.setMessage("ErrorDefinitionException");
		}
    	
    	return errorDefinition;
    }

}
