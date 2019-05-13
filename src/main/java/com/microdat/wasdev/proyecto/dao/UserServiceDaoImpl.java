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

import com.microdat.wasdev.proyecto.model.DTOComponente;
import com.microdat.wasdev.proyecto.model.DTOEmpresa;
import com.microdat.wasdev.proyecto.model.DTOListaOpciones;
import com.microdat.wasdev.proyecto.model.DTOSolicitud;
import com.microdat.wasdev.proyecto.model.DTOUsuario;
import com.microdat.wasdev.proyecto.model.wrapper.DTOComponenteList;
import com.microdat.wasdev.proyecto.util.RoutineUtils;
//Axcel

@Service("userServiceDao")
public class UserServiceDaoImpl implements IUserServiceDao{
	
	@Autowired
	RoutineUtils routineUtils;

	//LISTAR USUARIO
	@Override
	public List<DTOUsuario> obtenerUsuarios() throws SQLException {
		
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
		return listaUsuarios;
	}

	@Override
	public DTOUsuario postUsuarioSolicitud(DTOUsuario dtoUsuario) {
	
		Connection co = null;
		PreparedStatement ps =null;
		int cantidad =0;
		Statement stm = null;
		ResultSet rs = null;
	
		System.out.println(dtoUsuario.getNombre());
		System.out.println(dtoUsuario.getCorreo());
		System.out.println(dtoUsuario.getTelefono());
		System.out.println(dtoUsuario.getEmpresa().getId());
		
			
		
		String sqlConsulta =
				"select COUNT(*) cantidadusuarios \r\n" + 
				"from usuario \r\n" + 
				"where nombre_usuario = '" + dtoUsuario.getNombre() + "'";
		
		String sql = "insert into usuario (id_empresa, nombre_usuario, " 
					+ "correo_usuario, telefono_usuario)"
					+ " values " + "(?,?,?,?)";
			
			DTOUsuario dtoUsuarioOut = new DTOUsuario();
			try {
				co = Conexion.conn();
				 ps = co.prepareStatement(sql);
				
				//Consulta Proyecto
					/*
					stm = co.createStatement();
					rs = stm.executeQuery(sqlConsulta);
						
					while (rs.next()) {
						System.out.println("La cantidad de proyectos:  " + rs.getInt("cantidadProyectos"));
						cantidad = rs.getInt("cantidadusuarios");
					
					}
				 
			//Valida duplicidad de Proyecto
			if(cantidad==0) {
				 */
				int i = 1;
				ps.setInt(i++, dtoUsuario.getEmpresa().getId());
				ps.setString(i++, dtoUsuario.getNombre());
				ps.setString(i++, dtoUsuario.getCorreo());
				ps.setString(i++, dtoUsuario.getTelefono());
				ps.executeQuery();
				System.out.println("Usuario insertado correctamente");
				
				dtoUsuarioOut.setNombre(dtoUsuario.getNombre());
				dtoUsuarioOut.setCorreo("Agregado Correctamente");
			
		/*	}else {
				System.out.println("Ya exite usuario");
				dtoUsuarioOut.setCorreo("Ya existe un Usuario con ese nombre");
			}*/
				
				ps.close();
				co.close();
			} catch (SQLException e) {
				System.out.println("Error al insertar usuario");
				e.printStackTrace();
			}
			return dtoUsuarioOut;
		}
		
		
	@Override
	public DTOUsuario actualizarUsuario(DTOUsuario dtoUsuario)  {
		String sql = "update usuario " + "set id_empresa = ? , nombre_usuario = ?, "
				+ "correo_usuario = ?, telefono_usuario = ? " 
				+ "where id_usuario = ? ";
		
		DTOUsuario usuario = new DTOUsuario();
		System.out.println("Empresa "+dtoUsuario.getEmpresa().getId());
		System.out.println("Nombre "+dtoUsuario.getNombre());
		System.out.println("correo "+dtoUsuario.getCorreo());
		System.out.println("telefono "+ dtoUsuario.getTelefono());
		System.out.println("usuario id "+dtoUsuario.getId());
		try {
			
			Connection conn = Conexion.conn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, dtoUsuario.getEmpresa().getId());
			ps.setString(2, dtoUsuario.getNombre());
			ps.setString(3, dtoUsuario.getCorreo());
			ps.setString(4, dtoUsuario.getTelefono());
			ps.setInt(5, dtoUsuario.getId());
			ps.executeUpdate();
			ps.close();
			//u = obtenerUsuario(usuario.getId());
			System.out.println("Usuario editado correctamente");
			
			ps.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("Error al editar usuario");
			e.printStackTrace();
		}
		return dtoUsuario;
	}
	
	
	@Override 
		public List<DTOEmpresa> obtenerEmpresas()  {

		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql = "select * from empresa order by id_empresa";
		List<DTOEmpresa> listaEmpresas = new ArrayList<DTOEmpresa>();
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
				listaEmpresas.add(empresa);
			}
			System.out.println("Lista de empresas obtenida correctamente");
			stm.close();
			rs.close();
			co.close();
			
		} catch (SQLException e) {
			System.out.println("Error al obtener la lista de empresas");
			e.printStackTrace();
		} 
		return listaEmpresas;
	}
	
	
	
	
	@Override
	public DTOComponenteList getComponentesSolicitud(int id,DTOComponente componente, String duplicados) {
		
		DTOComponenteList dtoComponenteList = new DTOComponenteList();
		List<DTOComponente> componentes = new ArrayList<DTOComponente>();
		
		DTOComponente dtoComponente1 = new DTOComponente();
		DTOSolicitud solicitud1 = new DTOSolicitud();
		solicitud1.setId("CodFelipe02");
		dtoComponente1.setSolicitud(solicitud1);
		
		DTOListaOpciones estado1 =new DTOListaOpciones();
		estado1.setId(15);
		dtoComponente1.setEstado(estado1);
		
		DTOComponente dtoComponente2 = new DTOComponente();
		DTOSolicitud solicitud2 = new DTOSolicitud();
		solicitud2.setId("2");
		dtoComponente2.setSolicitud(solicitud2);
		
		DTOListaOpciones estado2 =new DTOListaOpciones();
		estado2.setId(35);
		dtoComponente2.setEstado(estado2);
		
		DTOComponente dtoComponente3 = new DTOComponente();
		DTOSolicitud solicitud3 = new DTOSolicitud();
		solicitud3.setId("3");
		dtoComponente3.setSolicitud(solicitud3);
		
		DTOListaOpciones estado3 =new DTOListaOpciones();
		estado3.setId(55);
		dtoComponente3.setEstado(estado3);
		
		componentes.add(dtoComponente1);
		componentes.add(dtoComponente2);
		componentes.add(dtoComponente3);
		
		dtoComponenteList.setComponentes(componentes);
		
		return dtoComponenteList;
	}
	
	@Override
	public DTOComponenteList getComponentesSolicitudFiltrado(DTOComponente componente) {
		
		DTOComponenteList dtoComponenteList = new DTOComponenteList();
		List<DTOComponente> componentes = new ArrayList<DTOComponente>();
		
		DTOComponente dtoComponente1 = new DTOComponente();
		DTOSolicitud solicitud1 = new DTOSolicitud();
		solicitud1.setId("CodFelipe01");
		dtoComponente1.setSolicitud(solicitud1);
		
		DTOListaOpciones estado1 =new DTOListaOpciones();
		estado1.setId(45);
		dtoComponente1.setEstado(estado1);
		
		DTOComponente dtoComponente2 = new DTOComponente();
		DTOSolicitud solicitud2 = new DTOSolicitud();
		solicitud2.setId("50");
		dtoComponente2.setSolicitud(solicitud2);
		
		DTOListaOpciones estado2 =new DTOListaOpciones();
		estado2.setId(50);
		dtoComponente2.setEstado(estado2);
		
		DTOComponente dtoComponente3 = new DTOComponente();
		DTOSolicitud solicitud3 = new DTOSolicitud();
		solicitud3.setId("80");
		dtoComponente3.setSolicitud(solicitud3);
		
		DTOListaOpciones estado3 =new DTOListaOpciones();
		estado3.setId(25);
		dtoComponente3.setEstado(estado3);
		
		componentes.add(dtoComponente1);
		
		
		dtoComponenteList.setComponentes(componentes);
		
		return dtoComponenteList;
	}
	
}
