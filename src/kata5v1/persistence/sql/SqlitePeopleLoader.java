package kata5v1.persistence.sql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kata5v1.persistence.PeopleLoader;
import kata5v1.model.Person;

public class SqlitePeopleLoader implements PeopleLoader {

    public SqlitePeopleLoader() {
    }

    @Override
    public List<Person> load() {
        List<Person> list = new ArrayList();
        try {
            Class.forName("org.sqlite.JDBC"); // carga dirver de sqlite
            Connection connection = DriverManager.getConnection("jdbc:sqlite:data/people.db"); //establece conexión con el driver sqlite cargado
            
            // Creamos una orden a la base de datos
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM people");
            while(set.next()){
                String name = set.getString("first_name")+set.getString("last_name");
                String address = set.getString("address");
                String email = set.getString("email");
                list.add(new Person(name, address, email));
            }
            return list;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SqlitePeopleLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
}