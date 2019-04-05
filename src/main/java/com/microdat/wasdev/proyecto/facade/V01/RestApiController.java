package com.microdat.wasdev.proyecto.facade.V01;

import java.sql.SQLException;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.microdat.wasdev.proyecto.util.BusinessServiceException;
import com.microdat.wasdev.proyecto.util.RoutineUtils;
import com.microdat.wasdev.proyecto.business.ComponenteService;
import com.microdat.wasdev.proyecto.business.ProyectoService;
import com.microdat.wasdev.proyecto.business.RelComponenteService;
import com.microdat.wasdev.proyecto.business.UserService;
import com.microdat.wasdev.proyecto.model.DTOAmbiente;
import com.microdat.wasdev.proyecto.model.DTOComponente;
import com.microdat.wasdev.proyecto.model.DTOProyecto;
import com.microdat.wasdev.proyecto.model.DTOUsuario;
import com.microdat.wasdev.proyecto.model.wrapper.DTOComponenteList;
import com.microdat.wasdev.proyecto.model.wrapper.DTOProyectoList;
//Axcel
@RestController
@RequestMapping("/V01")
public class RestApiController {

	@Autowired
	UserService userService;
	
	@Autowired
	ComponenteService componenteService;
	
	@Autowired
	ProyectoService proyectoService;
	
	@Autowired
	RelComponenteService relComponenteService;
	
	@Autowired
	BusinessServiceException businessServiceException;
	
	@Autowired
	RoutineUtils routineUtils;

	//*******************LISTAR
	
	//LISTAR REL COMPONENTES  LISTO
	@GetMapping(path = "/componentes", produces = "application/json")
	public ResponseEntity<?> getComponentesProyecto() {
		
		DTOComponenteList dtoComponenteList = new DTOComponenteList();
		DTOComponente dtoComponente = new DTOComponente();
		
		dtoComponenteList = relComponenteService.getComponentesProyecto(dtoComponente);
		
		return new ResponseEntity<DTOComponenteList>(dtoComponenteList,HttpStatus.OK);
		
	}
	
	//LISTAR 1 REL COMPONENTES  LISTO
		
		@GetMapping(path = "/relcomponente/{id}", produces = "application/json")
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
		@PostMapping(path = "/{id}/componentes", consumes = "application/json", produces = "application/json")
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
	@GetMapping(path = "/{id}/ubicacion/componentes/{id_componente}/{id_ambiente}", produces = "application/json")
	public ResponseEntity<?> getObtenerComponente(
			@PathVariable(value = "id_componente") int id_componente,@PathVariable("id") int id, @PathVariable(value="id_ambiente") int id_ambiente) throws SQLException {
		
			DTOComponente componente = componenteService.obtenerComponente(id_componente, id_ambiente);
		
		
		return new ResponseEntity<DTOComponente>(componente,HttpStatus.OK);
		
	}
	
	//INSERTAR COMPONENTE - UBICACION LISTO
		@PostMapping(path = "/componentes/ubicacion", consumes = "application/json", produces = "application/json")
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
	@RequestMapping(path = "{id}/componentes/{id_comp}/{id_amb}", produces = "application/json", consumes = "application/json", method = RequestMethod.PUT)
	public ResponseEntity<?> actualizarComponente(@PathVariable(value="id") int id, @PathVariable(value = "id_comp") int id_comp,
			@PathVariable(value = "id_amb") int id_amb, @RequestBody DTOComponente componente) throws SQLException{
		
		DTOAmbiente amb = new DTOAmbiente();
		amb.setId(id_amb);
		componente.setId(id_comp);
		componente.setAmbienteSubida(amb);
		
		componenteService.actualizarComponente(componente);
		return new ResponseEntity<DTOComponente>(componente,HttpStatus.OK);
		
	}
		
	//ELIMINAR COMPONENTE - UBICACION LISTO
	@RequestMapping(value="{id}/componentes/{id_componente}", method = {RequestMethod.DELETE})
	public @ResponseBody void eliminar(@PathVariable(value = "id_componente") int id_componente, @PathVariable(value="id") int id) throws SQLException {
			componenteService.eliminarComponente(id_componente);
		
		}	
		
		
//*********************************************************		
		
	//LISTAR PROYECTOS LISTO
	@GetMapping(path = "/proyectos", produces = "application/json")
	public ResponseEntity<?> getProyectos(
			//@PathVariable("id") String id
			) {
		
		
		DTOProyectoList dtoProyectoList = new DTOProyectoList();
		DTOProyecto dtoProyecto = new DTOProyecto();
		
		//DTOComponente dtoComponente = new DTOComponente();
		/*
		try {
			dtoProyecto.setId(Integer.parseInt(id));
		}catch (NumberFormatException e){
			return businessServiceException.getError("paramValueIsNotAsExpected", "id", "numéricos");
		}
		*/
		dtoProyectoList = proyectoService.getProyectos(dtoProyecto);
		
		
		return new ResponseEntity<DTOProyectoList>(dtoProyectoList,HttpStatus.OK);
		
	}
	
	
	//LISTAR 1 PROYECTO LISTO
		@GetMapping(path = "/proyecto/{id}", produces = "application/json")
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
			
	//No desarrollado aun*****
	@PostMapping(path = "/componentes/solicitud/usuario", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> postUsuarioSolicitud(
			@RequestBody DTOUsuario dtoUsuario) {
		
		DTOUsuario dtoUsuarioOut = new DTOUsuario();
		
		if(dtoUsuario == null) {
			return businessServiceException.getError("mandatoryParametersMissingExplicit", "usuario");
		}else if(dtoUsuario.getNombre() == null || dtoUsuario.getNombre().equals(StringUtils.EMPTY)) {
			return businessServiceException.getError("mandatoryParametersMissingExplicit", "usuario.nombre");
		}else if(dtoUsuario.getCorreo() == null || dtoUsuario.getCorreo().equals(StringUtils.EMPTY)) {
			return businessServiceException.getError("mandatoryParametersMissingExplicit", "cliente.correo");
		}else if(dtoUsuario.getTelefono() == null || dtoUsuario.getTelefono().equals(StringUtils.EMPTY)) {
			return businessServiceException.getError("mandatoryParametersMissingExplicit", "cliente.telefono");
		}else if(dtoUsuario.getEmpresa() == null) {
			return businessServiceException.getError("mandatoryParametersMissingExplicit", "cliente.empresa");
		}else if(dtoUsuario.getEmpresa().getId() == null) {
			return businessServiceException.getError("mandatoryParametersMissingExplicit", "cliente.empresa.id");
		}
		
		dtoUsuarioOut = userService.postUsuarioSolicitud(dtoUsuario);
		
		return new ResponseEntity<DTOUsuario>(dtoUsuarioOut,HttpStatus.OK);
	}
	


}