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
public enum ProgramClassification {
    Regular("(R)"), Night("(N)"), Summer("(S)"), Weeknd("(W)");
    public final String shortTerm;
    private ProgramClassification(String shortTerm) {
        this.shortTerm = shortTerm;
    }
}
