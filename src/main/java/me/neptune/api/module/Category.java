package me.neptune.api.module;

public enum Category
{
    CLIENT("Client"),
    COMBAT("Combat"),
    MISC("Miscellaneous"),
    MOVEMENT("Movement"),
    RENDER("Render"),
    WORLD("World");


    private final String name;
    Category(String name){
        this.name = name;
    }

    String getString(){
        return name;
    }

}
