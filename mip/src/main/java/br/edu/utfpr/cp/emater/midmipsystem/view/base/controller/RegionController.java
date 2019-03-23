package br.edu.utfpr.cp.emater.midmipsystem.view.base.controller;

import br.edu.utfpr.cp.emater.midmipsystem.domain.base.City;
import br.edu.utfpr.cp.emater.midmipsystem.domain.base.CityRepository;
import br.edu.utfpr.cp.emater.midmipsystem.domain.base.MacroRegion;
import br.edu.utfpr.cp.emater.midmipsystem.domain.base.MacroRegionRepository;
import br.edu.utfpr.cp.emater.midmipsystem.domain.base.Region;
import br.edu.utfpr.cp.emater.midmipsystem.domain.base.RegionRepository;
import br.edu.utfpr.cp.emater.midmipsystem.view.base.dto.RegionDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/region")
public class RegionController {

    private final RegionRepository regionRepository;
    private final MacroRegionRepository macroRegionRepository;
    private final CityRepository cityRepository;

    private final Environment environment;

    private boolean operationSuccessMessage;

    private void resetOperationSuccessMessage() {
        if (this.operationSuccessMessage)
            this.operationSuccessMessage = false;
    }

    private void setOperationSuccessMessage() {
        this.operationSuccessMessage = true;
    }

    @Autowired
    public RegionController(RegionRepository regionRepository, MacroRegionRepository macroRegionRepository, CityRepository cityRepository, Environment environment) {
        this.regionRepository = regionRepository;
        this.macroRegionRepository = macroRegionRepository;
        this.cityRepository = cityRepository;
        this.environment = environment;
        this.operationSuccessMessage = false;

    }

    private List<City> retrieveCitiesNotAssignToAnyRegion() {

        List<City> citiesAssignedToRegions = new ArrayList<>();
        for (Region r: regionRepository.findAll())
            citiesAssignedToRegions.addAll(r.getCities());

        List<City> allCities = cityRepository.findAll();
        allCities.removeAll(citiesAssignedToRegions);
        
        return allCities;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listAll(Model data) {

        data.addAttribute("regions", regionRepository.findAll());
        data.addAttribute("macroRegions", macroRegionRepository.findAll());
        data.addAttribute("cities", this.retrieveCitiesNotAssignToAnyRegion());
        data.addAttribute("success", this.operationSuccessMessage);

        this.resetOperationSuccessMessage();


        data.addAttribute("urlCreate", environment.getProperty("app.view.route.create.field.region"));
        data.addAttribute("urlUpdate", environment.getProperty("app.view.route.update.field.region"));
        data.addAttribute("urlDelete", environment.getProperty("app.view.route.delete.field.region"));

        return environment.getProperty("app.view.route.template.main.field.region");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(RegionDTO region) {

        Region r = new Region();
        r.setName(region.getName());

        MacroRegion mr = macroRegionRepository.findById(new Long(region.getMacroRegionId())).orElseThrow();
        r.setMacroRegion(mr);

        for (String cityID : region.getCitiesIDs()) 
            r.addCity(cityRepository.findById(new Long(cityID)).orElseThrow());

        regionRepository.save(r);

        this.setOperationSuccessMessage();

        return environment.getProperty("app.view.route.create.success.field.region");
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(RegionDTO region) {

        Region r = regionRepository.findById(new Long(region.getId())).orElseThrow();
        r.setName(region.getName());

        MacroRegion mr = macroRegionRepository.findById(new Long(region.getMacroRegionId())).orElseThrow();
        r.setMacroRegion(mr);

        r.setCities(new ArrayList<City>());
        for (String cityID : region.getCitiesIDs()) 
            r.addCity(cityRepository.findById(new Long(cityID)).orElseThrow());

        regionRepository.saveAndFlush(r);

        this.setOperationSuccessMessage();        

        return environment.getProperty("app.view.route.update.success.field.region");
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam int id) {
        regionRepository.deleteById(new Long(id));

        this.setOperationSuccessMessage();

        return environment.getProperty("app.view.route.delete.success.field.region");
    }
}
