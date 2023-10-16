package com.apis.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apis.product.model.PersonJava;
import com.apis.product.repositoy.RepositoryMet;
import com.apis.product.servico.Servico;

@RestController

public class Controller {
	
	//routes
	
	@Autowired
	private RepositoryMet action;
	@Autowired
	private Servico ser;
	@PostMapping("/api")
	public ResponseEntity<?> register(@RequestBody PersonJava p) {
		
		return ser.register(p);
	}
	//return list person in web
	@GetMapping("/api")
	public ResponseEntity<?> selectPerson(){
		return ser.selectP();
	}
	//edit person
	@PutMapping("/api")
	public ResponseEntity<?> editPerson(@RequestBody PersonJava p) {
		return ser.editPerson(p);
	}
	//delete person
	@DeleteMapping("/api/{codigo}")
	public ResponseEntity<?> removePerson(@PathVariable int codigo) {
		return ser.deletePerson(codigo);
	}
	//count person
	@GetMapping("/api/cont")
	public long countPerson() {
		return action.count();
	}
	//search per id  
	@GetMapping("/api/{id}")
	public ResponseEntity<?> selectId(@PathVariable int id) {
		return ser.selectId(id);
	}
	//order person per name
	@GetMapping("/api/orderName")
	public List<PersonJava> ordername(){
		return action.findByOrderByName();
	}
	//list person name start with 
	@GetMapping("/api/nameS")
	public List<PersonJava> nameStrat(){ 
		return action.findByNameStartsWith("a");
		
	}
	//list person name end with
	@GetMapping("/api/nameE")
	public List<PersonJava> nameEnds(){
		return action.findByNameEndsWith("o");
	}
	@GetMapping("api/nameContem")
	public List<PersonJava> nameContem(){
		return action.findByNameContaining("m");
	}
	@GetMapping("api/orderNameY")
	public List<PersonJava> orderNamePerY(){
		return action.findByNameOrderByYearsDesc("paula");
	}
	//sum years person with connection bank
	@GetMapping("/api/sum")
	public int sumYears() {
		return action.sumY();
	}
	@GetMapping("/api/big")
	public List<PersonJava> yearsBig(){
		return action.yearsBigger(20);
	}
	@GetMapping("")
	public String mensagem() {
		return "hello world";
	}
	@GetMapping("/welcome/{nome}")
	public String welcome(@PathVariable String nome) {
		return "welcome"+nome;
	}
	@PostMapping("/per")
	public PersonJava per(@RequestBody PersonJava p) {
		return p;
	}
	@GetMapping("/status")
	public ResponseEntity<?> status() {
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	//hello

}
