package com.pwc.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Syst {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String hosting;

    @Column
    private String owner;

    @ManyToMany
    @JoinTable(name = "person_syst", joinColumns = @JoinColumn(name = "syst_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private Set<Person> persons = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "syst_proc", joinColumns = @JoinColumn(name = "syst_id"),
            inverseJoinColumns = @JoinColumn(name = "proc_id"))
    private Set<Proc> processes = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHosting() {
        return hosting;
    }

    public void setHosting(String hosting) {
        this.hosting = hosting;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    public Set<Proc> getProcesses() {
        return processes;
    }

    public void setProcesses(Set<Proc> processes) {
        this.processes = processes;
    }

    public Syst() {}

    public Syst(Integer id) {
        this.id = id;
    }

    public Syst(String name) {
        this.name = name;
    }

    public Syst(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
