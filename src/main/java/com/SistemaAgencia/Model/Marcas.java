package com.SistemaAgencia.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Marcas")
public class Marcas implements Serializable{

	/*

CREATE TABLE MODELOS(
ID NUMBER PRIMARY KEY,
NOMBRE VARCHAR2(100) NOT NULL,
TIPO VARCHAR2(70) NOT NULL,
PRECIO NUMBER NOT NULL,
FECHA_LANZ DATE NOT NULL,
ID_MARCA NUMBER NOT NULL,
FOREIGN KEY(ID_MARCA) REFERENCES MARCAS(ID)
);
);

	 */
	
	@Id
	@Column(name="ID")
	private Long id;
	
	@Column(name="NOMBRE")
	private String nombre;

	@Column(name="ORIGEN")
	private String origen;
	
	@Column(name="ESLOGAN")
	private String eslogan;
	
	//CARDINALIDAD
	//UNA MARCA TIENE MUCHOS MODELOS
	@OneToMany(mappedBy="marca",cascade=CascadeType.ALL)
	@JsonIgnore
	List<Modelos> lista=new ArrayList<Modelos>();

	public Marcas(Long id, String nombre, String origen, String eslogan) {
		this.id = id;
		this.nombre = nombre;
		this.origen = origen;
		this.eslogan = eslogan;
	}

	public Marcas() {
	}



	@Override
	public String toString() {
		return "Marcas [id=" + id + ", nombre=" + nombre + ", origen=" + origen + ", eslogan=" + eslogan + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getEslogan() {
		return eslogan;
	}

	public void setEslogan(String eslogan) {
		this.eslogan = eslogan;
	}



	
//	Agregar getter, setter y todo eso sin agregar lista
	
	
}
