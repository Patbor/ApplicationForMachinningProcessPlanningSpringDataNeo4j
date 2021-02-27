package org.patbor.pracainzynierska.Service;

import org.patbor.pracainzynierska.Models.MachCut;
import org.patbor.pracainzynierska.Models.Setup;
import org.patbor.pracainzynierska.Repository.MachCutRepository;
import org.patbor.pracainzynierska.Repository.SetupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class MachCutService {
    MachCutRepository machCutRepository;
    SetupService setupService;

    @Autowired
    public MachCutService(MachCutRepository machCutRepository, SetupService setupService) {
        this.machCutRepository = machCutRepository;
        this.setupService = setupService;
    }

    public List<MachCut> findAllByIdset(String idset) {
        return machCutRepository.findAllByIdset(idset);
    }

    public void saveTreatment(MachCut machCut) {
        Setup setup = setupService.getSetup();
        String idop = setup.getIdop();
        String setupId = setup.getIdset();

        String idadtr = machCut.getTypeDT();
        if (machCut.getId() == null) {
            String treatmentId = createIDMC();
            Optional<MachCut> tempMach = machCutRepository.findByIdmc(machCut.getIdmc());
            if (tempMach.isPresent()) {
                throw new RuntimeException("Treatment already exists");
            }
            machCut.setIdmc(treatmentId);
            machCut.setIdset(setupId);
            machCut.setMcno(checkNumberOfTreatmentsOnSetup(idop));
            machCutRepository.save(machCut);
            machCutRepository.createRelationshipBetweenTreatmentAndSetup(treatmentId, setupId);
            machCutRepository.createRelationshipBetweenToolAndTreatment(machCut.getToolID(), treatmentId);
            machCutRepository.createRelationshipBetweenFeatureAndTreatmentAndADTR(machCut.getIdpft(), treatmentId, idadtr);
        } else {
            MachCut tempMach = machCutRepository.findById(machCut.getId()).get();
            machCutRepository.updateTreatment(tempMach.getIdmc(),machCut.getPathNo(),machCut.getDescription(),machCut.getIdpft(),machCut.getDp(),machCut.getDk(),
                    machCut.getL(),machCut.getAp(), machCut.getF(),machCut.getVc(),machCut.getN(),machCut.getTg());
            machCutRepository.deleteRelationshipBetweenFeatureAndTreatment(tempMach.getIdpft(),tempMach.getIdmc());
            machCutRepository.createRelationshipBetweenFeatureAndTreatment(machCut.getIdpft(),tempMach.getIdmc());
        }
    }

    private Integer checkNumberOfTreatmentsOnSetup(String idIdop) {
        List<Setup> setups = setupService.findSetupWithIdop(idIdop);
        List<MachCut> machCuts = setups.stream()
                .map(s -> s.getListOfMachCuts())
                .flatMap(list -> list.stream())
                .collect(Collectors.toList());

        if (machCuts == null) {
            return 1;

        }
        return machCuts.size() + 1;
    }

    private String createIDMC() {
        MachCut tempMachcut = findLastIDMC();
        String tempIdmc;
        if (tempMachcut == null) {

            return "MC00001";
        }
        tempIdmc = tempMachcut.getIdmc();

        String number = tempIdmc.substring(tempIdmc.indexOf("C") + 1).replaceFirst("^0*", "");
        int size = number.length();
        if (Pattern.matches("[9]*", number)) {
            size += 1;
        }
        String newIDMC = tempIdmc.replaceAll(tempIdmc.substring(tempIdmc.length() - size), String.valueOf(Integer.valueOf(number) + 1));

        return newIDMC;
    }

    private MachCut findLastIDMC() {
        List<MachCut> machCuts = machCutRepository.findAll();
        if (machCuts.size() == 0) {
            return null;
        }
        Optional<MachCut> machCut = machCuts.stream()
                .sorted(Comparator.comparing(MachCut::getIdmc).reversed())
                .findFirst();

        return machCut.get();
    }

    public MachCut findByID(Long id) {
        return machCutRepository.findById(id).get();
    }

    public void deleteTreatment(Long id) {
        MachCut machCut = findByID(id);
        machCutRepository.deleteTreatment(machCut.getIdmc());
    }
}
