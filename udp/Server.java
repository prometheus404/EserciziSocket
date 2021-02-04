import java.net.DatagramSocket;
import java.net.DatagramPacket;

class Server {
	public static void main(String[] args) {
		int bufSize = 100;
		try {
			DatagramSocket ds = new DatagramSocket();
			System.out.println("port: " + ds.getLocalPort());
			byte[] buf = new byte[bufSize];
			DatagramPacket dp;
			String str;
			do {
				dp = new DatagramPacket(buf, 0, bufSize);
				ds.receive(dp);
				str = new String(dp.getData(), 0, dp.getLength());
				System.out.println(str);
			} while (!str.equals("."));
			ds.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
