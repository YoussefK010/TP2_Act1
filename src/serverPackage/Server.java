package serverPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        System.out.println("Je suis un serveur en attente de la connexion d'un client");
        Socket socket = serverSocket.accept();
        System.out.println("Un client est connecte");
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        String ligne1 = in.readLine();
        String ligne2 = in.readLine();
        String ligne3 = in.readLine();
        String resultat;
        try {
            int operande1 = Integer.parseInt(ligne1);
            char operateur = ligne2.charAt(0);
            int operande2 = Integer.parseInt(ligne3);
            switch (operateur) {
                case '+': resultat = String.valueOf(operande1 + operande2); break;
                case '-': resultat = String.valueOf(operande1 - operande2); break;
                case '*': resultat = String.valueOf(operande1 * operande2); break;
                case '/':
                    resultat = (operande2 == 0) ? "Erreur : division par zero"
                            : String.valueOf((double) operande1 / operande2);
                    break;
                default:  resultat = "Erreur : operateur inconnu";
            }
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            resultat = "Erreur : operation invalide";
        }
        out.println(resultat);
        System.out.println("Resultat : " + ligne1 + " " + ligne2 + " " + ligne3 + " = " + resultat);
        in.close();
        out.close();
        socket.close();
        serverSocket.close();
        System.out.println("Connexion terminee");
    }
}
