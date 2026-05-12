package com.ghostnet.ghostnet.model;

import jakarta.persistence.*;

// meldende und bergende personen werden in derselben tabelle gespeichert
// unterschied nur über personType, ist das so ok? nochmal nachfragen?
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;

    @Enumerated(EnumType.STRING)
    private PersonType personType;

    public enum PersonType {
        MELDEND,
        BERGEND
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public PersonType getPersonType() { return personType; }
    public void setPersonType(PersonType personType) { this.personType = personType; }
}