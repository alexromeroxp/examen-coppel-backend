package com.app.backend.controller;

import com.app.backend.DTO.PolicyResponseDTO;
import com.app.backend.DTO.ResponseUtilDTO;
import com.app.backend.entity.PolicyEntity;
import com.app.backend.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/policy")
public class PolicyController {

    private static final Logger logger = LoggerFactory.getLogger(PolicyController.class);

    private final PolicyService policyService;

    @Autowired
    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @GetMapping("/{policyId}")
    public ResponseEntity<ResponseUtilDTO.Response<Object>> getPolicyByPolicyId(@PathVariable Long policyId) {
        try {
            logger.info("Fetching policy by policyId: {}", policyId);
            PolicyResponseDTO policy = policyService.getPolicyByPolicyId(policyId);

            if (policy == null) {
                logger.warn("Policy not found for policyId: {}", policyId);
                return ResponseUtilDTO.generateErrorResponse("Póliza no encontrada");
            }

            logger.info("Policy found for policyId: {}", policyId);
            return ResponseUtilDTO.generateSuccessResponse(policy);

        } catch (Exception e) {
            logger.error("Error occurred while fetching policy by policyId: {}", policyId, e);
            return ResponseUtilDTO.generateErrorResponse( e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseUtilDTO.Response<Object>> getAllPolicies() {
        try {
            logger.info("Fetching all policies");
            List<PolicyResponseDTO> policies = policyService.getAllPolicies();

            if (policies.isEmpty()) {
                logger.warn("No policies available");
                return ResponseUtilDTO.generateErrorResponse("No hay pólizas disponibles");
            }

            logger.info("Fetched {} policies", policies.size());
            return ResponseUtilDTO.generateSuccessResponse(policies);

        } catch (Exception e) {
            logger.error("Error occurred while fetching all policies", e);
            return ResponseUtilDTO.generateErrorResponse( e.getMessage());
        }
    }

// ... (código anterior)

    @PostMapping("/add")
    public ResponseEntity<ResponseUtilDTO.Response<Object>> addPolicy(@RequestBody PolicyEntity policy) {
        try {
            logger.info("Adding new policy: {}", policy);
            PolicyResponseDTO addedPolicy = policyService.addPolicy(policy);
            logger.info("Policy added successfully with ID: {}", addedPolicy.getPoliza().getIDPoliza());
            return ResponseUtilDTO.generateSuccessResponse(addedPolicy);
        } catch (Exception e) {
            logger.error("Error occurred while adding a new policy", e);
            return ResponseUtilDTO.generateErrorResponse( e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseUtilDTO.Response<Object>> updatePolicy(@RequestBody PolicyEntity policy) {
        try {
            logger.info("Updating policy with ID: {}", policy.getPolicyId());
            PolicyEntity updatedPolicy = policyService.updatePolicy(policy);
            logger.info("Policy updated successfully with ID: {}", updatedPolicy.getPolicyId());
            return ResponseUtilDTO.generateSuccessResponse("Se actualizó correctamente la póliza # " + updatedPolicy.getPolicyId());
        } catch (Exception e) {
            logger.error("Error occurred while updating policy", e);
            return ResponseUtilDTO.generateErrorResponse( e.getMessage());
        }
    }

    @DeleteMapping("/delete/{policyId}")
    public ResponseEntity<ResponseUtilDTO.Response<Object>> deletePolicyByPolicyId(@PathVariable Long policyId) {
        try {
            logger.info("Deleting policy with ID: {}", policyId);
            policyService.deletePolicyByPolicyId(policyId);
            logger.info("Policy deleted successfully with ID: {}", policyId);
            return ResponseUtilDTO.generateSuccessResponse("Se eliminó correctamente la póliza # " + policyId);
        } catch (Exception e) {
            logger.error("Error occurred while deleting policy with ID: {}", policyId, e);
            return ResponseUtilDTO.generateErrorResponse( e.getMessage());
        }
    }
}
