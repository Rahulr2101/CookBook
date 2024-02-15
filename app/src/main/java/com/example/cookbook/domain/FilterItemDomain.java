package com.example.cookbook.domain;

import java.util.ArrayList;

public class FilterItemDomain {
    private String name;
    private boolean isSelected;

    private static ArrayList<FilterItemDomain> filterItemsList = new ArrayList<>();

    public FilterItemDomain(String name, boolean isSelected) {
        this.name = name;
        this.isSelected = isSelected;
    }

    public String getName() {
        return name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
        if (selected) {
            for (FilterItemDomain item : filterItemsList) {
                if (item != this) {
                    item.setSelected(false);
                }
            }
        }
    }
    public static void setFilterItemsList(ArrayList<FilterItemDomain> list) {
        filterItemsList = list;
    }
}
