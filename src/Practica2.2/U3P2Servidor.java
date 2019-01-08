import java.io.*;
import java.net.*;

//Servidor espera conexiones a las que atenderá con un segundo socket
//Servidor saludará con frase "Hola cliente"
class Servidor {

	// puerto espera conexiones
	static final int PUERTO = 5001;

	public Servidor() {

		try {
			// crea socket y espera
			ServerSocket skServidor = new ServerSocket(PUERTO);
			System.out.println("Esperando conexi�n...");

			while (true) {
				// Primitiva accept() crea nuevo socket skCliente
				// skCliente atiene al cliente
				Socket skCliente = skServidor.accept(); // Crea objeto
				System.out.println("Conectado.");

				// CREO LOS FLUJOS DE ENTRADA Y SALIDA PARA EL SOCKET
				// recojo flujo de datos del socket
				// System.out.println("Leo del cliente ");
				InputStream is = skCliente.getInputStream();
				// asocio flujo de datos flujo de tipo DataInputStream
				DataInputStream dis = new DataInputStream(is);
				// asocio flujo salida de datos al socket
				OutputStream os = skCliente.getOutputStream();
				// asocio flujo de datos flujo de tipo DataOutputStream
				DataOutputStream dos = new DataOutputStream(os);
				String cadena;
				do {
					cadena = dis.readUTF();
					System.out.println("Cliente: " + cadena);
					switch (cadena) {
					case "Hola":
						dos.writeUTF("Hola soy el servidor");
						break;
					case "�Como estas?":
						dos.writeUTF("Muy bien");
						break;
					case "Adios":
						dos.writeUTF("Hasta luego");
						break;
					default:
						dos.writeUTF("Cadena no reconocida por el servidor");
						break;
					}
				} while (!cadena.equals("Adios"));

				// cierro conexión
				System.out.println("Cerrando conexi�n...");
				skCliente.close();
			} // fin while

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] arg) {
		new Servidor();
	}
}
