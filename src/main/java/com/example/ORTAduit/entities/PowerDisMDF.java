package com.example.ORTAduit.entities;

import jakarta.persistence.*;

import java.io.Serializable;

//Power distribution to MDF Racks
@Entity
@Table(name="mdf")
public class PowerDisMDF implements Serializable {

    //Source A connected to MDF UPS supplied outlet (PDU)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",columnDefinition = "i")
    private int id;
    @Column(name="sourcemdf", columnDefinition = "i")
    private String sourceAMDF;

    //Source B connected to non-UPS supplied outlet (RPP)
    @Column(name="sourcenonmdf", columnDefinition = "i")
    private String sourceBNonMDF;
    //Ensure UPS PDU cabinet and RPP cabinet have breakers labeled to which rack and ATS source the breaker supplies
    @Column(name="brackets", columnDefinition = "i")
    private String upsPduRPPbrackets;
    @Column(name="sitenotes", columnDefinition = "i")
    private String siteNotes;

    public PowerDisMDF(String sourceAMDF, String sourceBNonMDF, String upsPduRPPbrackets, String siteNotes) {
        this.sourceAMDF = sourceAMDF;
        this.sourceBNonMDF = sourceBNonMDF;
        this.upsPduRPPbrackets = upsPduRPPbrackets;
        this.siteNotes = siteNotes;
    }

    public PowerDisMDF() {
    }

    public String isSourceAMDFF() {
        return sourceAMDF;
    }

    public void setSourceAMDF(String sourceAMDF) {
        this.sourceAMDF = sourceAMDF;
    }

    public String isSourceBNonMDFf() {
        return sourceBNonMDF;
    }

    public void setSourceBNonMDF(String sourceBNonMDF) {
        this.sourceBNonMDF = sourceBNonMDF;
    }

    public String isUpsPduRPPbracketss() {
        return upsPduRPPbrackets;
    }

    public void setUpsPduRPPbrackets(String upsPduRPPbrackets) {
        this.upsPduRPPbrackets = upsPduRPPbrackets;
    }



    public String getSiteNotes() {
        return siteNotes;
    }

    public void setSiteNotes(String siteNotes) {
        this.siteNotes = siteNotes;
    }

    public String getSourceAMDF() {
        return sourceAMDF;
    }

    public String getSourceBNonMDF() {
        return sourceBNonMDF;
    }

    public String getUpsPduRPPbrackets() {
        return upsPduRPPbrackets;
    }

    public String sourceMDFResult() {
        return "Source A connected to MDF UPS supplied outlet (PDU):\t " + isSourceAMDFF();
    }

    public String sourceNonMDFResult() {
        return "Source B connected to non-UPS supplied outlet (RPP):\t " + isSourceBNonMDFf();
    }
    public String pduRacksResult() {
        return  "Ensure UPS PDU cabinet and RPP cabinet have breakers labeled to which rack and ATS source the breaker supplies:\t " + isUpsPduRPPbracketss();
    }
    public String siteNotesResult() {
        return  "Site notes:\t" + getSiteNotes();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Source A connected to MDF UPS supplied outlet (PDU):\t " + isSourceAMDFF() + "\n" +
                "Source B connected to non-UPS supplied outlet (RPP):\t " + isSourceBNonMDFf() + "\n" +
                "Ensure UPS PDU cabinet and RPP cabinet have breakers labeled to which rack and ATS source the breaker supplies:\t " + isUpsPduRPPbracketss() + "\n" +
                "Site notes:\t" + getSiteNotes();
    }
}
