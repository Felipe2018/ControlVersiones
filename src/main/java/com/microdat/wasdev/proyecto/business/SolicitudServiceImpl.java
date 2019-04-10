package com.microdat.wasdev.proyecto.business;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.microdat.wasdev.proyecto.dao.ISolicitudServiceDao;
import com.microdat.wasdev.proyecto.model.DTOSolicitud;

@Service("solicitudService")
public class SolicitudServiceImpl implements ISolicitudService {

	@Autowired
	private ISolicitudServiceDao solicitudServiceDao;
	
	@Override
	public List<DTOSolicitud> obtenerSolicitudes() throws SQLException {
		
		return solicitudServiceDao.obtenerSolicitudes();
	}

	@Override
	public DTOSolicitud insertarSolicitud(DTOSolicitud solicitud) throws SQLException {
		
		return solicitudServiceDao.insertarSolicitud(solicitud);
	}

	@Override
	public DTOSolicitud editarSolicitud(DTOSolicitud solicitud) throws SQLException {
		
		return solicitudServiceDao.editarSolicitud(solicitud);
	}

}
