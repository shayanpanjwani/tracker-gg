package com.shayan;

import java.util.Map;
import java.util.Comparator;


public class Match {

    public enum Role {
        Top, Jungle, Middle, ADC, Support 
    }
    public enum Result {
        Victory, Defeat, Remake
    }
    private String[] fields = {"Champion", "Role", "Kills", "Deaths", "Assists", "Gold", "CS", "CS/M", "Duration", "Date", "Result"};
    private Object[] rowData;
    private String champion;
    private Role role;

    private Integer kills;
    private Integer deaths;
    private Integer assists;

    private Integer gold;
    private Integer cs;
    private Double cs_per_m;

    private String duration;
    private String date;
    private Result result;

    public Match(String champion, String role, 
                 Integer kills, Integer deaths, Integer assists,
                 Integer gold, Integer cs,
                 String duration, String date, String result) {
        this.champion = champion;
        this.role = Role.valueOf(role);
        
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;

        this.gold = gold;
        this.cs = cs;
        

        this.duration = duration;
        this.date = date;
        this.result = Result.valueOf(result);
        Double min = Double.valueOf(duration.split(":")[0]);
        this.cs_per_m = cs/min;
        this.rowData = new Object[] {this.champion, this.role, 
                                this.kills, this.deaths, this.assists, 
                                this.gold, this.cs, this.cs_per_m,
                                this.duration, this.date, this.result};

    }

    public Match(Map<String, String> m) {

        this.champion = m.get("Champion");
        this.role = Role.valueOf(m.get("Role"));

        this.kills = Integer.parseInt(m.get("Kills"));
        this.deaths = Integer.parseInt(m.get("Deaths"));
        this.assists = Integer.parseInt(m.get("Assists"));

        this.gold = Integer.parseInt(m.get("Gold").replace(",", "").toString());
        this.cs = Integer.parseInt(m.get("CS"));

        this.duration = m.get("Duration");
        this.date = m.get("Date");
        this.result = Result.valueOf(m.get("Result"));
        Double min = Double.valueOf(duration.split(":")[0]);
        this.cs_per_m = cs/min;
        this.rowData = new Object[] {this.champion, this.role, 
                                     this.kills, this.deaths, this.assists, 
                                     this.gold, this.cs, this.cs_per_m,
                                     this.duration, this.date, this.result};
            
}

    

    public String getKDA() {
        return kills + "  /  " + deaths + "  /  " + assists;
    }
    public Double getCSM() {
        return cs_per_m;
    }

    @Override 
    public String toString() {
        return "Role: " + getRole() + 
               "\t| Champion: " + getChampion() + 
               "\t| KD: " + getKDA() + 
               "\t| CS: " + getCS() + 
               "\t| CS/M: " + String.format("%.2f", getCSM()) + 
               "\t| Result: " + getResult();
    }
    public static void main(String[] args) {
        Match m1 = new Match("Diana", "Jungle",
                             4, 11, 9,
                             11273, 164, 
                             "33:30", "8/12/22", "Defeat");
        System.out.println(m1);
    }

    public Object[] getRowData() {
        return rowData;
    }

    public String[] getFields() {
        return fields;
    }

    public String getChampion() {
        return champion;
    }

    public Role getRole() {
        return role;
    }

    public Integer getKills() {
        return kills;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public Integer getAssists() {
        return assists;
    }

    public Integer getGold() {
        return gold;
    }

    public Integer getCS() {
        return cs;
    }

    public String getDuration() {
        return duration;
    }

    public String getDate() {
        return date;
    }

    public Result getResult() {
        return result;
    }

    public static Comparator<Match> MatchChampionComparator = new Comparator<Match>() {
        public int compare(Match m1, Match m2) {
            String c1 = m1.getChampion();
            String c2 = m2.getChampion();
            return c1.compareTo(c2);
        }
    };
    
    public static Comparator<Match> MatchRoleComparator = new Comparator<Match>() {
        public int compare(Match m1, Match m2) {
            Role r1 = m1.getRole();
            Role r2 = m2.getRole();
            return r1.compareTo(r2);
        }
    };

    public static Comparator<Match> MatchKillsComparator = new Comparator<Match>() {
        public int compare(Match m1, Match m2) {
            int k1 = m1.getKills();
            int k2 = m2.getKills();
            return k1-k2;
        }
    };
    
    public static Comparator<Match> MatchDeathsComparator = new Comparator<Match>() {
        public int compare(Match m1, Match m2) {
            int d1 = m1.getDeaths();
            int d2 = m2.getDeaths();
            return d1-d2;
        }
    };

    public static Comparator<Match> MatchAssistsComparator = new Comparator<Match>() {
        public int compare(Match m1, Match m2) {
            int a1 = m1.getAssists();
            int a2 = m2.getAssists();
            return a1-a2;
        }
    };

    public static Comparator<Match> MatchGoldComparator = new Comparator<Match>() {
        public int compare(Match m1, Match m2) {
            int g1 = m1.getGold();
            int g2 = m2.getGold();
            return g1-g2;
        }
    };

    public static Comparator<Match> MatchCSComparator = new Comparator<Match>() {
        public int compare(Match m1, Match m2) {
            int c1 = m1.getCS();
            int c2 = m2.getCS();
            return c2-c1;
        }
    };

    public static Comparator<Match> MatchCSMComparator = new Comparator<Match>() {
        public int compare(Match m1, Match m2) {
            double c1 = m1.getCSM();
            double c2 = m2.getCSM();
            return -Double.compare(c1, c2);
        }
    };

    public static Comparator<Match> MatchResultComparator = new Comparator<Match>() {
        public int compare(Match m1, Match m2) {
            Result r1 = m1.getResult();
            Result r2 = m2.getResult();
            return r1.compareTo(r2);
        }
    };

    public static Map<String, Comparator<Match>> matchComparators = Map.of("champion", MatchChampionComparator,
                                                                           "role", MatchRoleComparator, 
                                                                           "kills", MatchKillsComparator,
                                                                           "deaths", MatchDeathsComparator,
                                                                           "assists", MatchAssistsComparator,
                                                                           "gold", MatchGoldComparator,
                                                                           "cs", MatchCSComparator, 
                                                                           "csm", MatchCSMComparator
                                                                           );
}
