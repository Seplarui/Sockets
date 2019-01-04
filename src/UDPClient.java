import java.net.*;
import java.io.*;

public class UDPClient {
    public static void main(String args[]) {
        //args recibe mensaje host destino y puerto

        DatagramSocket aSocket = null;

        //control entrada de argumentos

        if(args.length<3) {
            System.out.println("Uso: Java UDPCLIENT <message> <Host name> <Port number>");
            System.exit(1);
        }
        try{
            //proceso envio datagrama
            aSocket=new DatagramSocket();
            byte[] m= args[0].getBytes();
            InetAddress aHost= InetAddress.getByName(args[1]);
            
            int serverPort= Integer.valueOf(args[2]).intValue();

            DatagramPacket request = new DatagramPacket(m, args[0].length(),aHost, serverPort);
            aSocket.send(request);

            //proceso recepción datagrama
            byte[] buffer= new byte[1000];
            DatagramPacket reply= new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);
            System.out.println("Reply: "+ new String(reply.getData()));
        } catch(SocketException e) {
            System.out.println("Socket: "+e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: "+e.getMessage());
        }
        finally {
            if(aSocket !=null) aSocket.close();
        }
    }
}