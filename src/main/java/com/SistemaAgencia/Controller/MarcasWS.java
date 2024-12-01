package com.SistemaAgencia.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SistemaAgencia.Model.Marcas;
import com.SistemaAgencia.Service.MarcasServImp;

@RestController
@RequestMapping(path="MarcasWS")
@CrossOrigin
public class MarcasWS {

	@Autowired 
	MarcasServImp marcaImp;
	
	@GetMapping(path="listar")
	public List<Marcas> listar(){
		return marcaImp.listar();
	}
	
	@PostMapping(path="guardar")
	public ResponseEntity<?> guardar(@RequestBody Marcas marca){
		if(marcaImp.guardar(marca)==null) {
			return new ResponseEntity<>("Esta marca ya está en la base de datos",HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(marca,HttpStatus.CREATED);
	}
	
	@PostMapping(path="buscar")
	public Marcas buscar(@RequestBody Marcas marca) {
		return marcaImp.buscar(marca.getId());
	}
	
	@PostMapping(path="editar")
	public ResponseEntity<?> editar(@RequestBody Marcas marca){
		if(marcaImp.editar(marca)==true)
			return new ResponseEntity<>(marca,HttpStatus.CREATED);
		else
			return new ResponseEntity<>("Este id no existe",HttpStatus.OK);
	}
	
	//Validar que si exista el id a eliminar
	@PostMapping(path="eliminar")
	public ResponseEntity<String> eliminar(@RequestBody Marcas marca){
		if(marcaImp.eliminar(marca.getId())==true)
			return new ResponseEntity<String>("Eliminado con exito",HttpStatus.OK);
		else
			return new ResponseEntity<>("Este id no existe",HttpStatus.OK);
	}
}
