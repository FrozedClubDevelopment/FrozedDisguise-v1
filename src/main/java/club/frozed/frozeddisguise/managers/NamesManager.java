package club.frozed.frozeddisguise.managers;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class NamesManager {

    private static List<String> shortWords;
    private static List<String> longWords;
    private static List<String> conjuctions;
    private static List<String> onlyNames;
    private static List<String> japaneseNames;

    public static String generate() {
        Random random = new Random();
        return generate(NicknamePattern.values()[random.nextInt(NicknamePattern.values().length)]);
    }

    public static String generate(NicknamePattern pattern) {

        String disguiseNickname = null;

        if (pattern.equals(NicknamePattern.NameWithNumbers)) {
            Random random = new Random();
            String name = NamesManager.onlyNames.get(random.nextInt(NamesManager.onlyNames.size()));
            if (name.length() <= 10 && chance(50.0)) {
                disguiseNickname = name + "_" + random.nextInt(9999);
            } else {
                disguiseNickname = name + random.nextInt(9999);
            }
        }

        if (pattern.equals(NicknamePattern.TwoShortsWithConjunction)) {
            pattern = NicknamePattern.JapaneseNameWithBirth;
        }

        if (pattern.equals(NicknamePattern.JapaneseNameWithBirth)) {
            Random random = new Random();
            String name = NamesManager.japaneseNames.get(random.nextInt(NamesManager.japaneseNames.size()));
            String birth = String.valueOf(random.nextInt(12) + 1) + String.valueOf(random.nextInt(30) + 1);
            if (chance(50.0)) {
                disguiseNickname = name + "_" + birth;
            } else {
                disguiseNickname = name + birth;
            }
        }

        if (pattern.equals(NicknamePattern.LongWithNumbers)) {
            Random random = new Random();
            String name = NamesManager.longWords.get(random.nextInt(NamesManager.longWords.size()));
            if (chance(50.0)) {
                disguiseNickname = name + "_" + random.nextInt(9999);
            } else {
                disguiseNickname = name + random.nextInt(9999);
            }
        }

        if (pattern.equals(NicknamePattern.ShortWithConjunction)) {
            Random random = new Random();
            String name = NamesManager.conjuctions.get(random.nextInt(NamesManager.conjuctions.size()));
            String secondName = NamesManager.shortWords.get(random.nextInt(NamesManager.shortWords.size()));
            if (chance(50.0)) {
                disguiseNickname = name + "_" + secondName;
            } else {
                disguiseNickname = name + secondName;
            }
        }

        if (pattern.equals(NicknamePattern.ShortAndLong)) {
            Random random = new Random();
            String name = NamesManager.shortWords.get(random.nextInt(NamesManager.shortWords.size()));
            String secondName = NamesManager.longWords.get(random.nextInt(NamesManager.longWords.size()));
            disguiseNickname = name + secondName;
        }

        if (chance(50.0)) {
            assert disguiseNickname != null;
            disguiseNickname = disguiseNickname.toLowerCase();
        }

        return disguiseNickname;
    }

    private static boolean chance(double percent) {
        return Math.random() < percent / 100.0;
    }

    static {
        NamesManager.shortWords = Arrays.asList("About", "Active", "Admit", "Advise", "Again", "After", "Agent", "Alive", "Alone", "Beach", "Basket", "Basic", "Bath", "Battle",
                "Bean", "Beat", "Bed", "Become", "Begin", "Before", "Beer", "Behind", "Blade", "Black", "Blue", "Bomb", "Brush", "Build", "Bunch", "Button", "Biz", "Busy",
                "Box", "Boy", "Break", "Best", "Better", "Cake", "Camera", "Campus", "Cap", "Card", "Care", "Case", "Catch", "Center", "Chain", "Chair", "Chara", "Charge",
                "Chase", "Cheap", "Cheese", "Check", "Close", "Choose", "Christ", "Circle", "Dad", "Dance", "Dark", "Data", "Dead", "Defend", "Desert", "Desk", "Device",
                "Detect", "Dinner", "Direct", "Dirt", "Dirty", "Doctor", "Down", "Drama", "Draw", "Dream", "Drop", "Earth", "Eat", "Easy", "Editor", "Effect", "Eight",
                "Elect", "Effort", "Emote", "Enter", "Engine", "Enemy", "Empty", "Entry", "Error", "Enough", "Every", "Exact", "Eye", "Expert", "Face", "Fact", "Fade",
                "Fail", "Family", "Famous", "Farmer", "Father", "Fight", "Find", "Finger", "Fire", "First", "Fit", "Fix", "Fish", "Field", "Floor", "Focus", "Fly",
                "Forest", "Force", "Frame", "Uber");

        NamesManager.longWords = Arrays.asList("Actually", "Aircraft", "Backbone", "Blooming", "Brightly", "Building", "Camellia", "Cardinal", "Careless", "Chemical", "Cheerful",
                "Civilian", "Daughter", "Demolish", "Detector", "Disaster", "Disposal", "Electron", "Elective", "Engaging", "Enormous", "Erection", "Evidence", "Exertion",
                "External", "Faithful", "Familiar", "Favorite", "Fearless", "Fixation", "Fragment", "Generous", "Grateful", "Grievous", "Hydrogen", "Horrible", "Ignorant",
                "Industry", "Majority", "Military", "Mountain", "Mythical", "Normally", "Numerous", "Organism", "Overview", "Pacifist", "Pentagon", "Perilous", "Physical",
                "Precious", "Prestige", "Puzzling", "Railroad", "Reckless");

        NamesManager.conjuctions = Arrays.asList("The", "Da", "And", "Of", "By", "Is", "El", "Its", "MC", "GANGMEMBER", "xXx", "_", "__");

        NamesManager.onlyNames = Arrays.asList("Ibirawyr", "Niniel", "Celahan", "Gwysien", "Figovudd", "Zathiel", "Adwiawyth", "Nydinia", "Laraeb", "Eowendasa", "Grendakin",
                "Werradia", "Cauth", "Umigolian", "Tardond", "Dwearia", "Yeiwyn", "Adraclya", "Zaev", "Thabeth", "Chuven", "Zaredon", "Bob", "Robert", "Johnny", "Joy",
                "Matthew", "Michael", "Jacob", "Joshua", "Daniel", "Christopher", "Andrew", "Ethan", "Joseph", "William", "Anthony", "David", "Alexander", "Nicholas",
                "Ryan", "Tyler", "James", "John", "Jonathan", "Noah", "Brandon", "Christian", "Dylan", "Samuel", "Benjamin", "Nathan", "Zachary", "Logan", "Justin",
                "Gabriel", "Emily", "Madison", "Emma", "Olivia", "Hannah", "Abigail", "Isabella", "Samantha", "Elizabeth", "Ashley", "Alexis", "Sarah", "Sophia",
                "Alyssa", "Grace", "Ava", "Taylor", "Brianna", "Lauren", "Chloe", "Natalie", "Kayla", "Jessica", "Anna", "Victoria", "Mia", "Hailey", "Sydney", "Jasmine");

        NamesManager.japaneseNames = Arrays.asList("Ai", "Aya", "Ayako", "Itsuki", "Eita", "Eiko", "Kanta", "Kaito", "Kenta", "Kento", "Kouki", "Kouta", "Kouya", "Kou",
                "Keito", "Keita", "Saya", "Sayako", "Sara", "Sizuki", "Sizuko", "Sizuno", "Sizuya", "Suzuka", "Suzuki", "Suzuko", "Sumi", "Seiya", "Souta", "Souya",
                "Sou", "Taichi", "Takuya", "Tatsuki", "Chitose", "Tutomu", "Tumuya", "Tetsuya", "Tetsuto", "Tekuto", "Touya", "Tomi", "Nami", "Nao", "Neo", "Notomi",
                "Haruya", "Harumi", "Haruto", "Hitomi", "Hitoshi", "Fuuta", "Fuyuki", "Fuuto", "Mami", "Maya", "Mai", "Masaya", "Masahiro", "Masato", "Misaki", "Mitsuki",
                "Mutsuki", "Mei", "Yae", "Yuuto", "Yuuta", "Yuuya", "Youta", "Youki");
    }

    public enum NicknamePattern {
        ShortAndLong,
        NameWithNumbers,
        LongWithNumbers,
        ShortWithConjunction,
        JapaneseNameWithBirth,
        TwoShortsWithConjunction
    }

}
