package me.adamhamilton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console{

    public String listenForArgs() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void print(String message){
        System.out.println(message);
    }

    public void clear(){
        System.out.flush();
        for (int i = 0; i < 50; ++i) System.out.println();
    }

    public Boolean nullCheckPlayer(int space){
        final Boolean s = Game.spaces.get(space);
        if(s != null) {
            return s;
        }
        return false;
    }

    public Boolean nullCheckBot(int space){
        final Boolean s = Game.spaces.get(space);
        if(s == null) {
            return true;
        }
        return s;
    }

    public void printGame(){

       final String sP1 = nullCheckPlayer(1) ? Game.getSavedCharacter() : " ";
       final String sP2 = nullCheckPlayer(2) ? Game.getSavedCharacter() : " ";
       final String sP3 = nullCheckPlayer(3) ? Game.getSavedCharacter() : " ";
       final String sP4 = nullCheckPlayer(4) ? Game.getSavedCharacter() : " ";
       final String sP5 = nullCheckPlayer(5) ? Game.getSavedCharacter() : " ";
       final String sP6 = nullCheckPlayer(6) ? Game.getSavedCharacter() : " ";
       final String sP7 = nullCheckPlayer(7) ? Game.getSavedCharacter() : " ";
       final String sP8 = nullCheckPlayer(8) ? Game.getSavedCharacter() : " ";
       final String sP9 = nullCheckPlayer(9) ? Game.getSavedCharacter() : " ";
        final String s1 = nullCheckBot(1) ? "" : Game.getBotCharacter();
        final String s2 = nullCheckBot(2) ? "" : Game.getBotCharacter();
        final String s3 = nullCheckBot(3) ? "" : Game.getBotCharacter();
        final String s4 = nullCheckBot(4) ? "" : Game.getBotCharacter();
        final String s5 = nullCheckBot(5) ? "" : Game.getBotCharacter();
        final String s6 = nullCheckBot(6) ? "" : Game.getBotCharacter();
        final String s7 = nullCheckBot(7) ? "" : Game.getBotCharacter();
        final String s8 = nullCheckBot(8) ? "" : Game.getBotCharacter();
        final String s9 = nullCheckBot(9) ? "" : Game.getBotCharacter();

        print("  " + s1 + sP1 + " | " + s2 + sP2 + " | "+ s3 + sP3);
        print("-------------");
        print("  " + s4 + sP4 + " | " + s5 + sP5 + " | "+ s6 + sP6);
        print("-------------");
        print("  " + s7 + sP7 + " | " + s8 + sP8 + " | "+ s9 + sP9);

    }

}
