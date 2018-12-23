package br.edu.utfpr.cp.emater.mip.view.field;

import br.edu.utfpr.cp.emater.mip.domain.field.macroregion.MacroRegion;
import br.edu.utfpr.cp.emater.mip.domain.field.macroregion.MacroRegionRepository;
import br.edu.utfpr.cp.emater.mip.domain.field.region.Region;
import br.edu.utfpr.cp.emater.mip.domain.field.region.RegionRepository;
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

    private final Environment environment;

    @Autowired
    public RegionController(RegionRepository regionRepository, MacroRegionRepository macroRegionRepository, Environment environment) {
        this.regionRepository = regionRepository;
        this.macroRegionRepository = macroRegionRepository;
        this.environment = environment;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listAll(Model data) {

        data.addAttribute("regions", regionRepository.findAll());
        data.addAttribute("macroRegions", macroRegionRepository.findAll());

        data.addAttribute("urlCreate", environment.getProperty("app.view.route.create.field.region"));
        data.addAttribute("urlUpdate", environment.getProperty("app.view.route.update.field.region"));
        data.addAttribute("urlDelete", environment.getProperty("app.view.route.delete.field.region"));

        return environment.getProperty("app.view.route.template.main.field.region");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@RequestParam String name, @RequestParam int macroRegionId) {

        MacroRegion mr = macroRegionRepository.findById(new Long(macroRegionId)).get();
        
        Region r = new Region();
        r.setMacroRegion(mr);
        r.setName(name);

        regionRepository.save(r);

        return environment.getProperty("app.view.route.create.success.field.region");
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam String name, @RequestParam int id, @RequestParam int macroRegionId) {

        MacroRegion mr = macroRegionRepository.findById(new Long(macroRegionId)).get();
        Region r = regionRepository.findById(new Long(id)).get();

        r.setName(name);
        r.setMacroRegion(mr);

        regionRepository.saveAndFlush(r);

        return environment.getProperty("app.view.route.update.success.field.region");
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam int id) {
        regionRepository.deleteById(new Long(id));

        return environment.getProperty("app.view.route.delete.success.field.region");
    }
}
