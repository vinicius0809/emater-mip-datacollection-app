package br.edu.utfpr.cp.emater.mip.view;

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

enum RegionLabels {
    PAGE_TITLE("Gerenciamento de Regiões"),
    ENTITY("Região"),
    ARTICLE("a"),
    URL_CREATE("/region/create"),
    URL_UPDATE("/region/update"),
    URL_DELETE("/region/delete");

    private String value;

    RegionLabels(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

enum RegionPath {
    SUCCESS_CREATE ("redirect:/region"),
    SUCCESS_UPDATE ("redirect:/region"),
    SUCCESS_DELETE ("redirect:/region"),
    SUCCESS_READ ("redirect:/region"),
    TEMPLATE_PATH ("/field/region/index");
    
    private String value;
    
    RegionPath (String value) {
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
@RequestMapping(value = "/region")
public class RegionController {

    private final RegionRepository regionRepository;
    private final MacroRegionRepository macroRegionRepository;

    @Autowired
    public RegionController(RegionRepository regionRepository, MacroRegionRepository macroRegionRepository) {
        this.regionRepository = regionRepository;
        this.macroRegionRepository = macroRegionRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listAll(Model data) {

        data.addAttribute("regions", regionRepository.findAll());
        data.addAttribute("macroRegions", macroRegionRepository.findAll());

        data.addAttribute("pageTitle", RegionLabels.PAGE_TITLE.getValue());
        data.addAttribute("article", RegionLabels.ARTICLE.getValue());
        data.addAttribute("entity", RegionLabels.ENTITY.getValue());
        data.addAttribute("urlCreate", RegionLabels.URL_CREATE.getValue());
        data.addAttribute("urlUpdate", RegionLabels.URL_UPDATE.getValue());
        data.addAttribute("urlDelete", RegionLabels.URL_DELETE.getValue());

        return RegionPath.TEMPLATE_PATH.getValue();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@RequestParam String name, @RequestParam int macroRegionId) {

        MacroRegion mr = macroRegionRepository.findById(new Long(macroRegionId)).get();
        regionRepository.save(new Region(null, name, mr));

        return RegionPath.SUCCESS_CREATE.getValue();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam String name, @RequestParam int id, @RequestParam int macroRegionId) {

        MacroRegion mr = macroRegionRepository.findById(new Long(macroRegionId)).get();
        Region r = regionRepository.findById(new Long(id)).get();

        r.setName(name);
        r.setMacroRegion(mr);

        regionRepository.saveAndFlush(r);

        return RegionPath.SUCCESS_UPDATE.getValue();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam int id) {
        regionRepository.deleteById(new Long(id));

        return RegionPath.SUCCESS_DELETE.getValue();
    }
}
