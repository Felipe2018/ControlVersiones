package com.microdat.wasdev.proyecto.business;


import java.sql.SQLException;
import java.util.List;

import com.microdat.wasdev.proyecto.model.DTOComponente;
import com.microdat.wasdev.proyecto.model.DTOEmpresa;
import com.microdat.wasdev.proyecto.model.DTOProyecto;
import com.microdat.wasdev.proyecto.model.DTOUsuario;
import com.microdat.wasdev.proyecto.model.wrapper.DTOComponenteList;



public interface IUserService {
	
	
	public List<DTOUsuario> obtenerUsuarios() throws SQLException;
	
	/*public DTOUsuario postUsuarioSolicitud(
			DTOUsuario dtoUsuario);
	*/

	public List<DTOEmpresa> obtenerEmpresas();

	public DTOUsuario insertarUsuario(
			DTOUsuario usuario);

	public DTOUsuario actualizarUsuario(
			DTOUsuario usuario);

	DTOComponenteList getComponentesSolicitud(
			int id, DTOComponente componente, String duplicados);
	
	/*
	public DTOComponenteList getComponentesProyecto(
			DTOComponente componente);
	*/

	/*
	public DTOComponente postComponenteProyectos(
			DTOComponente dtoComponente, DTOProyecto dtoProyecto);
	*/
}
