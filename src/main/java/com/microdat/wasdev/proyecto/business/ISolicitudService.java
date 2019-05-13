package com.microdat.wasdev.proyecto.business;

import java.sql.SQLException;
import java.util.List;

import com.microdat.wasdev.proyecto.model.DTOSolicitud;

public interface ISolicitudService {

	public List<DTOSolicitud> obtenerSolicitudes() throws SQLException;
	
	public DTOSolicitud getSolicitud(int id);

	public DTOSolicitud insertarSolicitud(DTOSolicitud solicitud) throws SQLException;
	
	public DTOSolicitud editarSolicitud(DTOSolicitud solicitud) throws SQLException;

}
