package com.microdat.wasdev.proyecto.business;

import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microdat.wasdev.proyecto.dao.IComponenteServiceDao;
import com.microdat.wasdev.proyecto.model.DTOComponente;
import com.microdat.wasdev.proyecto.model.wrapper.DTOComponenteList;


@Service("componenteService")
public class ComponenteServiceImpl implements ComponenteService {
	
	@Autowired
	private IComponenteServiceDao componenteServiceDao;

	@Override
	public DTOComponente actualizarComponente(DTOComponente componente) throws SQLException {
		// TODO Auto-generated method stub
		return componenteServiceDao.actualizarComponente(componente);
	}

	@Override
	public DTOComponenteList getUbicacionComponentes(DTOComponente componente) {
		return componenteServiceDao.getUbicacionComponentes(componente);
	}
	
	@Override
	public DTOComponente obtenerComponente(int id_componente, int id_ambiente) throws SQLException {
		// TODO Auto-generated method stub
		return componenteServiceDao.obtenerComponente(id_componente, id_ambiente);
	}

	@Override
	public void eliminarComponente(int id) throws SQLException {
		componenteServiceDao.eliminarComponente(id);
		
	}

	//Insertar componente
	@Override
	public DTOComponente postUbicacion(DTOComponente dtoComponente) {
		return componenteServiceDao.postUbicacion(dtoComponente);
		
	}
	

}
