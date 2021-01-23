import java.net.Socket;
import java.io.InputStream;
import java.net.ServerSocket;

class SingleConnectionServer {
	public static void main(String[] args) {
		int buffDim = 100;
		try {
			ServerSocket servSock = new ServerSocket(0);
			System.out.println("port: " + servSock.getLocalPort());
			Socket s = servSock.accept();
			InputStream in = s.getInputStream();
			byte[] buffer = new byte[buffDim];
			int nBytes = in.read(buffer);
			System.out.println(new String(buffer, 0, nBytes));
			servSock.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
