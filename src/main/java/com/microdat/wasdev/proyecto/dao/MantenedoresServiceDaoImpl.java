package com.microdat.wasdev.proyecto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.microdat.wasdev.proyecto.model.DTOAmbiente;
import com.microdat.wasdev.proyecto.model.DTOComponente;
import com.microdat.wasdev.proyecto.model.DTOListaOpciones;
import com.microdat.wasdev.proyecto.model.DTOProyecto;
import com.microdat.wasdev.proyecto.model.wrapper.DTOComponenteList;
import com.microdat.wasdev.proyecto.model.wrapper.DTOProyectoList;

@Service("IMantenedoresServiceDao")
public class MantenedoresServiceDaoImpl implements IMantenedoresServiceDao {

	@Override
	public DTOProyectoList obtenerFaseProyecto(DTOProyecto dtoProyecto) throws SQLException {
		
		String query="select * from fase_proyecto "
				+ "order by id_fase_proyecto";
		Connection conn = Conexion.conn();
		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		
		List<DTOProyecto> proyectos = new ArrayList<DTOProyecto>();
		DTOProyectoList dtoproyectoList = new DTOProyectoList();
		
		try {
			while(rs.next()) {
				DTOProyecto dtoProyectoCarga = new DTOProyecto();
				
				DTOListaOpciones faseProyecto = new DTOListaOpciones();
				faseProyecto.setId(rs.getInt("id_fase_proyecto"));
				faseProyecto.setNombre(rs.getString("nombre_fase_proyecto"));
				dtoProyectoCarga.setFaseDesarrollo(faseProyecto);
				
				proyectos.add(dtoProyectoCarga);
			}
			System.out.println("Lista de fases de desarrollo obtenida correctamente");
			
		}catch (SQLException e) {
			System.out.println("Error al obtener la lista de fases de proyecto");
			e.printStackTrace();
		}finally {
			rs.close();
			ps.close();
			conn.close();
		}
		
		dtoproyectoList.setProyectos(proyectos);
		return  dtoproyectoList;
	}

	@Override
	public DTOComponenteList obtenerAmbiente(DTOComponente dtoComponente) throws SQLException {
		
		String query="select * from ambiente "
				+ "order by id_ambiente";
		Connection conn = Conexion.conn();
		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		List<DTOComponente> componentes = new ArrayList<DTOComponente>();
		DTOComponenteList dtoComponenteList = new DTOComponenteList();
		
		try {
			while(rs.next()) {
				DTOComponente dtoComponenteCarga = new DTOComponente();
				DTOAmbiente dtoAmbiente = new DTOAmbiente();
				dtoAmbiente.setId(rs.getInt("id_ambiente"));
				dtoAmbiente.setNombre(rs.getString("nombre_ambiente"));
				dtoAmbiente.setIp(rs.getNString("ip_ambiente"));
				dtoComponenteCarga.setAmbienteSubida(dtoAmbiente);
				
				componentes.add(dtoComponenteCarga);
				
			}
			System.out.println("Lista de ambientes de desarrollo obtenida correctamente");
			
		}catch (SQLException e) {
			System.out.println("Error al obtener la lista de ambiente de desarrollo");
			e.printStackTrace();
		}finally {
			rs.close();
			ps.close();
			conn.close();
		}
		dtoComponenteList.setComponentes(componentes);
		return dtoComponenteList;
	}

	@Override
	public DTOComponenteList obtenerFuncionalidad(DTOComponente dtoComponente) throws SQLException {
		
		String query="select * from funcionalidad "
				+ "order by id_funcionalidad";
		Connection conn = Conexion.conn();
		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		List<DTOComponente> componentes = new ArrayList<DTOComponente>();
		DTOComponenteList dtoComponenteList = new DTOComponenteList();
		
		try {
			while(rs.next()) {
				DTOComponente dtoComponenteCarga = new DTOComponente();
				DTOListaOpciones funcionalidad = new DTOListaOpciones();
				
				funcionalidad.setId(rs.getInt("id_funcionalidad"));
				funcionalidad.setNombre(rs.getString("nombre_funcionalidad"));
				dtoComponenteCarga.setFuncionalidad(funcionalidad);
				componentes.add(dtoComponenteCarga);
			}
			System.out.println("Lista de funcionalidades obtenida correctamente");
			
		}catch (SQLException e) {
			System.out.println("Error al obtener la lista de funcionalidades");
			e.printStackTrace();
		}finally {
			rs.close();
			ps.close();
			conn.close();
		}
		
		dtoComponenteList.setComponentes(componentes);
		return dtoComponenteList;
	}

	@Override
	public DTOComponenteList obtenerGrupoFuncional(DTOComponente dtoComponente) throws SQLException {
		
		String query="select * from grupo_funcional "
				+ "order by id_grupo_funcional";
		Connection conn = Conexion.conn();
		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		List<DTOComponente> componentes = new ArrayList<DTOComponente>();
		DTOComponenteList dtoComponenteList = new DTOComponenteList();
		
		try {
			while(rs.next()) {
				DTOComponente dtoComponenteCarga = new DTOComponente();
				DTOListaOpciones grupoFuncional = new DTOListaOpciones();
				
				grupoFuncional.setId(rs.getInt("id_grupo_funcional"));
				grupoFuncional.setNombre(rs.getString("nombre_grupo_funcional"));
				dtoComponenteCarga.setFuncionalidad(grupoFuncional);
				componentes.add(dtoComponenteCarga);
			}
			System.out.println("Lista de grupo funcional obtenida correctamente");
			
		}catch (SQLException e) {
			System.out.println("Error al obtener la lista de grupo funcional");
			e.printStackTrace();
		}finally {
			rs.close();
			ps.close();
			conn.close();
		}
		
		dtoComponenteList.setComponentes(componentes);
		return dtoComponenteList;
	}

}
