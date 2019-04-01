package com.microdat.wasdev.proyecto.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@XmlRootElement(name = "DTOUbicacion")
public class DTOUbicacion  implements Serializable{

	public final static long serialVersionUID = 1L;
	
	private String ruta;
	private DTOAmbiente ambiente;

	public DTOUbicacion(){}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public DTOAmbiente getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(DTOAmbiente ambiente) {
		this.ambiente = ambiente;
	}
	
}
