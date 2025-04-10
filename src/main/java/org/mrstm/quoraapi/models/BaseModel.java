package org.mrstm.quoraapi.models;


import jakarta.persistence.*;

import java.util.Date;

@MappedSuperclass
public abstract class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false , updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @PrePersist //making sure method is called before.
    protected void onCreate() {
        createdAt = new Date();
    }

}
