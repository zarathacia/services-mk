package org.exemple.project_one.controllers;

public enum Role {
    SURFER(1L),CLIENT(1L<<1L),ACCOUNTANT(1L<<2L),COMMERCIAL(1L<<3L),ADMINISTRATOR(1L<<4L);
    private final long value;

    Role(long value){
        this.value=value;
    }

    public long getValue() {
        return value;
    }
}
