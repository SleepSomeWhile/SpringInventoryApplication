package com.pwc.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Proc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String dataComposition;

    @Column
    private String monetaryRightsSubjects;

    @Column
    private String processingBasis;

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

    public String getDataComposition() {
        return dataComposition;
    }

    public void setDataComposition(String dataComposition) {
        this.dataComposition = dataComposition;
    }

    public String getMonetaryRightsSubjects() {
        return monetaryRightsSubjects;
    }

    public void setMonetaryRightsSubjects(String monetaryRightsSubjects) { this.monetaryRightsSubjects = monetaryRightsSubjects; }

    public String getProcessingBasis() {
        return processingBasis;
    }

    public void setProcessingBasis(String processingBasis) {
        this.processingBasis = processingBasis;
    }

    @ManyToMany
    @JoinTable(name = "person_proc", joinColumns = @JoinColumn(name = "proc_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private Set<Person> persons = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "syst_proc", joinColumns = @JoinColumn(name = "proc_id"),
            inverseJoinColumns = @JoinColumn(name = "syst_id"))
    private Set<Syst> systems = new HashSet<>();

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    public Set<Syst> getSystems() {
        return systems;
    }

    public void setSystems(Set<Syst> systems) {
        this.systems = systems;
    }

    @Override
    public String toString() {
        return name;
    }
}
