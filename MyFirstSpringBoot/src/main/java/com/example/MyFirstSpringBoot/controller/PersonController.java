package com.example.MyFirstSpringBoot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.MyFirstSpringBoot.model.Person;
import com.example.MyFirstSpringBoot.repository.PersonRepository;

@Controller
public class PersonController {
	
	@Autowired
	private PersonRepository personRepository;

	@RequestMapping(method=RequestMethod.GET, value="/initperson")
	public ModelAndView init() {
		
		Iterable<Person> people = personRepository.findAll();
		
		ModelAndView andView = new ModelAndView("crud/initperson");
		andView.addObject("person", new Person());
		andView.addObject("people", people);
		
		return andView;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="**/saveperson")
	public ModelAndView save(Person person) {
		personRepository.save(person);

		ModelAndView andView = new ModelAndView("crud/initperson");
		
		Iterable<Person> people = personRepository.findAll();
		
		andView.addObject("people", people);
		andView.addObject("person", new Person());
		
		return andView;
	}

	@RequestMapping(method=RequestMethod.GET, value="/listall")
	public ModelAndView listAll() {
		ModelAndView andView = new ModelAndView("crud/initperson");
		
		Iterable<Person> people = personRepository.findAll();
		
		andView.addObject("people", people);
		andView.addObject("person", new Person());
		
		return andView;
	}

	@GetMapping("/updateperson/{id}") //Same as @RequestMapping(method=RequestMethod.GET, value="/updateperson")
	public ModelAndView update(@PathVariable("id") Long id) {

		Optional<Person> person = personRepository.findById(id);

		ModelAndView andView = new ModelAndView("crud/initperson");
		andView.addObject("person", person.get());
		
		return andView;
	}

	@GetMapping("/deleteperson/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {

		personRepository.deleteById(id);
		Iterable<Person> people = personRepository.findAll();
		
		ModelAndView andView = new ModelAndView("crud/initperson");
		andView.addObject("person", new Person());
		andView.addObject("people", people);

		
		return andView;
	}
	
	@PostMapping("**/findperson")
	public ModelAndView find(@RequestParam("name") String name) {

		ModelAndView andView = new ModelAndView("crud/initperson");
		
		Iterable<Person> people = personRepository.findPersonByName(name);
		
		andView.addObject("people", people);
		andView.addObject("person", new Person());
		
		return andView;
	}
}
