package ro.itschool.testcontroller.persistence.dao;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String adress;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private int age;

}
