package com.example.MyFirstSpringBoot.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.MyFirstSpringBoot.model.Person;
import com.example.MyFirstSpringBoot.repository.PersonRepository;

@RestController
@RequestMapping("/personservice")
public class PersonResource {
	
	@Autowired
	PersonRepository personRepository;
	
	@GetMapping(path = "/all", produces = "application/json")
	public Iterable<Person> listAll() {
		
		return personRepository.findAll();
	}

	@GetMapping(path = "/{id}", produces = "application/json")
	public Person findPerson(@PathVariable(name = "id") Long id) {
		
		return personRepository.findById(id).orElse(null);
	}

	@DeleteMapping(path = "/{id}", produces = "text/plain")
	public String deletePerson(@PathVariable(name = "id") Long id) {
		
		Optional<Person> person = personRepository.findById(id);
		
		if(person.isPresent()) {

			personRepository.deleteById(id);
			
			return "success"; 
		} else {
			
			return "false";
		}
	}
	
	@PostMapping(consumes = "application/json", produces = "application/json")
	public Person savePerson(@RequestBody Person person) {
		
		return personRepository.save(person);
	}

	@PutMapping(consumes = "application/json", produces = "application/json")
	public Person updatePerson(@RequestBody Person person) {
		
		return personRepository.save(person);
	}

}
