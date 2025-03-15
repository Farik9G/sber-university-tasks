package ru.meeral.task18.cybers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cybers")
public class CyberController {
    private final CyberService cyberService;

    public CyberController(CyberService cyberService) {
        this.cyberService = cyberService;
    }

    @GetMapping
    public List<Cyber> getAllCybers() {
        return cyberService.getAllCybers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cyber> getCyberById(@PathVariable Long id) {
        Cyber cyber = cyberService.getCyberById(id);
        return cyber != null ? ResponseEntity.ok(cyber) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Cyber createCyber(@RequestBody Cyber cyber) {
        return cyberService.saveCyber(cyber);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cyber> updateCyber(@PathVariable Long id, @RequestBody Cyber updatedCyber) {
        Cyber cyber = cyberService.getCyberById(id);
        if (cyber == null) {
            return ResponseEntity.notFound().build();
        }
        cyber.setNickname(updatedCyber.getNickname());
        cyber.setRole(updatedCyber.getRole());
        return ResponseEntity.ok(cyberService.saveCyber(cyber));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCyber(@PathVariable Long id) {
        cyberService.deleteCyber(id);
        return ResponseEntity.noContent().build();
    }
}
