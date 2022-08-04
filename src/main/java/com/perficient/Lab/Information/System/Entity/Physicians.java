package com.perficient.Lab.Information.System.Entity;

import com.fasterxml.jackson.annotation.JsonFilter;

import javax.persistence.*;

//@JsonFilter("PhysicianFilter")
@Entity
@Table(name = "physicians")
public class Physicians {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phy_id")

    private int phyId;

    @Column(name = "name")
    private String name;

    @Column(name = "office_address")
    private String officeaddress;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zipcode")
    private int zipCode;

    public Physicians() {
        super();
    }

    public Physicians(int phyId, String name, String officeaddress, String city, String state, int zipCode) {
        this.phyId = phyId;
        this.name = name;
        this.officeaddress = officeaddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public int getPhyId() {
        return phyId;
    }

    public void setPhyId(int phyId) {
        this.phyId = phyId;
    }

    public String  getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOfficeaddress() {
        return officeaddress;
    }

    public void setOfficeaddress(String officeaddress) {
        this.officeaddress = officeaddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Physicians{" +
                "phyId=" + phyId +
                ", name='" + name + '\'' +
                ", officeaddress='" + officeaddress + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode=" + zipCode +
                '}';
    }
}
