package controller.addTask;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import model.AddTaskTable;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddTaskController implements AddTaskService {
    private static AddTaskController instance;
    private AddTaskController() {}

    public static AddTaskController getInstance() {
        return instance == null ? instance = new AddTaskController() : instance;
    }

    @Override
    public List<AddTaskTable> getAll() throws SQLException {
        List<AddTaskTable> tasks = new ArrayList<>();
        String SQL = "SELECT * FROM active_task";

        ResultSet resultSet = DBConnection.getInstance()
                .getConnection()
                .createStatement()
                .executeQuery(SQL);

        while (resultSet.next()) {
            int taskId = resultSet.getInt(1);
            String taskTitle = resultSet.getString(2);
            String taskDescription = resultSet.getString(3);
            Date completeDate = resultSet.getDate(4);
            String category = resultSet.getString(5);

            if (completeDate != null) {
                tasks.add(new AddTaskTable(
                        taskId,
                        taskTitle,
                        taskDescription,
                        completeDate.toLocalDate(),
                        category,
                        new CheckBox()
                ));
            }
        }
        return tasks;
    }

    @Override
    public boolean addTask(AddTaskTable task) throws SQLException {
        String SQL = "INSERT INTO active_task(task_title,task_description,completion_date,category) VALUES(?,?,?,?)";
        PreparedStatement psTm = DBConnection
                .getInstance()
                .getConnection()
                .prepareStatement(SQL);

        psTm.setObject(1, task.getTitle());
        psTm.setObject(2, task.getDescription());
        psTm.setObject(3, task.getCompleteDate());
        psTm.setObject(4, task.getCategory());

        return psTm.executeUpdate()>0;
    }

    @Override
    public boolean updateTask(AddTaskTable task) throws SQLException {
        String SQL = "UPDATE active_task SET task_title=?, task_description=?,completion_date=?,category=? WHERE task_id=?";
        PreparedStatement psTm = DBConnection
                .getInstance()
                .getConnection()
                .prepareStatement(SQL);
        psTm.setObject(1, task.getTitle());
        psTm.setObject(2, task.getDescription());
        psTm.setObject(3, task.getCompleteDate());
        psTm.setObject(4, task.getCategory());
        psTm.setObject(5, task.getTaskId());

        return psTm.executeUpdate() > 0;
    }

    @Override
    public boolean deleteTask(int id) throws SQLException {
        String SQL = "DELETE FROM active_task WHERE task_id=?";
        PreparedStatement psTm = DBConnection
                .getInstance()
                .getConnection()
                .prepareStatement(SQL);
        psTm.setObject(1, id);

        return psTm.executeUpdate() > 0;
    }

    @Override
    public boolean completeTask(AddTaskTable task) throws SQLException {
        String SQL1 = "INSERT INTO complete_task(task_title,task_description,completion_date,category) VALUES(?,?,?,?)";
        PreparedStatement psTmInsert = DBConnection
                .getInstance()
                .getConnection()
                .prepareStatement(SQL1);

        psTmInsert.setObject(1, task.getTitle());
        psTmInsert.setObject(2, task.getDescription());
        psTmInsert.setObject(3, task.getCompleteDate());
        psTmInsert.setObject(4, task.getCategory());

        if (psTmInsert.executeUpdate() > 0) {
            String SQL2 = "DELETE FROM active_task WHERE task_id=?";
            PreparedStatement psTmDelete = DBConnection
                    .getInstance()
                    .getConnection()
                    .prepareStatement(SQL2);
            psTmDelete.setObject(1, task.getTaskId());
            return psTmDelete.executeUpdate() > 0;
        }
        return false;
    }
}
