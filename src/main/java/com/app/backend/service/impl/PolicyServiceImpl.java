package com.app.backend.service.impl;

import com.app.backend.DTO.PolicyResponseDTO;
import com.app.backend.entity.EmployeeEntity;
import com.app.backend.entity.InventoryEntity;
import com.app.backend.entity.PolicyEntity;
import com.app.backend.repository.EmployeeRepository;
import com.app.backend.repository.InventoryRepository;
import com.app.backend.repository.PolicyRepository;
import com.app.backend.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PolicyServiceImpl implements PolicyService {

    private final PolicyRepository policyRepository;
    private final EmployeeRepository employeeRepository;
    private final InventoryRepository inventoryRepository;

    @Autowired
    public PolicyServiceImpl(PolicyRepository policyRepository, EmployeeRepository employeeRepository, InventoryRepository inventoryRepository) {
        this.policyRepository = policyRepository;
        this.employeeRepository = employeeRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public PolicyResponseDTO getPolicyByPolicyId(Long policyId) {
        PolicyEntity policy = policyRepository.findById(policyId).orElse(null);
        return buildPolicyResponseDTO(policy);

    }

    @Override
    public List<PolicyResponseDTO> getAllPolicies() {
        List<PolicyEntity> policies = policyRepository.findAll();
        List<PolicyResponseDTO> responseDTOs = new ArrayList<>();

        for (PolicyEntity policy : policies) {
            PolicyResponseDTO responseDTO = buildPolicyResponseDTO(policy);
            responseDTOs.add(responseDTO);
        }

        return responseDTOs;
    }


    @Override
    @Transactional
    public PolicyResponseDTO addPolicy(PolicyEntity policy) {
        InventoryEntity inventory = inventoryRepository.findBySku(policy.getSku());

        if (inventory != null) {
            if (inventory.getQuantity() >= policy.getQuantity()) {
                int updatedQuantity = inventory.getQuantity() - policy.getQuantity();
                inventory.setQuantity(updatedQuantity);
                inventoryRepository.save(inventory);

                PolicyEntity addedPolicy = policyRepository.save(policy);
                addedPolicy.setEmployee(employeeRepository.findById((long) addedPolicy.getEmployeeId()).orElse(null));
                addedPolicy.setInventory(inventoryRepository.findBySku(addedPolicy.getSku()));

                return buildPolicyResponseDTO(addedPolicy);
            } else {
                throw new RuntimeException("No se puede agregar la póliza. La cantidad solicitada es mayor que la cantidad en inventario.");
            }
        } else {
            throw new RuntimeException("No se encontró el artículo en el inventario.");
        }
    }


    private PolicyResponseDTO buildPolicyResponseDTO(PolicyEntity policy) {
        PolicyResponseDTO responseDTO = new PolicyResponseDTO();

        PolicyResponseDTO.PolicyInfo policyInfo = new PolicyResponseDTO.PolicyInfo();
        policyInfo.setIDPoliza(policy.getPolicyId());
        policyInfo.setCantidad(policy.getQuantity());
        responseDTO.setPoliza(policyInfo);

        PolicyResponseDTO.EmployeeInfo employeeInfo = new PolicyResponseDTO.EmployeeInfo();
        EmployeeEntity employeeEntity = policy.getEmployee(policy.employeeId);
        employeeInfo.setNombre(employeeEntity.getName());
        employeeInfo.setApellido(employeeEntity.getLastName());
        employeeInfo.setIdEmpleado((long) policy.employeeId);
        responseDTO.setEmpleado(employeeInfo);

        PolicyResponseDTO.InventoryInfo inventoryInfo = new PolicyResponseDTO.InventoryInfo();
        InventoryEntity inventoryEntity = policy.getInventory();
        inventoryInfo.setSKU(inventoryEntity.getSku());
        inventoryInfo.setNombre(inventoryEntity.getName());
        responseDTO.setDetalleArticulo(inventoryInfo);

        return responseDTO;
    }

    @Override
    @Transactional
    public PolicyEntity updatePolicy(PolicyEntity policy) {
        return policyRepository.save(policy);
    }

    @Override
    @Transactional
    public void deletePolicyByPolicyId(Long policyId) {
        policyRepository.deleteById(policyId);
    }
}
