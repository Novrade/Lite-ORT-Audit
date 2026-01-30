package com.example.ORTAudit.service;

import com.example.ORTAudit.entities.PowerDisMDF;
import com.example.ORTAudit.entities.RackPowerPrioDemarc;
import com.example.ORTAudit.entities.RackSafety;
import com.example.ORTAudit.entities.Report;

public interface ReportService {

    public Report getByWhidOrThrow(String whid);
    public Report findOrCreate(String whid);
    public Report updateDemarc(String whid, RackPowerPrioDemarc demarc);
    public void updateMdf(String whid, PowerDisMDF mdf);
    public void updateRack(String whid, RackSafety rack);
}
