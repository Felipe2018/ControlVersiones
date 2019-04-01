package com.microdat.wasdev.proyecto.model.wrapper;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.microdat.wasdev.proyecto.model.DTOComponente;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@XmlRootElement(name = "DTOComponenteList")
public class DTOComponenteList {
	
public final static long serialVersionUID = 1L;
	
	private List<DTOComponente> componentes;
	
	public DTOComponenteList() {}

	public List<DTOComponente> getComponentes() {
		return componentes;
	}

	public void setComponentes(List<DTOComponente> componentes) {
		this.componentes = componentes;
	}
	
}
