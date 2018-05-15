package br.edu.utfpr.cp.emater.mipdatacollection.presentation;

import br.edu.utfpr.cp.emater.mipdatacollection.model.Pest;
import br.edu.utfpr.cp.emater.mipdatacollection.model.PestType;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class PestEvolutionBean {
    
    private double value;
    
    public Set<Pest> listAllPests() {
        return Stream.of(
                new Pest("Lagarta da Soja", "Anticarsia sp.", "http://www.cnpso.embrapa.br/helicoverpa/folha_large/_3105441205.jpg", PestType.LESS_THAN_15CM),
                new Pest("Falsa Medideira", "Chrysodeixis ssp.", "http://www.cnpso.embrapa.br/helicoverpa/folha_large/_9148710221.jpg", PestType.LESS_THAN_15CM),
                new Pest("Lagarta das Vagens", "Spodoptera ssp.", "http://www.cnpso.embrapa.br/helicoverpa/vagens_large/_4331614441.jpg", PestType.LESS_THAN_15CM)
        ).collect(Collectors.toSet());
        
    }
    
    public void saveSample() {
        
        System.out.println("done");
    }
    
    public String save() {
        return "/sample-pest/index";
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    
    
    
}
