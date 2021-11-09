package classes;

import org.jsoup.nodes.Document;

public class Kanji extends Card {

    private String readingMnemonic;

    private String radicalCombination;

    private String reading;

    private  String meaningMnemonic;



    public Kanji(Document document) {

        super(document.getElementsByClass("kanji-icon").text(),
                document.getElementsByClass("alternative-meaning").get(0).child(1).text());
        this.meaningMnemonic = document.getElementsByClass("mnemonic-content").first().text();
         this.readingMnemonic = document.getElementsByClass("mnemonic-content").last().text();
         this.radicalCombination = document.getElementsByClass("radical-icon").text();
         this.reading = document.getElementsByClass("span4").first().child(1).text();





    }

    @Override
    public String toAnkiFormat() {


        StringBuilder builder = new StringBuilder();
        builder.append(super.toAnkiFormat());
        builder.append(", ");
        builder.append(reading);
        builder.append("|");
        builder.append(meaningMnemonic);
        builder.append("|");
        builder.append(readingMnemonic);
        builder.append("|");
        builder.append(radicalCombination);
        builder.append("\n");
        return builder.toString();

    }
}
