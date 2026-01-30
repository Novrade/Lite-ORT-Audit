package com.example.ORTAudit.controller;

import com.example.ORTAudit.entities.RackPowerPrioDemarc;
import com.example.ORTAudit.entities.Report;
import com.example.ORTAudit.service.ReportService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ReportController.class)
class ReportControllerTest {

    @MockBean
    private ReportService reportService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void createReportSuccessful() throws Exception {
        Report report = new Report();
        report.setWhID("BB-02");

        when(reportService.findOrCreate("BB-02")).thenReturn(report);

        mockMvc.perform(post("/selectWHID")
                .param("whID",report.getWhID()))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location","/demarc"));

        verify(reportService).findOrCreate("BB-02");
    }

    @Test
    void createReportNoWhIdRedirectHome() throws Exception {
        Report report = new Report();

        when(reportService.findOrCreate("")).thenReturn(report);

        mockMvc.perform(post("/selectWHID")
                .param("whID",report.getWhID()))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location","/"));

    }

    @Test
    void demarcFormView() throws Exception {

        mockMvc.perform(get("/demarc"))
                .andExpect(status().isOk())
                .andExpect(view().name("formPageOne"))
                .andExpect(model().attributeExists("powerPrioDemarc"));

    }

    @Test
    void createDemarcFormSuccessful() throws Exception {
        Report report = new Report();
        report.setWhID("BB-02");
        RackPowerPrioDemarc demarc = new RackPowerPrioDemarc();
        demarc.setId(1);
        demarc.setSiteNotes("True");


        when(reportService.updateDemarc(report.getWhID(),demarc)).thenReturn(report);


        mockMvc.perform(post("/powerdemarc")
                        .param("siteNotes",demarc.getSiteNotes())
                        .sessionAttr("whid",report.getWhID()))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location","/mdf"));


    }

}