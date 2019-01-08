import java.net.*;
import java.io.*;
import java.util.*;
public class ClienteUDP {

	public static void main(String[] args) {
		
		DatagramSocket aSocket= null;

		// if(args.length>3) {
		// 	System.out.println("Uso: java ClienteUDP <message> <Host Name> <Port Number>");
		// 	System.exit(1);
		// }

		try {
			aSocket= new DatagramSocket();
			//byte[] m=args[0].getBytes();
			System.out.println("Introducción de fecha--------");
			System.out.println("Introducción día (1/31):");
			Scanner numero= new Scanner(System.in);
			String dia;
			dia= numero.nextLine();
			
			System.out.println("Introducción mes(1/12)");
			String mes;
			mes= numero.nextLine();

			System.out.println("Introducción año(1800/2016)");
			String anyo;
			anyo = numero.nextLine();


			
			
			
			
			
			byte[] m=dia.getBytes();
			InetAddress aHost= InetAddress.getByName("127.0.0.1"); 
			int serverPort= 3000; 

			DatagramPacket request= new DatagramPacket(m, dia.length(),aHost, serverPort);
			/*DatagramPacket*/ request= new DatagramPacket(m, mes.length(), aHost, serverPort);

			aSocket.send(request);

			byte[] buffer= new byte[1000];
			DatagramPacket reply= new DatagramPacket(buffer, buffer.length);
			aSocket.receive(reply);
			System.out.println("Reply: " + new String(reply.getData()));
		} catch (SocketException e) {
			System.out.println("Socket: "+ e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: "+ e.getMessage());
		} finally {
			if (aSocket!=null) {
				aSocket.close();
			}
		}
	}
}
