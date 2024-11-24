package dad.hipotecacalc.app;

import dad.hipotecacalc.controllers.RootController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HipotecaCalcApp extends Application {

    public void start (Stage primaryStage) {

        RootController rootController = new RootController();

        primaryStage.setTitle("HipotecaCalc");
        primaryStage.setScene(new Scene(rootController.getRoot()));
        primaryStage.show();
    }
}
