package classes;

import org.jsoup.nodes.Document;

public class Vocabulary extends Card{

    private String readingMnemonic;

    private  String meaningMnemonic;

    private String reading;

    public Vocabulary(Document document) {
        super(
                document.getElementsByClass("vocabulary-icon").text(),
                document.getElementsByClass("alternative-meaning").get(0).child(1).text());
        this.readingMnemonic = document.getElementsByClass("mnemonic-content").last().text();
        this.reading = document.getElementsByClass("pronunciation-variant").text();
        this.meaningMnemonic = document.getElementsByClass("mnemonic-content").first().text();

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
        builder.append("\n");
        return builder.toString();

    }
}
