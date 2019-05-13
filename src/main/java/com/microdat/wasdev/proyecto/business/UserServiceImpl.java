package com.microdat.wasdev.proyecto.business;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microdat.wasdev.proyecto.dao.IUserServiceDao;
import com.microdat.wasdev.proyecto.model.DTOComponente;
import com.microdat.wasdev.proyecto.model.DTOEmpresa;
import com.microdat.wasdev.proyecto.model.DTOUsuario;
import com.microdat.wasdev.proyecto.model.wrapper.DTOComponenteList;
import com.microdat.wasdev.proyecto.util.RoutineUtils;


@Service("userService")
public class UserServiceImpl implements IUserService{
	
	@Autowired
	IUserServiceDao userServiceDao;
	
	@Autowired
	RoutineUtils routineUtils;

	@Override
	public List<DTOUsuario> obtenerUsuarios() throws SQLException {
		/*
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
		//String sql = "select * from usuario order by id_usuario";
		String sql = "select u.id_usuario, u.nombre_usuario, u.correo_usuario, u.telefono_usuario, u.id_empresa, "
				+ "e.razon_social, e.direccion_empresa, e.telefono_empresa "
				+ "from usuario u "
				+ "inner join empresa e on u.id_empresa = e.id_empresa "
				+ "order by u.id_usuario";
		
		List<DTOUsuario> listaUsuarios = new ArrayList<DTOUsuario>();

		try {
			co = Conexion.conn();
			stm = co.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				DTOEmpresa empresa = new DTOEmpresa();
				empresa.setId(rs.getInt("id_empresa"));
				empresa.setRazonSocial(rs.getString("razon_social"));
				empresa.setDireccion(rs.getString("direccion_empresa"));
				empresa.setTelefono(rs.getString("telefono_empresa"));
				DTOUsuario usuario = new DTOUsuario();
				usuario.setId(rs.getInt("id_usuario"));
				usuario.setEmpresa(empresa);
				usuario.setNombre(rs.getString("nombre_usuario"));
				usuario.setCorreo(rs.getString("correo_usuario"));
				usuario.setTelefono(rs.getString("telefono_usuario"));
				listaUsuarios.add(usuario);
			}
			System.out.println("Lista de usuarios obtenida correctamente");
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error al obtener la lista de usuarios");
			e.printStackTrace();
		}
		return listaUsuarios;*/
		return userServiceDao.obtenerUsuarios();
	}

	@Override
	public List<DTOEmpresa> obtenerEmpresas() {
		// TODO Auto-generated method stub
		return userServiceDao.obtenerEmpresas();
	}

	@Override
	public DTOUsuario insertarUsuario(DTOUsuario dtoUsuario) {
		// TODO Auto-generated method stub
		return userServiceDao.postUsuarioSolicitud(dtoUsuario);
	}

	@Override
	public DTOUsuario actualizarUsuario(DTOUsuario dtoUsuario) {
		// TODO Auto-generated method stub
		return userServiceDao.actualizarUsuario(dtoUsuario);
				
	}
	//Axell
	@Override
	public DTOComponenteList getComponentesSolicitud(int id, DTOComponente componente, String duplicados) {
		// TODO Auto-generated method stub
		/*String duplicados = StringUtils.EMPTY;
		duplicados = routineUtils.valorDesdeFiltro(filter, "duplicados");*/
		System.out.println("duplicado: "+duplicados);
		
		if(duplicados.equals("1")) {
			System.out.println("Envio correcto filtro duplicado");
			return null;
		}
		
		else {
			System.out.println("Envio correcto sin filtro");
			return userServiceDao.getComponentesSolicitud(id,componente,duplicados);	
		}
		
		
	}
	
	/*
		@Override
		public DTOComponenteList getComponentesSolicitud(int id,DTOComponente componente) {
			
			return userServiceDao.getComponentesSolicitud(id,componente);
			
		}*/


	
}
