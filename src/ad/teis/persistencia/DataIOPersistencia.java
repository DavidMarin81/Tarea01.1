/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ad.teis.persistencia;

import ad.teis.model.Persona;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mfernandez
 */
public class DataIOPersistencia implements IPersistencia {

    public void escribirPersonas(ArrayList<Persona> personas, String ruta) {
        try (
                //Poner el true para evitar que sobreescriba
                 FileOutputStream fos = new FileOutputStream(ruta);  DataOutputStream dos = new DataOutputStream(fos);) {
            for (Persona persona : personas) {
                dos.writeLong(persona.getId());
                dos.writeChars(persona.getDni());

                StringBuilder sb = new StringBuilder(persona.getNombre());
                sb.setLength(Persona.MAX_LENGTH_NOMBRE);
                dos.writeChars(sb.toString());

                dos.writeInt(persona.getEdad());
                dos.writeFloat(persona.getSalario());
                dos.writeBoolean(persona.isBorrado());
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataIOPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DataIOPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Persona> leerTodo(String ruta) {
        long id;
        String dni;
        String nombre;
        int edad;
        float salario;
        boolean borrado;

        Persona persona = null;
        ArrayList<Persona> personas = new ArrayList<>();
        try (
                FileInputStream fis = new FileInputStream(ruta);  
                DataInputStream dis = new DataInputStream(fis);) {
            do {
                id = dis.readLong();

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < Persona.MAX_LENGTH_DNI; i++) {
                    sb.append(dis.readChar());
                }
                dni = sb.toString();

                sb = new StringBuilder();
                for (int i = 0; i < Persona.MAX_LENGTH_NOMBRE; i++) {
                    sb.append(dis.readChar());
                }
                nombre = sb.toString();

                edad = dis.readInt();
                salario = dis.readFloat();
                borrado = dis.readBoolean();
                persona = new Persona(id, dni, edad, salario, nombre);
                persona.setBorrado(borrado);

                personas.add(persona);

            } while (true);
            
        }catch(EOFException ex) {
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataIOPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DataIOPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (personas);
    }
}
