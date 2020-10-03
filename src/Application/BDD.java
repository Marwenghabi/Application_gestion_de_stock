package Application;

import java.net.Socket;
import java.sql.*;

public class BDD {
    //les declaration

    Connection connection;
    Statement statement;
    String SQL;

    String url;
    String username;
    String password;
    Socket client;
    int Port;
    String Host;

    public BDD(String url, String username, String password, Socket client, int Port, String Host) {

        this.url = url;
        this.username = username;
        this.password = password;
        this.client = client;
        this.Port = Port;
        this.Host = Host;
    }
    //fonction pour faire une connection au basede donnee

    public BDD(String HOST_DB, String USERNAME_DB, String PASSWORD_DB, String IPHOST, int PORT) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Connection connexionDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.err.println(e.getMessage()); // e.gestmessage est pour  afficher ou se trouve le probleme exactement , afficher le erreur err 
        }
        return connection;
    }

    //fct ^pour fermer cnx de DB
    public Connection Closeconnexion() {
        try {
            connection.close();

        } catch (Exception e) {
            System.err.println(e); // e.gestmessage est pour  afficher ou se trouve le probleme exactement , afficher le erreur err 
        }
        return connection;
    }
// pour l'excution de requete 

    public ResultSet exécutionQuery(String sql) {
        connexionDatabase();
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            System.out.println(sql);
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return resultSet;
    }
//pour execute la requete update 

    public String exécutionUpdate(String sql) {

        connexionDatabase();
        String result = "";
        try {

            statement = connection.createStatement();
            statement.executeUpdate(sql);
            result = sql;
        } catch (SQLException ex) {
            result = ex.toString();
        }
        return result;
    }

    // pour afficher tous 
    public ResultSet querySelectALL(String nomTable) {
        connexionDatabase();
        SQL = "SELECT* FROM " + nomTable;
        System.out.println(SQL);
        return this.exécutionQuery(SQL);
    }
//fction pour afficher tous avec de parameter ('etat)

    public ResultSet querySelectALL(String nomTable, String état) {
        connexionDatabase();
        SQL = "SELECT* FROM " + nomTable + "WHERE" + état;
        System.out.println(SQL);
        return this.exécutionQuery(SQL);
    }
//

    public ResultSet querySelect(String[] nomColonne, String nomTable) {
        connexionDatabase();
        int i;
        SQL = "SELECT";
        for (i = 0; 1 <= nomColonne.length - 1; i++) {
            SQL += nomColonne[i];

            if (i < nomColonne.length - 1) {
                SQL += ",";
            }
        }
        SQL += "FROM" + nomTable;
        return this.exécutionQuery(SQL);
    }

    //insert
    public String queryInsert(String nomTable, String[] contenuTableau) {
        connexionDatabase();
        int i;
        SQL = "INSERT INTO" + nomTable + "VALUE (";
        for (i = 0; 1 <= contenuTableau.length - 1; i++) {
            SQL += "'" + contenuTableau[i] + "'";
            if (i < contenuTableau.length - 1) {
                SQL += ",";
            }
        }
        SQL += ")";
        return this.exécutionUpdate(SQL);
    }

    //
    public String queryInsert(String nomTable, String[] nomColonne, String[] contenuTableau) {
        connexionDatabase();
        int i;
        SQL = "INSERT INTO" + nomTable + " (";
        for (i = 0; 1 <= nomColonne.length - 1; i++) {
            SQL += "'" + nomColonne[i];
            if (i < nomColonne.length - 1) {
                SQL += ",";
            }
        }
        SQL += ") VALUES (";
        for (i = 0; 1 <= contenuTableau.length - 1; i++) {
            SQL += "'" + contenuTableau[i] + "'";
            if (i < contenuTableau.length - 1) {
                SQL += ",";
            }

        }
         SQL += ")";
        return this.exécutionUpdate(SQL);
    }
    //
    public String queryUpdate(String nomTable,String[] nomColonne, String[] contenuTableau, String état) {
        connexionDatabase();
        int i;
        SQL = "UPDATE" + nomTable + "SET";
        for(i = 0;1 <= nomColonne.length - 1; i++){
            SQL +=  nomColonne[i] +"'" +contenuTableau[i]+"'";
            if (i < nomColonne.length - 1) {
                SQL += ",";
            }
        }
        
        SQL += "WHERE"+ état;
       return this.exécutionUpdate(SQL);
           
         
    }
    //fonction pour la requete supprimer sans parameter
    public String queryDelete (String nomTable){
        connexionDatabase();
        SQL="DELETE FORM"+ nomTable;
        return this.exécutionUpdate(SQL);
    }
    
    
    
    // fction supprimer avec parameter
    public String queryDelete (String nomTable,String état){
        connexionDatabase();
        SQL=état+ "DELETE FORM"+ nomTable+ "WHERE";
        return this.exécutionUpdate(SQL);
    }
    
}
  
