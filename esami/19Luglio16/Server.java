import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

/* SPECIFICHE
 * massimo 4 client (?)
 * a ogni richiesta di connessione stampa notifica + indirizzo
 * operazione non ammessa(non + - * /)-> chiede di nuovo
 * verifica se i valori ricevuti sono double
 * applica e invia il risultato
 * con . chiude la connessione
 */

class Server {
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(0);
			Socket client;
			System.out.println("port: " + ss.getLocalPort());
			ss.setSoTimeout(60 * 1000);
			byte[] buffer = new byte[100];
			// finchè ci sono client accetta
			while (true) {
				client = ss.accept();
				System.out.println(client.getInetAddress().toString() + " ha avviato una connessione");
				InputStream in = client.getInputStream();
				OutputStream out = client.getOutputStream();
				do {
					try {
						// leggi operatore
						String op = getOperator(in, out, buffer);
						if (op == null)
							break; // falso solo se uguale a null
						// leggi numero
						Double first = getNumber(in, out, buffer);
						if (first == null)
							break; // falso solo se uguale a null
						// leggi secondo numero
						Double second = getNumber(in, out, buffer);
						if (second == null)
							break; // falso solo se uguale a null
						// calcola
						switch (op) {
							case "+":
								write("" + (first + second), out);
								break;
							case "-":
								write("" + (first - second), out);
								break;
							case "*":
								write("" + (first * second), out);
								break;
							case "/":
								write("" + (first / second), out);
								break;
						}
					} catch (IOException i) {
						break;
					}
				} while (true);
				client.close();
			}
		} catch (SocketTimeoutException ste) {
			System.out.println("nessuna richiesta rilevata, spegnimento...");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static String getOperator(InputStream in, OutputStream out, byte[] buffer) throws IOException {
		String input;
		while (true) {
			input = read(in, buffer).trim();
			switch (input) {
				case "+":
				case "-":
				case "*":
				case "/":
					write("ok", out);
					return input;
				case ".":
					return null;
				default:
					write("no", out);
					break;
			}
		}
	}

	private static Double getNumber(InputStream in, OutputStream out, byte[] buffer)
			throws SocketException, IOException {
		Double number;
		String input;
		while (true) {
			input = read(in, buffer).trim();
			if (input.equals("."))
				return null;
			try {
				number = Double.parseDouble(input);
				write("ok", out);
				return number;
			} catch (NumberFormatException nfe) {
				write("no", out);
			}
		}
	}

	private static void write(String s, OutputStream out) throws IOException, SocketException {
		try {
			out.write(s.getBytes());
		} catch (Exception e) {
			throw e;

		}
	}

	private static String read(InputStream in, byte[] buffer) throws SocketException, IOException {
		int nByte;
		try {
			nByte = in.read(buffer);
		} catch (SocketException e) {
			throw e;
		} catch (IOException i) {
			throw i;
		}

		return new String(buffer, 0, nByte);
	}
}
