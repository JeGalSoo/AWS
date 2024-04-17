package com.example.demo.user.model;

import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity(name="users")
@Builder
public class User {
    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
        private String username;
    private String password;
    private String name;
    private String phone;
    private Long addressId;
    private String job;
}