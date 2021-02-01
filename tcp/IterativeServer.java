import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

class IterativeServer {
	public static void main(String[] args) {
		int buffDim = 100;
		byte[] buffer = new byte[buffDim];

		try {
			ServerSocket ss = new ServerSocket(0);
			System.out.println("port: " + ss.getLocalPort());
			Socket client;
			while (true) {
				client = ss.accept();
				InputStream in = client.getInputStream();
				int nBytes = in.read(buffer);
				String str = new String(buffer, 0, nBytes);
				System.out.println(str);
				if (str.equals("close"))
					break;
			}
			ss.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
