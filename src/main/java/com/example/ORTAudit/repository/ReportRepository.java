package com.example.ORTAudit.repository;

import com.example.ORTAudit.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report,Long> {
        public Report findByWhID(String whID);
        public boolean existsByWhID(String whID);
}
