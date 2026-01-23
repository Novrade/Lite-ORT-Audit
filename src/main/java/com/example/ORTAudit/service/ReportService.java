package com.example.ORTAudit.service;

import com.example.ORTAudit.entities.Report;

public interface ReportService {

    public Report getBtWhidOrThrow();
    public Report findOrCreate();
    public void updateDemarc();
    public void updateMdf();
}
