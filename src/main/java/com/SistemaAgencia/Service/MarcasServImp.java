package com.SistemaAgencia.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SistemaAgencia.Dao.MarcasDao;
import com.SistemaAgencia.Model.Marcas;

@Service
public class MarcasServImp {

	@Autowired
	MarcasDao marcadao;
	
	@Transactional(readOnly=true)
	public List<Marcas> listar(){
		return marcadao.findAll();
	}
	
	//Validar que el id y nombre no se repita
	@Transactional
	public String guardar(Marcas marca) {
		boolean bandera=false;
		
		for(Marcas m:marcadao.findAll()) {
			if((marca.getId().equals(m.getId())||(marca.getNombre().equals(m.getNombre())))) {
				bandera=true;
				break;
			}
		}
		if(bandera==false) {
			marcadao.save(marca);
			return "guardado";
		}
		else
			return null;
	}
	
	@Transactional(readOnly=true)
	public Marcas buscar(Long id) {
		return marcadao.findById(id).orElse(null);
	}
	
	//Validar que el id exista, contrario no editar
	@Transactional
	public boolean editar(Marcas marca) {
		
		boolean bandera=false;
		
		for(Marcas m:marcadao.findAll()) {
			if(marca.getId().equals(m.getId())) {
				bandera=true;
				break;
			}
		}
		if(bandera==true) {
			marcadao.save(marca);
		}
		return bandera;
	}
	
	//Validar que el id exista
	@Transactional
	public boolean eliminar(Long id) {
		boolean bandera=false;
		
		for(Marcas m:marcadao.findAll()) {
			if(id.equals(m.getId())) {
				bandera=true;
				break;
			}
		}
		if(bandera==true) {
			marcadao.deleteById(id);
		}
		return bandera;
	}
}
