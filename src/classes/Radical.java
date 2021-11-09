package classes;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Radical extends Card{

    String meaningMnemonic;

    public Radical(String title, String meaning) {
        super(title, meaning);
    }

    private String getRadicalIconTextElement(Document radicalPage) {
        Element radicalIcon = radicalPage.getElementsByClass("radical-icon").first();

        return radicalIcon.text();
    }

    

    public Radical(Document radicalPage) {

        super(radicalPage.getElementsByClass("radical-icon").text(),
                radicalPage.getElementsByClass("alternative-meaning").get(0).child(1).text()

                );
        this.meaningMnemonic = radicalPage.getElementsByClass("mnemonic-content").text();
    }

    @Override
    public String toAnkiFormat() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.toAnkiFormat());
        builder.append("|");
        builder.append(meaningMnemonic);
        builder.append("\n");
        return builder.toString();
    }
}
