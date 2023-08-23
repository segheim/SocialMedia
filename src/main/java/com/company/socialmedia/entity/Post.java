package com.company.socialmedia.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Data
@Table(name = "POSTS")
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Size(max = 40)
    @Column(name = "TITLE")
    private String title;

    @Size(max = 100)
    @Column(name = "TEXT")
    private String text;

    @Column(name = "PICTURE")
    private String picture;
}
