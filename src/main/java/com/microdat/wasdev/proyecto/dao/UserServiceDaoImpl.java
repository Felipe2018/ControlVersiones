package com.microdat.wasdev.proyecto.dao;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.microdat.wasdev.proyecto.model.DTOUsuario;
import com.microdat.wasdev.proyecto.util.RoutineUtils;
//Axcel

@Service("userServiceDao")
public class UserServiceDaoImpl implements UserServiceDao{
	
	@Autowired
	RoutineUtils routineUtils;

	
	//Listar 1 Componente
		
		//Insertar Usuario
	@Override
	public DTOUsuario postUsuarioSolicitud(DTOUsuario dtoUsuario) {
		
		DTOUsuario dtoUsuarioOut = new DTOUsuario();
		
		dtoUsuarioOut.setId(1123);
		
		return dtoUsuarioOut;
	}

	

}
