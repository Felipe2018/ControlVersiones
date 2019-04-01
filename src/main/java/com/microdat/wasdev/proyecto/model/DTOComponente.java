package com.microdat.wasdev.proyecto.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@XmlRootElement(name = "DTOComponente")
public class DTOComponente implements Serializable{

	public final static long serialVersionUID = 1L;
	
	private Integer id;
	private String nombre;
	private DTOListaOpciones grupoFuncional;
	private DTOListaOpciones funcionalidad;
	private DTOListaOpciones estado;
	private String canal;
	private DTOAmbiente ambienteSubida;
	private List<DTOUbicacion> ubicaciones;
	private String descripcion;
	private String comentario;
	private DTOSolicitud solicitud;
	private DTOReglaConflicto reglaConflicto;
	private Integer cantidad;
	public DTOComponente() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public DTOListaOpciones getGrupoFuncional() {
		return grupoFuncional;
	}

	public void setGrupoFuncional(DTOListaOpciones grupoFuncional) {
		this.grupoFuncional = grupoFuncional;
	}

	public DTOListaOpciones getFuncionalidad() {
		return funcionalidad;
	}

	public void setFuncionalidad(DTOListaOpciones funcionalidad) {
		this.funcionalidad = funcionalidad;
	}

	public DTOListaOpciones getEstado() {
		return estado;
	}

	public void setEstado(DTOListaOpciones estado) {
		this.estado = estado;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public DTOAmbiente getAmbienteSubida() {
		return ambienteSubida;
	}

	public void setAmbienteSubida(DTOAmbiente ambienteSubida) {
		this.ambienteSubida = ambienteSubida;
	}

	public List<DTOUbicacion> getUbicaciones() {
		return ubicaciones;
	}

	public void setUbicaciones(List<DTOUbicacion> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public DTOSolicitud getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(DTOSolicitud solicitud) {
		this.solicitud = solicitud;
	}

	public DTOReglaConflicto getReglaConflicto() {
		return reglaConflicto;
	}

	public void setReglaConflicto(DTOReglaConflicto reglaConflicto) {
		this.reglaConflicto = reglaConflicto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
}
