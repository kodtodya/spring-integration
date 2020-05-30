package com.kodtodya.practice.spring.integration.model;

public class Training {
    private int id;
    private String technology;
    private String prerequisite;
    private int noOfDays;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(String prerequisite) {
        this.prerequisite = prerequisite;
    }

    public int getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
    }

    public Training() {
    }

    public Training(int id, String technology, String prerequisite, int noOfDays) {
        this.id = id;
        this.technology = technology;
        this.prerequisite = prerequisite;
        this.noOfDays = noOfDays;
    }
}
