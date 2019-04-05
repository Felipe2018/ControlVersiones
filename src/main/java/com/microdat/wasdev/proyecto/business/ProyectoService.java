package com.microdat.wasdev.proyecto.business;


import com.microdat.wasdev.proyecto.model.DTOProyecto;
import com.microdat.wasdev.proyecto.model.wrapper.DTOProyectoList;


public interface ProyectoService {

	
	public DTOProyectoList getProyectos(
			DTOProyecto dtoProyecto);
	
	public DTOProyecto getProyecto(int id);
	
	public DTOProyecto postProyecto(
			DTOProyecto dtoProyecto);

	public DTOProyecto putProyecto(
			DTOProyecto dtoProyecto);
}
