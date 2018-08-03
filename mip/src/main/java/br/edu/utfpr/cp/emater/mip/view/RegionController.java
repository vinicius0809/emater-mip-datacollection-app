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

@Controller
@RequestMapping(value = "/region")
public class RegionController {

    private RegionRepository regionRepository;
    private MacroRegionRepository macroRegionRepository;

    @Autowired
    public RegionController(RegionRepository regionRepository, MacroRegionRepository macroRegionRepository) {
        this.regionRepository = regionRepository;
        this.macroRegionRepository = macroRegionRepository;
    }
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listAll(Model data) {
        
        data.addAttribute("regions", regionRepository.findAll());
        data.addAttribute("macroRegions", macroRegionRepository.findAll());
        data.addAttribute("title", "Gerenciamento de Regiões");
        
        return "/field/region/index";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(@RequestParam String name, @RequestParam int macroRegionId) {

        MacroRegion mr = macroRegionRepository.findById(new Long (macroRegionId)).get();
        regionRepository.save(new Region(null, name, mr));
        
        return "/region";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(@RequestParam String name, @RequestParam int id, @RequestParam int macroRegionId) {

        MacroRegion mr = macroRegionRepository.findById(new Long (macroRegionId)).get();
        Region r = regionRepository.findById(new Long(id)).get();
        
        r.setName(name);
        r.setMacroRegion(mr);
        
        regionRepository.saveAndFlush(r);
        
        return "/region";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam int id) {
        regionRepository.deleteById(new Long (id));

        return "/region";
    }
}
