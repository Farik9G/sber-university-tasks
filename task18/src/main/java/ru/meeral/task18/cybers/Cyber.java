package ru.meeral.task18.cybers;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cybers")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Cyber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String role;
}
