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
			System.out.println("NO EJECUTA QRY");
			rs = stm.executeQuery(sql);
			//ResultSet rs = routineUtils.driverManagerConnection(sql);
			System.out.println("Conexion Correcta bbdd");
			
			while (rs.next()) {
				
					//Agrega id componente
					DTOComponente dtoComponente1 = new DTOComponente();
					dtoComponente1.setId(rs.getInt("id_componente"));
					dtoComponente1.setDescripcion(rs.getString("descripcion"));
					dtoComponente1.setComentario(rs.getString("comentario"));
					
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

	//Listar 1 id Componente
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
				System.out.println("while");
					//Agrega id componente
					DTOComponente dtoComponente1 = new DTOComponente();
					dtoComponente1.setId(rs.getInt("id_componente"));
					dtoComponente1.setDescripcion(rs.getString("descripcion"));
					dtoComponente1.setComentario(rs.getString("comentario"));
					
					DTOAmbiente ambiente = new DTOAmbiente();
					DTOUbicacion ubicacion = new DTOUbicacion();
					ambiente.setId(rs.getInt("id_ambiente"));
					ubicacion.setAmbiente(ambiente);
					List<DTOUbicacion> ubicaciones = new ArrayList<DTOUbicacion>();
					ubicaciones.add(ubicacion);
					dtoComponente1.setUbicaciones(ubicaciones);
					System.out.println("solicitud");
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
	
	//Insertar Componente
		@Override
		public DTOComponente postComponenteProyectos(DTOComponente dtoComponente,DTOProyecto dtoProyecto) {
				
			Connection co = null;
			PreparedStatement ps =null;
			String sql = 
					
				"insert into rel_proy_componente (id_proyecto,id_componente,id_ambiente,\r\n" + 
				"nro_solicitud,id_estado_componente,id_ambiente_subida,descripcion)\r\n" + 
				"values(?,?,?,?,?,?,?)";	
						
			System.out.println("Envia query insertar rel componente");
			
			
			DTOComponente dtoComponenteOut = new DTOComponente();
			//TOProyecto dtoProyecto = new DTOProyecto();
			System.out.println(dtoProyecto.getId());
			try {
				co = Conexion.conn();
				 ps = co.prepareStatement(sql);
			
				//int i = 1;
				
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
				
				ps.setInt(1, dtoProyecto.getId());
				ps.setInt(2, dtoComponente.getId());
				ps.setInt(3, dtoComponente.getUbicaciones().get(0).getAmbiente().getId());
				ps.setString(4, dtoComponente.getSolicitud().getId());
				ps.setInt(5, dtoComponente.getEstado().getId());
				ps.setInt(6, dtoComponente.getAmbienteSubida().getId());
				ps.setString(7, dtoComponente.getDescripcion());
				//ps.setInt(8, dtoComponente.getUbicaciones().get(0).getAmbiente().getId());
				
				
				//Ejecuta Query Por cada Ambiente
				ps.executeQuery();
				System.out.println("Si ejecuta qry insertar relacion");
			
				
				//dtoComponenteOut.setNombre(dtoComponente.getNombre());
				//dtoComponenteOut.setCanal(dtoComponente.getCanal());
				
				
				//Ejecuta Query
				//ps.executeQuery();
				//System.out.println("Si ejecuta qry y pasa a buscar id");
				//dtoComponente.setId(1123);
				
				/*
				//PRUEBA INSERTAR
				String sql2 = 
						
						"insert into PRUEBA (NOM_PRUEBA,FECHA)\r\n" + 
						"values(?,?)";	
								
					System.out.println("Envia query insertar rel componente");
					
					co = Conexion.conn();
					 ps2 = co.prepareStatement(sql2);
					
					System.out.println(dtoComponente.getNombre());
					//NRO SOLICITUD
					System.out.println(dtoComponente.getSolicitud().getFechaEmision());
						
					ps2.setString(1, dtoComponente.getNombre());
					ps2.setDate(2, dtoComponente.getSolicitud().getFechaEmision());
					//ps.setString(3, dtoComponente.getFuncionalidad().getNombre());
					//ps2.setInt(3, dtoComponente.getFuncionalidad().getId());
		
					dtoComponenteOut.setNombre(dtoComponente.getNombre());
					//dtoComponenteOut.setSolicitud(dtoComponente.getSolicitud().getFechaEmision());
					
					
					//Ejecuta Query
					ps2.executeQuery();
					System.out.println("Si ejecuta qry y pasa a buscar id");
					*/
					
					
			System.out.println("Lista de rel componente agregada correctamente");
			ps.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error al obtener la lista de rel componentes");
			e.printStackTrace();
		}
			
			
			return dtoComponenteOut;
		}
}
