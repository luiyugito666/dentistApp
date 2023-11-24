/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Shift implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idShift;
    @Temporal(TemporalType.DATE)
    private Date shiftDate;
    private String shiftHour;
    private String Condition;
    
    @ManyToOne
    @JoinColumn(name="idShiftDent")
    private Dentist dentist;
    
    @ManyToOne
    @JoinColumn(name="idShiftPat")
    private Patient patient;

    public Shift() {
    }

    public Shift(int idShift, Date shiftDate, String shiftHour, String Condition, Dentist dentist, Patient patient) {
        this.idShift = idShift;
        this.shiftDate = shiftDate;
        this.shiftHour = shiftHour;
        this.Condition = Condition;
        this.dentist = dentist;
        this.patient = patient;
    }

    public int getIdShift() {
        return idShift;
    }

    public void setIdShift(int idShift) {
        this.idShift = idShift;
    }

    public Date getShiftDate() {
        return shiftDate;
    }

    public void setShiftDate(Date shiftDate) {
        this.shiftDate = shiftDate;
    }

    public String getShiftHour() {
        return shiftHour;
    }

    public void setShiftHour(String shiftHour) {
        this.shiftHour = shiftHour;
    }

    public String getCondition() {
        return Condition;
    }

    public void setCondition(String Condition) {
        this.Condition = Condition;
    }

    public Dentist getDentist() {
        return dentist;
    }

    public void setDentist(Dentist dentist) {
        this.dentist = dentist;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    
}
