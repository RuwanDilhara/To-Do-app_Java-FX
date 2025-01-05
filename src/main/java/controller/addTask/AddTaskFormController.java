package controller.addTask;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.AddTaskTable;
import javafx.event.ActionEvent;

import java.net.URL;

import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AddTaskFormController implements Initializable {

    @FXML
    public TableView<AddTaskTable> tblTaskTable;

    @FXML
    public TableColumn colTaskId;

    @FXML
    public Label lbltaskID;

    @FXML
    private TableColumn<?, ?> colCheckBox;

    @FXML
    private TableColumn<?, ?> colCompleteDate;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private DatePicker completeDate;

    @FXML
    private TextArea txtDescription;

    @FXML
    private TextField txtTitle;

    @FXML
    private TextField txtCategory;

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    void btnAddOnAction(javafx.event.ActionEvent actionEvent) {
        try {
            boolean isAdded = AddTaskController.getInstance().addTask(
                    new AddTaskTable(
                            1,
                            txtTitle.getText(),
                            txtDescription.getText(),
                            completeDate.getValue(),
                            txtCategory.getText(),
                            new CheckBox()
                    ));

            new Alert(Alert.AlertType.INFORMATION,
                    isAdded ? "Task Added !..."
                    : "Task Not Added !...")
                    .show();

            loadTaskTable();
            txtClear();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnCompleteOnAction(ActionEvent event) {
        ObservableList<AddTaskTable> taskList = tblTaskTable.getItems();
        taskList.forEach(task -> {
            if (task.getCheckBox().isSelected()) {
                try {
                    boolean isCompleted = AddTaskController.getInstance().completeTask(task);
                    new Alert(Alert.AlertType.INFORMATION,
                            isCompleted ? "Task Completed !..."
                                    : "Task Not Completed !...").show();

                    loadTaskTable();
                    txtClear();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtClear();
    }

    public void txtClear() {
        txtTitle.clear();
        txtDescription.clear();
        completeDate.setValue(null);
        txtCategory.clear();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if (lbltaskID != null) {
            try {
                boolean isDeleted = AddTaskController
                        .getInstance()
                        .deleteTask(Integer
                                .parseInt(lbltaskID
                                        .getText()));

                new Alert(Alert.AlertType.INFORMATION,
                        isDeleted ? "Task Deleted !..."
                                : "Task Not Deleted !...").show();

                loadTaskTable();
                txtClear();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            if (lbltaskID != null) {
                boolean isUpdated = AddTaskController.getInstance().updateTask(
                        new AddTaskTable(
                                Integer.parseInt(lbltaskID.getText()),
                                txtTitle.getText(),
                                txtDescription.getText(),
                                completeDate.getValue(),
                                txtCategory.getText(),
                                new CheckBox()
                        ));
                new Alert(Alert.AlertType.INFORMATION,
                        isUpdated ? "Task Updated !..." :
                                "Task Not Updated !...").show();

                loadTaskTable();
                txtClear();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadTaskTable();
    }

    public void loadTaskTable() {
        ObservableList<AddTaskTable> taskListObservableList = FXCollections
                .observableArrayList();
        try {
            List<AddTaskTable> all = AddTaskController.getInstance().getAll();
            taskListObservableList.addAll(all);
            tblTaskTable.setItems(taskListObservableList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void selectTask(AddTaskTable task) {
        txtTitle.setText(task.getTitle());
        txtDescription.setText(task.getDescription());
        completeDate.setValue(task.getCompleteDate());
        txtCategory.setText(task.getCategory());
        lbltaskID.setText(task.getTaskId().toString());
        System.out.println(task.getCheckBox().isSelected());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCheckBox.setCellValueFactory(new PropertyValueFactory<>("checkBox"));
        colTaskId.setCellValueFactory(new PropertyValueFactory<>("taskId"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colCompleteDate.setCellValueFactory(new PropertyValueFactory<>("completeDate"));

        loadTaskTable();

        tblTaskTable.getSelectionModel()
                .selectedItemProperty()
                .addListener((observableValue, o, task) -> {
                    if (task != null) {
                        selectTask(task);
                    }
                });
    }
}
