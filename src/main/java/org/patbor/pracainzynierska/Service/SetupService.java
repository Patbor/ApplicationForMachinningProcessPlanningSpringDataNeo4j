package org.patbor.pracainzynierska.Service;


import org.patbor.pracainzynierska.Models.Operation;
import org.patbor.pracainzynierska.Models.Setup;
import org.patbor.pracainzynierska.Repository.SetupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class SetupService {
    SetupRepository setupRepository;

    Setup setup;

    @Autowired
    public SetupService(SetupRepository setupRepository) {
        this.setupRepository = setupRepository;
    }

    public List<Setup> findSetupWithIdop(String idop) {

        return setupRepository.findByIdop(idop);
    }

    public void saveSetup(Setup setup, Operation operation) {
        Optional<Setup> optional = setupRepository.findByIdset(setup.getIdset());
        if (setup.getId() == null) {
            if (optional.isPresent()) {
                throw new RuntimeException("Setup already exists");
            }
            String operationId = operation.getIdop();
            setup.setIdop(operationId);
            String setId = createSetupId();
            setup.setIdset(setId);
            setup.setSetNo(numberOfSetupsOnOperation(operationId));
            setupRepository.save(setup);
            setupRepository.createRelationshipBetweenOperationAndSetup(operationId, setId);
            setupRepository.createRelationshipBetweenSetupAndADPU(setId, setup.getSetType());
        } else {
            Setup tempSetup = setupRepository.findById(setup.getId()).get();
            setupRepository.updateSetup(tempSetup.getIdset(), setup.getSetType(), setup.getDescription());
            setupRepository.deleteRelationshipBetweenSetupAndADPU(tempSetup.getIdset(), tempSetup.getSetType());
            setupRepository.createRelationshipBetweenSetupAndADPU(tempSetup.getIdset(), setup.getSetType());
        }
    }

    private Setup findLastSetup() {
        List<Setup> setups = setupRepository.findAll().stream()
                .sorted(Comparator.comparing(Setup::getIdset).reversed())
                .collect(Collectors.toList());
        if (setups.size() == 0) {
            return null;
        }
        return setups.get(0);
    }

    private String createSetupId() {
        Setup tempSetup = findLastSetup();
        String tempIdSet;
        if (tempSetup == null) {
           return tempIdSet = "SET0001";
        } else {
            tempIdSet = tempSetup.getIdset();
        }
        String number = tempIdSet.substring(tempIdSet.indexOf("T") + 1).replaceFirst("^0*", "");
        Integer size = number.length();
        if (Pattern.matches("[9]*", number)) {
            size += 1;
        }
        String newSetupId = tempIdSet.replaceAll(tempIdSet.substring(tempIdSet.length() - size), String.valueOf(Integer.valueOf(number) + 1));

        return newSetupId;
    }

    public Integer numberOfSetupsOnOperation(String idop) {
        List<Setup> setups = findSetupWithIdop(idop);
        if (setups == null) {
            return 1;
        }
        return setups.size() + 1;
    }

    public void deleteSetup(Long id) {
        Setup tempSetup = setupRepository.findById(id).get();
        setupRepository.deleteFourthNeighborhoodPart(tempSetup.getIdset());
    }

    public Setup getSetup() {
        return setup;
    }

    public void setSetup(Setup setup) {
        this.setup = setup;
    }

    public Setup findByID(Long id) {
        return setupRepository.findById(id).get();
    }
}
