module worldofzult.presentation {
    requires javafx.controls;
    requires javafx.fxml;


    opens worldofzult.presentation to javafx.fxml;
    exports worldofzult.presentation;
    exports worldofzult.presentation.GUIcontrollers;
    opens worldofzult.presentation.GUIcontrollers to javafx.fxml;
}