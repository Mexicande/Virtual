package cn.com.virtualbitcoin.bean;

/**
 * Created by apple on 2018/3/17.
 */

public class IntroBean {
    private String co;
    private String type;
    private String industry;
    private String description;
    private String features;
    private String technical;
    private String website;
    private String name;
    private String logo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getTechnical() {
        return technical;
    }

    public void setTechnical(String technical) {
        this.technical = technical;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "IntroBean{" +
                "co='" + co + '\'' +
                ", type='" + type + '\'' +
                ", industry='" + industry + '\'' +
                ", description='" + description + '\'' +
                ", features='" + features + '\'' +
                ", technical='" + technical + '\'' +
                ", website='" + website + '\'' +
                ", name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
}
