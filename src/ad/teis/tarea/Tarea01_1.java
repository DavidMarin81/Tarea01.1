/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ad.teis.tarea;

import ad.teis.model.Persona;
import ad.teis.persistencia.DataIOPersistencia;
import ad.teis.persistencia.IPersistencia;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mfernandez
 */
public class Tarea01_1 {

    public static final Path PERSONAS_ORIGEN_PATH = Paths.get("src", "docs", "origen.dat");
    private static final Path PERSONAS_ORIGEN_COPIA_PATH = Paths.get("src", "docs", "borrados", "origen.dat.bk");
    private static final Path PERSONAS_DESTINO_PATH = Paths.get("src", "docs",
            "destino.dat.");

    private static ArrayList<Persona> filtrarPersonas(ArrayList<Persona> personas) {
        ArrayList<Persona> personasFiltradas = new ArrayList<>();
        for (Persona p : personas) {
            if (!p.isBorrado()) {
                personasFiltradas.add(p);
            }
        }

        return personasFiltradas;
    }

    private static void cribarBorrados() {
        ArrayList<Persona> personas = new ArrayList<>();
        File ficheroOrigen = new File(PERSONAS_ORIGEN_PATH.toString());
        File ficheroDestino = new File(PERSONAS_DESTINO_PATH.toString());

        IPersistencia diop = new DataIOPersistencia();

        if (ficheroOrigen.exists()) {
            personas = diop.leerTodo(PERSONAS_ORIGEN_PATH.toString());
        } else {
            try {
                System.out.println("El fichero de origen no existe. Se procede a su creado.");
                ficheroOrigen.createNewFile();
                personas = diop.leerTodo(PERSONAS_ORIGEN_PATH.toString());
            } catch (IOException ex) {
                System.out.println("No se ha podido crear el fichero");
            }
        }

        //Completa el método
        personas = filtrarPersonas(personas);

        DataIOPersistencia dao = new DataIOPersistencia();

        //Se comprueba si el fichero existe
        if (ficheroDestino.exists()) {
            //Si el fichero ya existía en el destino, deberá borrarse previamente a la creación del fichero.
            try {
                ficheroDestino.delete();
                ficheroDestino.createNewFile();
                dao.escribirPersonas(personas, Tarea01_1.PERSONAS_DESTINO_PATH.toString());
            } catch (IOException ex) {
                System.out.println("No se ha podido crear el fichero");
            }
        } else {
            try {
                System.out.println("El fichero no existe. Se procede a su creado.");
                ficheroDestino.createNewFile();
                dao.escribirPersonas(personas, Tarea01_1.PERSONAS_DESTINO_PATH.toString());
            } catch (IOException ex) {
                System.out.println("No se ha podido crear el fichero");
            }
        }

        //Por último, el fichero en la ruta PERSONAS_ORIGEN_PATH, deberá moverse a la ruta PERSONAS_ORIGEN_COPIA_PATH, sobrescribiéndose el destino en caso de existir ya.
        try {
            Files.move(PERSONAS_ORIGEN_PATH, PERSONAS_ORIGEN_COPIA_PATH, REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(Tarea01_1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ArrayList<Persona> personasRecuperadas = new ArrayList<>();
        IPersistencia daop = new DataIOPersistencia();
        if (Files.exists(PERSONAS_ORIGEN_PATH)) {

            cribarBorrados();
            personasRecuperadas = daop.leerTodo(PERSONAS_DESTINO_PATH.toString());
            int contador = 1;
            for (Persona p : personasRecuperadas) {
                System.out.println("Persona recuperada " + contador + ": " + p);
                contador++;
            }

        } else {
            System.out.println("No existe el fichero en la ruta: " + PERSONAS_ORIGEN_PATH);
        }

    }
}
