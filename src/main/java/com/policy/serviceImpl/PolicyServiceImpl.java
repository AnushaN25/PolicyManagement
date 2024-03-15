package com.policy.serviceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.policy.exception.PolicyCreationException;
import com.policy.exception.PolicyNotFoundException;
import com.policy.model.Policy;
import com.policy.repository.PolicyRepository;
import com.policy.service.PolicyService;

import java.util.List;
import java.util.Optional;

@Service
public class PolicyServiceImpl implements PolicyService {

    private static final Logger logger = LoggerFactory.getLogger(PolicyServiceImpl.class);

    @Autowired
    private PolicyRepository policyRepo;

	@Override
	public Policy createPolicy(Policy policy)throws PolicyCreationException {
		 logger.info("Creating new policy with details: {}", policy);
		    Policy createdPolicy = policyRepo.save(policy);
		    if (createdPolicy == null) {
		        String errorMessage = "Failed to create policy: returned policy object is null";
		        logger.error(errorMessage);
		        throw new PolicyCreationException(errorMessage);
		    }
		    return createdPolicy;
	}
	
	@Override
	public boolean deletePolicy(long policyId)throws PolicyNotFoundException {
		logger.info("Deleting policy with ID: {}", policyId);
        if (policyRepo.existsById(policyId)) {
            policyRepo.deleteById(policyId);
            return true;
        } else {
            logger.error("Policy with ID {} not found", policyId);
            throw new PolicyNotFoundException("Policy with ID " + policyId + " not found");
        }
    }
	

	@Override
	public Optional<Policy> updatePolicy(long policyId, Policy policyDetails) throws PolicyNotFoundException  {
		logger.info("Updating policy with ID: {}", policyId);
        Optional<Policy> optionalPolicy = policyRepo.findById(policyId);
        if (optionalPolicy.isPresent()) {
            Policy existingPolicy = optionalPolicy.get();
            existingPolicy.setPolicyName(policyDetails.getPolicyName());
            // Update existing policy attributes with the new details
            existingPolicy.setPolicyType(policyDetails.getPolicyType());
            existingPolicy.setMaxNoOfYears(policyDetails.getMaxNoOfYears());
            existingPolicy.setPremiumRate(policyDetails.getPremiumRate());
            existingPolicy.setMaxSumAssured(policyDetails.getMaxSumAssured());
            return Optional.of(policyRepo.save(existingPolicy));
        } else {
            logger.error("Policy with ID {} not found", policyId);
            throw new PolicyNotFoundException("Policy with ID " + policyId + " not found");
        }
	}

	

	@Override
	public List<Policy> getAllPolicies() {
		 logger.info("Fetching all policies");
	        return policyRepo.findAll();
	}

	@Override
	public Optional<Policy> getPolicyById(long policyId) throws PolicyNotFoundException {
		logger.info("Fetching policy with ID: {}", policyId);
        Optional<Policy> policyOptional = policyRepo.findById(policyId);
        if (policyOptional.isPresent()) {
            return policyOptional;
        } else {
            logger.error("Policy with ID {} not found", policyId);
            throw new PolicyNotFoundException("Policy with ID " + policyId + " not found");
        }
	}

    

   
}
