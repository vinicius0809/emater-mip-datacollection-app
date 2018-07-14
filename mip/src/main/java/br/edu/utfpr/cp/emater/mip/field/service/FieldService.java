package br.edu.utfpr.cp.emater.mip.field.service;

import br.edu.utfpr.cp.emater.mip.field.entity.City;
import br.edu.utfpr.cp.emater.mip.field.entity.Farmer;
import br.edu.utfpr.cp.emater.mip.field.entity.Field;
import br.edu.utfpr.cp.emater.mip.field.entity.MacroRegion;
import br.edu.utfpr.cp.emater.mip.field.entity.Region;
import br.edu.utfpr.cp.emater.mip.field.entity.Supervisor;
import java.io.Serializable;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

@Stateful
public class FieldService implements Serializable {
    
    private Set<Field> fieldDao;
    
    @PostConstruct
    public void init () {
        fieldDao = Stream.of(
                new Field(
                        1L, 
                        "Nice Place to Live", 
                        "Town", 
                        new City(
                                1L, 
                                "Londrina", 
                                new Region(
                                        1L, 
                                        "Norte Pioneiro", 
                                        new MacroRegion(
                                                1L, 
                                                "Norte")
                                )
                        ), 
                        new Farmer(1L, "John Doe"), 
                        Collections.singleton(new Supervisor (1L, "Charles Babbage"))
                ),
                new Field(
                        2L, 
                        "Nice Place to Work", 
                        "Rural area", 
                        new City(
                                2L, 
                                "Pato Branco", 
                                new Region(
                                        2L, 
                                        "Sudoeste 1", 
                                        new MacroRegion(
                                                2L, 
                                                "Sudoeste")
                                )
                        ), 
                        new Farmer(1L, "Anne Doe"), 
                        Collections.singleton(new Supervisor (1L, "Denis Ritchie"))
                )
                ).collect(Collectors.toSet());
    }
    
    public Set<Field> readAll() {
        return fieldDao;
    }
    
    public void create (Field field) {
        fieldDao.add(field);
        
    }

    public void update (Field field, Long id) {
        
        
    }

    public void delete (Long id) {
        fieldDao.removeIf(field -> field.getId().equals(id));    
        
    }
}
