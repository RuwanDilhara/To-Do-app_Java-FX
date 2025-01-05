import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Starter extends Application {

    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("view/todo-view.fxml"))));
        stage.setTitle("ToDo-App");
        stage.getIcons().add(new Image("img/icon/TD_Logo new.png"));
        stage.setResizable(false);
        stage.show();
    }
}
