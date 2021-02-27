package org.patbor.pracainzynierska.Controllers;

import org.patbor.pracainzynierska.Models.Feature;
import org.patbor.pracainzynierska.Models.Machine;
import org.patbor.pracainzynierska.Models.Operation;
import org.patbor.pracainzynierska.Models.Part;
import org.patbor.pracainzynierska.Service.FeatureService;
import org.patbor.pracainzynierska.Service.PartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/feature")
public class FeatureContoller {
    FeatureService featureService;
    PartService partService;

    public FeatureContoller(FeatureService featureService, PartService partService) {
        this.featureService = featureService;
        this.partService = partService;
    }
    @GetMapping("/formFeature")
    public String featureForm(Model model) {
        Feature feature = new Feature();
        model.addAttribute("feature", feature);
        return "inz/form/form-feature";
    }

    @PostMapping("/save")
    public String saveFeature(@ModelAttribute("feature") Feature feature) {
        Part part = partService.getPart();
        featureService.saveFeature(feature);
        String fullUrl = "redirect:/view/detail?id=" + part.getIdPart();

        return fullUrl;
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("featureId") Long id, Model model) {
        Feature feature = featureService.findFeatureById(id);
        model.addAttribute("feature", feature);

        return "inz/form/form-feature";
    }

    @GetMapping("/delete")
    public String deleteFeature(@ModelAttribute("featureId") Long id) {
        Part part = partService.getPart();
        featureService.deleteFeature(id);
        String fullUrl = "redirect:/view/detail?id=" + part.getIdPart();

        return fullUrl;
    }
}
