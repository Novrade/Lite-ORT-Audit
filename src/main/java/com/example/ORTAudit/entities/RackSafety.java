package com.example.ORTAudit.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="rack")
public class RackSafety implements Serializable {

    //Ensure each rack has grounding connections attached back to MDF grounding bar / building ground.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="grounding")
    private String areGroundingConnectionAttached;

    //Ensure each rack is bolted to floor (if applicable, earthquake zones)
    @Column(name="bolting")
    private String isRackBolted;

    @Column(name="sitenotes")
    private String siteNotes;

    public RackSafety(String areGroundingConnectionAttached, String isRackBolted, String siteNotes) {
        this.areGroundingConnectionAttached = areGroundingConnectionAttached;
        this.isRackBolted = isRackBolted;
        this.siteNotes = siteNotes;
    }

    public RackSafety() {
    }

    public String isAreGroundingConnectionAttached() {
        return areGroundingConnectionAttached;
    }

    public void setAreGroundingConnectionAttached(String areGroundingConnectionAttached) {
        this.areGroundingConnectionAttached = areGroundingConnectionAttached;
    }

    public String isRackBolted() {
        return isRackBolted;
    }


    public String getAreGroundingConnectionAttached() {
        return areGroundingConnectionAttached;
    }

    public String getIsRackBolted() {
        return isRackBolted;
    }

    public void setIsRackBolted(String isRackBolted) {
        this.isRackBolted = isRackBolted;
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
        return "Ensure each rack has grounding connections attached back to MDF grounding bar / building ground:\t " + isAreGroundingConnectionAttached() + "\\nn" +
                "Ensure each rack is bolted to floor (if applicable, earthquake zones):\t " + isRackBolted() + "\n\n" +
                 "Site notes:\t" + getSiteNotes();
    }
}
