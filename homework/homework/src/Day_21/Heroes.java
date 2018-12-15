package streamStudy;

public class Heroes {
    private int id;
    private String name;
    private String home;
    private String sex;
    private int birth;
    private int dead;
    private int ability;

    public Heroes(int id, String name, String home,String sex, int birth, int dead, int ability) {
        this.id = id;
        this.name = name;
        this.home = home;
        this.sex = sex;
        this.birth = birth;
        this.dead = dead;
        this.ability = ability;
    }

    public Heroes() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getBirth() {
        return birth;
    }

    public void setBirth(int birth) {
        this.birth = birth;
    }

    public int getDead() {
        return dead;
    }

    public void setDead(int dead) {
        this.dead = dead;
    }

    public int getAbility() {
        return ability;
    }

    public void setAbility(int ability) {
        this.ability = ability;
    }

    @Override
    public String toString() {
        return "Heroes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", home='" + home + '\'' +
                ", sex='" + sex + '\'' +
                ", birth=" + birth +
                ", dead=" + dead +
                ", ability=" + ability +
                '}';
    }
}
