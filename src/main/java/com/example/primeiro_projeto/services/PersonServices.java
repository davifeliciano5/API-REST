package com.example.primeiro_projeto.services;

import com.example.primeiro_projeto.exception.ResourceNotFoundException;
import com.example.primeiro_projeto.model.Person;
import com.example.primeiro_projeto.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {
    
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    /*MOCK testes - estruturas usadas para testes*/

    public List<Person> findAll(){
        return  repository.findAll();
    }

    

    public Person findById(Long id) {
        logger.info("Finding one Person!");
        return repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No record found for this ID"))
                ;
    }

    public Person create(Person person){
        logger.info("Creating person");
        return repository.save(person);
    }

    public Person update(Person person){
        logger.info("Updating person");

        Person entity = repository.findById(person.getId())
                .orElseThrow(()-> new ResourceNotFoundException("No record found for this ID"))
        ;

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return repository.save(person);
    }

    public void delete(Long id){
        logger.info("Deleting person");
        Person entity = repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No record found for this ID"))
        ;
        repository.delete(entity);
    }
}
