package org.exemple.project_one.util;

public class IdentityUtility {
    private static final ThreadLocal<String> UsernameTL= new ThreadLocal<>();
    public static void iAm(String username){
        UsernameTL.set(username);
    }
    public static String whoAmI() {

        return UsernameTL.get();
    }
}
