package com.microdat.wasdev.proyecto.dao;

import java.sql.SQLException;

import com.microdat.wasdev.proyecto.model.DTOComponente;
import com.microdat.wasdev.proyecto.model.DTOProyecto;
import com.microdat.wasdev.proyecto.model.wrapper.DTOComponenteList;

public interface IRelComponenteServiceDao {

	public DTOComponenteList getComponentesProyecto(
			DTOComponente componente) ;
	
	public DTOComponenteList getComponente(
			DTOComponente componente) ;
	
	public DTOComponente postComponenteProyectos(
			DTOComponente componente,
			DTOProyecto dtoProyecto
			);
	
	public void eliminarRelProyComponente(
			int id_componente, int id_proyecto
			) throws SQLException;

	DTOComponenteList getComponentesProyectoFirltrar(
			DTOComponente componente);
	
}
