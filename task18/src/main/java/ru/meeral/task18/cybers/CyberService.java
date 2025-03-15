package ru.meeral.task18.cybers;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CyberService {
    private final CyberRepository cyberRepository;

    public CyberService(CyberRepository cyberRepository) {
        this.cyberRepository = cyberRepository;
    }

    public List<Cyber> getAllCybers() {
        return cyberRepository.findAll();
    }

    public Cyber getCyberById(Long id) {
        return cyberRepository.findById(id).orElse(null);
    }

    public Cyber saveCyber(Cyber cyber) {
        return cyberRepository.save(cyber);
    }

    public void deleteCyber(Long id) {
        cyberRepository.deleteById(id);
    }
}
