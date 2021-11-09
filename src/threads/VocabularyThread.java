package threads;

import classes.Vocabulary;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class VocabularyThread extends Thread{

    private Document levelPage;

    public VocabularyThread(Document levelPage){
        this.levelPage = levelPage;
    }

    @Override
    public void run() {
    try {
        BufferedWriter vocabularyWriter = new BufferedWriter(new FileWriter("vocabulary.txt"));

        Element vocabularyGrid = levelPage.getElementsByClass("multi-character-grid").get(0);

        for (Element child : vocabularyGrid.children()) {
            Document vocabPage = Jsoup.connect("https://www.wanikani.com/" + child.child(1).attr("href")).get();
            Vocabulary vocabulary = new Vocabulary(vocabPage);
            vocabularyWriter.write(vocabulary.toAnkiFormat());
            System.out.println(vocabulary.toAnkiFormat());


        }
        vocabularyWriter.close();
    }
    catch (Exception e) {
        e.printStackTrace();
    }
    }
}
