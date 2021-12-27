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
public enum ProgramLevel {
    Bachelor("(B)"), Master("(M)"), Doctorial("(P)"), Certificate("(C)");
    public final String shortTerm;
    private ProgramLevel(String shortTerm) {
        this.shortTerm = shortTerm;
    }
}
