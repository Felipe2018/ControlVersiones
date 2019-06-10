package com.microdat.wasdev.proyecto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.microdat.wasdev.proyecto.model.DTOAmbiente;
import com.microdat.wasdev.proyecto.model.DTOComponente;
import com.microdat.wasdev.proyecto.model.DTOListaOpciones;
import com.microdat.wasdev.proyecto.model.DTOUbicacion;
import com.microdat.wasdev.proyecto.model.wrapper.DTOComponenteList;

@Service("componenteServiceDao")
public class ComponenteServiceDaoImpl implements IComponenteServiceDao {

	//Actualizar Componente
	@Override
	public DTOComponente actualizarComponente(DTOComponente componente) throws SQLException {
		
		String sql = "update componente " 
				+ "set id_grupo_funcional = ? , id_funcionalidad = ?, "
				+ "nombre_componente = ?, canal = ? " 
				+ "where id_componente = ?";
		
		String sql2 = "update rel_componente_ambiente " 
				+ "set id_ambiente = ?, "
				+ "ruta = ? " 
				+ "where id_componente = ? and id_ambiente = ?";
		
		DTOComponente componenteRetorno = null;
		Connection conn = Conexion.conn();
		PreparedStatement ps = conn.prepareStatement(sql);
		PreparedStatement ps2 = conn.prepareStatement(sql2);
		
		try {
			ps.setInt(1, componente.getGrupoFuncional().getId());
			ps.setInt(2, componente.getFuncionalidad().getId());
			ps.setString(3, componente.getNombre());
			ps.setString(4, componente.getCanal());
			ps.setInt(5, componente.getId());
			ps2.setInt(1, componente.getAmbienteSubida().getId());
			ps2.setString(2, componente.getUbicaciones().get(0).getRuta());
			ps2.setInt(3, componente.getId());
			ps2.setInt(4, componente.getAmbienteSubida().getId());
			ps.executeUpdate();
			ps.close();
			System.out.println("Componente actualizado");
			ps2.executeUpdate();
			ps2.close();
			System.out.println("Rel_com_amb actualizado");
		    componenteRetorno = obtenerComponente(componente.getId(), componente.getAmbienteSubida().getId() ); 
			conn.close();
		    System.out.println("Actualizacion se ha realizado correctamente");
		} catch (SQLException e) {
			System.out.println("Error al actualizar");
			e.printStackTrace();
		} finally {
			conn.close();	
		}
		return componenteRetorno;
	}
	//Listar 1 Componente
	@Override
	public DTOComponente obtenerComponente(int id_componente, int id_ambiente) throws SQLException {
		
		String sql = "select c.id_componente , c.nombre_componente, c.canal,f.id_funcionalidad, f.nombre_funcionalidad,\r\n" + 
				"				gf.id_grupo_funcional, gf.nombre_grupo_funcional,a.id_ambiente, a.nombre_ambiente,a.ip_ambiente,rca.ruta \r\n" + 
				"				from REL_COMPONENTE_AMBIENTE rca\r\n" + 
				"				join COMPONENTE c on rca.ID_COMPONENTE = c.ID_COMPONENTE\r\n" + 
				"				join AMBIENTE a on rca.ID_AMBIENTE = a.ID_AMBIENTE\r\n" + 
				"				join GRUPO_FUNCIONAL gf on c.ID_GRUPO_FUNCIONAL = gf.ID_GRUPO_FUNCIONAL\r\n" + 
				"				join FUNCIONALIDAD f on c.ID_FUNCIONALIDAD = f.ID_FUNCIONALIDAD \r\n" + 
				"				where rca.id_componente = ? and rca.id_ambiente = ?";
		
		DTOComponente componente = null;
		Connection conn = Conexion.conn();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id_componente);
		ps.setInt(2, id_ambiente);
		ResultSet rs = ps.executeQuery();

		try {
			if (rs.next()) {
				Integer id_func = rs.getInt("id_funcionalidad");//
				String nomb_funcionalidad = rs.getString("nombre_funcionalidad");//
				Integer id_gr_func = rs.getInt("id_grupo_funcional");//
				String nomb_gr_funcionalidad = rs.getString("nombre_grupo_funcional");//
				Integer id_amb = rs.getInt("id_ambiente");
				String nom_amb = rs.getString("nombre_ambiente");
				String ip = rs.getString("ip_ambiente");
				String ruta = rs.getString("ruta");
				DTOListaOpciones listaOpF = new DTOListaOpciones();
				DTOListaOpciones listaOpGf = new DTOListaOpciones();
				DTOAmbiente ambiente = new DTOAmbiente();
				DTOUbicacion ubicacion = new DTOUbicacion();
				List<DTOUbicacion> ubicaciones = new ArrayList<DTOUbicacion>();	
				listaOpF.setId(id_func);
				listaOpF.setNombre(nomb_funcionalidad);
				listaOpGf.setId(id_gr_func);
				listaOpGf.setNombre(nomb_gr_funcionalidad);
				ambiente.setId(id_amb);
				ambiente.setNombre(nom_amb);
				ambiente.setIp(ip);
				ubicacion.setRuta(ruta);
				ubicaciones.add(ubicacion);	
				componente = new DTOComponente();
				componente.setId(rs.getInt("id_componente"));
				componente.setNombre(rs.getString("nombre_componente"));
				componente.setCanal(rs.getString("canal"));
				componente.setFuncionalidad(listaOpF);
				componente.setGrupoFuncional(listaOpGf);
				componente.setAmbienteSubida(ambiente);
				componente.setUbicaciones(ubicaciones);
				System.out.println("Componente obtenido correctamente");
			}
		} catch (SQLException e) {
			System.out.println("Error al obtener componente");
			e.printStackTrace();
		} finally {
			rs.close();
			ps.close();
		}
		return componente;
	}
	
	//Eliminar Componente
	@Override
	public void eliminarComponente(int id) throws SQLException{
		
		String sql="delete from componente where id_componente = ? ";
		
		Connection conn = Conexion.conn();
		PreparedStatement ps = conn.prepareStatement(sql);

		try {
			ps.setInt(1, id);
			ps.execute();
			System.out.println("Componente eliminado correctamente");
		} catch (SQLException e) {
			System.out.println("Error al eliminar componente");
			e.printStackTrace();
		} finally {
			ps.close();
		}
		
		
		
	}

	//Insertar Componente
	@Override
	public DTOComponente postUbicacion(DTOComponente dtoComponente) {
			
		Connection co = null;
		PreparedStatement ps =null;
		PreparedStatement ps2 =null;
		Statement stm = null;
		ResultSet rs = null;
		String sql = 
				
			"insert into componente (nombre_componente,canal,id_funcionalidad, id_grupo_funcional)\r\n" + 
			"values(?,?,?,?)";	
					
		System.out.println("Envia query componente");
		DTOComponente dtoComponenteOut = new DTOComponente();
		try {
			co = Conexion.conn();
			 ps = co.prepareStatement(sql);
		
			//int i = 1;
			
			 //Traer id Funcionalidad y GrupoFuncional
			
			System.out.println(dtoComponente.getNombre());
			System.out.println(dtoComponente.getCanal());
			System.out.println(dtoComponente.getFuncionalidad().getId());
			System.out.println(dtoComponente.getGrupoFuncional().getId());
			//System.out.println(  dtoComponente.getUbicaciones().get(0).getRuta());
			//System.out.println(  dtoComponente.getUbicaciones().get(0).getAmbiente().getNombre());		
			
			
			ps.setString(1, dtoComponente.getNombre());
			ps.setString(2, dtoComponente.getCanal());
			//ps.setString(3, dtoComponente.getFuncionalidad().getNombre());
			ps.setInt(3, dtoComponente.getFuncionalidad().getId());
			//ps.setString(5, dtoComponente.getGrupoFuncional().getNombre());
			ps.setInt(4, dtoComponente.getGrupoFuncional().getId());
			
			//ps.setString(7, dtoComponente.getUbicaciones().get(0).getRuta());
			//ps.setInt(8, dtoComponente.getUbicaciones().get(0).getAmbiente().getId());
			
			dtoComponenteOut.setNombre(dtoComponente.getNombre());
			dtoComponenteOut.setCanal(dtoComponente.getCanal());
			
			
			//Ejecuta Query
			ps.executeQuery();
			System.out.println("Si ejecuta qry y pasa a buscar id");
			//dtoComponente.setId(1123);
			
			
			//BUSQUEDA ID DE COMPONENTE
			String sql2 = 
				
					"select * from componente c\r\n" + 
					"where c.nombre_componente= '" + dtoComponente.getNombre() +"' and c.canal= '"+ dtoComponente.getCanal() +"' \r\n" + 
					"and c.id_funcionalidad= '"+ dtoComponente.getFuncionalidad().getId() + "' and c.id_grupo_funcional= '"+dtoComponente.getGrupoFuncional().getId() +"'";
			/*		
			"SELECT * FROM componente " 
			+"WHERE nombre_componente = "+ dtoComponente.getNombre()+" and "
			+ "canal = " + dtoComponente.getCanal() +" and "
			+"id_grupo_funcional = " + dtoComponente.getGrupoFuncional().getId() +"' and "
			+ "id_funcionalidad = "+dtoComponente.getFuncionalidad().getId()+ "";
			*/
			 
			List<DTOComponente> componentes = new ArrayList<DTOComponente>();
			//co = Conexion.conn();
				stm = co.createStatement();
				rs = stm.executeQuery(sql2);
				
				while (rs.next()) {
				
				System.out.println("Ejecucion Correcta consulta Id"); 
				/*
				dtoComponente.setId(rs.getInt("id_prueba"));
				System.out.println(dtoComponente.getId());
				*/
			 	DTOComponente dtoComponente1 = new DTOComponente();
			 	dtoComponente1.setId(rs.getInt("id_componente"));
				
				componentes.add(dtoComponente1);
				System.out.println(dtoComponente1.getId());
				
				
				System.out.println("Id de componente obtenido correctamente");
				//dtoComponenteList.setComponentes(componentes);
				}
			//FIN BUSQUEDA ID COMPONENTE
			 
				
				//INSERTAR  REL AMBIENTE COMPONENTE
				
				String sql3 = 
						
						"insert into rel_componente_ambiente\r\n" + 
						"(id_componente, id_ambiente,ruta)\r\n" + 
						"values (?,?,?)";
				
				ps2 = co.prepareStatement(sql3);
				
				System.out.println("QRY INSERTAR REL AMBIENTE");
		
				System.out.println(  dtoComponente.getUbicaciones().get(0).getRuta());
					
				System.out.println("ID para insertar:");
				System.out.println( componentes.get(0).getId());
				ps2.setInt(1, componentes.get(0).getId());
				
				for(int i = 0; i < dtoComponente.getUbicaciones().size(); i++)
				{
					
					ps2.setInt(2, dtoComponente.getUbicaciones().get(i).getAmbiente().getId());
					ps2.setString(3, dtoComponente.getUbicaciones().get(i).getRuta());
					
					//Ejecuta Query
					ps2.executeQuery();
					System.out.println("Si ejecuta qry insertar rel Ambiente Componente");
				}
				
			
			
		System.out.println("Lista de componente agregada correctamente");
		ps.close();
		co.close();
	} catch (SQLException e) {
		System.out.println("Error al obtener la lista de componentes");
		e.printStackTrace();
	}
		
		
		return dtoComponenteOut;
	}

	//Listar componente
	@Override
	public DTOComponenteList getUbicacionComponentes(DTOComponente componente)  {
		
		Connection co = null;
		Statement stm = null;
		ResultSet rs = null;
		
		String sql = 
				
				"select c.id_componente, c.nombre_componente, c.canal,\r\n" + 
						"				f.id_funcionalidad, f.nombre_funcionalidad,\r\n" + 
						"				gf.id_grupo_funcional, gf.nombre_grupo_funcional,\r\n" + 
						"				a.id_ambiente, a.nombre_ambiente,ip_ambiente,\r\n" + 
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
						"order by c.nombre_componente asc,c.id_componente" ;
				
				/* Anterior
				"select c.id_componente , c.nombre_componente, c.canal,f.id_funcionalidad, f.nombre_funcionalidad,\r\n" + 
						"       gf.id_grupo_funcional, gf.nombre_grupo_funcional,a.id_ambiente, a.nombre_ambiente,a.ip_ambiente,rca.ruta       \r\n" + 
						"from REL_COMPONENTE_AMBIENTE rca \r\n" + 
						"join COMPONENTE c on rca.ID_COMPONENTE = c.ID_COMPONENTE\r\n" + 
						"join AMBIENTE a on rca.ID_AMBIENTE = a.ID_AMBIENTE\r\n" + 
						"join GRUPO_FUNCIONAL gf on c.ID_GRUPO_FUNCIONAL = gf.ID_GRUPO_FUNCIONAL\r\n" + 
						"join FUNCIONALIDAD f on c.ID_FUNCIONALIDAD = f.ID_FUNCIONALIDAD\r\n" + 
						"order by c.id_componente asc";*/
		
		System.out.println("Si ejecuta query");
		
		List<DTOComponente> componentes = new ArrayList<DTOComponente>();
		DTOComponenteList dtoComponenteList = new DTOComponenteList();
			try {
			co = Conexion.conn();
			stm = co.createStatement();
			rs = stm.executeQuery(sql);
			//ResultSet rs = routineUtils.driverManagerConnection(sql);
			System.out.println("Conexion Correcta bbdd");
			
			while (rs.next()) {
				
				//System.out.println(rs.getInt("cantidadEnDesarrollo"));
				//Agrega id y nombre componente
				DTOComponente dtoComponente1 = new DTOComponente();
				dtoComponente1.setId(rs.getInt("id_componente"));
				dtoComponente1.setNombre(rs.getString("nombre_componente"));
				//Agrega canal
				dtoComponente1.setCanal(rs.getString("canal"));
									
				//grupoFuncional
				DTOListaOpciones grupoFuncional = new DTOListaOpciones();
				grupoFuncional.setId(rs.getInt("id_grupo_funcional"));
				grupoFuncional.setNombre(rs.getString("nombre_grupo_funcional"));
				dtoComponente1.setGrupoFuncional(grupoFuncional);
				
				//Funcionalidad
				DTOListaOpciones funcionalidad = new DTOListaOpciones();
				funcionalidad.setId(rs.getInt("id_funcionalidad"));
				funcionalidad.setNombre(rs.getString("nombre_funcionalidad"));
				dtoComponente1.setFuncionalidad(funcionalidad);
				
				//Ambiente y ruta
				DTOAmbiente ambiente = new DTOAmbiente();
				DTOUbicacion ubicacion = new DTOUbicacion();
				
				ambiente.setId(rs.getInt("id_ambiente"));
				ambiente.setNombre(rs.getString("nombre_ambiente"));
				ambiente.setIp(rs.getString("ip_ambiente"));
				ubicacion.setAmbiente(ambiente);
				ubicacion.setRuta(rs.getString("ruta"));
				List<DTOUbicacion> ubicaciones = new ArrayList<DTOUbicacion>();
				ubicaciones.add(ubicacion);
				dtoComponente1.setUbicaciones(ubicaciones);
				
				//Cantidad en desarrollo
				dtoComponente1.setCantidad(rs.getInt("cantidadEnDesarrollo"));
				
				componentes.add(dtoComponente1);
			}
						
			System.out.println("Lista obtenida correctamente");
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error al obtener la lista de componentes");
			e.printStackTrace();
		}
				
			dtoComponenteList.setComponentes(componentes);
			
			return dtoComponenteList;
		
	  }

	

}
