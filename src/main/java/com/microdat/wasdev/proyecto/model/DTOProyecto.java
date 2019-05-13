package com.microdat.wasdev.proyecto.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@XmlRootElement(name = "DTOProyecto")
public class DTOProyecto implements Serializable {

	public final static long serialVersionUID = 1L;
	
	private Integer id;
	private String nombreProyecto;
	private DTOListaOpciones faseDesarrollo;
	private DTOUsuario responsable;
	private DTOUsuario responsableDyd;
	private String comentario;
	private String codigo;
	private Date fechaCreacion;
	private Date fechaEntrega;
	private Date fechaEstimadaEntrega;
	private List<DTOComponente> componentes;
	private Integer dyd;
	private String tipo;

	public DTOUsuario getResponsableDyd() {
		return responsableDyd;
	}

	public void setResponsableDyd(DTOUsuario responsableDyd) {
		this.responsableDyd = responsableDyd;
	}

	public DTOProyecto() {}

	public Integer getId() {
		return id;
	}

	public String getNombreProyecto() {
		return nombreProyecto;
	}

	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DTOListaOpciones getFaseDesarrollo() {
		return faseDesarrollo;
	}

	public void setFaseDesarrollo(DTOListaOpciones faseDesarrollo) {
		this.faseDesarrollo = faseDesarrollo;
	}

	public DTOUsuario getResponsable() {
		return responsable;
	}

	public void setResponsable(DTOUsuario responsable) {
		this.responsable = responsable;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public List<DTOComponente> getComponentes() {
		return componentes;
	}

	public void setComponentes(List<DTOComponente> componentes) {
		this.componentes = componentes;
	}

	public Integer getDyd() {
		return dyd;
	}

	public void setDyd(Integer dyd) {
		this.dyd = dyd;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Date getFechaEstimadaEntrega() {
		return fechaEstimadaEntrega;
	}

	public void setFechaEstimadaEntrega(Date fechaEstimadaEntrega) {
		this.fechaEstimadaEntrega = fechaEstimadaEntrega;
	}
	
}
