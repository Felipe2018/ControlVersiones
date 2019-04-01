package com.microdat.wasdev.proyecto.business;


import com.microdat.wasdev.proyecto.model.DTOProyecto;
import com.microdat.wasdev.proyecto.model.wrapper.DTOProyectoList;


public interface ProyectoService {

	
	public DTOProyectoList getProyecto(
			DTOProyecto dtoProyecto);
	
	public DTOProyecto postProyecto(
			DTOProyecto dtoProyecto);
}
