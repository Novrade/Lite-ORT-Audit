package com.example.ORTAudit.entities;

import jakarta.persistence.*;

import java.io.Serializable;

//Rack Power Priority for Demarc
@Entity
@Table(name="demarc")
public class RackPowerPrioDemarc implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    //Source A (UPS) should be the primary/preferred Source.
    @Column(name="upsprimary")
    private String UPSPrimary;

    @Column(name="numbering")
    //Rack PDU labeled with numbering matching the upstream ATS.
    private String numberingCorrect;

    @Column(name="retention")
    //Power cable retention is installed for all PDU to device cabling.
    private String retentionInstalled;

    @Column(name="sitenotes")
    private String siteNotes;

    public RackPowerPrioDemarc(String UPSPrimary, String numberingCorrect, String retentionInstalled, String siteNotes) {
        this.UPSPrimary = UPSPrimary;
        this.numberingCorrect = numberingCorrect;
        this.retentionInstalled = retentionInstalled;
        this.siteNotes = siteNotes;
    }

    public RackPowerPrioDemarc() {
    }

    public String getUPSPrimary() {
        return UPSPrimary;
    }

    public void setUPSPrimary(String UPSPrimary) {
        this.UPSPrimary = UPSPrimary;
    }

    public String getNumberingCorrect() {
        return numberingCorrect;
    }

    public void setNumberingCorrect(String numberingCorrect) {
        this.numberingCorrect = numberingCorrect;
    }

    public String getRetentionInstalled() {
        return retentionInstalled;
    }

    public void setRetentionInstalled(String retentionInstalled) {
        this.retentionInstalled = retentionInstalled;
    }


    public String getSiteNotes() {
        return siteNotes;
    }

    public void setSiteNotes(String siteNotes) {
        this.siteNotes = siteNotes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Source A (UPS) should be the primary/preferred Source:\t " + getUPSPrimary() + "\n\n" +
                "Rack PDU labeled with numbering matching the upstream ATS:\t " + getNumberingCorrect() + "\n\n" +
                "Power cable retention is installed for all PDU to device cabling:\t " + getRetentionInstalled() + "\n\n" +
                "Site notes:\t" + getSiteNotes();
    }
}
