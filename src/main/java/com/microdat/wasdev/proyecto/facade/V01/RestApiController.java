package com.microdat.wasdev.proyecto.facade.V01;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microdat.wasdev.proyecto.util.BusinessServiceException;
import com.microdat.wasdev.proyecto.util.RoutineUtils;
import com.microdat.wasdev.proyecto.business.ComponenteService;
import com.microdat.wasdev.proyecto.business.IMantenedoresService;
import com.microdat.wasdev.proyecto.business.IRelComponenteService;
import com.microdat.wasdev.proyecto.business.ISolicitudService;
import com.microdat.wasdev.proyecto.business.IUserService;
import com.microdat.wasdev.proyecto.business.ProyectoService;
import com.microdat.wasdev.proyecto.model.DTOAmbiente;
import com.microdat.wasdev.proyecto.model.DTOComponente;
import com.microdat.wasdev.proyecto.model.DTOEmpresa;
import com.microdat.wasdev.proyecto.model.DTOListaOpciones;
import com.microdat.wasdev.proyecto.model.DTOProyecto;
import com.microdat.wasdev.proyecto.model.DTOSolicitud;
import com.microdat.wasdev.proyecto.model.DTOUsuario;
import com.microdat.wasdev.proyecto.model.wrapper.DTOComponenteList;
import com.microdat.wasdev.proyecto.model.wrapper.DTOProyectoList;
//Axcel
@RestController
@RequestMapping("/V01")
@CrossOrigin(origins = "*")
public class RestApiController {

	
	@Autowired
	IUserService userService;
	
	@Autowired
	ISolicitudService solicitudService;
	
	@Autowired
	ComponenteService componenteService;
	
	@Autowired
	ProyectoService proyectoService;
	
	@Autowired
	IRelComponenteService relComponenteService;
	
	@Autowired
	IMantenedoresService mantenedoresService;
	
	
	@Autowired
	BusinessServiceException businessServiceException;
	
	@Autowired
	RoutineUtils routineUtils;

	//*******************LISTAR
	
	//LISTAR REL COMPONENTES  LISTO
	@GetMapping(path = "/relComponentes", produces = "application/json")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> getComponentesProyecto(
			@RequestParam(value="$filter", required = false) String filter) {
		
		DTOComponenteList dtoComponenteList = new DTOComponenteList();
		DTOComponente dtoComponente = new DTOComponente();
		//Filtro
		//DTOComponenteList dtoComponenteList = new DTOComponenteList();
		//DTOComponente dtoComponente = new DTOComponente();
		
		//DTOComponente proyecto = new DTOComponente();
		DTOSolicitud solicitud = new DTOSolicitud();
		DTOListaOpciones grupoFuncional = new DTOListaOpciones();
		DTOListaOpciones estado = new DTOListaOpciones();
		DTOListaOpciones funcionalidad = new DTOListaOpciones();
		
		String proyectoId =  StringUtils.EMPTY;
		String solicitudId = StringUtils.EMPTY;
		String grupoFuncionalId = StringUtils.EMPTY;
		String estadoId = StringUtils.EMPTY;
		String funcionalidadId = StringUtils.EMPTY;
		
		
		if (filter != null && !filter.equals("") && !filter.equals("()") ){
			proyectoId = routineUtils.valorDesdeFiltro(filter, "proyectoId");
			solicitudId = routineUtils.valorDesdeFiltro(filter, "solicitudId");
			grupoFuncionalId = routineUtils.valorDesdeFiltro(filter, "grupoFuncionalId");
			estadoId = routineUtils.valorDesdeFiltro(filter, "estadoId");
			funcionalidadId = routineUtils.valorDesdeFiltro(filter, "funcionalidadId");
			
		}/*else {
			return businessServiceException.getError("filterEmpty");
		}*/
		
		/*
		if(solicitudId.equals(StringUtils.EMPTY)) {
			return businessServiceException.getError("mandatoryParametersMissingExplicit", "solicitudId");
		}else {
			try {
				solicitud.setId(solicitudId);
				//solicitud.setId(Integer.parseInt(solicitudId));
			}catch (NumberFormatException e){
				return businessServiceException.getError("paramValueIsNotAsExpected", "solicitudId", "numéricos");
			}
		}*/
		//------------
		if(!proyectoId.equals(StringUtils.EMPTY)) {
			try {
				dtoComponente.setIdProyecto(Integer.parseInt(proyectoId));
			}catch (NumberFormatException e){
				return businessServiceException.getError("paramValueIsNotAsExpected", "proyectoId", "numéricos");
			}
		}
		
		/*
		if(!solicitudId.equals(StringUtils.EMPTY)) {
			try {
				solicitud.setId(solicitudId);
			}catch (NumberFormatException e){
				return businessServiceException.getError("paramValueIsNotAsExpected", "solicitudId", "numéricos");
			}
		}
		//------------
		
		if(!grupoFuncionalId.equals(StringUtils.EMPTY)) {
			try {
				grupoFuncional.setId(Integer.parseInt(grupoFuncionalId));
			}catch (NumberFormatException e){
				return businessServiceException.getError("paramValueIsNotAsExpected", "grupoFuncionalId", "numéricos");
			}
		}
		
		if(!estadoId.equals(StringUtils.EMPTY)) {
			try {
				estado.setId(Integer.parseInt(estadoId));
			}catch (NumberFormatException e){
				return businessServiceException.getError("paramValueIsNotAsExpected", "estadoId", "numéricos");
			}
		}
		
		if(!funcionalidadId.equals(StringUtils.EMPTY)) {
			try {
				funcionalidad.setId(Integer.parseInt(funcionalidadId));
			}catch (NumberFormatException e){
				return businessServiceException.getError("paramValueIsNotAsExpected", "funcionalidadId", "numéricos");
			}
		}*/
		
	
		//dtoComponente.setIdProyecto(proyecto);
		//dtoComponente.setSolicitud(solicitud);;
		/*
		dtoComponente.setGrupoFuncional(grupoFuncional);
		dtoComponente.setEstado(estado);
		dtoComponente.setFuncionalidad(funcionalidad);*/
		
		
		dtoComponenteList = relComponenteService.getComponentesProyecto(dtoComponente);
		
		return new ResponseEntity<DTOComponenteList>(dtoComponenteList,HttpStatus.OK);
		
	}
	
	//LISTAR 1 REL COMPONENTES  LISTO
		
		@GetMapping(path = "/relComponentes/{id}", produces = "application/json")
		@CrossOrigin(origins = "*")
		public ResponseEntity<?> getComponente(
				@PathVariable("id") Integer id
				) {
			
			DTOComponenteList dtoComponenteList = new DTOComponenteList();
			DTOComponente dtoComponente = new DTOComponente();
			
			try {
				dtoComponente.setId(id);
			}catch (NumberFormatException e){
				return businessServiceException.getError("paramValueIsNotAsExpected", "id", "numéricos");
			}
			
			dtoComponenteList = relComponenteService.getComponente(dtoComponente);
			
			return new ResponseEntity<DTOComponenteList>(dtoComponenteList,HttpStatus.OK);
			
		}
	
	//INSERTAR REL COMPONENTE
		@PostMapping(path = "/{id}/relComponentes", consumes = "application/json", produces = "application/json")
		public ResponseEntity<?> postComponenteProyectos(
				@PathVariable("id") Integer id,
				@RequestBody DTOComponente dtoComponente, DTOProyecto dtoProyecto) {
			System.out.println("Entra a servicio Insertar Rel Componente");
			
			DTOComponente dtoComponenteOut = new DTOComponente();
			//DTOProyecto dtoProyecto =new DTOProyecto();
			
			try {
				dtoProyecto.setId(id);
			}catch (NumberFormatException e){
				return businessServiceException.getError("paramValueIsNotAsExpected", "id", "numéricos");
			}
			
			
			if(dtoComponente == null) {
				return businessServiceException.getError("mandatoryParametersMissingExplicit", "componente");
			}else if(dtoComponente.getId() == null ) {
				return businessServiceException.getError("mandatoryParametersMissingExplicit", "componente.id");
			}else if(dtoComponente.getSolicitud().getId()== null ) {
				return businessServiceException.getError("mandatoryParametersMissingExplicit", "componente.id_solicitud");
			}else if(dtoComponente.getUbicaciones().get(0).getAmbiente().getId()== null ) {
				return businessServiceException.getError("mandatoryParametersMissingExplicit", "componente.idambiente");
			}
			
			dtoComponenteOut = relComponenteService.postComponenteProyectos(dtoComponente,dtoProyecto);
			//dtoComponenteList = userService.getComponentesProyecto(dtoComponenteOut);
			return new ResponseEntity<DTOComponente>(dtoComponenteOut,HttpStatus.OK);
		}

	//ELIMINAR rel_proy_componente
	@RequestMapping(value = "relComponentes/{id_componente}/{id_proyecto}", method = { RequestMethod.DELETE })
	public @ResponseBody void eliminarRelProyComponente(@PathVariable(value = "id_componente") int id_componente,
			@PathVariable(value = "id_proyecto") int id_proyecto, @PathVariable(value = "id") int id) throws SQLException {
		relComponenteService.eliminarRelProyComponente(id_componente, id_proyecto);
	}
		
//*********************************************************		
		
	//LISTAR COMPONENTES - UBICACION LISTO
		@GetMapping(path = "/ubicacion/componentes", produces = "application/json")
		public ResponseEntity<?> getUbicacionComponentes(
				//@PathVariable("id") String id
				) {
			
			
			DTOComponenteList dtoComponenteList = new DTOComponenteList();
			DTOComponente dtoComponente = new DTOComponente();
			/*
			try {
				dtoComponente.setId(Integer.parseInt(id));
			}catch (NumberFormatException e){
				return businessServiceException.getError("paramValueIsNotAsExpected", "id", "numéricos");
			}
			*/
			dtoComponenteList = componenteService.getUbicacionComponentes(dtoComponente);
			
			return new ResponseEntity<DTOComponenteList>(dtoComponenteList,HttpStatus.OK);
			
		}
	
	//LISTAR 1 COMPONENTE - UBICACION LISTO
	@GetMapping(path = "/ubicacion/componentes/{id_componente}/{id_ambiente}", produces = "application/json")
	public ResponseEntity<?> getObtenerComponente(
			@PathVariable(value = "id_componente") int id_componente, @PathVariable(value="id_ambiente") int id_ambiente) throws SQLException {
		
			DTOComponente componente = componenteService.obtenerComponente(id_componente, id_ambiente);
		
		
		return new ResponseEntity<DTOComponente>(componente,HttpStatus.OK);
		
	}
	
	//INSERTAR COMPONENTE - UBICACION LISTO
		@PostMapping(path = "/ubicacion/componentes", consumes = "application/json", produces = "application/json")
		public ResponseEntity<?> postUbicacion(
				@RequestBody DTOComponente dtoComponente) {
			
			//DTOComponenteList dtoComponenteList = new DTOComponenteList();
			DTOComponente dtoComponenteOut = new DTOComponente();
			
			System.out.println("Entra a servicio Componente");
			
			if(dtoComponente == null) {
				return businessServiceException.getError("mandatoryParametersMissingExplicit", "componente");
			}else if(dtoComponente.getNombre() == null || dtoComponente.getNombre().equals(StringUtils.EMPTY)) {
				return businessServiceException.getError("mandatoryParametersMissingExplicit", "componente.nombre");
			}else if(dtoComponente.getCanal() == null || dtoComponente.getCanal().equals(StringUtils.EMPTY)) {
				return businessServiceException.getError("mandatoryParametersMissingExplicit", "componente.canal");
			}
			
			dtoComponenteOut = componenteService.postUbicacion(dtoComponente);
			//dtoComponenteList = userService.getComponentesProyecto(dtoComponenteOut);
			return new ResponseEntity<DTOComponente>(dtoComponenteOut,HttpStatus.OK);
		}
		
	//ACTUALIZAR COMPONENTE - UBICACION LISTO
	@RequestMapping(path = "/ubicacion/componentes/{id_comp}/{id_amb}", produces = "application/json", consumes = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<?> actualizarComponente( @PathVariable(value = "id_comp") int id_comp,
			@PathVariable(value = "id_amb") int id_amb, @RequestBody DTOComponente componente) throws SQLException{
		
		DTOAmbiente amb = new DTOAmbiente();
		amb.setId(id_amb);
		componente.setId(id_comp);
		componente.setAmbienteSubida(amb);
		
		componenteService.actualizarComponente(componente);
		return new ResponseEntity<DTOComponente>(componente,HttpStatus.OK);
		
	}
		
	//ELIMINAR COMPONENTE - UBICACION LISTO
	@RequestMapping(value="/componentes/{id_componente}", method = {RequestMethod.DELETE})
	public @ResponseBody void eliminar(@PathVariable(value = "id_componente") int id_componente) throws SQLException {
			componenteService.eliminarComponente(id_componente);
		
		}	
			
//*********************************************************		
		
	//LISTAR PROYECTOS LISTO
	@GetMapping(path = "", produces = "application/json")
	public ResponseEntity<?> getProyectos(
		
			) {
		
		DTOProyectoList dtoProyectoList = new DTOProyectoList();
		DTOProyecto dtoProyecto = new DTOProyecto();
	
		dtoProyectoList = proyectoService.getProyectos(dtoProyecto);
		
		return new ResponseEntity<DTOProyectoList>(dtoProyectoList,HttpStatus.OK);
	}
	
	//LISTAR 1 PROYECTO LISTO
		@GetMapping(path = "/{id}", produces = "application/json")
		public ResponseEntity<?> getProyecto(
				@PathVariable("id") Integer id) {
		
			DTOProyecto dtoProyecto = new DTOProyecto();
			
			try {
				dtoProyecto.setId(id);
			}catch (NumberFormatException e){
				return businessServiceException.getError("paramValueIsNotAsExpected", "id", "numéricos");
			}
			
			dtoProyecto = proyectoService.getProyecto(id);
		
			return new ResponseEntity<DTOProyecto>(dtoProyecto,HttpStatus.OK);	
		}
	
	//INSERTAR PROYECTO LISTO
			@PostMapping(path = "/proyecto", consumes = "application/json", produces = "application/json")
			public ResponseEntity<?> postProyecto(
					@RequestBody DTOProyecto dtoProyecto) {
				DTOProyecto dtoProyectoOut = new DTOProyecto();
				
				System.out.println("Entra a servicio Insertar Proyecto");
				
				if(dtoProyecto == null) {
					return businessServiceException.getError("mandatoryParametersMissingExplicit", "proyecto");
				}else if(dtoProyecto.getNombreProyecto() == null || dtoProyecto.getNombreProyecto().equals(StringUtils.EMPTY)) {
					return businessServiceException.getError("mandatoryParametersMissingExplicit", "proyecto.nombre");
				}else if(dtoProyecto.getCodigo()== null || dtoProyecto.getCodigo().equals(StringUtils.EMPTY)) {
					return businessServiceException.getError("mandatoryParametersMissingExplicit", "proyecto.codigo");
				}else if(dtoProyecto.getComentario()== null ) {
					return businessServiceException.getError("mandatoryParametersMissingExplicit", "proyecto.comentario");
				}else if(dtoProyecto.getResponsable().getId()== null ) {
					return businessServiceException.getError("mandatoryParametersMissingExplicit", "proyecto.id_Responsable");
				}else if(dtoProyecto.getTipo()== null ) {
					return businessServiceException.getError("mandatoryParametersMissingExplicit", "proyecto.tipo");
				}
				
				dtoProyectoOut = proyectoService.postProyecto(dtoProyecto);
				//dtoComponenteList = userService.getComponentesProyecto(dtoComponenteOut);
				return new ResponseEntity<DTOProyecto>(dtoProyectoOut,HttpStatus.OK);
			}
	
	//MODIFICAR PROYECTO
		@PutMapping(path = "/proyecto/{id_proyecto}", consumes = "application/json", produces = "application/json")
		public ResponseEntity<?> putProyecto(@PathVariable(value="id_proyecto") int id,
				@RequestBody DTOProyecto dtoProyecto){
			
			DTOProyecto dtoProyectoOut = new DTOProyecto();
			
			System.out.println("Entra a servicio Modificar Proyecto");
			
			dtoProyectoOut = proyectoService.putProyecto(dtoProyecto);
			
			return new ResponseEntity<DTOProyecto>(dtoProyectoOut,HttpStatus.OK);
		
		}
			
//********************************************************
			

//*******************************************************
	// listar solicitudes
		@GetMapping(path = "/solicitud", produces = "application/json")
	public ResponseEntity<?> obtenerSolicitudes(//@PathVariable("id") int id
			) throws SQLException {
			List<DTOSolicitud> solicitudes = solicitudService.obtenerSolicitudes();
			if (solicitudes.size() == 0) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(solicitudes, HttpStatus.OK);
		}
		
		//LISTAR 1 SOLICITUD
		@GetMapping(path = "/{id}/solicitud", produces = "application/json")
	public ResponseEntity<?> getSolicitud(
			@PathVariable("id") Integer id) {
	
		DTOSolicitud dtoSolicitud = new DTOSolicitud();
		DTOProyecto dtoProyecto = new DTOProyecto();
		try {
			dtoProyecto.setId(id);
		}catch (NumberFormatException e){
			return businessServiceException.getError("paramValueIsNotAsExpected", "id", "numéricos");
		}
		
		dtoSolicitud = solicitudService.getSolicitud(id);
	
		return new ResponseEntity<DTOSolicitud>(dtoSolicitud,HttpStatus.OK);	
	}
		

	// insertar solicitud
	@RequestMapping(value = "/solicitud", produces = "application/json", consumes = "application/json", method = {
			RequestMethod.POST })
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> postSolicitud( @RequestBody DTOSolicitud solicitud)
			throws SQLException {
		// return solicitudService.insertarSolicitud(solicitud);
		DTOSolicitud dtoSolicitud = new DTOSolicitud();
		dtoSolicitud = solicitudService.insertarSolicitud(solicitud);
		//dtoSolicitud.setId(solicitud.getId());

		return new ResponseEntity<DTOSolicitud>(dtoSolicitud, HttpStatus.OK);
	}

	// editar solicitud
	@RequestMapping(value = "/solicitud/{id_solicitud}", produces = "application/json", consumes = "application/json", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
	public DTOSolicitud editar( @PathVariable(value = "id_solicitud") String id_solicitud,
				@RequestBody DTOSolicitud solicitud) throws SQLException {
			solicitud.setId(id_solicitud);
			return solicitudService.editarSolicitud(solicitud);
		}
	
//*********************************************
	// LISTAR EMPRESAS
	@GetMapping(path = "/componente/solicitud/usuarios/empresas", produces = "application/json")
	public ResponseEntity<?> obtenerEmpresas() throws SQLException {
		List<DTOEmpresa> empresas = userService.obtenerEmpresas();
		if (empresas.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(empresas, HttpStatus.OK);
	}
	
	//LISTAR USUARIO
		@GetMapping(path = "/componente/solicitud/usuarios", produces = "application/json")
	public ResponseEntity<?> obtenerUsuarios() throws SQLException {
			List<DTOUsuario> usuarios = userService.obtenerUsuarios();
			if (usuarios.size() == 0) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(usuarios, HttpStatus.OK);
		}	

	// insertar usuario
		@PostMapping(path = "/componente/solicitud/usuarios", consumes = "application/json", produces = "application/json")
		public ResponseEntity<?> insertarUsuario( @RequestBody DTOUsuario usuario) throws SQLException {
			
			System.out.println("Ingresa a Servicio insertar");
			DTOUsuario dtoUsuario = new DTOUsuario();
			dtoUsuario = userService.insertarUsuario(usuario);
			
	
			return new ResponseEntity<DTOUsuario>(dtoUsuario, HttpStatus.CREATED);
		}
	
		// editar usuario
		@PutMapping(path = "/componente/solicitud/usuarios/{id}", consumes = "application/json", produces = "application/json")
		//@RequestMapping(value = "/componente/solicitud/usuarios/{id}", produces = "application/json", consumes = "application/json", method = {RequestMethod.PUT})
		public ResponseEntity<?> editarUsuario( @PathVariable(value = "id") int id, @RequestBody DTOUsuario usuario) throws SQLException {
				System.out.println("Ingresa a Servicio Modificar");
			DTOUsuario dtoUsuario = new DTOUsuario();
			dtoUsuario.setId(id);
			dtoUsuario = userService.actualizarUsuario(usuario);
			//dtoUsuario.setId(id);
			
			return new ResponseEntity<DTOUsuario>(dtoUsuario, HttpStatus.CREATED);
		
		}
		
	
		@GetMapping(path = "/componentes/{id}", produces = "application/json")
		public ResponseEntity<?> getComponentesSolicitud(@PathVariable("id") Integer id,
				@RequestParam(value="$filter", required = false) String filter) {
			
			
			System.out.println(filter);
			DTOComponenteList dtoComponenteList = new DTOComponenteList();
			DTOComponente dtoComponente = new DTOComponente();
			
			DTOSolicitud solicitud = new DTOSolicitud();
			DTOListaOpciones grupoFuncional = new DTOListaOpciones();
			
			String solicitudId = StringUtils.EMPTY;
			String grupoFuncionalId = StringUtils.EMPTY;
			String duplicados = StringUtils.EMPTY;
			System.out.println("entra igual");
			if (filter != null && !filter.equals("") && !filter.equals("()") ){
				System.out.println("valida filtro");
			
				duplicados = routineUtils.valorDesdeFiltro(filter, "duplicados");
				solicitudId = routineUtils.valorDesdeFiltro(filter, "solicitudId");
				grupoFuncionalId = routineUtils.valorDesdeFiltro(filter, "grupoFuncionalId");
			
				
			}/*else {
				return businessServiceException.getError("filterEmpty");
			}*/
			
			/*
			if(solicitudId.equals(StringUtils.EMPTY)) {
				return businessServiceException.getError("mandatoryParametersMissingExplicit", "solicitudId");
			}else {
				try {
					solicitud.setId(solicitudId);
					//solicitud.setId(Integer.parseInt(solicitudId));
				}catch (NumberFormatException e){
					return businessServiceException.getError("paramValueIsNotAsExpected", "solicitudId", "numéricos");
				}
			}*/
		
			if(!grupoFuncionalId.equals(StringUtils.EMPTY)) {
				try {
					grupoFuncional.setId(Integer.parseInt(grupoFuncionalId));
				}catch (NumberFormatException e){
					return businessServiceException.getError("paramValueIsNotAsExpected", "grupoFuncionalId", "numéricos");
				}
			}
		
			dtoComponente.setSolicitud(solicitud);
			dtoComponente.setGrupoFuncional(grupoFuncional);
			
			dtoComponenteList = userService.getComponentesSolicitud(id, dtoComponente,duplicados);
			
			return new ResponseEntity<DTOComponenteList>(dtoComponenteList,HttpStatus.OK);
			
		}
		
//********************************************	
		

		// listar fase proyecyo
		@GetMapping(path = "/faseProyecto", produces = "application/json")
		public ResponseEntity<?> obtenerFaseProyecto() throws SQLException {
			DTOProyectoList dtoProyectoList = new DTOProyectoList();
			DTOProyecto dtoProyecto = new DTOProyecto();
			
			dtoProyectoList = mantenedoresService.obtenerFaseProyecto(dtoProyecto);
			
			return new ResponseEntity<DTOProyectoList>(dtoProyectoList,HttpStatus.OK);
				
		}
		
		// listar ambientes
		@GetMapping(path = "/ambiente", produces = "application/json")
		public ResponseEntity<?> obtenerAmbiente() throws SQLException {
			DTOComponenteList dtoComponenteList= new DTOComponenteList();
			DTOComponente dtoComponente= new DTOComponente();
			
			dtoComponenteList = mantenedoresService.obtenerAmbiente(dtoComponente);
			
			return new ResponseEntity<DTOComponenteList>(dtoComponenteList,HttpStatus.OK);
				
		}
		
		// listar funcionalidades
		@GetMapping(path = "/funcionalidad", produces = "application/json")
		public ResponseEntity<?> obtenerFuncionalidad() throws SQLException {
			DTOComponenteList dtoComponenteList= new DTOComponenteList();
			DTOComponente dtoComponente= new DTOComponente();
			dtoComponenteList = mantenedoresService.obtenerFuncionalidad(dtoComponente);
							
			return new ResponseEntity<DTOComponenteList>(dtoComponenteList,HttpStatus.OK);
		}
		
		// listar grupos funcionales
		@GetMapping(path = "/grupoFuncional", produces = "application/json")
		public ResponseEntity<?> obtenerGrupoFuncional() throws SQLException {
			DTOComponenteList dtoComponenteList= new DTOComponenteList();
			DTOComponente dtoComponente= new DTOComponente();
			dtoComponenteList = mantenedoresService.obtenerGrupoFuncional(dtoComponente);
									
			return new ResponseEntity<DTOComponenteList>(dtoComponenteList,HttpStatus.OK);
		}
			
		
		
}