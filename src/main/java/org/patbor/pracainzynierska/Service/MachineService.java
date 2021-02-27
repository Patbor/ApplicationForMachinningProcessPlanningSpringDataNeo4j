package org.patbor.pracainzynierska.Service;

import org.patbor.pracainzynierska.Models.Machine;
import org.patbor.pracainzynierska.Repository.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MachineService {
    MachineRepository machineRepository;

    @Autowired
    public MachineService(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    public Machine findMachineByID(String id) {
        Optional<Machine> machine = machineRepository.findByPkidmt(id);
        if(!machine.isPresent()) {
            throw new RuntimeException("Machine with ID" + id + "doesn't exist");
        } else {
            return machine.get();
        }
    }
    public List<Machine> getAllMachines() {
        List<Machine> machines = machineRepository.findAll();
       return machines;
    }
}
