package org.patbor.pracainzynierska.Controllers;


import org.patbor.pracainzynierska.Models.Part;
import org.patbor.pracainzynierska.Service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/part")
public class PartController {

    private PartService partService;


    @Autowired
    public PartController(PartService partService) {
        this.partService = partService;
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Part part = new Part();
        model.addAttribute("part", part);
        return "inz/form/part-form";
    }


    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("partId") Long id, Model model) {
        Part part = partService.getPartById(id);
        model.addAttribute("part", part);
        return "inz/form/part-form";
    }

    @PostMapping("/save")
    public String savePart(@ModelAttribute("part") Part part) {
        partService.savePart(part);
        return "redirect:/view";
    }

    @GetMapping("/delete")
    public String deletePart(@ModelAttribute("partId") Long id) {
        partService.deletePart(id);
        return "redirect:/view";
    }
}
