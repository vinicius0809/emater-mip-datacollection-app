package br.edu.utfpr.cp.emater.mip.view;

import br.edu.utfpr.cp.emater.mip.domain.field.city.City;
import br.edu.utfpr.cp.emater.mip.domain.field.city.CityRepository;
import br.edu.utfpr.cp.emater.mip.domain.field.macroregion.MacroRegion;
import br.edu.utfpr.cp.emater.mip.domain.field.macroregion.MacroRegionRepository;
import br.edu.utfpr.cp.emater.mip.domain.field.region.Region;
import br.edu.utfpr.cp.emater.mip.domain.field.region.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

enum CityLabels {
    PAGE_TITLE("Gerenciamento de Cidades"),
    ENTITY("Cidade"),
    ARTICLE("a"),
    URL_CREATE("/city/create"),
    URL_UPDATE("/city/update"),
    URL_DELETE("/city/delete");

    private String value;

    CityLabels(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

enum CityPath {
    SUCCESS_CREATE ("/city"),
    SUCCESS_UPDATE ("/city"),
    SUCCESS_DELETE ("/city"),
    SUCCESS_READ ("/city"),
    TEMPLATE_PATH ("/field/city/index");
    
    private String value;
    
    CityPath (String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

@Controller
@RequestMapping(value = "/city")
public class CityController {

    private final RegionRepository regionRepository;
    private final MacroRegionRepository macroRegionRepository;
    private final CityRepository cityRepository;

    @Autowired
    public CityController(CityRepository cityRepository, RegionRepository regionRepository, MacroRegionRepository macroRegionRepository) {
        this.cityRepository = cityRepository;
        this.regionRepository = regionRepository;
        this.macroRegionRepository = macroRegionRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listAll(Model data) {

        data.addAttribute("regions", regionRepository.findAll());
        data.addAttribute("macroRegions", macroRegionRepository.findAll());
        data.addAttribute("cities", cityRepository.findAll());

        data.addAttribute("pageTitle", CityLabels.PAGE_TITLE.getValue());
        data.addAttribute("article", CityLabels.ARTICLE.getValue());
        data.addAttribute("entity", CityLabels.ENTITY.getValue());
        data.addAttribute("urlCreate", CityLabels.URL_CREATE.getValue());
        data.addAttribute("urlUpdate", CityLabels.URL_UPDATE.getValue());
        data.addAttribute("urlDelete", CityLabels.URL_DELETE.getValue());

        return CityPath.TEMPLATE_PATH.getValue();
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(@RequestParam String name, @RequestParam int regionId) {

        Region region = regionRepository.findById(new Long (regionId)).get();
        cityRepository.save(new City(null, name, region));

        return CityPath.SUCCESS_CREATE.getValue();
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(@RequestParam String name, @RequestParam int id, @RequestParam int regionId) {

        Region r = regionRepository.findById(new Long(regionId)).get();
        City c = cityRepository.findById(new Long(id)).get();

        c.setName(name);
        c.setRegion(r);

        cityRepository.saveAndFlush(c);

        return CityPath.SUCCESS_UPDATE.getValue();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam int id) {
        cityRepository.deleteById(new Long(id));

        return CityPath.SUCCESS_DELETE.getValue();
    }

}
