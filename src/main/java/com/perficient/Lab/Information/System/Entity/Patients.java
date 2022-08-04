package com.perficient.Lab.Information.System.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "patients")
public class Patients extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pat_id")
    private int patId;

    @Column(name = "LName")
    private String lName;

    @Column(name = "FName")
    private String fName;

    @Column(name = "DOB")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate DOB;

    @Column(name = "SSN")
    private String SSN;

    @Column(name = "Address")
    private String address;

    @Column(name = "city")
    private String City;

    @Column(name = "state")
    private String State;

    @Column(name = "zipcode")
    private int zipcode;

    @Column(name = "orderdate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderdate;

//    @Column(name = "physician_id")
    @OneToOne(fetch = FetchType.EAGER, targetEntity = Physicians.class)
    @JoinColumn(name = "physician_id", referencedColumnName = "phy_id",nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Physicians phyId;


    public Patients() {
        super();
    }

    public Patients(int patId, String lName, String fName, LocalDate DOB, String SSN, String address, String city, String state, int zipcode, LocalDate orderdate, Physicians phyId) {
        this.patId = patId;
        this.lName = lName;
        this.fName = fName;
        this.DOB = DOB;
        this.SSN = SSN;
        this.address = address;
        City = city;
        State = state;
        this.zipcode = zipcode;
        this.orderdate = orderdate;
        this.phyId = phyId;
    }

    public int getPatId() {
        return patId;
    }

    public void setPatId(int patId) {
        this.patId = patId;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public LocalDate getDOB() {
        return DOB;
    }

    public void setDOB(LocalDate DOB) {
        this.DOB = DOB;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public LocalDate getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(LocalDate orderdate) {
        this.orderdate = orderdate;
    }

    public Physicians getPhyId() {
        return phyId;
    }

    public void setPhyId(Physicians phyId) {
        this.phyId = phyId;
    }

    @Override
    public String toString() {
        return "Patients{" +
                "patId=" + patId +
                ", lName='" + lName + '\'' +
                ", fName='" + fName + '\'' +
                ", DOB=" + DOB +
                ", SSN=" + SSN +
                ", address='" + address + '\'' +
                ", City='" + City + '\'' +
                ", State='" + State + '\'' +
                ", zipcode=" + zipcode +
                ", orderdate=" + orderdate +
                ", phyId=" + phyId +
                '}';
    }
}
