package proiect.utilitati.serviceClass;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
//  daca un user exista in baza de date, i se va returna ID-ul, daca nu, se va returna 0
    public int cautaUser(String userName) {
        String selectSql = "SELECT * FROM person WHERE username = ?";
        try {
            PreparedStatement preparedStatement = DBService.getStatement(selectSql);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
//                exista deja utilizatorul in baza de date
                System.out.println("Am intrat");
                return resultSet.getInt("idPerson");}


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
 //                nu exista utilizatorul in baza de date -> returnam 0
        return 0;
    }

//    adaugare utilizator in baza de date
    public int addUserToDB(String lastName, String firstName, String email, int salary, String userName) {
//        trebuie sa vedem ce id ii atribuim
//        executam o comanda select in care se va gasi cel mai mare id din baza de date
        int maxId = 0;
        String selectIdSql = "SELECT MAX(idPerson) AS `max_id` FROM person";
        try {
            PreparedStatement preparedStatement = DBService.getStatement(selectIdSql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                maxId = resultSet.getInt("max_id");

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
//       dupa ce am gasit id-ul maxim, trebuie sa adaugam noul user in sistem
        maxId += 1;
        String insertUser = "INSERT INTO person VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = DBService.getStatement(insertUser);
            preparedStatement.setInt(1, maxId);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, firstName);
            preparedStatement.setString(4, email);
            preparedStatement.setInt(5, salary);
            preparedStatement.setString(6, userName);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
//        returnam ID-ul user-ului adaugat
        return maxId;

    }


}
