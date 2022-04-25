package org.exemple.project_one.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Person  <ID extends java.io.Serializable> extends SimplePKEntity<ID>{
    @Basic
    private String forename;
    @Basic
    private String surname;

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "{" +
                "\"super\":"+super.toString()+
                ",\"forename\":\"" + forename + "\"" +
                ",\"surname\":\"" + surname + "\"" +
                '}';
    }
}
