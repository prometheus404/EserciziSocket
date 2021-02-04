import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Utente {
	public static void main(String[] args) {
		int buffLength = 100;
		byte[] buffer = new byte[buffLength];
		// leggi la porta da args[0]
		int port = 0;
		try {
			port = Integer.parseInt(args[0]);
		} catch (NumberFormatException n) {
			System.out.println(args[0] + " non e' un numero");
			System.exit(-1);
		} catch (ArrayIndexOutOfBoundsException a) {
			System.out.println("Specificare il numero di porta");
			System.exit(-1);
		}
		Scanner sc = new Scanner(System.in);
		String treno;
		try {
			InetAddress serverAddress = InetAddress.getLocalHost();
			DatagramSocket ds = new DatagramSocket();
			DatagramPacket dpSend, dpReceive;
			dpReceive = new DatagramPacket(buffer, buffLength);
			do {
				// leggi codice treno
				System.out.println("inserire codice treno");
				treno = sc.nextLine();
				if (treno.equals("."))// non vedo perche' inviare al server qualcosa di inutile
					break;
				// prepara paccetto
				dpSend = new DatagramPacket(treno.getBytes(), treno.length(), serverAddress, port);
				ds.send(dpSend);
				// risposta
				ds.receive(dpReceive);
				System.out.println(new String(dpReceive.getData(), 0, dpReceive.getLength()));
			} while (true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
