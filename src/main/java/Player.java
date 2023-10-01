public class Player {
    private String name;
    private Integer health;
    private Integer maxHealth;

    private boolean isLose = false;

    @Override
    public String toString() {
        return "Игрок: " + name + "(Уровень: " + level + ")";
    }

    public boolean isWin() {
        return isWin;
    }

    public void setWin(boolean win) {
        isWin = win;
    }

    private boolean isWin = false;

    public boolean isLose() {
        return isLose;
    }

    public void setLose(boolean lose) {
        isLose = lose;
    }

    public boolean isChest() {
        return isChest;
    }

    public void setChest(boolean chest) {
        isChest = chest;
    }

    public boolean isLegs() {
        return isLegs;
    }

    public void setLegs(boolean legs) {
        isLegs = legs;
    }

    public boolean isShoulder() {
        return isShoulder;
    }

    public void setShoulder(boolean shoulder) {
        isShoulder = shoulder;
    }

    public boolean isHead() {
        return isHead;
    }

    public void setHead(boolean head) {
        isHead = head;
    }

    private boolean isChest = false;
    private boolean isLegs = false;
    private boolean isShoulder = false;
    private boolean isHead = false;

    public Integer getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(Integer maxHealth) {
        this.maxHealth = maxHealth;
    }

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
        return "Имя игрока: " + player.getName() + "\nЗдоровье игрока: " + player.getHealth() +
                "/" + player.getMaxHealth()
                + "\nКоличество монет: " + player.getCoins() + "\nУровень игрока: " + player.getLevel();
    }


}
