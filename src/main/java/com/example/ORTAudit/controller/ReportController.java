package com.example.ORTAudit.controller;


import com.example.ORTAudit.entities.PowerDisMDF;
import com.example.ORTAudit.entities.RackPowerPrioDemarc;
import com.example.ORTAudit.entities.RackSafety;
import com.example.ORTAudit.entities.Report;
import com.example.ORTAudit.repository.ReportRepository;
import com.example.ORTAudit.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@SessionAttributes("whid")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("report", new Report());
        return "home";
    }

    @PostMapping("/selectWHID")
    public String formPageOne(@Validated @ModelAttribute("report") Report reports,
                              Model model,
                              RedirectAttributes redirectAttributes) {

        try {
            Report report = reportService.findOrCreate(reports.getWhID());
            model.addAttribute("whid",report.getWhID());
            return "redirect:/demarc";
        }catch (NullPointerException ex) {
            redirectAttributes.addFlashAttribute("whid","Warehouse ID cannot be empty.");
            return "redirect:/";
        }
    }

    @GetMapping("/demarc")
    public String demarcForm(Model model) {
        model.addAttribute("powerPrioDemarc", new RackPowerPrioDemarc());
        return "formPageOne";
    }

    @PostMapping("/powerdemarc")
    public String formPageTwo(@ModelAttribute("powerPrioDemarc") RackPowerPrioDemarc demarc,
                              @SessionAttribute("whid") String whid) {
        reportService.updateDemarc(whid,demarc);
        return "redirect:/mdf";
    }

    @GetMapping("/mdf")
    public String pageTwoPresent(Model model) {
        model.addAttribute("powertomdf",new PowerDisMDF());
        return "formPageTwo";
    }

    @PostMapping("/powermdf")
    public String formPageThree(@ModelAttribute("powertomdf") PowerDisMDF mdf, @SessionAttribute("whid") String whid) {
        reportService.updateMdf(whid,mdf);
        return "redirect:/rack";
    }

    @GetMapping("/rack")
    public String pageThreePresent(Model model) {
        model.addAttribute("rack",new RackSafety());
        return "formPageThree";
    }


    @PostMapping("/result")
    public String result(@ModelAttribute("rack") RackSafety rack,
                         @SessionAttribute("whid") String whid,
                         Model model,
                         RedirectAttributes ra,
                         SessionStatus status) {
        reportService.updateRack(whid, rack);
        Report report = reportService.getByWhidOrThrow(whid);

        model.addAttribute("report", report);

        if (report.getWhID() == null || report.getWhID().isBlank()) {
            ra.addFlashAttribute("whid", "Warehouse ID cannot be empty");
            return "redirect:/";
        }
        if (!report.isCompleted()) {
            ra.addFlashAttribute("error",
                    "One or more items not completed. Please complete all the items to submit report.");
            return "redirect:/rack";

        }


        status.setComplete();
        return "result";
    }


    @GetMapping("/temp")
    public String resultTemp(@SessionAttribute("whid") String whid, Model model) {
        Report report = reportService.getByWhidOrThrow(whid);
        model.addAttribute("report", report);
        return "result";
    }


}
