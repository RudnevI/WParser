package classes;

public abstract class Card {

    private String title;

    private String meaning;



    public String toAnkiFormat() {

        StringBuilder builder = new StringBuilder();
        builder.append(title.equals("")? "needs attention":title);
        builder.append("|");
        builder.append(meaning);

        return builder.toString();


    }

    public Card(String title, String meaning) {
        this.title = title;
        this.meaning = meaning;

    }

}
