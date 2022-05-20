package org.exemple.project_one.controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestRole {

    @Test
    public void doIt(){
        assertEquals(17L,Role.SURFER.getValue()|Role.ADMINISTRATOR.getValue());
        System.out.println(Role.SURFER.getValue()|Role.ADMINISTRATOR.getValue());
    }
}
