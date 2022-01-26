module com.example.captiongenerator {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;
    requires json.simple;


    opens com.example.captiongenerator to javafx.fxml;
    exports com.example.captiongenerator;
}