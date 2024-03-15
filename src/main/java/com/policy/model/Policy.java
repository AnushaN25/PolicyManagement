package com.policy.model;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="policy_info")
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long policyId;
    
    @NotNull(message = "name is required")
    @Size(min = 6, message = "Policy name must contain atleast 6 charaters")
    @Column(name = "policy_name")
    private String policyName;

    @NotBlank(message = "Policy type is required")
    private String policyType;

    @NotNull(message = "Max number of years must be specified")
    @PositiveOrZero(message = "Max number of years must be a positive or zero value")
    private int maxNoOfYears;

    @NotNull(message = "Premium rate must be specified")
    @PositiveOrZero(message = "Premium rate must be a positive or zero value")
    private double premiumRate;

    @NotNull(message = "Max sum assured must be specified")
    @PositiveOrZero(message = "Max sum assured must be a positive or zero value")
    private double maxSumAssured;

}
