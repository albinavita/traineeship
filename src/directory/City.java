package directory;

public class City {
    private int id;
    private String name;
    private String region;
    private String district;
    private int population;
    private String foundation;

    public City(int id, String name, String region, String district, int population, String foundation) {
        this.id = id;
        // Наименование города
        this.name = name;
        // Регион
        this.region = region;
        // Округ
        this.district = district;
        // Население (количество жителей)
        this.population = population;
        // Дата основания или первое упоминание
        this.foundation = foundation;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDistrict() {
        return district;
    }

    public int getPopulation() {
        return population;
    }

    public String getRegion() {
        return region;
    }

    @Override
    public String toString() {
        return builderString();
    }
    private String builderString(){
        StringBuilder builder = new StringBuilder();

        builder.append("City{name=\'");
        builder.append(this.name)
                .append("\', region=\'")
                .append(this.region)
                .append("\', distict=\'")
                .append(this.district)
                .append("\', population=")
                .append(this.population)
                .append(", foundation=\'")
                .append(this.foundation)
                .append("\'}");
        return builder.toString();

    }

}
