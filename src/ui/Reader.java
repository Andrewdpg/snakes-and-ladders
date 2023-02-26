package ui;

import java.util.Scanner;

public class Reader {
    
    private static Scanner reader = new Scanner(System.in);

    public static int readInt(int def){
        if(reader.hasNextInt()){
            return reader.nextInt();
        }
        reader.next();
        return def;
    }

    public static String readString(){
        return reader.next();
    }
}
