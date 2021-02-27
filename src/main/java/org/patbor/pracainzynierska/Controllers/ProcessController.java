package org.patbor.pracainzynierska.Controllers;

import org.patbor.pracainzynierska.Models.Blank;
import org.patbor.pracainzynierska.Models.Part;
import org.patbor.pracainzynierska.Models.Process;
import org.patbor.pracainzynierska.Service.PartService;
import org.patbor.pracainzynierska.Service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/process")
public class ProcessController {
    private ProcessService processService;
    private PartService partService;

    @Autowired
    public ProcessController(ProcessService processService, PartService partService) {
        this.processService = processService;
        this.partService = partService;
    }

    @GetMapping("/formProcess")
    public String showFormProcess(Model model) {
        Process process = new Process();
        model.addAttribute("process", process);
        return "inz/form/form-process";
    }

    @PostMapping("/save")
    public String saveProcess(@ModelAttribute("process") Process process) {
        Part part = partService.getPart();
        String idPart = part.getIdPart();
        processService.saveProcess(part, process);
        String fullUrl = "redirect:/view/detail?id=" + idPart;
        return fullUrl;
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("processId") Long id, Model model) {
        Process process = processService.findProcessById(id);
        model.addAttribute("process", process);
        return "inz/form/form-process";
    }
}
