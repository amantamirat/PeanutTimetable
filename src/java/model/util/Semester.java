/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.util;

import java.util.Arrays;
import java.util.Collection;

/**
 *
 * @author Amanuel
 */
public enum Semester {

    SemesterI("Semester-I", "pi pi-calendar-plus", new ProgramClassification[]{ProgramClassification.Regular, ProgramClassification.Night, ProgramClassification.Weeknd}),
    SemesterII("Semester-II", "pi pi-calendar-times", new ProgramClassification[]{ProgramClassification.Regular, ProgramClassification.Night, ProgramClassification.Weeknd}),
    SemesterIII("Semester-III", "p pi-calendar-plus", new ProgramClassification[]{ProgramClassification.Night, ProgramClassification.Weeknd, ProgramClassification.Summer}),
    Distance("Distance-II", "pi pi-calendar-times", new ProgramClassification[]{ProgramClassification.Summer});
    private final String shortTerm;
    private final String icon;
    private final Collection<ProgramClassification> programClassifications;

    private Semester(String shortTerm, String icon, ProgramClassification[] programClassifications) {
        this.shortTerm = shortTerm;
        this.icon = icon;
        this.programClassifications = Arrays.asList(programClassifications);
    }

    public String getShortTerm() {
        return shortTerm;
    }

    public Collection<ProgramClassification> getProgramClassifications() {
        return programClassifications;
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return this.shortTerm;
    }

}
