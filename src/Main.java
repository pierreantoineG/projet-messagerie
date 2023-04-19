import Accueil.fenetre;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        System.out.println("Good good");
        fenetre fenetre = new fenetre();

    }
}
