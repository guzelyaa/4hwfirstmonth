package com.guzelya;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.Random;

public class Main {
    public static  int bossHealth = 700;
    public static int bossDamage = 50;

    public static int[] heroesHealth = {290,270,280, 260,200,450};
    public static int[] heroesDamage = {0,20,15,25,10,5};


    public static String[] heroesAttackType = {"Medic","Physical", "Magical", "Kinetic","Lucky","Golem"};
    public static String bossDefenceType = "";

    public static int roundNumber = 0;


    public static void main(String[] args) {

        printStatistics();
        while (!isGameFinished()){
            round();
        }

    }

    /*public static void bossHits(){
    }

     */


    public static void golem(){
        for (int i = 0; i < (heroesHealth.length-1); i++) {
            heroesHealth[i] = heroesHealth[i] - bossDamage/5*4;
        }
        heroesHealth[5] = heroesHealth[5] - bossDamage - bossDamage*(heroesHealth.length-1)/5;

        for (int i = 0; i < heroesHealth.length; i++) {
            if(heroesHealth[i] < bossDamage){
                heroesHealth[i] = 0;
            }
        }
    }


    public static void lucky(){
        Random random = new Random();
        int luck = random.nextInt(2);
        if(luck == 1){
            heroesHealth[4] = heroesHealth[4] + 50;
            System.out.println("Lucky escaped boss's hit");
        }
    }

    public static void healing(){
        Random random = new Random();
        int randomI = random.nextInt(50);
        Random rand = new Random();
        int randomIn = random.nextInt(5)+1;
        for (int i = randomIn; i <heroesHealth.length; i++) {
            if (heroesHealth[i]<100 && heroesHealth[0]>0){
                heroesHealth[i] = heroesHealth[i] + randomI;
                System.out.println(heroesAttackType[i] + " is healed by "+ randomI + " points!");
                break;
            }
            continue;
        }
    }


    public static Boolean isGameFinished(){
        if (bossHealth<=0){
            System.out.println("Heroes won!");
            return true;
        }

        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0 && heroesHealth[3]<=0 && heroesHealth[4]<=0 && heroesHealth[5]<=0) {
            System.out.println("Boss won!");
            return  true;
        }
        return false;
    }

    public static void printStatistics(){
        System.out.println(roundNumber + " ROUND");
        System.out.println("Boss Health: " + bossHealth + "["+bossDamage+"]");

        for (int i = 0; i < heroesDamage.length; i++) {
            // System.out.println("Hero Health: " + heroesHealth[i] +"["+heroesDamage[i]+"]");
            System.out.println(heroesAttackType[i] + " health " + heroesHealth[i] +"["+heroesDamage[i]+"]");
        }
    }


    public static  void heroesHits(){
        for (int i = 1; i < heroesDamage.length; i++) {
            if(heroesHealth[i]>0 && bossHealth>0) {
                if(bossDefenceType == heroesAttackType[i]){
                    Random r = new Random();
                    int coef = r.nextInt(8)+2; //2-7

                    if(bossHealth < heroesDamage[i]*coef){
                        bossHealth  = 0;
                    }else {
                        bossHealth = bossHealth - heroesDamage[i]*coef;
                    }
                    System.out.println("Critical damage: " + heroesDamage[i]*coef);
                }else{
                    if(bossHealth<heroesDamage[i]){
                        bossHealth = 0;
                    }else{
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }

        }
    }

    public static void chooseBossDefenceType(){
        Random random = new Random();
        int randomIndex = random.nextInt(3)+1;
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Boss choice: " + bossDefenceType);
    }



    public static void round(){
        roundNumber++;
        chooseBossDefenceType();
        //bossHits();
        golem();
        heroesHits();
        lucky();
        healing();
        printStatistics();
    }


}
