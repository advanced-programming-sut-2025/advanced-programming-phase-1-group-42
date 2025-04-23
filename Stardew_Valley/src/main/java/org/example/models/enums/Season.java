package org.example.models.enums;

public enum Season {
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    SPRING(),
    SUMMER(),
    FALL(),
    WINTER();
=======
=======
>>>>>>> Stashed changes
    SPRING("Spring",1),
    SUMMER("Summer",2),
    FALL("Fall",3),
    WINTER("Winter",4);

    private String name;
    private int value;

    Season(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
}
