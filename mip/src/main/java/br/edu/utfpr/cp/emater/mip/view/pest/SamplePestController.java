package br.edu.utfpr.cp.emater.mip.view.pest;

import br.edu.utfpr.cp.emater.mip.domain.pest.GrowthPhase;
import br.edu.utfpr.cp.emater.mip.domain.pest.MipPestSurveyRepository;
import br.edu.utfpr.cp.emater.mip.domain.pest.PestRepository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pest-survey")
public class SamplePestController {
    
    private final MipPestSurveyRepository mipPestSurveyRepository;
    private final PestRepository pestRepository;

    @Autowired
    public SamplePestController(MipPestSurveyRepository mipPestSurveyRepository, PestRepository pestRepository) {
        this.mipPestSurveyRepository = mipPestSurveyRepository;
        this.pestRepository = pestRepository;
        
    }
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listAll(Model data) {
        data.addAttribute("mipPestSurveys", mipPestSurveyRepository.findAll());
        
        data.addAttribute("pageTitle", "Anotação de Campo sobre a Flutuação das Pragas");
        
        return "/pest/mip-pest-survey-list";
    }
    
    @RequestMapping(value = "/add-sample", method = RequestMethod.GET)
    public String addSample(@RequestParam int mipPestSurveyId, Model data) {
        data.addAttribute("pageTitle", "Nova Amostra");
        
        data.addAttribute("growthPhases", GrowthPhase.values());
        data.addAttribute("pestList", pestRepository.findAll());
        
        return "/pest/sample-pest";
    }

    @PostMapping ("/save-sample")
    public String saveSample(@RequestParam Map<String, String> values) {

        // The page must receive the reference from MipPestSurvey

        values.entrySet().stream().filter(e -> !values.get(e.getKey()).isEmpty()).forEach(e -> System.out.println(e.getKey() + " - " + values.get(e.getKey())));
        
        return "redirect:/pest-survey";
    }
    
}
