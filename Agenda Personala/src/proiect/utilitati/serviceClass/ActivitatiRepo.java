//package proiect.utilitati.serviceClass;
//
//import proiect.DBConfiguration.DataBaseConfiguration;
//import proiect.activitati.Activitate;
//import proiect.activitati.ActivitatiExtrascolare;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//public class ActivitatiRepo{
////    lista in care retin activitatile
//    ArrayList<Activitate> activitati = new ArrayList<Activitate>();
//    public void listeazaActivitati(int UserId) throws SQLException {
////        --------- listam activitatile pentru un anumit USER dat prin ID-ul sau
//        String selectSql = "SELECT * FROM activitati where user_id = ?";
////        ne conectam la baza de date
//        Connection databaseConnection = DataBaseConfiguration.getDatabaseConnection();
//        try {
//            PreparedStatement preparedStatement  =databaseConnection.prepareStatement(selectSql);
//            preparedStatement.setInt(1, UserId);
//
////            rezultatul
//            ResultSet resultSet = preparedStatement.executeQuery();
//            return mapToActivitate(resultSet);
//        }
//    }
//
//    private Activitate mapToActivitate(ResultSet resultSet) throws SQLException {
//        if (resultSet.next()) {
//
//        }
//    }
//}
