package be.domino;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Algoritme {
    public ArrayList<Steen> backtrack(ArrayList<Steen> gelegdeStenen, ArrayList<Steen> hoop, int poging) {

        if (hoop.isEmpty() || (poging > hoop.size() - 1)) {
            return gelegdeStenen;
        } else {
            for (Steen steen : hoop) {
                if (!hoop.isEmpty() && matcht(gelegdeStenen.get(gelegdeStenen.size()-1),steen)) {
                    gelegdeStenen.add(steen);
                    gelegdeStenen.get(gelegdeStenen.size() - 1).setGelegd(true);
                    hoop.remove(0);
                    backtrack(gelegdeStenen, hoop, 0);
                }
                /* steen past niet */
                if (!hoop.isEmpty()) {
                    Steen temp = steen;
                    hoop.remove(0);
                    hoop.add(temp);
                    poging++;
                    backtrack(gelegdeStenen, hoop, poging);
                }
            }
        }
        return gelegdeStenen;
    }

    private boolean matcht(Steen steen1, Steen steen2){
        if (steen1.getKleur() == steen2.getKleur()){
            return false;
        } if (steen1.getOgen2() == steen2.getOgen1()){
            return true;
        } else if (steen1.getOgen2() == steen2.getOgen2()) {
            steen2.flip();
            return true;
        } else {
            return false;
        }
    }
}

