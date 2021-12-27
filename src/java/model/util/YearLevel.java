/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.util;

import model.AcademicCalendar;
import model.Batch;

/**
 *
 * @author Amanuel
 */
public enum YearLevel {

    First("First Year"),
    Second("Second Year"),
    Third("Third Year"),
    Fourth("Fourth Year"),
    Fifth("Fifth Year"),
    Sixth("Sixth Year"),
    Seventh("Seventh Year"),
    Undefined("Undefined");
    public final String shortTerm;

    private YearLevel(String shortTerm) {
        this.shortTerm = shortTerm;
    }

    public static YearLevel computeYearLevel(AcademicCalendar ac, Batch bc) {
        try {
            int a = Integer.parseInt(ac.getAcademicYear().substring(5, 7));
            int b = Integer.parseInt(bc.getEntranceYear().substring(5, 7));
            int y = a - b;
            if (y < bc.getProgram().getMaxYearOfStudy()) {
                return YearLevel.values()[y];
            }
        } catch (NullPointerException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
        }
        return YearLevel.Undefined;
    }
    
    
}
