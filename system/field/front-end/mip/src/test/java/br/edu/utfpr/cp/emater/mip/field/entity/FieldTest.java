package br.edu.utfpr.cp.emater.mip.field.entity;

import java.util.Collections;
import org.junit.Test;
import static org.junit.Assert.*;

public class FieldTest {

    @Test
    public void create() {
        Field f = new Field(
                1L, 
                "A nice place to live", 
                "Rural area", 
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
                new Farmer(
                        1L, 
                        "John Doe"
                ), 
                Collections.singleton(
                        new Supervisor(
                                1L, 
                                "Anne Doe")
                )
        );
        
        assertEquals(f.getCity().getRegion().getMacroRegion().getName(), "Norte");
    }
    
}
