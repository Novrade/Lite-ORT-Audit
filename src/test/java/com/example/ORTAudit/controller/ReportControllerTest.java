package com.example.ORTAudit.controller;

import com.example.ORTAudit.entities.Report;
import com.example.ORTAudit.service.ReportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ReportController.class)
class ReportControllerTest {

    @MockBean
    private ReportService reportService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void createReport() throws Exception {
        Report report = new Report();
        report.setWhID("BB-02");

        when(reportService.findOrCreate("BB-02")).thenReturn(report);

        ResultActions result = mockMvc.perform(post("/selectWHID")
                .param("whID",report.getWhID()))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location","/demarc"));

        verify(reportService).findOrCreate("BB-02");
    }

}