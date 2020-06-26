package club.frozed.frozeddisguise.data;

public class Ranks {

    /*
     * The Rank Selector feature is
     * still under development
     */

    private String name;
    private String prefix;
    private String suffix;
    private int weight;
    private String chatColor;

    public Ranks(String name, String prefix, String suffix, int weight, String chatColor) {
        this.name = name;
        this.prefix = prefix;
        this.suffix = suffix;
        this.weight = weight;
        this.chatColor = chatColor;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public int getWeight() {
        return weight;
    }

    public String getChatColor() {
        return chatColor;
    }
}
