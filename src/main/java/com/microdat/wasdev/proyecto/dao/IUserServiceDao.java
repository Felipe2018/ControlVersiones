package com.microdat.wasdev.proyecto.dao;



import java.sql.SQLException;
import java.util.List;

import com.microdat.wasdev.proyecto.model.DTOUsuario;



public interface IUserServiceDao {
	
	public DTOUsuario postUsuarioSolicitud(
			DTOUsuario dtoUsuario);
	
	public List<DTOUsuario> obtenerUsuarios()
			throws SQLException;
	/*
	public DTOComponente postComponenteProyectos(
			DTOComponente componente,
			DTOProyecto dtoProyecto
			) ;
	*/
}
