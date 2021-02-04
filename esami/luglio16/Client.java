import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
		int port = 0;
		try {
			port = Integer.parseInt(args[0]);
		} catch (ArrayIndexOutOfBoundsException a) {
			System.out.println("specificare la porta del server come argomento");
			System.exit(-1);
		} catch (NumberFormatException n) {
			System.out.println(args[0] + " non è un numero di porta corretto");
			System.exit(-1);
		}
		try {
			InetAddress serverAddress = InetAddress.getLocalHost();
			Socket server = new Socket(serverAddress, port);
			OutputStream out = server.getOutputStream();
			InputStream in = server.getInputStream();

			byte[] buffer = new byte[100];
			int nByte;
			String op;
			while (true) {
				// leggi op
				do {
					System.out.println("inserire operatore");
					nByte = System.in.read(buffer);
					op = new String(buffer, 0, nByte);
					out.write(op.getBytes());
					if (op.trim().equals(".")) {
						server.close();
						System.exit(0);
					}
					nByte = in.read(buffer);
					op = new String(buffer, 0, nByte);
				} while (op.equals("no"));
				// leggi numero
				do {
					System.out.println("inserire primo numero");
					nByte = System.in.read(buffer);
					out.write(buffer, 0, nByte);
					nByte = in.read(buffer);
					op = new String(buffer, 0, nByte);
				} while (op.equals("no"));
				do {
					System.out.println("inserire secondo numero");
					nByte = System.in.read(buffer);
					out.write(buffer, 0, nByte);
					nByte = in.read(buffer);
					op = new String(buffer, 0, nByte);
				} while (op.equals("no"));
				nByte = in.read(buffer);
				System.out.println(new String(buffer, 0, nByte));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
