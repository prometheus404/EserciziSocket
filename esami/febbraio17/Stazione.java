import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Stazione {
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
		String treno, ritardo;
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
				// leggi ritardo
				System.out.println("inserire ritardo");
				ritardo = sc.nextLine();
				if (ritardo.equals("."))
					break;
				// prepara paccetto
				dpSend = new DatagramPacket(("S " + treno + " " + ritardo).getBytes(),
						treno.length() + ritardo.length() + 3, serverAddress, port);
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
