package com.microdat.wasdev.proyecto.dao;


import com.microdat.wasdev.proyecto.model.DTOProyecto;
import com.microdat.wasdev.proyecto.model.wrapper.DTOProyectoList;



public interface IProyectoServiceDao {

	public DTOProyectoList getProyecto(
			DTOProyecto dtoProyecto) ;
	
	
	public DTOProyecto postProyecto(
			DTOProyecto dtoProyecto);
}
