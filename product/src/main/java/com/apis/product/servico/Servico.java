package com.apis.product.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.apis.product.model.Menssage;
import com.apis.product.model.PersonJava;
import com.apis.product.repositoy.RepositoryMet;

@Service
public class Servico {
	
	@Autowired
	private Menssage menssage;
	
	@Autowired
	private RepositoryMet action;
	
	//method for register person
	
	public ResponseEntity<?> register(PersonJava p){
		
		//validation register null
		if(p.getName().equals("")) {
			menssage.setMenssage("please check your form");
			return new ResponseEntity<>(menssage,HttpStatus.BAD_REQUEST);
		}else if(p.getYears() < 0) {
		//validation
			menssage.setMenssage("please check your year");
			return new ResponseEntity<>(menssage,HttpStatus.BAD_REQUEST);
		}else {
		//create success
			
			return new ResponseEntity<>(action.save(p),HttpStatus.CREATED);
		}
		
	}
	//select person
	public ResponseEntity<?> selectP(){
		return new ResponseEntity<>(action.findAll(),HttpStatus.OK);
	}
	
	//method select person per id
	public ResponseEntity<?> selectId(int id){
		if(action.countById(id)==0) {
			menssage.setMenssage("person not exists");
			return new ResponseEntity<>(menssage,HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<>(action.findById(id),HttpStatus.OK);
		}
	}
	//method edit person
	public ResponseEntity<?> editPerson(PersonJava p){
		//validation 
		if(action.countById(p.getId())==0) {
			menssage.setMenssage("id not exist");
			return new ResponseEntity<>(menssage,HttpStatus.NOT_FOUND);
		}else if(p.getName().equals("")) {
			menssage.setMenssage("fill in the form please");
			return  new ResponseEntity<>(menssage,HttpStatus.BAD_REQUEST);
		}else if(p.getYears()==0) {
			menssage.setMenssage("age is invalid");
			return new ResponseEntity<>(menssage,HttpStatus.BAD_REQUEST);
			
		}else {
			return new ResponseEntity<>(action.save(p),HttpStatus.OK);
		}
			
		
	}
	public ResponseEntity<?> deletePerson(int  p){
		
		if(action.countById(p)==0) {
			menssage.setMenssage("id not exist");
			return new ResponseEntity<>(menssage,HttpStatus.BAD_REQUEST);
		}else {
			PersonJava pp=action.findById(p);
			action.delete(pp);
			
			menssage.setMenssage("person sucessfully removed");
			return new ResponseEntity<>(menssage,HttpStatus.OK);
			
		}
	}

}
