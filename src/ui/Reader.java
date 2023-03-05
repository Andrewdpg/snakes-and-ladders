package ui;

import java.util.Scanner;

public class Reader {

    private static Scanner reader = new Scanner(System.in);

    public static int readInt(int def) {
        if (reader.hasNextInt()) {
            return reader.nextInt();
        }
        reader.next();
        System.out.println("Entrada inválida. Valor tomado: " + def);
        return def;
    }

    public static int readInt(int def, int min) {
        if (reader.hasNextInt()) {
            int value = reader.nextInt();
            if (value >= min) {
                return value;
            }
            System.out.println("Valor por debajo del mínimo. Valor tomado: " + min);
            return min;
        }
        reader.next();
        System.out.println("Entrada inválida. Valor tomado: " + def);
        return def;
    }

    public static String readString() {
        return reader.next();
    }

    public static int randInt(int start, int end) {
        if(end > start){
            return (int) ((Math.random() * (1 + end - start)) + start);
        }
        return start;
    }

    public static int randInt(int end) {
        return (int) (Math.random() * (end+1));
    }
}
