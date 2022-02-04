package com.nord.shorter.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;


@Entity
@Table(name = "shorter", schema = "shorter")
public class Shorter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hash;

    @Column(name = "original_url")
    private String originalUrl;
//    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
//    private ZonedDateTime createdAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Shorter() {
    }

    public Shorter(Long id, String hash, String originalUrl, LocalDate createdAt, User user) {
        this.id = id;
        this.hash = hash;
        this.originalUrl = originalUrl;
        this.createdAt = createdAt;
        this.user = user;
    }

    public Shorter(String originalUrl) {
        this.hash = null;
        this.originalUrl = originalUrl;
        this.createdAt = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Shorter{" +
                "id=" + id +
                ", hash='" + hash + '\'' +
                ", originalUrl='" + originalUrl + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shorter shorter = (Shorter) o;
        return Objects.equals(id, shorter.id) && Objects.equals(hash, shorter.hash) && Objects.equals(originalUrl, shorter.originalUrl) && Objects.equals(createdAt, shorter.createdAt) && Objects.equals(user, shorter.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hash, originalUrl, createdAt, user);
    }
}
