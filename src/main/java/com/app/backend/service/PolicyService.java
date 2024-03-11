package com.app.backend.service;

import com.app.backend.DTO.PolicyResponseDTO;
import com.app.backend.entity.PolicyEntity;

import java.util.List;

public interface PolicyService {
    PolicyResponseDTO getPolicyByPolicyId(Long policyId);
    List<PolicyResponseDTO> getAllPolicies();
    PolicyResponseDTO addPolicy(PolicyEntity policy);
    PolicyEntity updatePolicy(PolicyEntity policy);
    void deletePolicyByPolicyId(Long policyId);
}
