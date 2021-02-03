import java.net.ServerSocket;

class MultithreadServer {
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(0);
			System.out.println("porta: " + ss.getLocalPort());

			while (true) {
				Thread t = new TCPServerThread(ss.accept());
				t.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
