
package Donante;
import java.io.*;
import java.util.*;
/**
 * Clase Donante donde se encuentran
 * Nombre del donante
 * Edad del donante
 * Peso del donante
 * Tipo de sangre
 * Ultima donacion
 * @author Axel Garcia
 */
public class DonanteP implements Serializable{

    private String Nombre,Departamento; //30*2=60   15*2=30     
    private short Edad;//2
    private float Peso;//4
    private char [] Tipo=new char [3];//6
    private String UltimaDonacion;//20
    

 

    public DonanteP(String Nombre, String Departamento, short Edad, float Peso, String UltimaDonacion,char [] Tipo) {
        this.Nombre = Nombre;
        this.Departamento = Departamento;
        this.Edad = Edad;
        this.Peso = Peso;
        this.UltimaDonacion = UltimaDonacion;
        this.Tipo=Tipo;
    }

    public int getTam(){
        return getNombre().length()*2+getDepartamento().length()*2+2+4+6+getUltimaDonacion().length()*2+6;
    }
    

    public String getNombre() {return Nombre;}

    public void setNombre(String nombre) {this.Nombre = nombre;}

    public short getEdad() {return Edad;}

    public void setEdad(short edad) {this.Edad = edad;}

    public float getPeso() {return Peso;}

    public void setPeso(float peso) {this.Peso = peso;}

    public char[] getTipo() {return Tipo;}

    public void setTipo(char[] Tipo) {this.Tipo = Tipo;}

    public String getUltimaDonacion() {return UltimaDonacion;}

    public void setUltimaDonacion(String UltimaDonacion) {this.UltimaDonacion = UltimaDonacion;}

    public String getDepartamento() {return Departamento;}

    public void setDepartamento(String Departamento) {this.Departamento = Departamento; }
    
    
    @Override
    public String toString() {
        return  "" + Nombre + "\t" + Edad + "\t" + Peso + "\t" + Tipo() + "\t" + UltimaDonacion + "\t"+Departamento+"\n";
    }
    
    public String Tipo(){return (new StringBuffer().append(Tipo)).toString();}
    
}
