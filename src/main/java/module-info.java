module worldofzult.presentation {
    requires javafx.controls;
    requires javafx.fxml;


    opens worldofzult.presentation to javafx.fxml;
    exports worldofzult.presentation;
}