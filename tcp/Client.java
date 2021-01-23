import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

class Client {
	public static void main(String[] args) {
		try {
			// questo metodo va bene solo se cliet e server
			// sono sulla stessa macchina
			InetAddress serverAddress = InetAddress.getLocalHost();
			int port = Integer.parseInt(args[0]);
			Socket s = new Socket(serverAddress, port);
			OutputStream out = s.getOutputStream();
			out.write(args[1].getBytes());
			s.close();

		} catch (ArrayIndexOutOfBoundsException a) {
			System.out.println("wrong argument number");
		} catch (NumberFormatException n) {
			System.out.println(args[0] + " is not a number");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
