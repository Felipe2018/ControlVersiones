package com.microdat.wasdev.proyecto.dao;

import com.microdat.wasdev.proyecto.model.DTOComponente;
import com.microdat.wasdev.proyecto.model.DTOProyecto;
import com.microdat.wasdev.proyecto.model.DTOUsuario;
import com.microdat.wasdev.proyecto.model.wrapper.DTOComponenteList;


public interface UserServiceDao {
	
	/*
	public DTOComponenteList getComponentesProyecto(
			DTOComponente componente) ;
	*/
	
	public DTOUsuario postUsuarioSolicitud(
			DTOUsuario dtoUsuario);
	/*
	public DTOComponente postComponenteProyectos(
			DTOComponente componente,
			DTOProyecto dtoProyecto
			) ;
	*/
}
