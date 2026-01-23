package com.example.ORTAudit.repository;

import com.example.ORTAudit.entities.PowerDisMDF;
import com.example.ORTAudit.entities.RackPowerPrioDemarc;
import com.example.ORTAudit.entities.RackSafety;
import com.example.ORTAudit.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report,Long> {
        public Report findByWhID(String whID);
        public boolean existsByWhID(String whID);

}
