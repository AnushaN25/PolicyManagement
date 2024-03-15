package com.policy.service;

import java.util.List;
import java.util.Optional;

import com.policy.exception.PolicyCreationException;
import com.policy.exception.PolicyNotFoundException;
import com.policy.model.Policy;

public interface PolicyService {
	Policy createPolicy(Policy policy)throws  PolicyCreationException;
    Optional<Policy> updatePolicy(long policyId, Policy policyDetails) throws PolicyNotFoundException;
    boolean deletePolicy(long policyId) throws PolicyNotFoundException;
    List<Policy> getAllPolicies();
    Optional<Policy> getPolicyById(long policyId)throws PolicyNotFoundException;
    
}

