package com.microdat.wasdev.proyecto.dao;

import java.sql.SQLException;
import com.microdat.wasdev.proyecto.model.DTOComponente;
import com.microdat.wasdev.proyecto.model.DTOProyecto;
import com.microdat.wasdev.proyecto.model.wrapper.DTOComponenteList;
import com.microdat.wasdev.proyecto.model.wrapper.DTOProyectoList;

public interface IMantenedoresServiceDao {
	
	DTOProyectoList obtenerFaseProyecto(DTOProyecto dtoProyecto) throws SQLException;

	DTOComponenteList obtenerAmbiente(DTOComponente dtoComponente) throws SQLException;
	
	DTOComponenteList obtenerFuncionalidad(DTOComponente dtoComponente) throws SQLException;
	
	DTOComponenteList obtenerGrupoFuncional(DTOComponente dtoComponente) throws SQLException;
	
}
