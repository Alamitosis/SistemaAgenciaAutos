package com.SistemaAgencia.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SistemaAgencia.Dao.MarcasDao;
import com.SistemaAgencia.Dao.ModelosDao;
import com.SistemaAgencia.Model.Marcas;
import com.SistemaAgencia.Model.Modelos;



@Service
public class ModelosServImp {

	 @Autowired 
	 ModelosDao modeldao;
	 @Autowired
	 MarcasDao mardao;
	 
	 @Transactional(readOnly=true)
	 public List<Modelos> listar(){
		 return modeldao.findAll();
	 }
	 
	 //Validar el idmodelo y nombremodelo no se repita, el idmarca exista
	 @Transactional
	 public String guardar (Modelos modelo) {
		 String respuesta="";
		 boolean banderaMar=false;
		 boolean banderaIdMod=false;
		 boolean banderaNombreMod=false;
		 
		 //Siempre iniciar validando las llaves foráneas
		 for(Marcas mar:mardao.findAll()) {
			 if(mar.getId().equals(modelo.getMarca().getId())) {
				 //IdMarca existe
				 banderaMar=true;
				 for(Modelos mod:modeldao.findAll()) {
					 if(mod.getId().equals(modelo.getId())) {
						 //el id modelo ya existe
						 banderaIdMod=true;
						 break;
					 } else if(mod.getNombre().equals(modelo.getNombre())) {
						 //el nombreModelo ya existe
						 banderaNombreMod=true;
						 break;
					 }
				 }
			 }
		 }
		 
		 if(banderaMar==false) {
			 //el idmarca no existe
			 respuesta="idmarcanoexiste";
		 }else if(banderaIdMod==true) {
			 respuesta="IdModeloExiste";
		 }else if(banderaNombreMod==true) {
			 respuesta="NombreModeloExiste";
		 }else if((banderaMar==true)&&(banderaIdMod==false)&&(banderaNombreMod==false)) {
			 modeldao.save(modelo);
			 respuesta="guardado";
		 }
		 return respuesta;
	 }
	 
	 @Transactional(readOnly=true)
	 public Modelos buscar(Long id) {
		 return modeldao.findById(id).orElse(null);
	 }
	 
	 //Validar que el idmodelo ya exista, el idmarca exista, contrario NO editar
	 @Transactional
	 public String editar(Modelos modelo) {
		 String respuesta="";
		 Boolean banderaMar=false;
		 Boolean banderaMod=false;
		 

	    
		 for(Marcas mar: mardao.findAll()) {
			 
			 if(mar.getId().equals(modelo.getMarca().getId())) {
				 banderaMar=true;
				 
				 for(Modelos modelo1:modeldao.findAll()) {
					 if(modelo1.getId().equals(modelo.getId())) {
						 banderaMod=true;
					 }
				 }
				 
			 }
		 }
		 
		 if(banderaMar==true && banderaMod==true) {
			 modeldao.save(modelo);
			 respuesta="editado";
		 }
		 else if(banderaMar==false)
			 respuesta="NoExisteIdMarca";
		 else if(banderaMod==false)
			 respuesta="NoExisteIdModelo";
		 return respuesta;
	 }
	 
	 //Validar que el idmodelo ya exista, contrario eliminamos
	 
	 @Transactional
	 public boolean eliminar(Long id) {
		 Boolean bandera=false;
		 
		 if (modeldao.existsById(id)){
			 modeldao.deleteById(id);
			 bandera=true;
		 }
		 return bandera;
	 }
}
