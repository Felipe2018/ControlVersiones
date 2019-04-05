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

import com.microdat.wasdev.proyecto.model.DTOAmbiente;
import com.microdat.wasdev.proyecto.model.DTOComponente;
import com.microdat.wasdev.proyecto.model.DTOListaOpciones;
import com.microdat.wasdev.proyecto.model.DTOProyecto;
import com.microdat.wasdev.proyecto.model.DTOReglaConflicto;
import com.microdat.wasdev.proyecto.model.DTOSolicitud;
import com.microdat.wasdev.proyecto.model.DTOUbicacion;
import com.microdat.wasdev.proyecto.model.wrapper.DTOComponenteList;
import com.microdat.wasdev.proyecto.util.RoutineUtils;

@Service("IRelComponenteServiceDao")
public class RelComponenteServiceDao implements IRelComponenteServiceDao{

	@Autowired
	RoutineUtils routineUtils;
	
	//Listar Rel Componentes
	@Override
	public DTOComponenteList getComponentesProyecto(DTOComponente componente)  {
		
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
		
		String sql = 
				
				"select* from rel_proy_componente rpc\r\n" + 
				"inner join estado_componente ed\r\n" + 
				"on rpc.id_estado_componente=ed.id_estado_componente";
				/*
				"select c.id_componente, c.nombre_componente, c.canal,\r\n" + 
				"				f.id_funcionalidad, f.nombre_funcionalidad,\r\n" + 
				"				gf.id_grupo_funcional, gf.nombre_grupo_funcional,\r\n" + 
				"				a.id_ambiente, a.nombre_ambiente,\r\n" + 
				"                rca.ruta,  \r\n" + 
				"(select count(rpc.id_componente) \r\n" + 
				"                    from rel_proy_componente rpc\r\n" + 
				"                    where rpc.ID_COMPONENTE = rca.ID_COMPONENTE \r\n" + 
				"                    AND rpc.ID_ESTADO_COMPONENTE != 1\r\n" + 
				"                    AND rpc.ID_ESTADO_COMPONENTE != 4\r\n" + 
				"                    AND rpc.ID_ESTADO_COMPONENTE != 5) \"cantidadEnDesarrollo\" \r\n" +
				
				"from REL_COMPONENTE_AMBIENTE rca \r\n" + 
				"join COMPONENTE c on rca.ID_COMPONENTE = c.ID_COMPONENTE\r\n" + 
				"join AMBIENTE a on rca.ID_AMBIENTE = a.ID_AMBIENTE\r\n" + 
				"join GRUPO_FUNCIONAL gf on c.ID_GRUPO_FUNCIONAL = gf.ID_GRUPO_FUNCIONAL\r\n" + 
				"join FUNCIONALIDAD f on c.ID_FUNCIONALIDAD = f.ID_FUNCIONALIDAD\r\n" + 
				"order by c.id_componente asc";*/
				
		System.out.println("Si ejecuta query listar rel componentes");
		
		List<DTOComponente> componentes = new ArrayList<DTOComponente>();
		DTOComponenteList dtoComponenteList = new DTOComponenteList();
			try {
			co = Conexion.conn();
			stm = co.createStatement();
			
			rs = stm.executeQuery(sql);
			
			System.out.println("Conexion Correcta bbdd");
			
			while (rs.next()) {
				
					//Agrega id componente
					DTOComponente dtoComponente1 = new DTOComponente();
					dtoComponente1.setId(rs.getInt("id_componente"));
					dtoComponente1.setDescripcion(rs.getString("descripcion"));
					dtoComponente1.setComentario(rs.getString("comentario"));
					dtoComponente1.setIdProyecto(rs.getInt("id_proyecto"));
					DTOAmbiente ambiente = new DTOAmbiente();
					DTOUbicacion ubicacion = new DTOUbicacion();
					ambiente.setId(rs.getInt("id_ambiente"));
					ubicacion.setAmbiente(ambiente);
					List<DTOUbicacion> ubicaciones = new ArrayList<DTOUbicacion>();
					ubicaciones.add(ubicacion);
					dtoComponente1.setUbicaciones(ubicaciones);
					
					DTOSolicitud solicitud = new DTOSolicitud();
					solicitud.setId(rs.getString("nro_solicitud"));
					dtoComponente1.setSolicitud(solicitud);
					
					DTOAmbiente ambienteSubida =new DTOAmbiente();
					ambienteSubida.setId(rs.getInt("id_ambiente_subida"));
					dtoComponente1.setAmbienteSubida(ambienteSubida);
					
					DTOReglaConflicto reglaConflicto = new DTOReglaConflicto();
					reglaConflicto.setId(rs.getInt("id_regla_conflicto"));
					dtoComponente1.setReglaConflicto(reglaConflicto);
					
					DTOListaOpciones estado = new DTOListaOpciones();
					estado.setId(rs.getInt("id_estado_componente"));
					estado.setNombre(rs.getString("nom_estado_componente"));
					dtoComponente1.setEstado(estado);
					
					componentes.add(dtoComponente1);
			}
						
			System.out.println("Lista rel componentes obtenida correctamente");
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error al obtener la lista de rel componentes");
			e.printStackTrace();
		}
				
			dtoComponenteList.setComponentes(componentes);
			
			return dtoComponenteList;
		
	  }

	//Listar 1 id Rel Componente
	@Override
	public DTOComponenteList getComponente(DTOComponente componente)  {
		
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
		
		String sql = 
				
				"select* from rel_proy_componente rpc\r\n" + 
				"inner join estado_componente ed\r\n" + 
				"on rpc.id_estado_componente=ed.id_estado_componente\r\n"+
				"where id_componente = "+ componente.getId()+"" ;
		System.out.println(componente.getId());	
		System.out.println("Si ejecuta query listar 1 rel componentes");
		
		List<DTOComponente> componentes = new ArrayList<DTOComponente>();
		DTOComponenteList dtoComponenteList = new DTOComponenteList();
			try {
			co = Conexion.conn();
			stm = co.createStatement();
			rs = stm.executeQuery(sql);
			
			//ResultSet rs = routineUtils.driverManagerConnection(sql);
			System.out.println("Conexion Correcta bbdd");
			
			while (rs.next()) {
				
					//Agrega id componente
					DTOComponente dtoComponente1 = new DTOComponente();
					dtoComponente1.setId(rs.getInt("id_componente"));
					dtoComponente1.setDescripcion(rs.getString("descripcion"));
					dtoComponente1.setComentario(rs.getString("comentario"));
					dtoComponente1.setIdProyecto(rs.getInt("id_proyecto"));
					
					DTOAmbiente ambiente = new DTOAmbiente();
					DTOUbicacion ubicacion = new DTOUbicacion();
					ambiente.setId(rs.getInt("id_ambiente"));
					ubicacion.setAmbiente(ambiente);
					List<DTOUbicacion> ubicaciones = new ArrayList<DTOUbicacion>();
					ubicaciones.add(ubicacion);
					dtoComponente1.setUbicaciones(ubicaciones);
					
					DTOSolicitud solicitud = new DTOSolicitud();
					solicitud.setId(rs.getString("nro_solicitud"));
					dtoComponente1.setSolicitud(solicitud);
					
					DTOAmbiente ambienteSubida =new DTOAmbiente();
					ambienteSubida.setId(rs.getInt("id_ambiente_subida"));
					dtoComponente1.setAmbienteSubida(ambienteSubida);
					
					DTOReglaConflicto reglaConflicto = new DTOReglaConflicto();
					reglaConflicto.setId(rs.getInt("id_regla_conflicto"));
					dtoComponente1.setReglaConflicto(reglaConflicto);
					
					DTOListaOpciones estado = new DTOListaOpciones();
					estado.setId(rs.getInt("id_estado_componente"));
					estado.setNombre(rs.getString("nom_estado_componente"));
					dtoComponente1.setEstado(estado);
					
					componentes.add(dtoComponente1);
			}
						
			System.out.println("Lista 1 rel componentes obtenida correctamente");
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error al obtener la lista de rel componentes");
			e.printStackTrace();
		}
				
			dtoComponenteList.setComponentes(componentes);
			
			return dtoComponenteList;
		
	  }
	
	//Insertar Rel Componente
		@Override
		public DTOComponente postComponenteProyectos(DTOComponente dtoComponente,DTOProyecto dtoProyecto)  {
				
			Connection co = null;
			PreparedStatement ps =null;
			Statement stm = null;
			ResultSet rs = null;
			int cantidad =0;
			Statement stm2 = null;
			ResultSet rs2 = null;
			int ProyectoCreado = 0;
			
			String sql = 
					
				"insert into rel_proy_componente (id_proyecto,id_componente,id_ambiente,\r\n" + 
				"nro_solicitud,id_estado_componente,id_ambiente_subida,descripcion)\r\n" + 
				"values(?,?,?,?,?,?,?)";	
					
			String sql2 =
					"select COUNT(*) cantidadComponente \r\n" + 
					"from rel_proy_componente \r\n" + 
					"where id_componente =  " +dtoComponente.getId()  + 
					" and id_proyecto =  "+ dtoProyecto.getId();
					
			String sql3 =
					"select id_proyecto, nro_solicitud \r\n" + 
					"from rel_proy_componente\r\n" + 
					"where nro_solicitud = '"+dtoComponente.getSolicitud().getId()+"'";
			
			
			DTOComponente dtoComponenteOut = new DTOComponente();
			//TOProyecto dtoProyecto = new DTOProyecto();
		
			try {
				co = Conexion.conn();
				ps = co.prepareStatement(sql);
				//Ejecuta Query Por cada Ambiente
				boolean componente = false;
				boolean solicitud = false;
				 
				stm = co.createStatement();
				rs = stm.executeQuery(sql2);
					
				while (rs.next()) {
					System.out.println(rs.getInt("cantidadComponente"));
					cantidad = rs.getInt("cantidadComponente");
				}
				
				if(cantidad == 0) {
					componente = true;
				}else {
					componente = false;
					throw new Exception ("Componente ya existe en este Proyecto");
				}
				
				
				stm2 = co.createStatement();
				rs2 = stm2.executeQuery(sql3);
				
				System.out.println("El proyecto a ingresar: "+ dtoProyecto.getId());
				while (rs2.next()) {
					
					System.out.println("El id Proyecto existente con el nro solicitud: "+ rs2.getInt("id_proyecto"));
					
					System.out.println("*******************************");
					ProyectoCreado = rs2.getInt("id_proyecto");
					
				}
				
				
					if( dtoProyecto.getId() == ProyectoCreado || ProyectoCreado== 0) {
						solicitud = true;
					}else {
						solicitud = false;
						throw new Exception("Solicitud existe en otro Proyecto");
					}
				
				
				
				/*
				//1 ID PROYECTO
				System.out.println( dtoProyecto.getId());
				//2 ID COMPONENTE
				System.out.println( dtoComponente.getId());
				//3 ID AMBIENTE
				System.out.println( dtoComponente.getUbicaciones().get(0).getAmbiente().getId());
				//4 NRO SOLICITUD
				System.out.println( dtoComponente.getSolicitud().getId());
				//5 ID ESTADO
				System.out.println( dtoComponente.getEstado().getId());
				//6 ID AMBIENTE SUBIDA
				System.out.println(dtoComponente.getAmbienteSubida().getId());		
				//7 ID REGLA CONFLICTO
				//System.out.println(dtoComponente.getReglaConflicto().getId());
				//8 DESCRIPCION
				System.out.println(dtoComponente.getDescripcion());//COMENTARIO
				//9 COMENTARIO
				//System.out.println(dtoComponente.getComentario());
				//10 PRIORIDAD
				//System.out.println();
				*/
				ps.setInt(1, dtoProyecto.getId());
				ps.setInt(2, dtoComponente.getId());
				ps.setInt(3, dtoComponente.getUbicaciones().get(0).getAmbiente().getId());
				ps.setString(4, dtoComponente.getSolicitud().getId());
				ps.setInt(5, dtoComponente.getEstado().getId());
				ps.setInt(6, dtoComponente.getAmbienteSubida().getId());
				ps.setString(7, dtoComponente.getDescripcion());
				//ps.setInt(8, dtoComponente.getUbicaciones().get(0).getAmbiente().getId());
				
				
				System.out.println(componente + " " + solicitud);
				if(componente== true & solicitud== true ) {
					ps.executeQuery();
					dtoComponenteOut.setId(dtoComponente.getId());
					dtoComponenteOut.setIdProyecto( dtoProyecto.getId());
					dtoComponenteOut.setDescripcion(dtoComponente.getDescripcion());
				}else {
					dtoComponenteOut.setComentario("No se ha podido insertar el rel componente");
				}
		
			ps.close();
			co.close();
			stm.close();
			rs.close();
			stm2.close();
			rs2.close();
		} catch (SQLException e) {
			System.out.println("Error al insertar la lista de rel componentes");
			e.printStackTrace();
			dtoComponenteOut.setComentario(e.getMessage());
			
		} catch (Exception e) {
			System.out.println("Error al insertar: Componente || Solicitud");
			e.printStackTrace();
			dtoComponenteOut.setComentario(e.getMessage());
			
		}
			
			
			return dtoComponenteOut;
		}
}
