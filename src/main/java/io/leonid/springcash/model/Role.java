package io.leonid.springcash.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by leonid on 13.08.14.
 */
@Entity
@Table(name = "Roles")
public class Role extends BaseEntity{
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
