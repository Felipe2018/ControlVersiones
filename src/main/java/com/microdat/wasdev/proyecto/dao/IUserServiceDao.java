package com.microdat.wasdev.proyecto.dao;



import java.sql.SQLException;
import java.util.List;

import com.microdat.wasdev.proyecto.model.DTOComponente;
import com.microdat.wasdev.proyecto.model.DTOEmpresa;
import com.microdat.wasdev.proyecto.model.DTOUsuario;
import com.microdat.wasdev.proyecto.model.wrapper.DTOComponenteList;



public interface IUserServiceDao {
	
	public DTOUsuario postUsuarioSolicitud(
			DTOUsuario dtoUsuario);

	public DTOUsuario actualizarUsuario(
			DTOUsuario dtoUsuario) ;
	
	public List<DTOUsuario> obtenerUsuarios()
			throws SQLException;
	/*
	public DTOComponente postComponenteProyectos(
			DTOComponente componente,
			DTOProyecto dtoProyecto
			) ;
	*/
		
	public List<DTOEmpresa> obtenerEmpresas()
			;
	
	
	public DTOComponenteList getComponentesSolicitudFiltrado(
			DTOComponente componente);

	public DTOComponenteList getComponentesSolicitud(
			int id, DTOComponente componente, String duplicados);


	
	
}
