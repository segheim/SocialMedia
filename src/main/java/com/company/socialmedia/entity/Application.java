package com.company.socialmedia.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "APPLICATIONS")
@Data
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "OWNER_ID")
    private User owner;

    @ManyToOne()
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "STATUS")
    private String status;
}
