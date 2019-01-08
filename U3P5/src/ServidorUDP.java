
import java.net.*;
import java.io.*;

public class ServidorUDP {

	public static void main(String[] args) {

		DatagramSocket aSocket = null;

		if(args.length<1) {
			System.out.println("Uso: java ServidorUDP <Port Number>");
			System.exit(1);
		}

		try {
			int socketPuerto= Integer.valueOf(args[0]).intValue();
			aSocket= new DatagramSocket(socketPuerto);
			byte[] buffer= new byte[1000];

			while(true) {
				DatagramPacket request= new DatagramPacket(buffer, buffer.length);
				aSocket.receive(request);
				System.out.println("Recibo cliente: "+ new String(request.getData()));

				DatagramPacket reply= new DatagramPacket(request.getData(),request.getLength(),request.getAddress(), request.getPort());
				request.getPort();
				aSocket.send(reply);
			}
		} catch(SocketException e) {
			System.out.println("Socket: "+e.getMessage());
		} catch(IOException e) {
			System.out.println("IO: "+e.getMessage());
		} finally {
			if(aSocket!=null) {
				aSocket.close();
			}
		}
	}
}
