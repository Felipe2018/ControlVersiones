package com.microdat.wasdev.proyecto.business;

import java.sql.SQLException;
import java.util.List;
import com.microdat.wasdev.proyecto.model.DTOAmbiente;
import com.microdat.wasdev.proyecto.model.DTOComponente;
import com.microdat.wasdev.proyecto.model.DTOListaOpciones;
import com.microdat.wasdev.proyecto.model.DTOProyecto;
import com.microdat.wasdev.proyecto.model.wrapper.DTOComponenteList;
import com.microdat.wasdev.proyecto.model.wrapper.DTOProyectoList;

public interface IMantenedoresService {
	
	public  DTOProyectoList obtenerFaseProyecto(
			DTOProyecto dtoProyecto) throws SQLException;	

	public  DTOComponenteList obtenerAmbiente(
			DTOComponente dtoComponente) throws SQLException;
	
	public  DTOComponenteList obtenerFuncionalidad(
			DTOComponente dtoComponente) throws SQLException;
	
	public  DTOComponenteList obtenerGrupoFuncional(
			DTOComponente dtoComponente) throws SQLException;



	

}
