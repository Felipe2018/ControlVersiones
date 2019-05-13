package com.microdat.wasdev.proyecto.dao;


import com.microdat.wasdev.proyecto.model.DTOProyecto;
import com.microdat.wasdev.proyecto.model.wrapper.DTOProyectoList;



public interface IProyectoServiceDao {

	public DTOProyectoList getProyectos(
			DTOProyecto dtoProyecto) ;
	
	public DTOProyecto getProyecto(int id) ;
	
	public DTOProyecto postProyecto(
			DTOProyecto dtoProyecto);
	
	public DTOProyecto putProyecto(
			DTOProyecto dtoProyecto);

	DTOProyectoList getProyectosFiltro(
			DTOProyecto dtoProyecto);
	
}
