package controller.viewTask;

import db.DBConnection;
import model.ViewTable;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewTaskController implements ViewTaskService {
    private static ViewTaskController instance;

    private ViewTaskController() {
    }

    public static ViewTaskController getInstance() {
        return instance == null
                ? instance = new ViewTaskController()
                : instance;
    }

    @Override
    public List<ViewTable> getAll() throws SQLException {
        List<ViewTable> comleteTaskList = new ArrayList<>();
        String SQL = "SELECT * FROM complete_task";
        ResultSet resultSet = DBConnection
                .getInstance()
                .getConnection()
                .createStatement().executeQuery(SQL);
        while (resultSet.next()) {
            int taskId = resultSet.getInt(1);
            String taskTitle = resultSet.getString(2);
            String taskDescription = resultSet.getString(3);
            Date completeDate = resultSet.getDate(4);
            String category = resultSet.getString(5);

            if (completeDate != null) {
                comleteTaskList.add(new ViewTable(
                        taskId,
                        taskTitle,
                        taskDescription,
                        category,
                        completeDate.toLocalDate()
                ));
            }
        }
        return comleteTaskList;
    }
}
