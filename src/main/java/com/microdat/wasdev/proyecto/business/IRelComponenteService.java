package com.microdat.wasdev.proyecto.business;

import java.sql.SQLException;

import com.microdat.wasdev.proyecto.model.DTOComponente;
import com.microdat.wasdev.proyecto.model.DTOProyecto;
import com.microdat.wasdev.proyecto.model.wrapper.DTOComponenteList;

public interface IRelComponenteService {

	public DTOComponenteList getComponentesProyecto(
			DTOComponente componente);
	
	public DTOComponenteList getComponente(
			DTOComponente componente);
	
	public DTOComponente postComponenteProyectos(
			DTOComponente dtoComponente, DTOProyecto dtoProyecto);
	
	public void eliminarRelProyComponente(
			int id_componente, int id_proyecto) throws SQLException;
	
}
