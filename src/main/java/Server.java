import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server {
  private static final int PORT = 8080;

  public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = new ServerSocket(PORT);
    Socket clientSocket = serverSocket.accept();
    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

    System.out.println("New connection accepted");

    out.println("Write your name");
    final String name = in.readLine();
    out.println("Are you child? (yes/no)");
    final String child = in.readLine();
    switch (child) {
      case "yes":
        out.println(String.format("Welcome to the kids area, %s! Let's play!", name));
        break;
      case "no":
        out.println(String.format("Welcome to the adult zone, %s! Have a good rest, or a good working day!", name));
        break;
    }
    clientSocket.close();
  }
}
