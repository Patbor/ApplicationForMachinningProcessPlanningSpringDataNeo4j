package org.patbor.pracainzynierska.Service;

import org.patbor.pracainzynierska.Models.Equipment;
import org.patbor.pracainzynierska.Models.ListOfEq;
import org.patbor.pracainzynierska.Models.Setup;
import org.patbor.pracainzynierska.Repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EquipmentSerivce {
    EquipmentRepository equipmentRepository;
    SetupService setupService;
    ListOfEqService listOfEqService;

    @Autowired
    public EquipmentSerivce(EquipmentRepository equipmentRepository, SetupService setupService, ListOfEqService listOfEqService) {
        this.equipmentRepository = equipmentRepository;
        this.setupService = setupService;
        this.listOfEqService = listOfEqService;
    }

    public Equipment findByIdeq(String ideq) {
        Optional<Equipment> eq = equipmentRepository.findByIdeq(ideq);
        if (!eq.isPresent()) {
            throw new RuntimeException("Eq with ID" + ideq + "doesn't exist");
        }
        return eq.get();
    }

    public List<Equipment> findAllEquipments() {
        List<Equipment> eqs = equipmentRepository.findAll();
        return eqs;
    }

    public void createConnections(Equipment equipment) {
        listOfEqService.saveListOfEq(equipment.getIdeq(), setupService.getSetup().getIdset());
    }

    public TreeSet<Equipment> findAllByIdeq(String idset) {
      TreeSet<Equipment> equipment = new TreeSet<>(Comparator.comparing(Equipment::getIdeq));
      List<Equipment> eqs = equipmentRepository.findEquipment(idset);
      eqs.forEach(k->equipment.add(k));

      return equipment;
    }
    public void deleteRelationship(Setup setup, String ideq) {
        List<ListOfEq> eqs = listOfEqService.findListOfEqByIDSet(setup.getIdset());
        for(ListOfEq list : eqs) {
            if(list.getIdeq().equals(ideq)) {
                listOfEqService.deleteByID(setup.getIdset(),ideq);
            }
        } }
}
