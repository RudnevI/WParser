package threads;

import classes.Radical;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RadicalThread extends Thread{

    private Document levelPage;

    public RadicalThread(Document levelPage) {
        this.levelPage = levelPage;
    }
    @Override
    public void run() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("radicals.txt"));
             Element radicalGrid = levelPage.getElementsByClass("single-character-grid").get(0);
        for (Element child : radicalGrid.children()) {
            Document radicalPage = Jsoup.connect("https://www.wanikani.com/" + child.child(1).attr("href")).get();
            Radical radical = new Radical(radicalPage);
            writer.write(radical.toAnkiFormat());
            System.out.println(radical.toAnkiFormat());

        }
        writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
