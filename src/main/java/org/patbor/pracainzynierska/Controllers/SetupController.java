package org.patbor.pracainzynierska.Controllers;

import org.patbor.pracainzynierska.Models.*;
import org.patbor.pracainzynierska.Models.Process;
import org.patbor.pracainzynierska.Service.ADPUService;
import org.patbor.pracainzynierska.Service.OperationService;
import org.patbor.pracainzynierska.Service.SetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/setup")
public class SetupController {
    SetupService setupService;
    OperationService operationService;
    ADPUService adpuService;

    @Autowired
    public SetupController(SetupService setupService, OperationService operationService, ADPUService adpuService) {
        this.setupService = setupService;
        this.operationService = operationService;
        this.adpuService = adpuService;
    }

    @GetMapping("/formSetup")
    public String formSetup(Model model) {
        Setup setup = new Setup();
        List<ADPU> adpus = adpuService.findAllADPUs();
        model.addAttribute("setup", setup);
        model.addAttribute("adpus", adpus);
        return "inz/form/form-setup";
    }

    @PostMapping("/save")
    public String saveProcess(@ModelAttribute("setup") Setup setup) {
        Operation operation = operationService.getOperation();
        setupService.saveSetup(setup, operation);
        String fullUrl = "redirect:/view/detail/operation?id=" + operation.getIdop();
        return fullUrl;
    }
    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("setupId") Long id, Model model) {
        Setup setup = setupService.findByID(id);
        List<ADPU> adpus = adpuService.findAllADPUs();
        model.addAttribute("setup", setup);
        model.addAttribute("adpus", adpus);
        return "inz/form/form-setup";
    }
    @GetMapping("/delete")
    public String deleteSetup(@ModelAttribute("setupId") Long id) {
        Operation operation = operationService.getOperation();
        setupService.deleteSetup(id);
        String fullUrl = "redirect:/view/detail/operation?id=" + operation.getIdop();
        return fullUrl;
    }
}
