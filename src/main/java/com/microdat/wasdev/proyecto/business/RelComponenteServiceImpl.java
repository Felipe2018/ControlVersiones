package com.microdat.wasdev.proyecto.business;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microdat.wasdev.proyecto.dao.IRelComponenteServiceDao;
import com.microdat.wasdev.proyecto.model.DTOComponente;
import com.microdat.wasdev.proyecto.model.DTOProyecto;
import com.microdat.wasdev.proyecto.model.wrapper.DTOComponenteList;

@Service("RelComponenteService")
public class RelComponenteServiceImpl implements IRelComponenteService {

	@Autowired
	IRelComponenteServiceDao relComponenteServiceDao;

	
	@Override
	public DTOComponenteList getComponentesProyecto(DTOComponente componente) {
		return relComponenteServiceDao.getComponentesProyecto(componente);
	}
	
	@Override
	public DTOComponenteList getComponente(DTOComponente componente) {
		return relComponenteServiceDao.getComponente(componente);
	}
	
	@Override
	public DTOComponente postComponenteProyectos(DTOComponente componente,DTOProyecto dtoProyecto) {
		return relComponenteServiceDao.postComponenteProyectos(componente,dtoProyecto);
	}
	
	@Override
	public void eliminarRelProyComponente(int id_componente, int id_proyecto) throws SQLException{
		relComponenteServiceDao.eliminarRelProyComponente(id_componente, id_proyecto);
	}
}

