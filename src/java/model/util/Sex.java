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
public enum Sex  {
    Male("Mr."),
    Female("Miss.");
    public final String shortTerm;
    private Sex(String shortTerm) {
        this.shortTerm = shortTerm;
    }
}
