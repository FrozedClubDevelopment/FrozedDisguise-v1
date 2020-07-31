package club.frozed.frozeddisguise.ranks;

import club.frozed.frozeddisguise.FrozedDisguise;

import java.util.ArrayList;
import java.util.List;

public class RanksManager {

    private List<Ranks> ranks;

    public RanksManager() {
        this.ranks = new ArrayList<>();
    }

    public void loadRanks() {
        for (String name : FrozedDisguise.rankConfig.getConfigurationSection("Ranks").getKeys(false)) {
            String prefix = FrozedDisguise.rankConfig.getString("Ranks." + name + ".prefix");
            String nameColor = FrozedDisguise.rankConfig.getString("Ranks." + name + ".nameColor");

            Ranks rank = new Ranks(name, prefix, nameColor);

            ranks.add(rank);
        }
    }

    public List<Ranks> getRanks() {
        return ranks;
    }

    public Ranks getRankName(String name) {
        for (Ranks rank : FrozedDisguise.getRankManager().getRanks()) {
            if (rank.getName().equals(name)) {
                return rank;
            }
        }
        return null;
    }

}
