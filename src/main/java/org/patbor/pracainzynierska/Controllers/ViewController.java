package org.patbor.pracainzynierska.Controllers;

import org.patbor.pracainzynierska.Models.*;
import org.patbor.pracainzynierska.Models.Process;
import org.patbor.pracainzynierska.POJO.IDWrapper;
import org.patbor.pracainzynierska.Service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/view")
public class ViewController {

    private ProcessService processService;
    private PartService partService;
    private OperationService operationService;
    private ViewService viewService;
    private SetupService setupService;
    private MachineService machineService;
    private ListOfEqService listOfEqService;
    private EquipmentSerivce equipmentSerivce;
    private MachCutService machCutService;
    private ADPUService adpuService;
    private List<Operation> operations;
    private List<Setup> setups;
    private List<MachCut> machCuts;
    private Part part;

    @Autowired
    public ViewController(ProcessService processService, PartService partService, OperationService operationService,
                          ViewService viewService, SetupService setupService, MachineService machineService,
                          ListOfEqService listOfEqService, EquipmentSerivce equipmentSerivce, MachCutService machCutService, ADPUService adpuService) {
        this.processService = processService;
        this.partService = partService;
        this.operationService = operationService;
        this.viewService = viewService;
        this.setupService = setupService;
        this.machineService = machineService;
        this.listOfEqService = listOfEqService;
        this.equipmentSerivce = equipmentSerivce;
        this.machCutService = machCutService;
        this.adpuService = adpuService;
    }

    @GetMapping
    public String showProcess(Model model) {
        IDWrapper id = new IDWrapper();
        List<Part> parts = partService.getAllParts();
        model.addAttribute("id", id);
        model.addAttribute("parts", parts);
        return "inz/view/part-list";
    }

    @GetMapping("/detail")
    public String getDetailsPart(Model model, @ModelAttribute("id") IDWrapper id) {
        part = partService.getPartByPartId(id.getId());
        Process process;
        Blank blank;
        List<Feature> features;
        if (part.getProcess() == null) {
            process = new Process();
        } else {
            process = part.getProcess();
        }
        if (part.getBlank() == null) {
            blank = new Blank();
        } else {
            blank = part.getBlank();
        }
        if (part.getFeatures() == null) {
            features = new ArrayList<>();
        } else {
            features = part.getFeatures();
        }
        features.sort(Comparator.comparing(Feature::getIdftr));
        processService.setBlank(blank);
        operations = operationService.getOperationWithIDPRJ(process.getIdprj());
        model.addAttribute("part", part);
        model.addAttribute("blank", blank);
        model.addAttribute("process", process);
        model.addAttribute("operations", operations);
        model.addAttribute("features", features);
        return "inz/view/detail";
    }

    @GetMapping("/detail/operation")
    public String getDetailsOperation(Model model, @ModelAttribute("id") IDWrapper id) {
        Operation operation = viewService.getOperationWithID(operations, id.getId());
        operationService.setOperation(operation);
        setups = setupService.findSetupWithIdop(operation.getIdop());
        Machine machine = operation.getMachineList();
        model.addAttribute("operation", operation);
        model.addAttribute("setups", setups);
        model.addAttribute("machine", machine);
        return "inz/view/details-operation";
    }

    @GetMapping("/detail/operation/setup")
    public String getDetailsSetup(Model model, @ModelAttribute("id") IDWrapper id) {
        Setup setup = viewService.getSetupWithID(setups, id.getId());
        String setupId = setup.getIdset();
        List<ListOfEq> temp = listOfEqService.findListOfEqByIDSet(setupId);
        TreeSet<Equipment> equipment = equipmentSerivce.findAllByIdeq(setupId);
        machCuts = machCutService.findAllByIdset(setupId);
        ADPU adpu = adpuService.findADPUById(setup.getSetType());
        setupService.setSetup(setup);
        model.addAttribute("setup", setup);
        model.addAttribute("equipment", equipment);
        model.addAttribute("machCuts", machCuts);
        model.addAttribute("adpu", adpu);
        return "inz/view/detail-operation-setup";
    }

    @GetMapping("/detail/operation/setup/treatment")
    public String getDetailsTreatment(Model model, @ModelAttribute("id") IDWrapper id) {
        MachCut treatment = viewService.findTreatment(machCuts, id.getId());
        Tool tool = treatment.getTool();
        ADTR adtr = treatment.getAdtr();
        Feature feature = treatment.getFeature();
        model.addAttribute("treatment", treatment);
        model.addAttribute("tool", tool);
        model.addAttribute("adtr", adtr);
        model.addAttribute("feature", feature);
        return "inz/view/detail-operation-setup-treatment";
    }

}

