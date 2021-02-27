package org.patbor.pracainzynierska.Service;


import org.patbor.pracainzynierska.Models.*;
import org.patbor.pracainzynierska.Models.Process;
import org.patbor.pracainzynierska.Repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class OperationService {

    private OperationRepository operationRepository;
    private SetupService setupService;
    private Operation operation;

    @Autowired
    public OperationService(OperationRepository operationRepository,SetupService setupService) {
        this.operationRepository = operationRepository;
        this.setupService = setupService;
    }

    public List<Operation> getOperationWithIDPRJ(String idprj) {
        List<Operation> operations = operationRepository.findByIdprj(idprj);
        operations.sort(Comparator.comparing(Operation::getIdop));
        return operations;
    }
    public Operation findProcessByIdop(String idop) {
        Optional<Operation> operation = operationRepository.findByIdop(idop);

        return operation.get();
    }
    public void saveOperation(Operation operation, Part part) {
        Optional<Operation> tempOper = operationRepository.findByIdop(operation.getIdop());
        if (operation.getId() == null) {
            if (tempOper.isPresent()) {
                throw new RuntimeException("Operation already exists");
            } else {

                String idprj = part.getProcess().getIdprj();
                String idop = createIDOP(idprj);
                operation.setIdop(idop);
                operation.setIdprj(idprj);
                operation.setOpno(findOPNo(idop));
                operationRepository.save(operation);
                operationRepository.createRelationshipBetweenProcessAndOperation(idprj, idop);
                operationRepository.createRelationshipBetweenOperationAndMachine(operation.getIdop(), operation.getWorkstation());
            }
        } else {
            Operation tempOp = findByID(operation.getId());
            operationRepository.updateOperation(tempOp.getIdop(), operation.getOpType(), operation.getDepartment(),
                    operation.getWorkstation(), operation.getDescription(), operation.getTpz(), operation.getTj());
            operationRepository.deleteRelationshipBetweenOperationAndMachine(tempOp.getIdop(), tempOp.getWorkstation());
            operationRepository.createRelationshipBetweenOperationAndMachine(tempOp.getIdop(), operation.getWorkstation());
        }
    }

    public Operation findByID(Long id) {
        return operationRepository.findById(id).get();
    }

    private Integer findOPNo(String idop) {
        String opnoStr = idop.substring(idop.indexOf("OP") + 2);
        Integer opno = Integer.valueOf(opnoStr);
        return opno;
    }

    public String createIDOP(String idprj) {
        StringBuilder st = new StringBuilder("PT");
        st.append(getNumberOfProcess(idprj));
        st.append("OP");
        st.append(numberOfOperationByProcess(idprj));
        return st.toString();
    }

    private String getNumberOfProcess(String idprj) {
        String number = idprj.substring(idprj.indexOf("J") + 1);
        return number;
    }

    private int numberOfOperationByProcess(String idprj) {
        List<Operation> operations = getOperationWithIDPRJ(idprj);
        int size = operations.size();
        if (size == 0) {
            return 10;
        }
        return (size + 1) * 10;
    }

    public void deleteOperation(long id) {
        Operation tempOp = findByID(id);
        List<Setup> setups = setupService.findSetupWithIdop(tempOp.getIdop());
        operationRepository.deleteThirdNeighborhoodPart(tempOp.getIdop());
        if(setups != null) {
            for (Setup s : setups) {
                operationRepository.deleteFourthNeighborhoodPart(s.getIdset());
            }
        }

    }
    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Operation getOperation() {
        return operation;
    }
}
