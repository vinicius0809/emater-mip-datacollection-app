package br.edu.utfpr.cp.emater.mipdatacollection.presentation;

import br.edu.utfpr.cp.emater.mipdatacollection.model.Crop;
import br.edu.utfpr.cp.emater.mipdatacollection.model.Farmer;
import br.edu.utfpr.cp.emater.mipdatacollection.model.Harvest;
import br.edu.utfpr.cp.emater.mipdatacollection.model.Pest;
import br.edu.utfpr.cp.emater.mipdatacollection.model.PestType;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

@ManagedBean
@SessionScoped
public class PestSampleCollectionBean implements Serializable {

    private List<Pest> pestList;
    private Pest pestSelected;
    private PestSample sample;
    private Date sampleCollectionDate;
    private List<Crop> cropList;
    private Crop cropSelected;
    private int sampleValue;

    @PostConstruct
    public void initPestList() {
        pestList = Stream.of(
                new Pest("Lagarta da Soja", "Anticarsia sp.", "http://www.cnpso.embrapa.br/helicoverpa/folha_large/_3105441205.jpg", PestType.LESS_THAN_15CM),
                new Pest("Falsa Medideira", "Chrysodeixis ssp.", "http://www.cnpso.embrapa.br/helicoverpa/folha_large/_9148710221.jpg", PestType.LESS_THAN_15CM),
                new Pest("Lagarta das Vagens", "Spodoptera ssp.", "http://www.cnpso.embrapa.br/helicoverpa/vagens_large/_4331614441.jpg", PestType.LESS_THAN_15CM)
        ).collect(Collectors.toList());

        cropList = Stream.of(
                new Crop("Potência RR", new Farmer("José TESTE da Silva", "http://"), new Harvest("Safra 2017/2018")),
                new Crop("Potência XX", new Farmer("Antônio TESTE da Silva", "http://"), new Harvest("Safra 2017/2018"))
        ).collect(Collectors.toList());
    }


    public String onSampleCollectionDate() {
        System.out.println(this.sampleCollectionDate);

        return "pm:sampleCollectionCropPage?transition=slide";
    }

    public String onSelectCrop() {

        String cropName = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("cropName");

        this.cropSelected = this.
                getCropList().
                stream().
                filter(
                        crop -> crop.getName().equalsIgnoreCase(cropName)).
                findFirst().
                orElseThrow(() -> new RuntimeException("Cultivar não encontrada!"));

        System.out.println(this.cropSelected);

        return "pm:pestSelectionInternalPage?transition=slide";
    }


    public String onSelectPest() {

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

        System.out.println(this.pestSelected);

        return "pm:pestCollectionInternalPage?transition=slide";
    }

    public String onSampleCollectionCrop() {
        
        return "pm:pestSelectionInternalPage?transition=slide";
    }


    public String save() {

        System.out.println(this.sampleValue);

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Coleta realizada com sucesso!", "Sucesso"));
        return "/index";
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

    public Date getSampleCollectionDate() {
        return sampleCollectionDate;
    }

    public void setSampleCollectionDate(Date sampleCollectionDate) {
        this.sampleCollectionDate = sampleCollectionDate;
    }

    public List<Crop> getCropList() {
        return cropList;
    }

    public void setCropList(List<Crop> cropList) {
        this.cropList = cropList;
    }

    public Crop getCropSelected() {
        return cropSelected;
    }

    public void setCropSelected(Crop cropSelected) {
        this.cropSelected = cropSelected;
    }

    public int getSampleValue() {
        return sampleValue;
    }

    public void setSampleValue(int sampleValue) {
        this.sampleValue = sampleValue;
    }
}
