package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import threads.KanjiThread;
import threads.RadicalThread;
import threads.VocabularyThread;

import java.io.*;
import java.util.Scanner;

public class Main {

    private static final Scanner input = new Scanner(System.in);


    public static int getLastLevel() {
        int lastLevel = -1;
        try {
            BufferedReader lastLevelReader = new BufferedReader(new FileReader("last_level.txt"));
            lastLevel = Integer.parseInt(lastLevelReader.readLine());
            lastLevelReader.close();

        }
        catch (IOException e) {
            System.out.println("Last level file not found");
        }
        return lastLevel;
    }

    public static int getCurrentLevel() {
        return Integer.parseInt(input.nextLine());
    }

    public static  void levelValidationCycle(int level) {
        while(level <1 || level>60) {


            System.out.println("There are only 60 levels");
            System.out.print("Level: ");
            level = getCurrentLevel();
        }
    }

    public static  void saveLastLevel(int lastLevel) {
        try {
            BufferedWriter lastLevelWriter = new BufferedWriter(new FileWriter("last_level.txt"));
            lastLevelWriter.write(lastLevel);
            lastLevelWriter.close();
        }
        catch (IOException e) {
            System.out.println("Last level file not found");
        }
    }

    public static Document getDocument(int level) {
        Document document = null;
        try {
            String url = String.format("https://www.wanikani.com/level/%d", level);
            document =  Jsoup.connect(url).get();
        }
        catch (IOException e) {
            System.out.println("Connection lost");
        }
        return document;
    }

    public static void startRadicalThread(Document levelPage) {
        RadicalThread radicalThread = new RadicalThread(levelPage);
        radicalThread.start();
    }

    public static  void startKanjiThread(Document levelPage) {
        KanjiThread kanjiThread = new KanjiThread(levelPage);
        kanjiThread.start();
    }

    public static  void startVocabThread(Document levelPage) {
        VocabularyThread vocabularyThread = new VocabularyThread(levelPage);
        vocabularyThread.start();
    }

    public static void main(String[] args) {




        int lastLevel = getLastLevel();

        System.out.printf("Last parsed level: %d%n", lastLevel);

        System.out.print("Level: ");

        int level = getCurrentLevel();

        levelValidationCycle(level);

        saveLastLevel(lastLevel);


        Document levelPage = getDocument(level);


        if(args.length > 0) {
            switch (args[0]) {
                case "r":
                    startRadicalThread(levelPage);
                    break;
                case "k":
                    startKanjiThread(levelPage);
                    break;
                case "v":
                    startVocabThread(levelPage);
                    break;
            }

        }
        else {
            startRadicalThread(levelPage);
            startKanjiThread(levelPage);
            startVocabThread(levelPage);
        }


    }
}
