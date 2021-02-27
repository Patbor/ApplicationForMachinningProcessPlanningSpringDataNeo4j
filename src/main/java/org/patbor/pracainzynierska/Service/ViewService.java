package org.patbor.pracainzynierska.Service;

import org.patbor.pracainzynierska.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViewService {
    EquipmentSerivce equipmentSerivce;
    SetupService setupService;

    @Autowired
    public ViewService(EquipmentSerivce equipmentSerivce,SetupService setupService) {
        this.equipmentSerivce = equipmentSerivce;
        this.setupService = setupService;
    }

    public Operation getOperationWithID(List<Operation> operations, String id) {
        List<Operation> op = operations.stream().
                filter(p -> p.getIdop().equals(id)).
                collect(Collectors.toList());
        if (op.isEmpty()) {
            throw new RuntimeException("Operation with ID" + id + " doesn't exist");
        } else {
            return op.get(0);
        }
    }
    public Setup getSetupWithID(List<Setup> setups, String id) {

        List<Setup> st = setups.stream()
                .filter(s->s.getIdset().equals(id))
                .collect(Collectors.toList());
        if (st.isEmpty()) {
            throw new RuntimeException("Setup with ID" + id + " doesn't exist");
        } else {
            return st.get(0);
        }
    }

    public List<Equipment> findEquipment(List<ListOfEq> temp) {
        List<Equipment> equipment = new ArrayList<>();
        for(ListOfEq l : temp) {
            equipment.add(equipmentSerivce.findByIdeq(l.getIdeq()));
        }
        return equipment;
    }
    public MachCut findTreatment(List<MachCut> machCuts,String idmc) {
        List<MachCut> list = machCuts.stream()
                .filter(p->p.getIdmc().equals(idmc))
                .collect(Collectors.toList());
        if(list.isEmpty()) {
            throw new RuntimeException("Treatment with ID: " + idmc + " doesn't exist");
        } else {
            return list.get(0);
        }
    }
}
