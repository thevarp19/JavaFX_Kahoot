module kahoot.it.project2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.media;
    requires javafx.graphics;

    exports kahoot.it.project2;
    exports kahoot.it.project3;
    exports kahoot.it.project2.tasks;
    opens kahoot.it.project3 to javafx.fxml;
}