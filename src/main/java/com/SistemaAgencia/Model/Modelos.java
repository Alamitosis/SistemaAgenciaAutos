package com.SistemaAgencia.Model;

import java.io.Serializable;
import java.sql.Date;
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
@Table(name="Modelos")
public class Modelos implements Serializable{
	
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

	@Column(name="TIPO")
	private String tipo;
	
	@Column(name="PRECIO")
	private Float precio;
	
	@Column(name="FECHA_LANZ")
	private Date fechaLanz;
	
	
	
	//fecth--Indicamos como debe ser cargada la entidad
	//FetchType.EAGER--Indicamos que la relacion debe ser cargada entre si
	//momento (procesos hilos)
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ID_MARCA")
	Marcas marca;
	
	

	public Modelos(Long id, String nombre, String tipo, Float precio, Date fechaLanz, Marcas marca) {
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.precio = precio;
		this.fechaLanz = fechaLanz;
		this.marca = marca;
	}

	public Modelos() {
	}

	@Override
	public String toString() {
		return "Modelos [id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", precio=" + precio + ", fechaLanz="
				+ fechaLanz + ", marca=" + marca + "]\n";
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public Date getFechaLanz() {
		return fechaLanz;
	}

	public void setFechaLanz(Date fechaLanz) {
		this.fechaLanz = fechaLanz;
	}

	public Marcas getMarca() {
		return marca;
	}

	public void setMarca(Marcas marca) {
		this.marca = marca;
	}

	
	
}
