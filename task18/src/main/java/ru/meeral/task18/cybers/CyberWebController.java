package ru.meeral.task18.cybers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("web/cybers")
public class CyberWebController {
    private final CyberService cyberService;

    public CyberWebController(CyberService cyberService) {
        this.cyberService = cyberService;
    }

    @GetMapping
    public String listCybers(Model model) {
        model.addAttribute("cybers", cyberService.getAllCybers());
        return "cyber-list";
    }

    @GetMapping("/new")
    public String showCyberForm(Model model) {
        model.addAttribute("cyber", new Cyber());
        return "cyber-form";
    }

    @PostMapping
    public String saveCyber(@ModelAttribute Cyber cyber) {
        cyberService.saveCyber(cyber);
        return "redirect:/web/cybers";
    }

    @GetMapping("/edit/{id}")
    public String editCyber(@PathVariable Long id, Model model) {
        model.addAttribute("cyber", cyberService.getCyberById(id));
        return "cyber-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteCyber(@PathVariable Long id) {
        cyberService.deleteCyber(id);
        return "redirect:/web/cybers";
    }
}
