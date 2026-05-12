package com.ghostnet.ghostnet.controller;

import com.ghostnet.ghostnet.model.GhostNet;
import com.ghostnet.ghostnet.service.GhostNetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GhostNetController {

    @Autowired
    private GhostNetService ghostNetService;

    @GetMapping("/")
    public String home(Model model) {
        // alle netze laden für tabelle und karte
        model.addAttribute("nets", ghostNetService.getAllNets());
        model.addAttribute("mapNets", ghostNetService.getNetsNotRecovered());
        return "list";
    }

    @GetMapping("/report")
    public String reportForm(Model model) {
        model.addAttribute("ghostNet", new GhostNet());
        return "report";
    }

    @PostMapping("/report")
    public String reportSubmit(@ModelAttribute GhostNet ghostNet,
                               @RequestParam(required = false) String reporterName,
                               @RequestParam(required = false) String reporterPhone) {
        // TODO: validierung verbessern?
        ghostNetService.reportNet(ghostNet, reporterName, reporterPhone);
        return "redirect:/";
    }

    @GetMapping("/recover")
    public String recoverForm(Model model) {
        model.addAttribute("nets", ghostNetService.getNetsToRecover());
        return "recover";
    }

    @PostMapping("/recover")
    public String recoverSubmit(@RequestParam Long netId,
                                @RequestParam String recovererName,
                                @RequestParam String recovererPhone,
                                Model model) {
        String error = ghostNetService.assignRecoverer(netId, recovererName, recovererPhone);
        if (error != null) {
            model.addAttribute("error", error);
            model.addAttribute("nets", ghostNetService.getNetsToRecover());
            return "recover";
        }
        return "redirect:/recover";
    }

    @PostMapping("/markRecovered")
    public String markRecovered(@RequestParam Long netId) {
        ghostNetService.markAsRecovered(netId);
        return "redirect:/";
    }

    @PostMapping("/markLost")
    public String markLost(@RequestParam Long netId,
                           @RequestParam String reporterName,
                           Model model) {
        if (reporterName == null || reporterName.isBlank()) {
            model.addAttribute("nets", ghostNetService.getAllNets());
            model.addAttribute("mapNets", ghostNetService.getNetsNotRecovered());
            model.addAttribute("lostError", "Du musst deinen Namen angeben um ein Netz als verschollen zu melden!");
            return "list";
        }
        ghostNetService.markAsLost(netId);
        return "redirect:/";
    }

    @GetMapping("/coordination")
    public String coordination(Model model) {
        model.addAttribute("nets", ghostNetService.getNetsWithRecoverer());
        return "coordination";
    }
}