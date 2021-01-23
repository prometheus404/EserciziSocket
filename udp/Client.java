import java.net.InetAddress;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.io.*;

public class Client {
	public static void main(String[] args) {
		// arg[0] -> porta del server
		// arg[1] -> messaggio
		DatagramSocket ds;
		try {
			InetAddress srvAddress = InetAddress.getLocalHost();
			int port = Integer.parseInt(args[0]);
			ds = new DatagramSocket();
			DatagramPacket dp = new DatagramPacket(args[1].getBytes(), args[1].length(), srvAddress, port);
			ds.send(dp);
			ds.close();
		} catch (ArrayIndexOutOfBoundsException a) {
			System.out.println("wrong arguments number");
		} catch (NumberFormatException n) {
			System.out.println(args[0] + " is not a port number");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
