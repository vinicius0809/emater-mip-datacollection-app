package br.edu.utfpr.cp.emater.mipdatacollection.presentation;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class DataCollectionBean implements Serializable {

    private List<Pest> pestList;
    private Pest pestSelected;
    private PestSample sample;

    @PostConstruct
    public void initPestList() {
        pestList = Stream.of(
                new Pest("Lagarta da Soja", "Anticarsia sp.", "http://www.cnpso.embrapa.br/helicoverpa/folha_large/_3105441205.jpg"),
                new Pest("Falsa Medideira", "Chrysodeixis ssp.", "http://www.cnpso.embrapa.br/helicoverpa/folha_large/_9148710221.jpg"),
                new Pest("Lagarta das Vagens", "Spodoptera ssp.", "http://www.cnpso.embrapa.br/helicoverpa/vagens_large/_4331614441.jpg")
        ).collect(Collectors.toList());
    }

    public String selectPest() {

        String pestScientificName = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("pestScientificName");

        this.pestSelected = this.
                getPestList().
                stream().
                filter(
                        pest -> pest.getScientificName().
                                equalsIgnoreCase(pestScientificName)).
                findFirst().
                orElseThrow(() -> new RuntimeException("Peste não encontrada!"));

        return "pm:pestCollectionInternalPage?transition=slide";
    }

    public String save() {
        
        this.sample.setPest(pestSelected);
        System.out.println(this.sample);
        
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Coleta realizada com sucesso!", "Sucesso"));
        return "/data-collection";
    }

    public List<Pest> getPestList() {
        return pestList;
    }

    public void setPestList(List<Pest> pestList) {
        this.pestList = pestList;
    }

    public Pest getPestSelected() {
        return pestSelected;
    }

    public void setPestSelected(Pest pestSelected) {
        this.pestSelected = pestSelected;
    }

    public PestSample getSample() {
        return sample;
    }

    public void setSample(PestSample sample) {
        this.sample = sample;
    }

}
