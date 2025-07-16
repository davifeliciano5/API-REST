package com.example.primeiro_projeto.services;

import com.example.primeiro_projeto.data.dto.PersonDTO;
import com.example.primeiro_projeto.exception.ResourceNotFoundException;
import com.example.primeiro_projeto.mapper.ObjectMapper;
import com.example.primeiro_projeto.model.Person;
import com.example.primeiro_projeto.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import static com.example.primeiro_projeto.mapper.ObjectMapper.parseListObject;
import static com.example.primeiro_projeto.mapper.ObjectMapper.parseObject;

@Service
public class PersonServices {
    
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    /*MOCK testes - estruturas usadas para testes*/

    public List<PersonDTO> findAll(){

        return parseListObject(repository.findAll(), PersonDTO.class);
    }

    

    public PersonDTO findById(Long id) {
        logger.info("Finding one Person!");
        var entity = repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No record found for this ID"))
                ;
        return parseObject(entity,PersonDTO.class);
    }

    public PersonDTO create(PersonDTO person){
        logger.info("Creating person");
        var entity =parseObject(person,Person.class);

        return parseObject(repository.save(entity),PersonDTO.class);
    }

    public PersonDTO update(PersonDTO person){
        logger.info("Updating person");

        Person entity = repository.findById(person.getId())
                .orElseThrow(()-> new ResourceNotFoundException("No record found for this ID"))
        ;

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return parseObject(repository.save(entity),PersonDTO.class);
    }

    public void delete(Long id){
        logger.info("Deleting person");
        Person entity = repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No record found for this ID"))
        ;
        repository.delete(entity);
    }
}
