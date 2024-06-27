import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/bourse"; // Changez votre URL de la BDD
    private static final String USER = "root"; // Changez le nom d'utilisateur
    private static final String PASSWORD = "mdp"; // Changez le mot de passe

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Assurez-vous que le pilote est dans votre classpath
            System.out.println("Driver chargé avec succès !");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion établie !");
        } catch (ClassNotFoundException e) {
            System.out.println("Pilote JDBC non trouvé !");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erreur de connexion à la base de données !");
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Connection connection = Database.getConnection();
        if (connection != null) {
            System.out.println("Connexion réussie !");
        } else {
            System.out.println("Échec de la connexion.");
        }
        Database.closeConnection(connection);
    }
}
