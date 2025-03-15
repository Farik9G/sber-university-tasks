package ru.meeral.task18.cybers;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CyberRepository extends JpaRepository<Cyber, Long> {
}
