package com.example.careercoach.different_exams.exams;

public class ExamData {

    String name,description,eligibility,syllabus;

    public ExamData(String name, String description, String eligibility, String syllabus) {
        this.name = name;
        this.description = description;
        this.eligibility = eligibility;
        this.syllabus = syllabus;
    }

    public ExamData(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    @Override
    public String toString() {
        return description+eligibility+syllabus;
    }
}
