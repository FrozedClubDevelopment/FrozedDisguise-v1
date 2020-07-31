package club.frozed.frozeddisguise.ranks;

public class Ranks {

    private String name;
    private String prefix;
    private String nameColor;

    public Ranks(String name, String prefix, String nameColor) {
        this.name = name;
        this.prefix = prefix;
        this.nameColor = nameColor;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getNameColor() {
        return nameColor;
    }

}
