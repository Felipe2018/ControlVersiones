package com.microdat.wasdev.proyecto.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@XmlRootElement(name = "DTOListaOpciones")
public class DTOListaOpciones  implements Serializable {

	public final static long serialVersionUID = 1L;
	
	private Integer id;
	private String nombre;
	
	public DTOListaOpciones() {}
	
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
	
}
