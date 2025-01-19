import java.sql.*;

public class App {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:postgresql://localhost:--/----";
        String username = "--";
        String password = "--";
        try {
            // create conncetion
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement statement = conn.createStatement();
            PreparedStatement preparedStatement;
            System.out.println("Connection established");

            // passing without parameters birthlocation
            String birthLocation = "INSERT INTO \"BirthLocation\" (country, city, state) VALUES ('Switzerland','Emmen', 'Lucerne' )";
            statement.executeUpdate(birthLocation);

            // passing with parameters birthlocation
            String birthlocation2 = "INSERT INTO \"BirthLocation\" (country, city, state) VALUES (?, ?, ?)";
            preparedStatement = conn.prepareStatement(birthlocation2, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, "countrytest");
            preparedStatement.setString(2, "citytest");
            preparedStatement.setString(3, "statetest");
            preparedStatement.executeUpdate();
            preparedStatement.executeUpdate();
            int id_BirthLocation = 0;
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id_BirthLocation = generatedKeys.getInt(1);
                System.out.println("Generated id_Birthlocation : " + id_BirthLocation);
            }
            // ************************************************************** */
            // passing without Cinema
            String Cinema = "INSERT INTO \"Cinema\" (cinema_name, location, type) VALUES ('cineplex','oshawa', 'average' )";
            statement.executeUpdate(Cinema);

            // passing with parameters
            String Cinema2 = "INSERT INTO \"Cinema\" (cinema_name, location, type) VALUES (?, ?, ?)";
            preparedStatement = conn.prepareStatement(Cinema2);

            preparedStatement.setString(1, "driveway");
            preparedStatement.setString(2, "north campus");
            preparedStatement.setString(3, "alright");
            preparedStatement.executeUpdate();

            // ResultSet generatedKeys = preparedStatement.executeQuery();
            int id_Cinema = 0;

            if (generatedKeys.next()) {
                id_Cinema = generatedKeys.getInt("id_Cinema");
                System.out.println("Generated id_Cinema: " + id_Cinema);
            }

            // ************************************************************** */

            // passing without parameters
            String Genre = "INSERT INTO \"Genre\" (type) VALUES ('comedy' )";
            statement.executeUpdate(Genre);

            // passing with parameters
            String Genre2 = "INSERT INTO \"Genre\" (type) VALUES (?)";
            preparedStatement = conn.prepareStatement(Genre2);

            preparedStatement.setString(1, "horror");
            preparedStatement.executeUpdate();

            // ************************************************************** */

            // passing without Cinema
            String University = "INSERT INTO \"University\" (name, is_private, color) VALUES ('OTU','false', 'Blue' )";
            statement.executeUpdate(University);

            // passing with parameters
            String University2 = "INSERT INTO \"University\" (name, is_private, color) VALUES (?, ?, ?)";
            preparedStatement = conn.prepareStatement(University2);

            preparedStatement.setString(1, "McMaster");
            preparedStatement.setString(2, "False");
            preparedStatement.setString(3, "Red");
            preparedStatement.executeUpdate();

            generatedKeys = preparedStatement.getGeneratedKeys();
            int id_University = 0;
            if (generatedKeys.next()) {
                id_University = generatedKeys.getInt(1);
                System.out.println("Generated id_Birthlocation : " + id_University);
            }

            // ************************************************************** */

            // passing without Cinema
            String Ticket1 = "INSERT INTO \"Ticket\" (price, id_Cinema) VALUES (50.0, (SELECT id_Cinema FROM \\\"Cinema\\\" WHERE cinema_name = 'cineplex')";
            statement.executeUpdate(Ticket1);

            // passing with parameters
            String Ticket2 = "INSERT INTO \"Ticket\" (price, id_Cinema) VALUES (?, ?)";
            preparedStatement = conn.prepareStatement(Ticket2);

            preparedStatement.setDouble(1, 50.5);
            preparedStatement.setInt(2, id_Cinema);
            preparedStatement.executeUpdate();

            // ************************************************************** */

            // passing without Cinema
            String movie1 = "INSERT INTO \"Movie\" (title, release_time, date, rating, budget, gross) VALUES ('From','01:00:00', '2020-02-10', '9.9', '600000' )";
            statement.executeUpdate(movie1);

            // passing with parameters
            String movie2 = "INSERT INTO \"Movie\" (title, release_time, date, rating, budget, gross) VALUES (?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = conn.prepareStatement(movie2);

            preparedStatement.setString(1, "Fall Guy");
            preparedStatement.setTime(2, Time.valueOf("20:09:01"));
            preparedStatement.setDate(3, Date.valueOf("2019-09-01"));
            preparedStatement.setDouble(1, 9.9); // Error: You should use setDouble here, not setInt.
            preparedStatement.setDouble(2, 50000);
            preparedStatement.setDouble(3, 100000);

            preparedStatement.executeUpdate();

            generatedKeys = preparedStatement.getGeneratedKeys();
            int id_Movie = 0;
            if (generatedKeys.next()) {
                id_Movie = generatedKeys.getInt(1);
                System.out.println("Generated id_Birthlocation : " + id_Movie);
            }

            // ************************************************************** */

            // passing without Cinema
            String showtime1 = "INSERT INTO \"ShowTime\" (name, is_private, color) VALUES ('OTU','false', 'Blue' )";
            statement.executeUpdate(showtime1);

            // passing with parameters
            String showtime2 = "INSERT INTO \"ShowTime\" (name, is_private, color) VALUES (?, ?, ?)";
            preparedStatement = conn.prepareStatement(showtime2);

            preparedStatement.setString(1, "McMaster");
            preparedStatement.setString(2, "False");
            preparedStatement.setString(3, "Red");
            preparedStatement.executeUpdate();

            // Actor table

            String actor1 = "INSERT INTO \"Actor\" (first_name, surname, year_of_birth, id_BirthLocation, eye_color) VALUES ('John', 'Doe', 1985, 1, 'Blue')";

            statement.executeUpdate(actor1);

            String actor2 = "INSERT INTO \"Actor\" (first_name, surname, year_of_birth, id_BirthLocation, eye_color) VALUES (?, ?, ?, ?, ?)";

            preparedStatement = conn.prepareStatement(actor2);

            preparedStatement.setString(1, "Jane");

            preparedStatement.setString(2, "Smith");

            preparedStatement.setInt(3, 1990);

            preparedStatement.setInt(4, id_BirthLocation);

            preparedStatement.setString(5, "Green");

            preparedStatement.executeUpdate();
            int Actor = 0;
            if (generatedKeys.next()) {
                Actor = generatedKeys.getInt(1);
                System.out.println("Generated id_Birthlocation : " + Actor);
            }

            // ************************************************************** */

            // Award table

            String award1 = "INSERT INTO \"Award\" (award_name, id_Movie) VALUES ('Best Picture', 1)";
            statement.executeUpdate(award1);
            String award2 = "INSERT INTO \"Award\" (award_name, id_Movie) VALUES (?, ?)";

            preparedStatement = conn.prepareStatement(award2);
            preparedStatement.setString(1, "Best Director");
            preparedStatement.setInt(2, id_Movie);

            preparedStatement.executeUpdate();

            generatedKeys = preparedStatement.getGeneratedKeys();
            int award_id_Award = 0;
            if (generatedKeys.next()) {
                id_Movie = generatedKeys.getInt(1);
                System.out.println("Generated id_Birthlocation : " + award_id_Award);
            }

            // ************************************************************** */

            // Category table

            String category1 = "INSERT INTO \"Category\" (category_name, award_id_Award, id_Movie_Award) VALUES ('Drama', 1, 1)";
            statement.executeUpdate(category1);
            String category2 = "INSERT INTO \"Category\" (category_name, award_id_Award, id_Movie_Award) VALUES (?, ?, ?)";

            preparedStatement = conn.prepareStatement(category2);
            preparedStatement.setString(1, "Action");
            preparedStatement.setInt(2, award_id_Award);
            preparedStatement.setInt(3, award_id_Award);
            preparedStatement.executeUpdate();

            // ************************************************************** */

            // Department table

            String department1 = "INSERT INTO \"Department\" (id_University, name) VALUES (1, 'Computer Science')";
            statement.executeUpdate(department1);
            String department2 = "INSERT INTO \"Department\" (id_University, name) VALUES (?, ?)";

            preparedStatement = conn.prepareStatement(department2);
            preparedStatement.setInt(1, id_University);
            preparedStatement.setString(2, "Engineering");

            preparedStatement.executeUpdate();

            // ************************************************************** */

            // Director table

            String director1 = "INSERT INTO \"Director\" (first_name, surname, year_of_birth, id_BirthLocation, id_Movie, id_University) VALUES ('Christopher', 'Nolan', 1970, 1, 1, 1)";
            statement.executeUpdate(director1);
            String director2 = "INSERT INTO \"Director\" (first_name, surname, year_of_birth, id_BirthLocation, id_Movie, id_University) VALUES (?, ?, ?, ?, ?, ?)";

            preparedStatement = conn.prepareStatement(director2);
            preparedStatement.setString(1, "Steven");
            preparedStatement.setString(2, "Spielberg");
            preparedStatement.setInt(3, 1946);
            preparedStatement.setInt(4, id_BirthLocation);
            preparedStatement.setInt(5, id_Movie);
            preparedStatement.setInt(6, id_University);

            preparedStatement.executeUpdate();

            // ************************************************************** */

            // MovieActor table

            String movieActor1 = "INSERT INTO \"MovieActor\" (id_Movie, id_Actor) VALUES (1, 1)";
            statement.executeUpdate(movieActor1);
            String movieActor2 = "INSERT INTO \"MovieActor\" (id_Movie, id_Actor) VALUES (?, ?)";
            preparedStatement = conn.prepareStatement(movieActor2);
            preparedStatement.setInt(1, id_Movie);
            preparedStatement.setInt(2, Actor);

            preparedStatement.executeUpdate();

            // ************************************************************** */
            String genreQuery = "INSERT INTO \"Genre\" (genre_name) VALUES (?)";
            statement.executeUpdate(genreQuery);
            String genreQuery2 = "INSERT INTO \"Genre\" (id_Movie, id_Actor) VALUES (?, ?)";
            preparedStatement = conn.prepareStatement(genreQuery2);
            preparedStatement.setInt(1, id_Movie);
            preparedStatement.setInt(2, Actor);

            preparedStatement.executeUpdate();
            int id_Genre = 0;
            if (generatedKeys.next()) {
                id_Genre = generatedKeys.getInt(1);
                System.out.println("Generated id_Birthlocation : " + id_Genre);
            }

            // MovieGenre table

            String movieGenre1 = "INSERT INTO \"MovieGenre\" (id_Movie, id_Genre) VALUES (1, 1)";
            statement.executeUpdate(movieGenre1);
            String movieGenre2 = "INSERT INTO \"MovieGenre\" (id_Movie, id_Genre) VALUES (?, ?)";

            preparedStatement = conn.prepareStatement(movieGenre2);
            preparedStatement.setInt(1, id_Movie);
            preparedStatement.setInt(2, id_Genre);
            preparedStatement.executeUpdate();
            generatedKeys = preparedStatement.getGeneratedKeys();
            int MovieGenre = 0;
            if (generatedKeys.next()) {
                MovieGenre = generatedKeys.getInt(1);
                System.out.println("Generated id_Birthlocation : " + MovieGenre);
            }

            // Close resources

            preparedStatement.close();

            statement.close();

            conn.close();

            System.out.println("Data inserted successfully");

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
