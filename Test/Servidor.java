package Test;
import java.io.*;
import java.net.*;

class Servidor {

    static final int  PUERTO=5000;
    
    public Servidor() {
        try{

            //Crea socket y espera
            ServerSocket skServidor= new ServerSocket(PUERTO);
            System.out.println("Esperando conexión...");

            while(true) {
                //Primitiva accept() crea nuevo socket skCliente
                //skCliente atiende al cliente

                Socket skCliente= skServidor.accept();
                System.out.println("Conectado");

                //Creo los flujos de entrada y salida para el socket
                //recojo flujo de datos del socket
                //System.out.println("Leo del cliente")

                InputStream is= skCliente.getInputStream();
                //asocio flujo de datos flujo de tipo DataInputStream
                DataInputStream dis= new DataInputStream(is);
                //asocio flujo de datos al socket
                OutputStream os= skCliente.getOutputStream();
                //asocio flujo de datos DataOutputStream
                DataOutputStream dos = new DataOutputStream(os);
                String cadena;

                do {
                    cadena= dis.readUTF();
                    System.out.println("Cliente: "+cadena);
                    switch(cadena) {
                        case "Hola":
                        dos.writeUTF("Hola soy el servidor");
                        break;
                        case "Como estas":
                        dos.writeUTF("Muy bien");
                        break;
                        case "Adios":
                        dos.writeUTF("Hasta luego");
                        break;
                        default:
                        dos.writeUTF("Cadena no reconocida por el servidor");
                        break;
                    }
                } while(!cadena.equals("Adios"));

                //cierro conexión
                System.out.println("Cerrando conexion...");
                skCliente.close();
            } //fin while primero
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] arg) {
        new Servidor();
    }

    
}