package br.edu.utfpr.cp.emater.mip.view.pest;

import br.edu.utfpr.cp.emater.mip.domain.pest.GrowthPhase;
import br.edu.utfpr.cp.emater.mip.domain.pest.MipPestSurveyRepository;
import br.edu.utfpr.cp.emater.mip.domain.pest.Pest;
import br.edu.utfpr.cp.emater.mip.domain.pest.PestOccurrence;
import br.edu.utfpr.cp.emater.mip.domain.pest.PestRepository;
import br.edu.utfpr.cp.emater.mip.domain.pest.SamplePest;
import br.edu.utfpr.cp.emater.mip.domain.pest.SamplePestRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

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
    private final SamplePestRepository samplePestRepository;

    @Autowired
    public SamplePestController(MipPestSurveyRepository mipPestSurveyRepository, PestRepository pestRepository, SamplePestRepository samplePestRepository) {
        this.mipPestSurveyRepository = mipPestSurveyRepository;
        this.pestRepository = pestRepository;
        this.samplePestRepository = samplePestRepository;
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
        data.addAttribute("mipPestSurveyId", mipPestSurveyId);
        
        return "/pest/sample-pest";
    }

    @PostMapping ("/save-sample")
    public String saveSample(@RequestParam Map<String, String> values) {

        samplePestRepository.save(validateEntries(values));
        
        return "redirect:/pest-survey";
    }

    private SamplePest validateEntries (Map<String, String> values) {

        String mipPestSurveyId = values.entrySet().stream().filter(e -> e.getKey().equalsIgnoreCase("mipPestSurveyId")).findAny().orElseThrow(IllegalArgumentException::new).getValue();
        String sampleDate = values.entrySet().stream().filter(e -> e.getKey().equalsIgnoreCase("sampleDate")).findAny().orElseThrow(IllegalArgumentException::new).getValue();
        String growthPhase = values.entrySet().stream().filter(e -> e.getKey().equalsIgnoreCase("growthPhase")).findAny().orElseThrow(IllegalArgumentException::new).getValue();
        String daysAfterEmergence = values.entrySet().stream().filter(e -> e.getKey().equalsIgnoreCase("daysAfterEmergence")).findAny().orElseThrow(IllegalArgumentException::new).getValue();
        String defoliation = values.entrySet().stream().filter(e -> e.getKey().equalsIgnoreCase("defoliation")).findAny().orElseThrow(IllegalArgumentException::new).getValue();

        Date parsedSampleDate;

        try {
            parsedSampleDate = new SimpleDateFormat("yyyy-MM-dd").parse(sampleDate);

        } catch (ParseException e1) {
            throw new ClassCastException("Error parsing sample date");
		}

        SamplePest sp = new SamplePest();
        sp.setMipPestSurvey(mipPestSurveyRepository.findById(new Long(mipPestSurveyId)).orElseThrow(IllegalArgumentException::new));
        sp.setSampleDate(parsedSampleDate);
        sp.setGrowthPhase(GrowthPhase.valueOf(growthPhase));
        sp.setDaysAfterEmergence(new Integer(daysAfterEmergence));
        sp.setDefoliation(new Integer(defoliation));
        sp.setPestOccurrenceList(validatePestEntries(values));

        return sp;
    }

    private List<PestOccurrence> validatePestEntries (Map<String, String> values) {

        List<PestOccurrence> occurrencesFound = null;

        List<String> pestsFound = values.entrySet()
                                        .stream()
                                        .filter(e -> !e.getValue().isEmpty())
                                        .map(e -> e.getKey())
                                        .filter(e -> e.startsWith("pest_"))
                                        .collect(Collectors.toList());

        if (pestsFound == null || pestsFound.size() == 0)
            throw new IllegalArgumentException("No pest was informed as a sample ");

        else {
            occurrencesFound = new ArrayList<>();
            PestOccurrence currentPestOccurrence = null;

            for (String pestFound: pestsFound) {
                currentPestOccurrence = new PestOccurrence();

                Long pestId = new Long(pestFound.split("_")[1]);
                Pest currentPest = pestRepository.findById(pestId).orElseThrow(IllegalArgumentException::new);
                currentPestOccurrence.setPest(currentPest);

                currentPestOccurrence.setValue(new Double (values.get(pestFound))); 

                occurrencesFound.add(currentPestOccurrence);
            }
        }
        
        return occurrencesFound;
    }
    
}
