package com.policy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.policy.model.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy,Long>{

}