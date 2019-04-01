package com.microdat.wasdev.proyecto.model.wrapper;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.microdat.wasdev.proyecto.model.DTOProyecto;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@XmlRootElement(name = "DTOProyectoList")
public class DTOProyectoList {

public final static long serialVersionUID = 1L;
	
	private List<DTOProyecto> proyectos;
	
	public DTOProyectoList() {}

	public List<DTOProyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(List<DTOProyecto> proyectos) {
		this.proyectos = proyectos;
	}
	
}
