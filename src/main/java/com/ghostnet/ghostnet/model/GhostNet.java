package com.ghostnet.ghostnet.model;

import jakarta.persistence.*;

// Entität für Geisternetze Lektion 5 JPA
@Entity
public class GhostNet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double latitude;
    private Double longitude;
    private String estimatedSize;

    @Enumerated(EnumType.STRING)
    private Status status;

    // ein Netz kann nur einem Berger zugeordnet sein (wie Aufgabe sagt)
    // ManyToOne weil mehrere Netze denselben Berger haben können
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person recoverer;

    public enum Status {
        GEMELDET,
        BERGUNG_BEVORSTEHEND,
        GEBORGEN,
        VERSCHOLLEN
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }
    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
    public String getEstimatedSize() { return estimatedSize; }
    public void setEstimatedSize(String estimatedSize) { this.estimatedSize = estimatedSize; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public Person getRecoverer() { return recoverer; }
    public void setRecoverer(Person recoverer) { this.recoverer = recoverer; }
}