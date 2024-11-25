package dad.hipotecacalc.app;

import dad.hipotecacalc.controllers.RootController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class HipotecaCalcApp extends Application {

    public void start (Stage primaryStage) {

        Image icon = new Image(getClass().getResourceAsStream("/images/hipotecalc.png"));

        primaryStage.getIcons().add(icon);

        RootController rootController = new RootController();

        primaryStage.setTitle("HipotecaCalc");
        primaryStage.setScene(new Scene(rootController.getRoot()));
        primaryStage.show();
    }
}
