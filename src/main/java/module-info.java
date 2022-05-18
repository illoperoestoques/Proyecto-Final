module Miguel.a_proyectoprueba {
	requires java.xml;
    requires java.xml.bind;
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	requires java.desktop;
	requires javafx.base;

    opens Miguel.a_proyectoprueba to javafx.fxml;
    opens Utils to java.xml.bind;
    exports Miguel.a_proyectoprueba;
}
