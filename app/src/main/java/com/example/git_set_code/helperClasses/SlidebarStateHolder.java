package com.example.git_set_code.helperClasses;

/**
 *
 */
public class SlidebarStateHolder {
    private boolean isSelected = false;
    private int tripNumber = 0;

    /**
     * @param isSelected
     * @param tripNumber
     */
    public SlidebarStateHolder(boolean isSelected, int tripNumber) {
        this.isSelected = isSelected;
        this.tripNumber = tripNumber;
    }

    /**
     * @return
     */
    public boolean isSelected() {
        return isSelected;
    }

    /**
     * @param selected
     */
    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    /**
     * @return
     */
    public int getTripNumber() {
        return tripNumber;
    }

    /**
     * @param tripNumber
     */
    public void setTripNumber(int tripNumber) {
        this.tripNumber = tripNumber;
    }
}
