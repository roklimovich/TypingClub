module pl.edu.pja.s27619.gui_pro3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens pl.edu.pja.s27619.gui_pro3 to javafx.fxml;
    exports pl.edu.pja.s27619.gui_pro3;
    exports pl.edu.pja.s27619.gui_pro3.service;
    opens pl.edu.pja.s27619.gui_pro3.service to javafx.fxml;
}