package com.microdat.wasdev.proyecto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.microdat.api.dao.Conexion;
import com.microdat.wasdev.proyecto.model.DTOAmbiente;
import com.microdat.wasdev.proyecto.model.DTOComponente;
import com.microdat.wasdev.proyecto.model.DTOListaOpciones;
import com.microdat.wasdev.proyecto.model.DTOProyecto;
import com.microdat.wasdev.proyecto.model.DTOReglaConflicto;
import com.microdat.wasdev.proyecto.model.DTOSolicitud;
import com.microdat.wasdev.proyecto.model.DTOUbicacion;
import com.microdat.wasdev.proyecto.model.DTOUsuario;
import com.microdat.wasdev.proyecto.model.wrapper.DTOComponenteList;
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
