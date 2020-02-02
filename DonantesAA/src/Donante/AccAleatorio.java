
package Donante;
import java.io.*;

/**
 *
 * @author Axel Garcia
 */
public class AccAleatorio{
   private static RandomAccessFile flujo;
   private static int NumeroD;
   private static final int Tam=128;
   
   public static void CrearFileDonante(File archivo) throws IOException{
       if(archivo.exists()&& !archivo.isFile()){
           throw new IOException(archivo.getName()+" no es un archivo");
                
       }
       flujo=new RandomAccessFile(archivo, "rw");
       NumeroD=(int) Math.ceil((double)flujo.length()/(double)Tam);
       
   }
   public static void cerrar() throws IOException{
       flujo.close();
   }
   public static DonanteP getDonante(int i) throws IOException{
       if(i>=0&&i<=getNumeroD()){
           flujo.seek(i*Tam);
           return new DonanteP(flujo.readUTF(), flujo.readUTF(), flujo.readShort(), flujo.readFloat(), flujo.readUTF(), flujo.readUTF().toCharArray());
       }else
       return null;
   }
   public static boolean setDonante(int i,DonanteP D) throws IOException{
       
       if(i>=0&&i<=getNumeroD()){
           if(D.getTam()>Tam){
               System.out.println("\n\t\t\tTamaño excedido.)");
           }else{
               flujo.seek(i*Tam);
               
               flujo.writeUTF(D.getNombre());
               flujo.writeUTF(D.getDepartamento());
               flujo.writeShort(D.getEdad());
               flujo.writeFloat(D.getPeso());
               flujo.writeUTF(D.getUltimaDonacion());
               flujo.writeUTF(D.Tipo());
               
               return true;
           }
       }else{
           System.out.println("\n\t\t\tNumero de registro fuera de limites");
       }
       return false;
   }
   public static void addDonante(DonanteP D) throws IOException{
       
       setDonante(NumeroD, D);NumeroD++;
   }
    
   public static int buscarDepartamento(String buscado) throws IOException{
       DonanteP D;
       if(buscado==null){
           return -1;
       }
       for (int i = 0; i < getNumeroD(); i++) {
           flujo.seek(i*Tam);
           D=getDonante(i);
           if(D.getDepartamento().equals(buscado)){
               return i;
           }
       }
       return -1;
   }
   public static boolean Modedad(int pos) throws IOException{
      // DonanteP D=getDonante(pos);
       short New;
       if(pos==-1)
           return false;
       else{
           System.out.print("\n\t\tDigite la edad: ");
           New=Leer.datoShort();
           DonanteP D1=getDonante(pos);
           D1.setEdad(New);
           setDonante(pos, D1);
           return true;
           
       }
    
   }
   public static boolean ModFecha(int pos) throws IOException{
       String Fecha;
       if(pos==-1){
           return false;
       }else{
           System.out.print("\n\t\tDigite la ultima fecha de donacion: ");
           Fecha=Leer.dato();
           DonanteP D1=getDonante(pos);
           D1.setUltimaDonacion(Fecha);
           setDonante(pos, D1);
           return true;
       }
   }
   public static boolean ModPeso(int pos) throws IOException{
       float peso;
       if(pos==-1){
           return false;
       }else{
           System.out.print("\n\t\tDigite la ultima fecha de donacion: ");
           peso=Leer.datoFloat();
           DonanteP D1=getDonante(pos);
           D1.setPeso(peso);
           setDonante(pos, D1);
           return true;
       }
   }
    public static int getNumeroD() {
        return NumeroD;
    }

    public static void setNumeroD(int NumeroD) {
        AccAleatorio.NumeroD = NumeroD;
    }
    
}
