package threads;

import classes.Kanji;
import classes.Radical;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class KanjiThread extends Thread{

    private Document levelPage;

    public KanjiThread(Document levelPage) {
        this.levelPage = levelPage;
    }

    @Override
    public void run() {
        try {
            BufferedWriter kanjiWriter = new BufferedWriter(new FileWriter("kanji.txt"));
             Element kanjiGrid = levelPage.getElementsByClass("single-character-grid").get(1);
        for (Element child : kanjiGrid.children()) {
            Document kanjiPage = Jsoup.connect("https://www.wanikani.com/" + child.child(1).attr("href")).get();
            Kanji kanji = new Kanji(kanjiPage);
            System.out.println(kanji.toAnkiFormat());
            kanjiWriter.write(kanji.toAnkiFormat());


        }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
