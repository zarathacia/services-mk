package org.exemple.project_one.util;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class Argon2Utility {
    private static final int saltLength = 32;
    private static final int hashLength = 128;
    private static final Argon2 argon2 = Argon2Factory.create(
            Argon2Factory.Argon2Types.ARGON2id,saltLength,hashLength);
    private static final int iterations = 23;
    private static final int memory = 97579;
    private static final int threadNumber = 2;

    public static String hash(char[] boundaryHash){
        try{
            return argon2.hash(iterations,memory,threadNumber,boundaryHash);
        }finally {
            argon2.wipeArray(boundaryHash);
        }
    }

    public static boolean check(String databaseHash,char[] boundaryHash){
        try{
            return argon2.verify(databaseHash, boundaryHash);
        }finally {
            argon2.wipeArray(boundaryHash);
        }
    }
}
