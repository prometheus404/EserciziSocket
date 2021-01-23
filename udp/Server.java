import java.net.DatagramSocket;
import java.net.DatagramPacket;

class Server {
	public static void main(String[] args) {
		int bufSize = 100;
		try {
			DatagramSocket ds = new DatagramSocket();
			System.out.println("port: " + ds.getLocalPort());
			byte[] buf = new byte[bufSize];
			DatagramPacket dp = new DatagramPacket(buf, 0, bufSize);
			ds.receive(dp);
			System.out.println(new String(dp.getData()));
			ds.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
