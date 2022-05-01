package be.domino;

import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Main {

    private ArrayList<Steen> hoop = new ArrayList<>();

    public ArrayList<Steen> getHoop() {
        return hoop;
    }

    private static String defaultInput = "12R 23G 32R 21P";

    public static void main(String[] args) {
        String input;
        if (args.length == 0) input = defaultInput;
        else input = readFileAsString(args[0]);

        ArrayList<Steen> hoop = readStones(input);
        if (hoop.size() > 1) hoop.get(1).flip();

        Algoritme rekenaar = new Algoritme();
        
        ArrayList<Steen> oplossing = null;

        for (int i = 0; i < hoop.size() - 1; i++) {
            ArrayList<Steen> gelegdeStenen = new ArrayList<>();
            gelegdeStenen.add(hoop.get(i));

            hoop = new ArrayList<>(hoop);
            hoop.remove(i);

            oplossing = rekenaar.backtrack(gelegdeStenen, hoop, 0);
            for (Steen steen : oplossing) {
                System.out.print("[" + steen.getOgen1() + " " + steen.getKleur() + " " + steen.getOgen2() + "]" + " ");
            }
            System.out.println("\n");
        }
        if (oplossing.isEmpty()) {
            System.out.println("geen oplossing");
        }
        //else {
           // System.out.println(oplossing);
        //}
    }

    public static String readFileAsString(String fileName)
    {
        try {
            String data = "";
            data = new String(Files.readAllBytes(Paths.get(fileName)));
            return data;
        }
        catch (Exception e) {
            return defaultInput;
        }
    }

    public static ArrayList<Steen> readStones(String txt) {
        ArrayList<Steen> stenen = new ArrayList<>();
        for (int i=0; i < txt.length(); i=i+4) {
            stenen.add(parseStone(txt.substring(i, i+3)));
        }
        return stenen;
    }

    public static Steen parseStone(String txt) {
        try {
            int ogen1 = Integer.valueOf(txt.substring(0,1)); //ogen1 is de integere waarde van karakter 1 en 2 van txt
            int ogen2 = Integer.valueOf(txt.substring(1,2)); //ogen2 is de integere waarde van karakter 2 en 3 van txt
            char kleur = txt.charAt(2);
            return new Steen(ogen1, ogen2, kleur);
        }
        catch (Exception e) {
            return new Steen(9);
        }
    }

}
