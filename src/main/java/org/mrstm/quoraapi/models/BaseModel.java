package org.mrstm.quoraapi.models;


import jakarta.persistence.*;


import java.util.Date;

@MappedSuperclass
public abstract class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;


}
