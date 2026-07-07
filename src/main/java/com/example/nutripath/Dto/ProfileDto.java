package com.example.nutripath.Dto;

public class ProfileDto {

    private Long accountId;
    private Integer age;
    private Double weightKg;
    private Double heightCm;
    private Double calculatedBmr;

    public ProfileDto() {
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(Double weightKg) {
        this.weightKg = weightKg;
    }

    public Double getHeightCm() {
        return heightCm;
    }

    public void setHeightCm(Double heightCm) {
        this.heightCm = heightCm;
    }

    public Double getCalculatedBmr() {
        return calculatedBmr;
    }

    public void setCalculatedBmr(Double calculatedBmr) {
        this.calculatedBmr = calculatedBmr;
    }
}