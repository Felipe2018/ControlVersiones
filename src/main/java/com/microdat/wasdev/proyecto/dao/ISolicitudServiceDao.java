package com.microdat.wasdev.proyecto.dao;

import java.sql.SQLException;
import java.util.List;

import com.microdat.wasdev.proyecto.model.DTOSolicitud;

public interface ISolicitudServiceDao {
	
	public List<DTOSolicitud> obtenerSolicitudes() throws SQLException;
	
	public DTOSolicitud insertarSolicitud(DTOSolicitud solicitud) throws SQLException;
	
	public DTOSolicitud editarSolicitud(DTOSolicitud solicitud) throws SQLException;



}
