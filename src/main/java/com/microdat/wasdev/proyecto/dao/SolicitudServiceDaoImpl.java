package com.microdat.wasdev.proyecto.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.microdat.wasdev.proyecto.model.DTOComponente;
import com.microdat.wasdev.proyecto.model.DTOEmpresa;
import com.microdat.wasdev.proyecto.model.DTOListaOpciones;
import com.microdat.wasdev.proyecto.model.DTOProyecto;
import com.microdat.wasdev.proyecto.model.DTOSolicitud;
import com.microdat.wasdev.proyecto.model.DTOUsuario;

@Service("solicitudServiceDao")
public class SolicitudServiceDaoImpl implements ISolicitudServiceDao {

	
	
	@Override
	public List<DTOSolicitud> obtenerSolicitudes() throws SQLException {

		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql = 
				"select s.nro_solicitud, s.fecha_estimada_entrega, s.fecha_real_entrega, "
				+ "s.descripcion_solicitud, s.comentario_solicitud, s.fecha_emision_solicitud, "
				+ "s.id_estado_solicitud, es.id_estado_solicitud, es.nombre_estado_solicitud, "
				+ "s.id_usuario, u.id_usuario, u.nombre_usuario, u.correo_usuario, u.telefono_usuario, "
				+ "u.id_empresa, e.id_empresa, e.razon_social, e.direccion_empresa, e.telefono_empresa "
				+ "from solicitud s "  
				+ "inner join estado_solicitud es on es.id_estado_solicitud = s.id_estado_solicitud " 
				+ "inner join usuario u on u.id_usuario = s.id_usuario " 
				+ "inner join empresa e on e.id_empresa = u.id_empresa " 
				+ "order by s.fecha_emision_solicitud";
		
		List<DTOSolicitud> listaSolicitudes = new ArrayList<DTOSolicitud>();
		
		try {
			co = Conexion.conn();
			stm = co.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				DTOSolicitud solicitud = new DTOSolicitud();				
				DTOListaOpciones estado = new DTOListaOpciones();
				Integer id_estado = rs.getInt("id_estado_solicitud");
				String nombre_estado = rs.getString("nombre_estado_solicitud");				
				DTOUsuario usuario = new DTOUsuario();
				Integer id_usuario = rs.getInt("id_usuario");
				String nombre = rs.getString("nombre_usuario");
				String correo = rs.getString("correo_usuario");
				String telefono_u = rs.getString("telefono_usuario");				
				DTOEmpresa empresa = new DTOEmpresa();
				Integer id_empresa = rs.getInt("id_empresa");
				String razon_social = rs.getString("razon_social");
				String direccion = rs.getString("direccion_empresa");
				String telefono_e = rs.getString("telefono_empresa");
				estado.setId(id_estado);
				estado.setNombre(nombre_estado);
				empresa.setId(id_empresa);
				empresa.setRazonSocial(razon_social);
				empresa.setDireccion(direccion);
				empresa.setTelefono(telefono_e);
				usuario.setId(id_usuario);
				usuario.setNombre(nombre);
				usuario.setCorreo(correo);
				usuario.setTelefono(telefono_u);
				usuario.setEmpresa(empresa);
				solicitud.setId(rs.getString("nro_solicitud"));
				solicitud.setUsuario(usuario);
				solicitud.setEstado(estado);
				solicitud.setFechaEmision(rs.getDate("fecha_emision_solicitud"));
				solicitud.setFechaEstimadaEntrega(rs.getDate("fecha_estimada_entrega"));
				solicitud.setFechaRealEntrega(rs.getDate("fecha_real_entrega"));
				solicitud.setDescripcion(rs.getString("descripcion_solicitud"));
				solicitud.setComentario(rs.getString("comentario_solicitud"));
				listaSolicitudes.add(solicitud);
			}
			System.out.println("Lista de solicitudes obtenida correctamente");
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error al obtener la lista de solicitudes");
			e.printStackTrace();
		}
		return listaSolicitudes;
	}


	@Override
	public DTOSolicitud editarSolicitud(DTOSolicitud solicitud) throws SQLException {
		String sql = "update solicitud " 
				+ "set id_usuario = ? , fecha_estimada_entrega = ?, fecha_real_entrega = ?, "
				+ "descripcion_solicitud = ?, comentario_solicitud = ?,"
				+ "id_estado_solicitud = ? " 
				+ "where nro_solicitud = ? ";
		Connection conn = Conexion.conn();
		PreparedStatement ps = conn.prepareStatement(sql);
		//DTOSolicitud solicitud2 = new DTOSolicitud();

		try {
			int i =1;
			ps.setInt(i++, solicitud.getUsuario().getId());
			ps.setDate(i++, (Date) solicitud.getFechaEstimadaEntrega());
			ps.setDate(i++, (Date) solicitud.getFechaRealEntrega());
			ps.setString(i++, solicitud.getDescripcion());
			ps.setString(i++, solicitud.getComentario());
			ps.setInt(i++, solicitud.getEstado().getId());
			ps.setString(i++, solicitud.getId());
			ps.executeUpdate();
			ps.close();
			//solicitud2 = obtenerSolicitud(solicitud.getId());
			System.out.println("Solicitud editada correctamente");
		} catch (SQLException e) {
			System.out.println("Error al editar solicitud");
			e.printStackTrace();
		} finally {
			ps.close();
			conn.close();
		}
		return solicitud;
	}
	
	
	//Listar 1 Proyecto
		@Override
		public DTOSolicitud getSolicitud( int id) {
			Connection co = null;
			Statement stm = null;
			ResultSet rs = null;
			String sql = 	
						"select nro_solicitud \r\n" + 
						"from rel_proy_componente \r\n" + 
						"where id_proyecto ="+id+" ";
		
					
					
			System.out.println("Si ejecuta query listar 1 Proyecto");
			System.out.println("Id ingresado: "+id);
			
			DTOSolicitud dtoSolicitudCarga = new DTOSolicitud();
			DTOComponente dtoComponente = new DTOComponente();	
			try {
				co = Conexion.conn();
				stm = co.createStatement();
				rs = stm.executeQuery(sql);
				
				System.out.println("Conexion Correcta bbdd");
			
				while (rs.next()) {

					System.out.println(rs.getString("nro_solicitud"));	
					
					
					dtoSolicitudCarga.setId(rs.getString("nro_solicitud"));
					dtoComponente.setSolicitud(dtoSolicitudCarga);
					//dtoSolicitudCarga.setNombreProyecto(rs.getString("nombre_proyecto"));
					/*
					dtoSolicitudCarga.setComentario(rs.getString("comentario_proyecto"));
					dtoSolicitudCarga.setCodigo(rs.getString("codigo_proyecto"));
					dtoProyectoCarga.setFechaCreacion(rs.getDate("ts_creacion_proyecto"));
					dtoProyectoCarga.setTipo(rs.getString("tipo"));
					
					DTOUsuario responsable = new DTOUsuario();
					responsable.setId(rs.getInt("id_responsable"));
					responsable.setNombre(rs.getString("nombre_usuario"));
					dtoProyectoCarga.setResponsable(responsable);
					
					DTOListaOpciones faseDesarrollo = new DTOListaOpciones();
					faseDesarrollo.setId(rs.getInt("id_fase_proyecto"));
					faseDesarrollo.setNombre(rs.getString("nombre_fase_proyecto"));
					dtoProyectoCarga.setFaseDesarrollo(faseDesarrollo);
					*/
					//dtoProyecto.setFechaCreacion(rs.getDate("ts_creacion_proyecto"));
					
				}
							
				System.out.println("Lista obtenida proyectos correctamente");
				stm.close();
				rs.close();
				co.close();
				System.out.println("No cae al retornar Servicio");
			} catch (SQLException e) {
				System.out.println("Error al obtener la lista de Proyectos");
				e.printStackTrace();
			}
				
				;
				return dtoSolicitudCarga;
			
	 }


		@Override
		public DTOSolicitud insertarSolicitud(DTOSolicitud solicitud) throws SQLException {
			
			String sql="insert into solicitud (nro_solicitud, id_usuario,fecha_emision_solicitud, fecha_estimada_entrega, " 
					+ "fecha_real_entrega, descripcion_solicitud, comentario_solicitud, id_estado_solicitud) " 
					+ " values "
					+ "(CONCAT('"+solicitud.getId()+"',to_char(SEQ_ID_"+solicitud.getId()+".NEXTVAL)),?,SYSDATE,?,?,?,?,?)";
			
			String sql2="select nro_solicitud from "
					+ "(select * from solicitud "
					+ "where nro_solicitud like '%"+solicitud.getId()+"%' "
							+ "order by fecha_emision_solicitud desc)"
							+ "where rownum <=1";
			
			 Connection conn = Conexion.conn();
			 ResultSet rs = null;
			 PreparedStatement ps = conn.prepareStatement(sql);
			 PreparedStatement ps2 = conn.prepareStatement(sql2);
			 //ResultSet rs = null;
			 try { 	 
				 int i = 1;
				 
				 ps.setInt(i++, solicitud.getUsuario().getId());
				 ps.setDate(i++, (Date)solicitud.getFechaEstimadaEntrega());
				 ps.setDate(i++, (Date)solicitud.getFechaRealEntrega());
				 ps.setString(i++, solicitud.getDescripcion()); 
				 ps.setString(i++, solicitud.getComentario()); 
				 ps.setInt(i++, solicitud.getEstado().getId());
				 ps.executeQuery();  
				 System.out.println("Solicitud insertada correctamente"); 
				 rs = ps2.executeQuery(sql2);
				 while (rs.next()) {
					 solicitud.setId(rs.getString("nro_solicitud"));
				 }
			 }catch (SQLException e) { 
				 System.out.println("Error al insertar solicitud"+e.getMessage());
			     e.printStackTrace(); 
			 } finally { 
				 ps.close();
				 ps2.close();
				 conn.close(); 
			 }
			return solicitud;
		}



		

}
