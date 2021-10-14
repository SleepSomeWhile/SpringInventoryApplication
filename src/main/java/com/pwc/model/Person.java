package com.pwc.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45, nullable = false)
    private String name;

    @Column
    private String surname;

    @Column
    private String email;

    @ManyToMany
    @JoinTable(name = "person_syst", joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "syst_id"))
    private Set<Syst> systems = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "person_proc", joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "proc_id"))
    private Set<Proc> processes = new HashSet<>();

    public Set<Proc> getProcesses() {
        return processes;
    }

    public void setProcesses(Set<Proc> processes) {
        this.processes = processes;
    }

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Syst> getSystems() {
        return systems;
    }

    public void setSystems(Set<Syst> systems) {
        this.systems = systems;
    }

    public void addSystem(Syst system) {
        systems.add(system);
    }

    public void removeSystem(Syst system) {
        systems.remove(system);
    }

    public Person() {}

    public Person(Integer id) {
        super();
        this.id = id;
    }

    public Person(String email, String name) {
        super();
        this.email = email;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
