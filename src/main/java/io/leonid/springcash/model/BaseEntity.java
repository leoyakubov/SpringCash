package io.leonid.springcash.model;

import javax.persistence.*;

/**
 * Created by leonid on 13.08.14.
 */
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    protected Integer id;


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
