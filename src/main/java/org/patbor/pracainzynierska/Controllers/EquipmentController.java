package org.patbor.pracainzynierska.Controllers;

import org.patbor.pracainzynierska.Models.Equipment;
import org.patbor.pracainzynierska.Models.Setup;
import org.patbor.pracainzynierska.Service.EquipmentSerivce;
import org.patbor.pracainzynierska.Service.SetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/equipment")
public class EquipmentController {

    EquipmentSerivce equipmentSerivce;
    SetupService setupService;

    @Autowired
    public EquipmentController(EquipmentSerivce equipmentSerivce, SetupService setupService) {
        this.equipmentSerivce = equipmentSerivce;
        this.setupService = setupService;
    }

    @GetMapping("/formEquipment")
    public String formEquipment(Model model) {
        Equipment equipment = new Equipment();
        List<Equipment> eqs = equipmentSerivce.findAllEquipments();
        model.addAttribute("eqs", eqs);
        model.addAttribute("equ", equipment);
        return "inz/form/form-equipment";
    }

    @PostMapping("/get")
    public String getEquipment(@ModelAttribute("equ") Equipment eq) {
        Equipment equipment = equipmentSerivce.findByIdeq(eq.getIdeq());
        equipmentSerivce.createConnections(equipment);
        String fullUrl = "redirect:/view/detail/operation/setup?id=" + setupService.getSetup().getIdset();
        return fullUrl;
    }

    @GetMapping("/delete")
    public String deleteRelationship(@ModelAttribute("ideq") String ideq) {
        Setup setup = setupService.getSetup();
        String fullUrl = "redirect:/view/detail/operation/setup?id=" + setup.getIdset();
        equipmentSerivce.deleteRelationship(setup, ideq);
        return fullUrl;

    }
}
