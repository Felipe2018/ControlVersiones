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
import com.microdat.wasdev.proyecto.model.DTOListaOpciones;
import com.microdat.wasdev.proyecto.model.DTOProyecto;
import com.microdat.wasdev.proyecto.model.DTOUsuario;
import com.microdat.wasdev.proyecto.model.wrapper.DTOProyectoList;
import com.microdat.wasdev.proyecto.util.RoutineUtils;


@Service("proyectoServiceDao")
public class ProyectoServiceDaoImpl implements IProyectoServiceDao{

	@Autowired
	RoutineUtils routineUtils;
	
	//Listar Proyectos
	@Override
	public DTOProyectoList getProyectos(DTOProyecto dtoProyecto) {
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql = 	
				"Select p.id_proyecto, p.nombre_proyecto,p.id_fase_proyecto ,p.codigo_proyecto, p.ts_creacion_proyecto, p.fecha_estimada_entrega, p.tipo, \r\n" + 
				"p.comentario_proyecto, fp.nombre_fase_proyecto, u.nombre_usuario, u.id_usuario,uDyd.nombre_usuario userDyd, uDyd.id_usuario idDyd \r\n" + 
				"from proyecto p \r\n" + 
				"inner join fase_proyecto fp on p.id_fase_proyecto = fp.id_fase_proyecto \r\n" + 
				"inner join usuario u on p.id_responsable= u.id_usuario \r\n" + 
				"left join usuario uDyd on p.id_dyd = uDyd.id_usuario order by id_proyecto asc";
				
		
		System.out.println("Si ejecuta query listar proyecto");
		
		
		List<DTOProyecto> proyectos = new ArrayList<DTOProyecto>();
		DTOProyectoList dtoproyectoList = new DTOProyectoList();
		
			try {
			co = Conexion.conn();
			stm = co.createStatement();
			rs = stm.executeQuery(sql);
					
			while (rs.next()) {

				DTOProyecto dtoProyectoCarga = new DTOProyecto();
			
				System.out.println(rs.getDate("ts_creacion_proyecto"));
				System.out.println(rs.getInt("id_proyecto"));	
				//System.out.println(rs.getInt("id_dyd"));
				System.out.println(rs.getString("nombre_proyecto"));
				System.out.println(rs.getString("codigo_proyecto"));
				System.out.println(rs.getString("comentario_proyecto"));
				//System.out.println(rs.getInt("id_responsable"));
				System.out.println(rs.getString("nombre_usuario"));
				//System.out.println(rs.getInt("id_fase_proyecto"));
				System.out.println(rs.getString("nombre_fase_proyecto"));
				System.out.println(rs.getString("userDyd"));
				
				dtoProyectoCarga.setId(rs.getInt("id_proyecto"));
				dtoProyectoCarga.setNombreProyecto(rs.getString("nombre_proyecto"));
				//dtoProyectoCarga.setDyd(rs.getInt("id_dyd"));	
				dtoProyectoCarga.setComentario(rs.getString("comentario_proyecto"));
				dtoProyectoCarga.setCodigo(rs.getString("codigo_proyecto"));
				dtoProyectoCarga.setFechaCreacion(rs.getDate("ts_creacion_proyecto"));
				dtoProyectoCarga.setTipo(rs.getString("tipo"));
				dtoProyectoCarga.setFechaEstimadaEntrega(rs.getDate("fecha_estimada_entrega"));
				
				DTOUsuario responsable = new DTOUsuario();
				//responsable.setId(rs.getInt("id_responsable"));
				responsable.setNombre(rs.getString("nombre_usuario"));
				responsable.setId(rs.getInt("id_usuario"));
				dtoProyectoCarga.setResponsable(responsable);
				
				DTOUsuario responsableDyd = new DTOUsuario();
				responsableDyd.setId(rs.getInt("idDyd"));
				responsableDyd.setNombre(rs.getString("userDyd"));
				dtoProyectoCarga.setResponsableDyd(responsableDyd);
				
				DTOListaOpciones faseDesarrollo = new DTOListaOpciones();
				faseDesarrollo.setId(rs.getInt("id_fase_proyecto"));
				faseDesarrollo.setNombre(rs.getString("nombre_fase_proyecto"));
				dtoProyectoCarga.setFaseDesarrollo(faseDesarrollo);
				
				//dtoProyecto.setFechaCreacion(rs.getDate("ts_creacion_proyecto"));
				
				proyectos.add(dtoProyectoCarga);
				
			}
						
			System.out.println("Lista obtenida proyectos correctamente");
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error al obtener la lista de Proyectos");
			e.printStackTrace();
		}
			
			dtoproyectoList.setProyectos(proyectos);
			return  dtoproyectoList;
		
 }
	
	@Override
	public DTOProyectoList getProyectosFiltro(DTOProyecto dtoProyecto) {
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql = 	
				"Select p.id_proyecto, p.nombre_proyecto,p.codigo_proyecto  \r\n" +  
				"from proyecto p \r\n"; 
				/*
				"inner join fase_proyecto fp on p.id_fase_proyecto = fp.id_fase_proyecto \r\n" + 
				"inner join usuario u on p.id_responsable= u.id_usuario \r\n" + 
				"left join usuario uDyd on p.id_dyd = uDyd.id_usuario order by id_proyecto asc";
				*/
		
		List<DTOProyecto> proyectos = new ArrayList<DTOProyecto>();
		DTOProyectoList dtoproyectoList = new DTOProyectoList();
		
			try {
			co = Conexion.conn();
			stm = co.createStatement();
			rs = stm.executeQuery(sql);
			
		
			while (rs.next()) {

				DTOProyecto dtoProyectoCarga = new DTOProyecto();
			
				System.out.println(rs.getInt("id_proyecto"));	
				System.out.println(rs.getString("nombre_proyecto"));
				dtoProyectoCarga.setId(rs.getInt("id_proyecto"));
				dtoProyectoCarga.setNombreProyecto(rs.getString("nombre_proyecto"));
				dtoProyectoCarga.setCodigo(rs.getString("codigo_proyecto"));
				
				proyectos.add(dtoProyectoCarga);
				
			}
						
			System.out.println("Lista obtenida proyectos correctamente");
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error al obtener la lista de Proyectos");
			e.printStackTrace();
		}
			
			dtoproyectoList.setProyectos(proyectos);
			return  dtoproyectoList;
		
 }
	
	
	//Listar 1 Proyecto
	@Override
	public DTOProyecto getProyecto( int id) {
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql = 	
				"select p.*, u.nombre_usuario, uDyd.nombre_usuario userDyd, f.nombre_fase_proyecto  from proyecto p "
				+"inner join usuario u on p.id_responsable = u.id_usuario " 
				+"left join usuario uDyd on p.id_dyd = uDyd.id_usuario " 
				+"inner join fase_proyecto f on p.id_fase_proyecto = f.id_fase_proyecto "
				+"where id_proyecto = " + id ;
				
		System.out.println("Si ejecuta query listar 1 Proyecto");
		
		
		DTOProyecto dtoProyectoCarga = new DTOProyecto();
			try {
			co = Conexion.conn();
			stm = co.createStatement();
			rs = stm.executeQuery(sql);
			
			System.out.println("Conexion Correcta bbdd");
		
			while (rs.next()) {

				System.out.println(rs.getDate("ts_creacion_proyecto"));
				System.out.println(rs.getInt("id_proyecto"));	
				//System.out.println(rs.getInt("id_dyd"));
				System.out.println(rs.getString("nombre_proyecto"));
				System.out.println(rs.getString("codigo_proyecto"));
				System.out.println(rs.getString("comentario_proyecto"));
				//System.out.println(rs.getInt("id_responsable"));
				System.out.println(rs.getString("nombre_usuario"));
				//System.out.println(rs.getInt("id_fase_proyecto"));
				System.out.println(rs.getString("nombre_fase_proyecto"));
				System.out.println(rs.getString("userDyd"));
				
				
				dtoProyectoCarga.setId(rs.getInt("id_proyecto"));
				dtoProyectoCarga.setNombreProyecto(rs.getString("nombre_proyecto"));
				//dtoProyectoCarga.setDyd(rs.getInt("id_dyd"));	
				dtoProyectoCarga.setComentario(rs.getString("comentario_proyecto"));
				dtoProyectoCarga.setCodigo(rs.getString("codigo_proyecto"));
				dtoProyectoCarga.setFechaCreacion(rs.getDate("ts_creacion_proyecto"));
				dtoProyectoCarga.setTipo(rs.getString("tipo"));
				
				DTOUsuario responsable = new DTOUsuario();
				responsable.setId(rs.getInt("id_responsable"));
				responsable.setNombre(rs.getString("nombre_usuario"));
				dtoProyectoCarga.setResponsable(responsable);
				
				DTOUsuario responsableDyd = new DTOUsuario();
				responsableDyd.setId(rs.getInt("id_dyd"));
				responsableDyd.setNombre(rs.getString("userDyd"));
				dtoProyectoCarga.setResponsableDyd(responsableDyd);
				
				DTOListaOpciones faseDesarrollo = new DTOListaOpciones();
				faseDesarrollo.setId(rs.getInt("id_fase_proyecto"));
				faseDesarrollo.setNombre(rs.getString("nombre_fase_proyecto"));//
				dtoProyectoCarga.setFaseDesarrollo(faseDesarrollo);
				
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
			
			return  dtoProyectoCarga;
		
 }
	
	//Insertar Proyecto
	@Override
	public DTOProyecto postProyecto(DTOProyecto dtoProyecto) {

		Connection co = null;
		PreparedStatement ps =null;
		Boolean agregaFecha = false;
		int cantidad =0;
		Statement stm = null;
		Statement stm2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		
		String sqlConsulta =
					"select COUNT(*) cantidadProyectos \r\n" + 
					"from proyecto \r\n" + 
					"where nombre_proyecto = '" + dtoProyecto.getNombreProyecto() + "'";
				
		String sql1=		
			"insert into proyecto (nombre_proyecto, id_responsable, id_dyd, "
			+"comentario_proyecto, id_fase_proyecto, ts_creacion_proyecto,codigo_proyecto,tipo  ";  
		String sqlVal =	
			 " ) values (?,?,?,?,?,SysDate ,?,? "; 
		String sqlFin =	
		" )";
		
		
		
			if(dtoProyecto.getFechaEstimadaEntrega()!=null) {
				System.out.println("Fecha no nula: " + dtoProyecto.getFechaEstimadaEntrega());
				sql1=sql1+ ", fecha_estimada_entrega";
				sqlVal= sqlVal+ ", ?";
				agregaFecha= true;
			}
		String sql = sql1+sqlVal+sqlFin ;
		
		
		System.out.println("Envia query insertar Proyecto");
		DTOProyecto dtoProyectoOut = new DTOProyecto();
		
		try {
			co = Conexion.conn();
			 ps = co.prepareStatement(sql);
		
			System.out.println(dtoProyecto.getNombreProyecto());
			System.out.println(dtoProyecto.getResponsable().getId());
			System.out.println(dtoProyecto.getResponsableDyd().getId());
			System.out.println(dtoProyecto.getComentario());
			System.out.println(dtoProyecto.getFaseDesarrollo().getId());
			System.out.println(dtoProyecto.getCodigo());
			System.out.println(dtoProyecto.getTipo());
			
			//Consulta Proyecto
			
			stm = co.createStatement();
			rs = stm.executeQuery(sqlConsulta);
				
			while (rs.next()) {
				System.out.println("La cantidad de proyectos:  " + rs.getInt("cantidadProyectos"));
				cantidad = rs.getInt("cantidadProyectos");
			
			}

			//Valida duplicidad de Proyecto
			if(cantidad==0) {
			
			//Insertar a Query
			ps.setString(1, dtoProyecto.getNombreProyecto());
			ps.setInt(2, dtoProyecto.getResponsable().getId());
			ps.setInt(3, dtoProyecto.getResponsableDyd().getId());
			ps.setString(4, dtoProyecto.getComentario());
			ps.setInt(5, dtoProyecto.getFaseDesarrollo().getId());
			ps.setString(6, dtoProyecto.getCodigo());
			ps.setString(7, dtoProyecto.getTipo());
			if(agregaFecha==true) {
				ps.setDate(8, dtoProyecto.getFechaEstimadaEntrega());
				}
			
				dtoProyectoOut.setNombreProyecto(dtoProyecto.getNombreProyecto());
				dtoProyectoOut.setComentario("Agregado Correctamente");
				ps.executeQuery();
				System.out.println("Si ejecuta qry Inserta");
				
				
				String sql2="select id_proyecto from \r\n" + 
						"(select * from proyecto \r\n" + 
						"order by proyecto.ts_creacion_proyecto desc)\r\n" + 
						"where rownum <=1";
				
				stm2 = co.createStatement();
				rs2 = stm.executeQuery(sql2);
				
				 while (rs2.next()) {
					 dtoProyectoOut.setId(rs2.getInt("id_proyecto"));
					
				 }
				
			}else {
				System.out.println("Ya exite Proyecto");
				dtoProyectoOut.setComentario("Ya existe un proyecto con ese nombre");
			}
				ps.close();
				co.close();
			} catch (SQLException e) {
				System.out.println("Error al insertar la lista de proyectos");
				e.printStackTrace();
			}
		return dtoProyectoOut;
	}

	//Modificar Proyecto
	@Override
	public DTOProyecto putProyecto(DTOProyecto dtoProyecto) {
		
		Connection co = null;
		PreparedStatement ps =null;
		
		String sql =
				"update proyecto " 
				+ "set nombre_proyecto = ? , id_responsable = ?, id_dyd = ?, id_fase_proyecto= ?,  "
				+ "comentario_proyecto = ?, codigo_proyecto = ?, tipo = ? " 
				+ "where id_proyecto = ?";
		
		DTOProyecto dtoProyectoOut = new DTOProyecto();
		
		try {
		
			co = Conexion.conn();
			ps = co.prepareStatement(sql);
			 
			ps.setString(1, dtoProyecto.getNombreProyecto());
			ps.setInt(2, dtoProyecto.getResponsable().getId());
			ps.setInt(3, dtoProyecto.getResponsableDyd().getId());
			ps.setInt(4, dtoProyecto.getFaseDesarrollo().getId());
			ps.setString(5, dtoProyecto.getComentario());
			ps.setString(6, dtoProyecto.getCodigo());
			ps.setString(7, dtoProyecto.getTipo());
			System.out.println("tipo proyecto ingresado:  "+dtoProyecto.getTipo());
			ps.setInt(7, dtoProyecto.getId());
			 
			ps.executeQuery();
			System.out.println("Si ejecuta qry Modificar");
	
			ps.close();
			co.close();
			
		} catch (SQLException e) {
			System.out.println("Error al modificar proyecto");
			e.printStackTrace();
		}
	return dtoProyectoOut;
				
	}

	//Eliminar "FALTA HACER!"

}
