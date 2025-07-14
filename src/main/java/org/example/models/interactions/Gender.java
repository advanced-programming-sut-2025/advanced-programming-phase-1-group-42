package org.example.models.interactions;

public enum Gender {
    MALE("Male"),
    FEMALE("Female");


    private String name;

    Gender(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Gender findGender(String gender) {
        for (Gender g : Gender.values()) {
            if (g.name.equals(gender)) {
                return g;
            }
        }
        return null;
    }
}
