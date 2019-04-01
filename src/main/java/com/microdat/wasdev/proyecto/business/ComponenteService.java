package com.microdat.wasdev.proyecto.business;

import java.sql.SQLException;
import com.microdat.wasdev.proyecto.model.DTOComponente;
import com.microdat.wasdev.proyecto.model.wrapper.DTOComponenteList;

public interface ComponenteService {

	public DTOComponente actualizarComponente(DTOComponente componente) throws SQLException;
	
	public DTOComponente obtenerComponente(int id_componente, int id_ambiente) throws SQLException;
	
	public void eliminarComponente(int id) throws SQLException;

	//Insertar Componente y Ubicacion
	public DTOComponente postUbicacion(DTOComponente dtoComponente);
	
	public DTOComponenteList getUbicacionComponentes(DTOComponente componente);
}
