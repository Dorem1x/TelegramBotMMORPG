public class Player {
    private String name;
    private Integer health;
    private Integer level;

    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    private Integer coins;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public static String getPlayerInfo(Player player){
        return "Имя игрока: " + player.getName() + "\nЗдоровье игрока: " + player.getHealth()
                + "\nКоличество монет: " + player.getCoins() + "\nУровень игрока: " + player.getLevel();
    }


}
