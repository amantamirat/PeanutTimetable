/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.util;


import java.sql.Date;
import java.time.DayOfWeek;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import model.util.ProgramClassification;
import model.util.ProgramLevel;
import model.util.Semester;

/**
 *
 * @author Amanuel
 */
@Named("peanutController")
@ApplicationScoped
public class PeanutController {

    public ProgramClassification[] getProgramClassifications() {
        return ProgramClassification.values();
    }

    public Semester[] getSemesters() {
        return Semester.values();
    }

    public ProgramLevel[] getProgramLevels() {
        return ProgramLevel.values();
    }
    
    public DayOfWeek[] getDays(){
        return DayOfWeek.values();
    }

}
