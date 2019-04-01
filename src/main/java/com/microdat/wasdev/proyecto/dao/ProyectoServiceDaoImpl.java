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
	
	@Override
	public DTOProyectoList getProyecto(DTOProyecto dtoProyecto) {
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
		String sql = 	
				"Select p.id_proyecto, p.nombre_proyecto, p.codigo_proyecto, p.ts_creacion_proyecto, \r\n" + 
				"p.comentario_proyecto, fp.nombre_fase_proyecto, u.nombre_usuario \r\n" + 
				"from proyecto p \r\n" + 
				"inner join fase_proyecto fp on p.id_fase_proyecto = fp.id_fase_proyecto\r\n" + 
				"inner join usuario u on p.id_responsable= u.id_usuario order by id_proyecto asc";
				//"Select * from proyecto";
		
		System.out.println("Si ejecuta query listar proyecto");
		
		//List<DTOProyecto> proyectos = new ArrayList<DTOProyecto>();
		List<DTOProyecto> proyectos = new ArrayList<DTOProyecto>();
		DTOProyectoList dtoproyectoList = new DTOProyectoList();
		
			try {
			co = Conexion.conn();
			stm = co.createStatement();
			rs = stm.executeQuery(sql);
			
			System.out.println("Conexion Correcta bbdd");
		
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
				
				
				dtoProyectoCarga.setId(rs.getInt("id_proyecto"));
				dtoProyectoCarga.setNombreProyecto(rs.getString("nombre_proyecto"));
				//dtoProyectoCarga.setDyd(rs.getInt("id_dyd"));	
				dtoProyectoCarga.setComentario(rs.getString("comentario_proyecto"));
				dtoProyectoCarga.setCodigo(rs.getString("codigo_proyecto"));
				dtoProyectoCarga.setFechaCreacion(rs.getDate("ts_creacion_proyecto"));;
				
				DTOUsuario responsable = new DTOUsuario();
				//responsable.setId(rs.getInt("id_responsable"));
				responsable.setNombre(rs.getString("nombre_usuario"));
				dtoProyectoCarga.setResponsable(responsable);
				
				DTOListaOpciones faseDesarrollo = new DTOListaOpciones();
				//faseDesarrollo.setId(rs.getInt("id_fase_proyecto"));
				faseDesarrollo.setNombre(rs.getString("nombre_fase_proyecto"));
				dtoProyectoCarga.setFaseDesarrollo(faseDesarrollo);
				
				//dtoProyecto.setFechaCreacion(rs.getDate("ts_creacion_proyecto"));
				
				proyectos.add(dtoProyectoCarga);
				
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
			
			dtoproyectoList.setProyectos(proyectos);
			return  dtoproyectoList;
		
 }
	

	@Override
	public DTOProyecto postProyecto(DTOProyecto dtoProyecto) {

		Connection co = null;
		PreparedStatement ps =null;
		
		String sql = 
				
			"insert into proyecto (nombre_proyecto, id_responsable, "
			+"comentario_proyecto, id_fase_desarrollo, ts_creacion_proyecto,codigo_proyecto) \r\n"  
			+ " values (?,?,?,?,SysDate ,? )";
			//+ " id_dyd)\r\n" SysDate 
			
		
		System.out.println("Envia query insertar Proyecto");
		DTOProyecto dtoProyectoOut = new DTOProyecto();
		
		try {
			co = Conexion.conn();
			 ps = co.prepareStatement(sql);
		
			//System.out.println(dtoProyecto.getId());
			//System.out.println(dtoProyecto.getCliente());
			System.out.println(dtoProyecto.getNombreProyecto());
			System.out.println(dtoProyecto.getResponsable().getId());
			System.out.println(dtoProyecto.getComentario());
			System.out.println(dtoProyecto.getFaseDesarrollo().getId());
			System.out.println(dtoProyecto.getFechaCreacion());
			System.out.println(dtoProyecto.getCodigo());
			//Insertar a Query
			ps.setString(1, dtoProyecto.getNombreProyecto());
			ps.setInt(2, dtoProyecto.getResponsable().getId());
			ps.setString(3, dtoProyecto.getComentario());
			ps.setInt(4, dtoProyecto.getFaseDesarrollo().getId());
			//ps.setDate(5, (Date) dtoProyecto.getFechaCreacion());	
			ps.setString(5, dtoProyecto.getCodigo());
			
			dtoProyectoOut.setNombreProyecto(dtoProyecto.getNombreProyecto());
			//dtoProyectoOut.setFaseDesarrollo.(dtoProyecto.getFaseDesarrollo().getId());
			//dtoProyectoOut.setResponsable(dtoProyecto.getResponsable().getNombre());
			//dtoProyectoOut.setResponsable(dtoProyecto.getCodigo());	
				
				//Ejecuta Query
				System.out.println("No ejecuta qry");
				ps.executeQuery();
				System.out.println("Si ejecuta qry Inserta");
		
				ps.close();
				co.close();
			} catch (SQLException e) {
				System.out.println("Error al insertar la lista de proyectos");
				e.printStackTrace();
			}
				
				
				return dtoProyectoOut;
	}

}
