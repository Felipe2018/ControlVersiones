package com.microdat.wasdev.proyecto.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@XmlRootElement(name = "DTOUsuario")
public class DTOUsuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nombre;
	private String correo;
	private String telefono;
	private DTOEmpresa empresa;
	
	public DTOUsuario() {}

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

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public DTOEmpresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(DTOEmpresa empresa) {
		this.empresa = empresa;
	}

}
