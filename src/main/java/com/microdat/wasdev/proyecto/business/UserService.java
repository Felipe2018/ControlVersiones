package com.microdat.wasdev.proyecto.business;


import com.microdat.wasdev.proyecto.model.DTOComponente;
import com.microdat.wasdev.proyecto.model.DTOProyecto;
import com.microdat.wasdev.proyecto.model.DTOUsuario;
import com.microdat.wasdev.proyecto.model.wrapper.DTOComponenteList;



public interface UserService {
	
	
	/*
	public DTOComponenteList getComponentesProyecto(
			DTOComponente componente);
	*/
	public DTOUsuario postUsuarioSolicitud(
			DTOUsuario dtoUsuario);
	/*
	public DTOComponente postComponenteProyectos(
			DTOComponente dtoComponente, DTOProyecto dtoProyecto);
	*/
}
