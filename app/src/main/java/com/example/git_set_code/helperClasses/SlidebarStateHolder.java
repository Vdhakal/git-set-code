package com.example.git_set_code.helperClasses;

public class SlidebarStateHolder {
    private boolean isSelected = false;
    private int tripNumber = 0;

    public SlidebarStateHolder(boolean isSelected, int tripNumber) {
        this.isSelected = isSelected;
        this.tripNumber = tripNumber;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getTripNumber() {
        return tripNumber;
    }

    public void setTripNumber(int tripNumber) {
        this.tripNumber = tripNumber;
    }
}
