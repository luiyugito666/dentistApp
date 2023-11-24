/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.Date;
import javax.persistence.Entity;

@Entity
public class Responsible extends Person{
    //private int  idResponsible;
    private String respType;
   
    
    public Responsible() {
    }

    public Responsible(String respType, int id, String dni, String name, String lastName, String phone, String address, Date birthday) {
        super(id, dni, name, lastName, phone, address, birthday);
        this.respType = respType;
    }

    public String getRespType() {
        return respType;
    }

    public void setRespType(String respType) {
        this.respType = respType;
    }
    

}
