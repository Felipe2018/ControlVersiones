package com.microdat.wasdev.proyecto.model;

import java.io.Serializable;
import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@XmlRootElement(name = "DTOSolicitud")
public class DTOSolicitud implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private DTOListaOpciones estado;
	private DTOCliente responsable;
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "es_CL", timezone = "America/Santiago") 
	private Date fechaEmision;
	private Date fechaEstimadaEntrega;
	private Date fechaRealEntrega;
	private String descripcion;
	private String comentario;
	private DTOUsuario usuario;
	
	public DTOSolicitud() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public DTOListaOpciones getEstado() {
		return estado;
	}

	public void setEstado(DTOListaOpciones estado) {
		this.estado = estado;
	}

	public DTOCliente getResponsable() {
		return responsable;
	}

	public void setResponsable(DTOCliente responsable) {
		this.responsable = responsable;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Date getFechaEstimadaEntrega() {
		return fechaEstimadaEntrega;
	}

	public void setFechaEstimadaEntrega(Date fechaEstimadaEntrega) {
		this.fechaEstimadaEntrega = fechaEstimadaEntrega;
	}

	public Date getFechaRealEntrega() {
		return fechaRealEntrega;
	}

	public void setFechaRealEntrega(Date fechaRealEntrega) {
		this.fechaRealEntrega = fechaRealEntrega;
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

	public DTOUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(DTOUsuario usuario) {
		this.usuario = usuario;
	}
	
}
