package com.microdat.wasdev.proyecto.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.microdat.wasdev.proyecto.dao.IProyectoServiceDao;
import com.microdat.wasdev.proyecto.model.DTOProyecto;
import com.microdat.wasdev.proyecto.model.wrapper.DTOProyectoList;

@Service("proyectoService")
public class ProyectoServiceImpl implements ProyectoService {

	@Autowired
	private IProyectoServiceDao proyectoServiceDao;
	
	@Override
	public DTOProyectoList getProyectos(DTOProyecto dtoProyecto) {
		return proyectoServiceDao.getProyectos(dtoProyecto);
	}

	@Override
	public DTOProyecto getProyecto(int id) {
		return proyectoServiceDao.getProyecto(id);
	}
	
	@Override
	public DTOProyecto postProyecto(DTOProyecto dtoProyecto) {
		return proyectoServiceDao.postProyecto(dtoProyecto) ;
	}

	@Override
	public DTOProyecto putProyecto(DTOProyecto dtoProyecto) {
		return proyectoServiceDao.putProyecto(dtoProyecto) ;
	}
}
