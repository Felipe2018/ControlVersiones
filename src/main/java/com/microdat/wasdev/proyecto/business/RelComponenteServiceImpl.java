package com.microdat.wasdev.proyecto.business;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microdat.wasdev.proyecto.dao.IRelComponenteServiceDao;
import com.microdat.wasdev.proyecto.model.DTOComponente;
import com.microdat.wasdev.proyecto.model.DTOProyecto;
import com.microdat.wasdev.proyecto.model.wrapper.DTOComponenteList;

@Service("RelComponenteService")
public class RelComponenteServiceImpl implements IRelComponenteService {

	@Autowired
	IRelComponenteServiceDao relComponenteServiceDao;

	
	@Override
	public DTOComponenteList getComponentesProyecto(DTOComponente componente) {
		System.out.println("Id Proyecto ingresado   "+componente.getIdProyecto());
		
		
		/*
		if(componente.getGrupoFuncional().getId()==null) {
			
		}else {
			System.out.println("Grupo Funcional :"+componente.getGrupoFuncional().getId());
		}
		if(componente.getEstado().getId()==null) {
			
		}else {
			System.out.println("Estado: "+componente.getEstado().getId());
		}
		if(componente.getFuncionalidad().getId()==null) {
			
		}else {
			System.out.println("Funcionalidad: "+componente.getFuncionalidad().getId());
		}*/
		
		
		
		if(componente.getIdProyecto()==null) {
			System.out.println("Entra normal" );
			return relComponenteServiceDao.getComponentesProyecto(componente);
		}else {
			System.out.println("Entra filtrado ");
			return relComponenteServiceDao.getComponentesProyectoFirltrar(componente);
		}
		
		//return relComponenteServiceDao.getComponentesProyecto(componente);
	}
	
	@Override
	public DTOComponenteList getComponente(DTOComponente componente) {
		return relComponenteServiceDao.getComponente(componente);
	}
	
	@Override
	public DTOComponente postComponenteProyectos(DTOComponente componente,DTOProyecto dtoProyecto) {
		return relComponenteServiceDao.postComponenteProyectos(componente,dtoProyecto);
	}
	
	@Override
	public void eliminarRelProyComponente(int id_componente, int id_proyecto) throws SQLException{
		relComponenteServiceDao.eliminarRelProyComponente(id_componente, id_proyecto);
	}
}

