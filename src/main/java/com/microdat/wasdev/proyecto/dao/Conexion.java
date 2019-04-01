package com.microdat.wasdev.proyecto.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	public static Connection conn() {

		Connection conn = null;

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@172.32.11.30:1521:xe", "paula", "12345");
			System.out.println("Conexión exitosa");

		} catch (SQLException | ClassNotFoundException e) {

			System.out.println("Conexión fallida: " + e);
			e.printStackTrace();
		}

		return conn;
	}

}
