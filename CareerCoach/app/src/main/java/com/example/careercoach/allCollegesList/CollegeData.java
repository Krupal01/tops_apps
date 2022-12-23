package com.example.careercoach.allCollegesList;

public class CollegeData {

    String CollegeName,state;
    boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public CollegeData(){

    }

    public CollegeData(String collegeName, String state) {
        CollegeName = collegeName;
        this.state = state;

    }

    public String getCollegeName() {
        return CollegeName;
    }

    public void setCollegeName(String collegeName) {
        CollegeName = collegeName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


}
