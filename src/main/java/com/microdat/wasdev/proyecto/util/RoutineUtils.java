package com.microdat.wasdev.proyecto.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

@Service("routineUtils")
public class RoutineUtils {
	
	@Value("${spring.datasource.url}")
	private String url;
	
	@Value("${spring.datasource.username}")
	private String username;
	
	@Value("${spring.datasource.password}")
	private String password;
	
	
	public String valorDesdeFiltro(String filtro, String campo) {
		
		String[] aFilter = (filtro.replaceAll("\\((.+?)\\)", "$1")).split(","); 
		for(String variable: aFilter) {
			String[] aCampo = variable.split("==");
			if(aCampo[0].equals(campo)) {
				return aCampo[1];
			}
		}
		
		return "";
	}
	
	public ResultSet driverManagerConnection(String sqlQuery) throws SQLException {
		
		Properties props = new Properties();
        props.setProperty("user", username);
        props.setProperty("password", password);
        
        Connection conn = DriverManager.getConnection(url,props);
		PreparedStatement preStatement = conn.prepareStatement(sqlQuery);
		
		return preStatement.executeQuery();
	}
	
}
