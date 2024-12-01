package com.SistemaAgencia.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SistemaAgencia.Model.Modelos;
import com.SistemaAgencia.Service.ModelosServImp;

@RestController
@RequestMapping(path="ModelosWS")
@CrossOrigin
public class ModelosWS {

	@Autowired
	ModelosServImp modelImp;
	
	//http://localhost:9000/ModelosWS/listar
	@GetMapping(path="listar")
	public List<Modelos> listar(){
		return modelImp.listar();
	}
	
	//http://localhost:9000/ModelosWS/guardar
	@PostMapping(path = "guardar")
	public ResponseEntity<?> guardar(@RequestBody Modelos modelo) {
		String response = modelImp.guardar(modelo);

		if (response.equals("IdModeloExiste")) {
			return new ResponseEntity<>("El Id de este modelo ya existe", HttpStatus.OK);
		} else if (response.equals("NombreModeloExiste")) {
			return new ResponseEntity<>("Ese nombre modelo ya existe", HttpStatus.OK);
		} else if (response.equals("idmarcanoexiste")) {
			return new ResponseEntity<>("Ese idMarca no existe", HttpStatus.OK);
		} else if(response.equals("guardado")){
			return new ResponseEntity<>(modelo, HttpStatus.CREATED);
		}
		else return null;
	}
	
	//http://localhost:9000/ModelosWS/editar
	@PostMapping(path="editar")
	public ResponseEntity<?> editar(@RequestBody Modelos modelo){
		String respuesta=modelImp.editar(modelo);
		
		if(respuesta.equals("editado"))
			return new ResponseEntity<>("Modelo editado con exito",HttpStatus.OK);
		else if(respuesta.equals("NoExisteIdModelo"))
			return new ResponseEntity<>("No existe el ID del modelo",HttpStatus.OK);
		else if(respuesta.equals("NoExisteIdMarca"))
			return new ResponseEntity<>("No existe la marca del modelo",HttpStatus.OK);
		else
			return new ResponseEntity<>("Error desconocido",HttpStatus.OK);
	}
	
	//http://localhost:9000/ModelosWS/eliminarId
	@PostMapping(path="eliminarId")
	public ResponseEntity<?> eliminarId(@RequestBody Modelos modelo){
		Boolean respuesta=modelImp.eliminar(modelo.getId());
		
		if(respuesta==true)
			return new ResponseEntity<>("Modelo borrado con exito",HttpStatus.OK);
		else
			return new ResponseEntity<>("Error al eliminar",HttpStatus.OK);
	}
}
