//package controller;
//
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.scene.layout.VBox;
//
//public class Todo_view_Controller {
//
//    @FXML
//    private TextField taskInput;
//
//    @FXML
//    private DatePicker deadlinePicker;
//
//    @FXML
//    private ChoiceBox<String> priorityBox;
//
//    @FXML
//    private ChoiceBox<String> categoryBox;
//
//    @FXML
//    private VBox taskListContainer;
//
//    @FXML
//    public void addTask() {
//        String taskTitle = taskInput.getText();
//        String deadline = deadlinePicker.getValue() != null ? deadlinePicker.getValue().toString() : "No deadline";
//        String priority = priorityBox.getValue() != null ? priorityBox.getValue() : "None";
//        String category = categoryBox.getValue() != null ? categoryBox.getValue() : "General";
//
//        if (!taskTitle.isEmpty()) {
//            Label taskLabel = new Label(taskTitle + " - " + category + " | Priority: " + priority + " | Due: " + deadline);
//            taskLabel.setStyle("-fx-font-size: 14px; -fx-padding: 10; -fx-border-color: #ccc; -fx-border-radius: 5; -fx-background-radius: 5; -fx-background-color: #fff;");
//            taskListContainer.getChildren().add(taskLabel);
//
//            taskInput.clear();
//            deadlinePicker.setValue(null);
//            priorityBox.setValue(null);
//            categoryBox.setValue(null);
//        }
//    }
//
//    @FXML
//    public void clearAllTasks() {
//        taskListContainer.getChildren().clear();
//    }
//
//    @FXML
//    public void removeCompletedTasks() {
//        taskListContainer.getChildren().removeIf(node -> node instanceof Label);
//    }
//
//    @FXML
//    public void syncTasks() {
//        // Add sync logic here (e.g., save to database or file).
//        System.out.println("Tasks synced!");
//    }
//}
package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;


public class TodoMainController {
    @FXML
    private StackPane stcBodyPANE;

    @FXML
    private AnchorPane ancAddTask;

    @FXML
    void btnAddTaskOnAction(ActionEvent event) {
        try {
            loadPage("/view/add_task_form.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnViewOnAction(ActionEvent event) {
        try {
            loadPage("/view/view_task.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void loadPage(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
        stcBodyPANE.getChildren().clear();
        stcBodyPANE.getChildren().add(fxmlLoader.load());
    }

}
