package com.microdat.wasdev.proyecto.dao;

import java.sql.SQLException;
import com.microdat.wasdev.proyecto.model.DTOComponente;
import com.microdat.wasdev.proyecto.model.wrapper.DTOComponenteList;


public interface IComponenteServiceDao {
	
	public DTOComponente actualizarComponente(DTOComponente componente) throws SQLException;
	
	public void eliminarComponente(int id) throws SQLException;
	
	//Listar 1 componente
	public DTOComponente obtenerComponente(int id_componente, int id_ambiente) throws SQLException;
	
	//Listar componentes
	public DTOComponenteList getUbicacionComponentes(
			DTOComponente componente) ;
	
	//Insertar Componente
	public DTOComponente postUbicacion(	DTOComponente dtoComponente);
	
	
	
}
