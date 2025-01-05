package controller.viewTask;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ViewTable;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ViewTaskFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colCompleteDate;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colTaskId;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private DatePicker completeDate;

    @FXML
    private Label lblaskID;

    @FXML
    private TableView<ViewTable> tblTaskTable;

    @FXML
    private TextField txtTitle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colTaskId.setCellValueFactory(new PropertyValueFactory<>("taskId"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colCompleteDate.setCellValueFactory(new PropertyValueFactory<>("completeDate"));

        loadTable();

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        ObservableList<ViewTable> filteredObserverList = FXCollections.observableArrayList();
        try {
            List<ViewTable> completeTaskList = ViewTaskController.getInstance().getAll();
                    if (completeDate.getValue()!=null){
                        completeTaskList.forEach(task -> {
                            if (task.getTitle().equals(txtTitle.getText()) && task.getCompleteDate().toString().equals(completeDate.getValue().toString())) {
                                filteredObserverList.add(task);
                            }
                            if (task.getTitle().equals(txtTitle.getText()) && !task.getCompleteDate().toString().equals(completeDate.getValue().toString())){
                                filteredObserverList.add(task);
                            }
                            if (!task.getTitle().equals(txtTitle.getText()) && task.getCompleteDate().toString().equals(completeDate.getValue().toString())){
                                filteredObserverList.add(task);
                            }
                        });
                    }
            tblTaskTable.setItems(filteredObserverList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtTitle.clear();
        completeDate.setValue(null);
    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadTable();
    }

    public void loadTable() {
        ObservableList<ViewTable> completeTaskObservableList = FXCollections
                .observableArrayList();
        try{
            completeTaskObservableList.addAll(
                    ViewTaskController
                            .getInstance()
                            .getAll());

            tblTaskTable.setItems(completeTaskObservableList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}