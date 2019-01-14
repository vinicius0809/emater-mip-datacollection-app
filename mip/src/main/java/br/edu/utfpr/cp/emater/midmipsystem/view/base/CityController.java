package br.edu.utfpr.cp.emater.midmipsystem.view.base;

import br.edu.utfpr.cp.emater.midmipsystem.domain.base.City;
import br.edu.utfpr.cp.emater.midmipsystem.domain.base.CityRepository;
import br.edu.utfpr.cp.emater.midmipsystem.domain.base.MacroRegionRepository;
import br.edu.utfpr.cp.emater.midmipsystem.domain.base.Region;
import br.edu.utfpr.cp.emater.midmipsystem.domain.base.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/city")
public class CityController {

    private final RegionRepository regionRepository;
    private final MacroRegionRepository macroRegionRepository;
    private final CityRepository cityRepository;

    private final Environment environment;

    @Autowired
    public CityController(CityRepository cityRepository, RegionRepository regionRepository, MacroRegionRepository macroRegionRepository, Environment environment) {
        this.cityRepository = cityRepository;
        this.regionRepository = regionRepository;
        this.macroRegionRepository = macroRegionRepository;
        this.environment = environment;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listAll(Model data) {

        data.addAttribute("regions", regionRepository.findAll());
        data.addAttribute("macroRegions", macroRegionRepository.findAll());
        data.addAttribute("cities", cityRepository.findAll());

        data.addAttribute("urlCreate", environment.getProperty("app.view.route.create.field.city"));
        data.addAttribute("urlUpdate", environment.getProperty("app.view.route.update.field.city"));
        data.addAttribute("urlDelete", environment.getProperty("app.view.route.delete.field.city"));

        return environment.getProperty("app.view.route.template.main.field.city");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@RequestParam String name, @RequestParam int regionId) {

        Region region = regionRepository.findById(new Long (regionId)).get();

        City c = new City();
        c.setName(name);
        c.setRegion(region);

        cityRepository.save(c);

        return environment.getProperty("app.view.route.create.success.field.city");
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam String name, @RequestParam int id, @RequestParam int regionId) {

        Region r = regionRepository.findById(new Long(regionId)).get();
        City c = cityRepository.findById(new Long(id)).get();

        c.setName(name);
        c.setRegion(r);

        cityRepository.saveAndFlush(c);

        return environment.getProperty("app.view.route.update.success.field.city");
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam int id) {
        cityRepository.deleteById(new Long(id));

        return environment.getProperty("app.view.route.delete.success.field.city");
    }

}
