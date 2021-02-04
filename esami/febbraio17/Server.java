import java.net.DatagramPacket;
import java.net.DatagramSocket;

class Server {
	public static void main(String[] args) {
		int nTreni = 100;
		int[] ritardo = new int[nTreni];
		try {
			DatagramSocket ds = new DatagramSocket();
			System.out.println("porta: " + ds.getLocalPort());
			DatagramPacket dpReceive, dpSend;
			int buffLength = 100;
			byte[] buffer = new byte[buffLength];
			dpReceive = new DatagramPacket(buffer, buffLength);

			while (true) {
				ds.receive(dpReceive);
				String[] msg = new String(dpReceive.getData(), 0, dpReceive.getLength()).split(" ");
				String reply = "";
				if (msg.length == 3) {
					reply = "entry inserita correttamente";
					try {
						ritardo[Integer.parseInt(msg[1])] = Integer.parseInt(msg[2]);
					} catch (Exception e) {
						reply = "errore durante l'elaborazione della richiesta";
					}
					System.out.println("il treno " + msg[1] + " e' in ritardo di " + msg[2]);
				}
				if (msg.length == 1) {
					try {
						reply = "ritardo: " + ritardo[Integer.parseInt(msg[0])];
					} catch (Exception e) {
						reply = "errore durante l'elaborazione della richiesta";
					}
				}

				dpSend = new DatagramPacket(reply.getBytes(), reply.length(), dpReceive.getAddress(),
						dpReceive.getPort());
				ds.send(dpSend);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
