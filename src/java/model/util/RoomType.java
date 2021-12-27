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
public enum RoomType {
    ClassRoom("CR"),
    Labratory("LAB"),
    LectureHall("LTH"),
    Library("LIB"),
    Cafe("Cafe"),
    Office("Off");
    public final String shortTerm;
    private RoomType(String shortTerm) {
        this.shortTerm = shortTerm;
    }
}
