package org.patbor.pracainzynierska.Controllers;

import org.patbor.pracainzynierska.Models.*;
import org.patbor.pracainzynierska.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/treatment")
public class MachCutController {
    MachCutService machCutService;
    SetupService setupService;
    ADTRService adtrService;
    ToolService toolService;
    FeatureService featureService;

    @Autowired
    public MachCutController(MachCutService machCutService, SetupService setupService, ADTRService adtrService, ToolService toolService, FeatureService featureService) {
        this.machCutService = machCutService;
        this.setupService = setupService;
        this.adtrService = adtrService;
        this.toolService = toolService;
        this.featureService = featureService;
    }

    @GetMapping("/formTreatment")
    public String showForm(Model model) {
        List<ADTR> adtrs = adtrService.findAllADTR();
        List<Tool> tools = toolService.findAllTools();
        List<Feature> features = featureService.findAllFeatures();
        MachCut machCut = new MachCut();
        model.addAttribute("treatment", machCut);
        model.addAttribute("adtrs", adtrs);
        model.addAttribute("tools", tools);
        model.addAttribute("features", features);
        return "inz/form/form-treatment";
    }

    @PostMapping("/save")
    public String saveTreatment(@ModelAttribute("treatment") MachCut machCut) {
        machCutService.saveTreatment(machCut);
        String fullUrl = "redirect:/view/detail/operation/setup?id=" + setupService.getSetup().getIdset();
        return fullUrl;
    }
    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("treatmentId") Long id, Model model) {
        MachCut machCut = machCutService.findByID(id);
        List<Feature> features = featureService.findAllFeatures();
        model.addAttribute("treatment", machCut);
        model.addAttribute("features", features);
        return "inz/form/form-treatment";
    }
    @GetMapping("/delete")
    public String deleteTreatment(@ModelAttribute("treatmentId") Long id) {
        String fullUrl = "redirect:/view/detail/operation/setup?id=" + setupService.getSetup().getIdset();
        machCutService.deleteTreatment(id);
        return fullUrl;
    }
}
