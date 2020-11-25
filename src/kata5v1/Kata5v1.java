package kata5v1;

import java.sql.*;
import kata5v1.persistence.PeopleLoader;
import kata5v1.persistence.sql.SqlitePeopleLoader;
import kata5v1.ui.persistence.HistogramDisplay;
import kata5v1.ui.swing.SwingHistogramDisplay;
import kata5v1.model.Histogram;
import kata5v1.model.Person;

public class Kata5v1 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        PeopleLoader peopleLoader = new SqlitePeopleLoader(); // Cargador de personas
        Histogram<String> histogram = new Histogram();
        for(Person p : peopleLoader.load()){
            histogram.increment(p.getEmailDomain());
        }
        HistogramDisplay histogramDisplay = new SwingHistogramDisplay("HISTOGRAM...", histogram);
        histogramDisplay.execute();
    }
}