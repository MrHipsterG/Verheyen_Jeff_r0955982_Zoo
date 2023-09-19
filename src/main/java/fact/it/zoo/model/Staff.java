package fact.it.zoo.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Jeff Verheyen r0955982
public class Staff extends Person {
    private LocalDate startDate;
    private boolean student;

    public Staff(String firstName, String surName) {
        super(firstName, surName);
        this.startDate = LocalDate.now();
        this.student = false;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public boolean isStudent() {
        return student;
    }

    public void setStudent(boolean student) {
        this.student = student;
    }

    @Override
    public String toString() {
        String studentString = " ";
        if (this.isStudent()){
            studentString = " (working student) ";
        }
        return "Staff member " + super.toString() + studentString + "is employed since " + this.getStartDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
