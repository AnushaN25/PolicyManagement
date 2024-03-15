package com.policy.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.policy.exception.PolicyCreationException;
import com.policy.exception.PolicyNotFoundException;
import com.policy.model.Policy;
import com.policy.serviceImpl.PolicyServiceImpl;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PolicyController {

    @Autowired
    private PolicyServiceImpl policyServiceImpl;
    
    @PostMapping("/createPolicy")
    public Policy createPolicy(@RequestBody Policy policy) throws PolicyCreationException {
        return policyServiceImpl.createPolicy(policy);
    }
    
    @DeleteMapping("deletePolicy/{policyId}")
    public void deletePolicy(@PathVariable long policyId) throws PolicyNotFoundException {
        policyServiceImpl.deletePolicy(policyId);
    }

    @PutMapping("/updatePolicy/{policyId}")
    public Policy updatePolicy(@PathVariable long policyId, @RequestBody Policy policyDetails) throws PolicyNotFoundException {
        return policyServiceImpl.updatePolicy(policyId, policyDetails)
                .orElseThrow(() -> new PolicyNotFoundException("Policy not found with id: " + policyId));
    }

    @GetMapping("/getAllPolicies")
    public List<Policy> getAllPolicies() {
        return policyServiceImpl.getAllPolicies();
    }
    
    @GetMapping("/getPolicyById/{policyId}")
    public Optional<Policy> getPolicyById(@PathVariable long policyId) throws PolicyNotFoundException {
        return policyServiceImpl.getPolicyById(policyId);
    }
}


