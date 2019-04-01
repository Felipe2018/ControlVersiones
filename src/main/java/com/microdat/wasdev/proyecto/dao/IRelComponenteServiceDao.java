package com.microdat.wasdev.proyecto.dao;

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
			) ;
}
