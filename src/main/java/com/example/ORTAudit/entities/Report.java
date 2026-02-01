package com.example.ORTAudit.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;


@Entity
@Table(name="report")
public class Report implements Serializable {

    @Id
    @Column(name = "whid")
    @ColumnDefault("")
    @NotNull
    @NotBlank
    private String whID;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="powermdf")
    @ColumnDefault("")
    private PowerDisMDF powerDistributionToMDFRacks;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="powerdemarc")
    @ColumnDefault("")
    private RackPowerPrioDemarc powerPrioDemarc;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="racksafety")
    @ColumnDefault("")
    private RackSafety rackSafety;


    public Report() {
    }

    public String getWhID() {
        return whID;
    }

    public void setWhID(String whID) {
        this.whID = whID;
    }

    public PowerDisMDF getPowerDistributionToMDFRacks() {
        return powerDistributionToMDFRacks;
    }

    public RackPowerPrioDemarc getPowerPrioDemarc() {
        return powerPrioDemarc;
    }

    public RackSafety getRackSafety() {
        return rackSafety;
    }

    public void setPowerDistributionToMDFRacks(PowerDisMDF powerDistributionToMDFRacks) {
        this.powerDistributionToMDFRacks = powerDistributionToMDFRacks;
    }

    public void setPowerPrioDemarc(RackPowerPrioDemarc powerPrioDemarc) {
        this.powerPrioDemarc = powerPrioDemarc;
    }

    public void setRackSafety(RackSafety rackSafety) {
        this.rackSafety = rackSafety;
    }

    public boolean isCompleted() {
        if(getPowerPrioDemarc().getNumberingCorrect() == null || getPowerPrioDemarc().getRetentionInstalled() == null || getPowerPrioDemarc().getUPSPrimary() == null) {
            return false;
        }else if(getPowerDistributionToMDFRacks().getUpsPduRPPbrackets()== null || getPowerDistributionToMDFRacks().getSourceAMDF()==null|| getPowerDistributionToMDFRacks().getSourceBNonMDF()==null){
            return false;
        }else if(getRackSafety().isRackBolted() == null || getRackSafety().isAreGroundingConnectionAttached() == null) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Report{" +
                "whID='" + whID + '\'' +
                ", powerDistributionToMDFRacks=" + powerDistributionToMDFRacks +
                ", powerPrioDemarc=" + powerPrioDemarc +
                ", rackSafety=" + rackSafety +
                '}';
    }
}
