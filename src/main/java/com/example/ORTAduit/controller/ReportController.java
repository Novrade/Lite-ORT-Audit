package com.example.ORTAduit.controller;


import com.example.ORTAduit.entities.PowerDisMDF;
import com.example.ORTAduit.entities.RackPowerPrioDemarc;
import com.example.ORTAduit.entities.RackSafety;
import com.example.ORTAduit.entities.Report;
import com.example.ORTAduit.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class ReportController {


    private String whid;

    @Autowired
    private ReportRepository repository;


    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("report", new Report());
        return "home";
    }

    @PostMapping("/selectWHID")
    public String formPageOne(@ModelAttribute("report") Report reports, Model model, RedirectAttributes redirectAttributes) {
        if(reports.getWhID() == null || reports.getWhID().equals("")) {
            redirectAttributes.addFlashAttribute("whid", "Warehouse ID cannot be empty");
            return "redirect:";
        }
        if(!repository.existsByWhID(reports.getWhID().toUpperCase())) {
            reports.setPowerDistributionToMDFRacks(new PowerDisMDF("","","",""));
            reports.setPowerPrioDemarc(new RackPowerPrioDemarc("","","",""));
            reports.setRackSafety(new RackSafety("","",""));
            repository.save(reports);
        }
        whid = reports.getWhID().toUpperCase();
        return "redirect:/demarc";
    }

    @GetMapping("/demarc")
    public String formPage(Model model) {
        model.addAttribute("powerPrioDemarc", new RackPowerPrioDemarc());
        return "formPageOne";
    }

    @PostMapping("/powerdemarc")
    public String formPageTwo(@ModelAttribute("powerPrioDemarc") RackPowerPrioDemarc demarc, Model model) {
        Report report = repository.findByWhID(whid);
        report.setPowerPrioDemarc(demarc);
        repository.save(report);
        return "redirect:/mdf";
    }

    @GetMapping("/mdf")
    public String pageTwoPresent(Model model) {
        model.addAttribute("powertomdf",new PowerDisMDF());
        return "formPageTwo";
    }

    @PostMapping("/powermdf")
    public String formPageThree(@ModelAttribute("powertomdf") PowerDisMDF mdf, Model model) {
        Report report = repository.findByWhID(whid);
        report.setPowerDistributionToMDFRacks(mdf);
        repository.save(report);
        return "redirect:/rack";
    }

    @GetMapping("/rack")
    public String pageThreePresent(Model model) {
        model.addAttribute("rack",new RackSafety());
        return "formPageThree";
    }

    @PostMapping("/result")
    public String result(@ModelAttribute("rack") RackSafety rack, Model model, RedirectAttributes redirectAttributes) {
        Report report = repository.findByWhID(whid);
        report.setRackSafety(rack);
        repository.save(report);
        model.addAttribute("report",report);
        if(report.getWhID()==null) {
            redirectAttributes.addFlashAttribute("whid", "Warehouse ID cannot be empty");
            return "redirect:";
        }
        if(!report.isCompleted()) {
            redirectAttributes.addFlashAttribute("error", "One or more items not completed. Please complete all the items to submit report.");
            return "redirect:/rack";
        }
        return "result";
    }

    @GetMapping("/temp")
    public String resultTemp(Model model) {
        Report report = repository.findByWhID(whid);
        System.out.println(report.getPowerDistributionToMDFRacks());
        model.addAttribute("report",report);
        return "result";
    }

}
