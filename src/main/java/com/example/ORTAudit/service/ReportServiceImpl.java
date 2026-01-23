package com.example.ORTAudit.service;

import com.example.ORTAudit.entities.PowerDisMDF;
import com.example.ORTAudit.entities.RackPowerPrioDemarc;
import com.example.ORTAudit.entities.RackSafety;
import com.example.ORTAudit.entities.Report;
import com.example.ORTAudit.repository.ReportRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReportServiceImpl implements ReportService {

    ReportRepository repository;

    public ReportServiceImpl(ReportRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Report getByWhidOrThrow(String whid) {
        String key = normalize(whid);
        Report report = repository.findByWhID(key);
        if(report == null) {
            throw new NullPointerException("Report with " + key + " cannot be found.");
        }
        return report;
    }

    @Override
    public Report findOrCreate(String whid) {
        String key = normalize(whid);
        Report report = repository.findByWhID(whid);

        if(report != null) {
            return report;
        }
        Report newReport = new Report();
        newReport.setWhID(key);
        newReport.setPowerDistributionToMDFRacks(new PowerDisMDF("","","",""));
        newReport.setPowerPrioDemarc(new RackPowerPrioDemarc("","","",""));
        newReport.setRackSafety(new RackSafety("","",""));
        return repository.save(newReport);

    }

    @Override
    public void updateDemarc(String whid, RackPowerPrioDemarc demarc) {
        Report report = getByWhidOrThrow(whid);
        report.setPowerPrioDemarc(demarc);
        repository.save(report);

    }

    @Override
    public void updateMdf(String whid, PowerDisMDF mdf) {
        Report report = getByWhidOrThrow(whid);
        report.setPowerDistributionToMDFRacks(mdf);
        repository.save(report);

    }

    @Override
    public void updateRack(String whid, RackSafety rack) {
        Report report = getByWhidOrThrow(whid);
        report.setRackSafety(rack);
        repository.save(report);

    }

    private String normalize(String whid) {
        if (whid == null || whid.isBlank()) {
            throw new IllegalArgumentException("Warehouse ID cannot be empty");
        }
        return whid.toUpperCase();
    }


    }
