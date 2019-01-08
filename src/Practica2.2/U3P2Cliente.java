import java.io.*;
import java.net.*;
import java.util.Scanner;

class Cliente {
	// nombre máquina y puerto
	static final String HOST = "localhost";
	static final int PUERTO = 5000;

	public Cliente(String host) {
		try {
			// se crea el socket
			Socket skServidor = new Socket(host, PUERTO);
			System.out.println("Conectado al servidor...");
			System.out.println("Conectado.");

			// CREO LOS FLUJOS DE ENTRADA Y SALIDA PARA EL SOCKET
			// recojo flujo de datos del socket
			InputStream is = skServidor.getInputStream();
			// asocio flujo de datos flujo de tipo DataInputStream
			DataInputStream dis = new DataInputStream(is);
			// asocio flujo salida de datos al socket
			OutputStream os = skServidor.getOutputStream();
			// asocio flujo de datos flujo de tipo DataOutputStream
			DataOutputStream dos = new DataOutputStream(os);
			
			String cadena;
			Scanner in;
			do {
				in = new Scanner(System.in);
				//System.out.println("Introduce lo que quieras enviar al servidor");
				cadena = in.nextLine();
				dos.writeUTF(cadena);
				System.out.println("Servidor: " + dis.readUTF());
			} while (!cadena.equals("Adios"));

			// Cierro el socket
			System.out.println("Cerrando conexi�n...");
			skServidor.close();
		} catch (Exception e) {
			System.out.println("Error: no se ha podido conectar a " + host + ":" + PUERTO);
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] arg) {
		if (arg.length != 1) {
			System.out.println("Error: debes introducir un par�metro que es el HOST");
			return;
		}

		new Cliente("localhost");
		return;

	}
}
