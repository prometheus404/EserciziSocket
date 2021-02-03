import java.io.InputStream;
import java.net.Socket;

public class TCPServerThread extends Thread {
	private Socket client;

	public TCPServerThread(Socket s) {
		client = s;
	}

	public void run() {
		int buffLen = 100;
		byte[] buffer = new byte[buffLen];
		try {
			InputStream in = client.getInputStream();
			int nByte = in.read(buffer);
			String str = new String(buffer, 0, nByte);
			System.out.println(str);
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
