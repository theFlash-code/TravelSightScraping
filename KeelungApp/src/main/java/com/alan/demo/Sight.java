package com.alan.demo;

import java.util.*;

public class Sight {
    public String sightName;
    public String Zone;
    public String Category;
    public List<String> photoList;
    public String Description;
    public String Address;

    public Sight() {
        sightName = "";
        Zone = "";
        Category = "";
        Description = "";
        Address = "";
        photoList = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SightName: " + sightName + "\nZone: " + Zone + "\nCategory: " + Category);
        if (photoList.size() > 0) {
            int photosize = 1;
            for (int i = 0; i < photosize; ++i) {
                sb.append("\nPhotoURL" + (i + 1) + ": " + photoList.get(i));
            }
        }
        if (Description.equals("旅遊王編輯組")) {
            sb.append("\nDescription: no description yet~\nAddress: " + Address + "\n");
        } else {
            sb.append("\nDescription: " + Description + "\nAddress: " + Address + "\n");
        }

        return sb.toString();
    }
}
