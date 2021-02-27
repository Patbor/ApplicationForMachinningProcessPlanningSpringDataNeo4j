package org.patbor.pracainzynierska.Controllers;

import org.patbor.pracainzynierska.Models.Machine;
import org.patbor.pracainzynierska.Models.Operation;
import org.patbor.pracainzynierska.Models.Part;
import org.patbor.pracainzynierska.Models.Process;
import org.patbor.pracainzynierska.Repository.OperationRepository;
import org.patbor.pracainzynierska.Service.MachineService;
import org.patbor.pracainzynierska.Service.OperationService;
import org.patbor.pracainzynierska.Service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/operation")
@Controller
public class OperationController {

    OperationService operationService;
    PartService partService;
    MachineService machineService;

    @Autowired
    public OperationController(OperationService operationService, PartService partService, MachineService machineService) {
        this.operationService = operationService;
        this.partService = partService;
        this.machineService = machineService;
    }

    @GetMapping("/formOperation")
    public String showOperationForm(Model model) {
        Operation operation = new Operation();
        List<Machine> machines = machineService.getAllMachines();
        model.addAttribute("operation", operation);
        model.addAttribute("machines", machines);
        return "/inz/form/form-operation";
    }

    @PostMapping("/save")
    public String saveOperation(@ModelAttribute("operation") Operation operation) {
        Part part = partService.getPart();
        operationService.saveOperation(operation, part);
        String fullUrl = "redirect:/view/detail?id=" + part.getIdPart();
        return fullUrl;
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("operationId") Long id, Model model) {
        Operation operation = operationService.findByID(id);
        List<Machine> machines = machineService.getAllMachines();
        model.addAttribute("operation", operation);
        model.addAttribute("machines", machines);
        return "inz/form/form-operation";
    }

    @GetMapping("/delete")
    public String deleteOperation(@ModelAttribute("operationId") Long id) {
        Part part = partService.getPart();
        String fullUrl = "redirect:/view/detail?id=" + part.getIdPart();
        operationService.deleteOperation(id);
        return fullUrl;
    }
}
