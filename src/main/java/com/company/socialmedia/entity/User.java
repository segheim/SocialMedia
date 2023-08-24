package com.company.socialmedia.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @NotBlank
    @Size(max = 60)
    @Column(name = "NAME", unique = true)
    private String name;

    @Email
    @Column(name = "EMAIL", unique = true)
    private String email;

    @NotBlank
    @Column(name = "PASSWORD")
    private String password;

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USERS_ROLES",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts;

    @ManyToMany
    private List<User> friends;

    @ManyToMany
    private List<User> subscribeTo;

    @ManyToMany
    private List<User> friendshipRequest;
}
