import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.function.Consumer;

public class Client {
  public static void main(String[] args) {
    String host = "netology.homework";
    int port = 8080;
    Consumer<String> println = System.out::println;

    try (Socket clientSocket = new Socket(host, port);
         PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
         BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

      String resp = in.readLine();
      while (resp != null) {
        switch (resp) {
          case "Write your name":
            out.println("Aleksey");
            break;
          case "Are you child? (yes/no)":
            out.println("yes");
            break;
        }
        println.accept(resp);
        resp = in.readLine();
      }
    } catch (UnknownHostException e) {
      println.accept("Неизвестный хост. Ошибка: " + e.getMessage());
    } catch (IOException e) {
      println.accept("Ошибка ввода/вывода: " + e.getMessage());
    }
  }
}
