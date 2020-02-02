package Principal;

import Donante.AccAleatorio;
import Donante.DonanteP;

import Donante.Leer;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author Axel Garcia
 */
public class Principal {

    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException, ClassNotFoundException {

        String Nombre, Departamento;
        short Edad;
        int opc = 0;
        float Peso;

        char[] Tipo = new char[3];
        boolean Verificador = false;
        String UltimaDonacion;

        File archivo = new File("src\\Datos\\Datos.dat");
        if (archivo.exists()) {

        } else {
            archivo.createNewFile();
        }
        System.out.println("\n\t\t\tRegistro de Donantes de Sangre\n");
        do {
            System.out.print("\n\t\t--------Menu de opciones---------\n"
                    + "\t\t1.Ver donantes registrados actualmente\n"
                    + "\t\t2.Ingresar donante\n"
                    + "\t\t3.Modificar ultima donacion,peso o edad\n"
                    + "\t\t4.Buscar donante por departamento\n"
                    + "\t\t5.Salir del programa "
                    + "\t\t\t\t---->");
            opc = Leer.datoInt();

            switch (opc) {
                case 1: {

                    int i = 0;
                    try {

                        AccAleatorio.CrearFileDonante(new File("Datos1.dat"));

                        while (i < AccAleatorio.getNumeroD()) {
                            System.out.println(AccAleatorio.getDonante(i));

                            i++;
                        }
                        if (i == 0) {
                            System.out.println("\n\t\tNo hay donantes registrados.");
                        }

                    } catch (IOException E) {
                        System.out.println("\n\t\tError de Busqueda de Registros.");
                    }
                    AccAleatorio.cerrar();
                    break;
                }
                case 2: {
                    try {
                        System.out.print("\n\t\tIngrese los siguientes datos del donante: \n");
                        do {
                            System.out.print("\n\tNombre: ");
                            Nombre = Leer.dato();
                        } while (Nombre == null || Nombre.length() >= 30);

                        do {
                            System.out.print("\n\tEdad(18-65): ");
                            Edad = Leer.datoShort();
                        } while (Edad < 18 || Edad > 65);
                        do {
                            System.out.print("\n\tPeso en Kg(>50): ");
                            Peso = Leer.datoFloat();
                        } while (Peso < 50);

                        System.out.print("\n\tTipo de Sangre: ");
                        Tipo = Leer.dato().toCharArray();

                        System.out.print("\n\tUltima donacion (DD/MM/AAAA): ");
                        UltimaDonacion = Leer.dato();
                        do {
                            System.out.print("\n\tDepartamento natal: ");
                            Departamento = Leer.dato();
                        } while (Departamento.length() > 15 || "".equals(Departamento));

                        DonanteP P = new DonanteP(Nombre, Departamento, Edad, Peso, UltimaDonacion, Tipo);

                        AccAleatorio.CrearFileDonante(new File("Datos1.dat"));
                        AccAleatorio.addDonante(P);
                        AccAleatorio.cerrar();
                        System.out.println("\n\t\tEl registro se realizo correctamente.");
                    } catch (IOException e) {
                        System.out.println("\n\t\tError en la escritura de datos.");

                    }
                    AccAleatorio.cerrar();
                    break;
                }

                case 3: {
                    do {
                        System.out.print("\n\t\tDigite el numero de registro del donante: \n\t\t\t");
                        int indice = Leer.datoInt();
                        indice--;
                        AccAleatorio.CrearFileDonante(new File("Datos1.dat"));
                        System.out.println(AccAleatorio.getDonante(indice));
                        System.out.print("\n\tEl donante a modificar es correcto?(Digite si o no)\n\t\t\t");
                        String XD = Leer.dato();

                        if ("si".equals(XD) || "Si".equals(XD) || "SI".equals(XD)) {
                            System.out.print("\n\tQue desea modificar:"
                                    + "\n\t\t1.Fecha de Ultima donacion."
                                    + "\n\t\t2.Edad \t3.Peso"
                                    + "\n\t\t4.Las 3\t--->");
                            int opc1 = Leer.datoInt();
                            switch (opc1) {
                                case 1: {
                                    AccAleatorio.ModFecha(indice);
                                    break;
                                }
                                case 2: {
                                    AccAleatorio.Modedad(indice);
                                    break;
                                }
                                case 3: {
                                    AccAleatorio.ModPeso(indice);
                                    break;
                                }
                                case 4: {
                                    AccAleatorio.ModFecha(indice);
                                    AccAleatorio.Modedad(indice);
                                    AccAleatorio.ModPeso(indice);
                                    break;
                                }
                                default: {

                                    System.out.print("\n\t\t\tOpcion equivocada, escoja bien >:v");
                                }

                            }
                            Verificador = true;
                        } else {
                            Verificador = false;
                        }

                    } while (Verificador == false);
                    AccAleatorio.cerrar();
                    break;
                }
                case 4: {

                    System.out.print("\n\t\tEscriba el Departamento de origen: ");
                    Departamento = Leer.dato();
                    if (Departamento.isEmpty()) {
                        System.out.println("Ingrese un String valido >:v");
                    }
                    try {
                        AccAleatorio.CrearFileDonante(new File("Datos1.dat"));
                        int i = AccAleatorio.buscarDepartamento(Departamento);
                        if (i == 1) {
                            System.out.println("\n\t\tNingun Donante que vive en " + Departamento);

                        }
                        if (AccAleatorio.getDonante(i) != null) {
                            System.out.println("\nLa  primera coincidencia es: \n" + AccAleatorio.getDonante(i));

                        }
                        AccAleatorio.cerrar();
                    } catch (IOException e) {
                        System.out.println("Sintax Error XD");
                    }
                    AccAleatorio.cerrar();
                    break;
//                        
                }

            }
        } while (opc != 5);

    }

    //menu de opciones
}
