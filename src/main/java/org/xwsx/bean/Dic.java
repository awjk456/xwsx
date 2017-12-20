package org.xwsx.bean;

public class Dic {
    private String type;
    private String code;
    private int weight;
    private String name;

    @Override
    public String toString() {
        return "Dic{" +
                "type='" + type + '\'' +
                ", category='" + code + '\'' +
                ", weight=" + weight +
                ", name='" + name + '\'' +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
