package org.exemple.project_one.entities;

public interface RootEntity <ID extends java.io.Serializable>{
    ID getId();
    void setId(ID id);
    Long getVersion();
    void setVersion(Long version);

}
