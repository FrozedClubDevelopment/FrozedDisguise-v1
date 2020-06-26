package club.frozed.frozeddisguise.managers;

import club.frozed.frozeddisguise.FrozedDisguise;
import club.frozed.frozeddisguise.data.Ranks;

import java.util.ArrayList;
import java.util.List;

public class RanksManager {

    /*
     * The Rank Selector feature is
     * still under development
     */

    private List<Ranks> ranks;

    public RanksManager() {
        this.ranks = new ArrayList<>();
    }

    public void loadRanks() {
        for (String r : FrozedDisguise.getInstance().getConfig().getConfigurationSection("Ranks").getKeys(false)) {
            String prefix = FrozedDisguise.getInstance().getConfig().getString("Ranks." + r + ".prefix");
            String suffix = FrozedDisguise.getInstance().getConfig().getString("Ranks." + r + ".suffix");
            int weight = FrozedDisguise.getInstance().getConfig().getInt("Ranks." + r + ".weight");
            String chatColor = FrozedDisguise.getInstance().getConfig().getString("Ranks." + r + ".chatColor");

            Ranks rank = new Ranks(r, prefix, suffix, weight, chatColor);

            if (rank == null) {
                FrozedDisguise.getInstance().getServer().getConsoleSender().sendMessage("§cAn error has occurred while trying to load the " + rank.getName());
                return;
            }

            ranks.add(rank);
        }
    }

    public List<Ranks> getRanks() {
        return ranks;
    }

    public Ranks getByName(String name) {
        for (Ranks rank : FrozedDisguise.getInstance().getRanksManager().getRanks()) {
            if (rank.getName().equals(name)) {
                return rank;
            }
        }
        return null;
    }

}
