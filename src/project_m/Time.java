/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project_m;

/**
 *
 * @author Elios
 */
public class Time {
    private int heure;
    private int minute;
    private int seconde;

    public Time(int heure, int minute, int seconde) {
        this.heure = heure;
        this.minute = minute;
        this.seconde = seconde;
    }

    public void setT(Time t){
        this.heure = t.heure;
        this.minute = t.minute;
        this.seconde = t.seconde;
    }
    
    public int getHeure() {
        return heure;
    }

    public int getMinute() {
        return minute;
    }

    public int getSeconde() {
        return seconde;
    }

    public void setHeure(int heure) {
        this.heure = heure;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setSeconde(int seconde) {
        this.seconde = seconde;
    }
    
    
}
