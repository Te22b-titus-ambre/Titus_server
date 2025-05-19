import java.io.*;
import java.net.*;

public class Titus_Server {
    public static void main(String[] args) {
        int port = 5000;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servern är igång och väntar på klient...");

            while (true) {
                try (
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
                ) {
                    String expression = in.readLine();
                    System.out.println("Mottaget: " + expression);

                    String response = calculateAddition(expression);
                    out.println(response);
                }
            }

        } catch (IOException e) {
            System.out.println("Fel med servern: " + e.getMessage());
        }
    }

    private static String calculateAddition(String expr) {
        expr = expr.replaceAll("\\s+", ""); // Tar bort mellanslag

        if (!expr.contains("+")) {
            return "Fel: skriv ett tal i formatet a+b";
        }

        String[] parts = expr.split("\\+");
        if (parts.length != 2) {
            return "Fel: endast en '+' tillåten.";
        }

        int a = Integer.parseInt(parts[0]);
        int b = Integer.parseInt(parts[1]);
        int sum = a + b;

        return "Summan av " + expr + " är " + sum;
    }
}
