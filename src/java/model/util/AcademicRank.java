/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.util;

/**
 *
 * @author Amanuel
 */
public enum AcademicRank {
    GAI(""),
    GAII(""),
    GAIII(""),
    Lecturer(""),
    Assistant_Professor(""),
    Associate_Professor("Dr."),
    Professor("Prof.");
    public final String shortTerm;
    private AcademicRank(String shortTerm) {
        this.shortTerm = shortTerm;
    }
}
