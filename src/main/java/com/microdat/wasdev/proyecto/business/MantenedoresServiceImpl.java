package com.microdat.wasdev.proyecto.business;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microdat.wasdev.proyecto.dao.IMantenedoresServiceDao;
import com.microdat.wasdev.proyecto.model.DTOAmbiente;
import com.microdat.wasdev.proyecto.model.DTOComponente;
import com.microdat.wasdev.proyecto.model.DTOListaOpciones;
import com.microdat.wasdev.proyecto.model.DTOProyecto;
import com.microdat.wasdev.proyecto.model.wrapper.DTOComponenteList;
import com.microdat.wasdev.proyecto.model.wrapper.DTOProyectoList;

@Service("mantenedoresService")
public class MantenedoresServiceImpl implements IMantenedoresService {
	
	@Autowired
	private IMantenedoresServiceDao mantenedoresServiceDao;

	@Override
	public DTOProyectoList obtenerFaseProyecto(DTOProyecto dtoProyecto) throws SQLException {
		return mantenedoresServiceDao.obtenerFaseProyecto(dtoProyecto);
	}

	@Override
	public DTOComponenteList obtenerAmbiente(DTOComponente dtoComponente) throws SQLException {
		return mantenedoresServiceDao.obtenerAmbiente(dtoComponente);
	}
	
	
	 @Override
	public DTOComponenteList obtenerFuncionalidad(DTOComponente dtoComponente) throws SQLException {
		return mantenedoresServiceDao.obtenerFuncionalidad(dtoComponente);
	}
	@Override
	public DTOComponenteList obtenerGrupoFuncional(DTOComponente dtoComponente) throws SQLException {
		return mantenedoresServiceDao.obtenerGrupoFuncional(dtoComponente);
	}
	 


}
