package com.microdat.wasdev.proyecto.dao;



import com.microdat.wasdev.proyecto.model.DTOUsuario;



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
