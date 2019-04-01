package com.microdat.wasdev.proyecto.business;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microdat.wasdev.proyecto.dao.UserServiceDao;
import com.microdat.wasdev.proyecto.model.DTOComponente;
import com.microdat.wasdev.proyecto.model.DTOProyecto;
import com.microdat.wasdev.proyecto.model.DTOUsuario;
import com.microdat.wasdev.proyecto.model.wrapper.DTOComponenteList;


@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserServiceDao userServiceDao;

	
/*
	@Override
	public DTOComponenteList getComponentesProyecto(DTOComponente componente) {
		return userServiceDao.getComponentesProyecto(componente);
	}*/

	@Override
	public DTOUsuario postUsuarioSolicitud(DTOUsuario dtoUsuario) {
		return userServiceDao.postUsuarioSolicitud(dtoUsuario);
	}

	/*
	@Override
	public DTOComponente postComponenteProyectos(DTOComponente componente,DTOProyecto dtoProyecto) {
		return userServiceDao.postComponenteProyectos(componente,dtoProyecto);
	}*/
}
