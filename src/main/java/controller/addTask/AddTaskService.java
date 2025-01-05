package controller.addTask;

import model.AddTaskTable;

import java.sql.SQLException;
import java.util.List;

public interface AddTaskService {
    List<AddTaskTable> getAll() throws SQLException;
    boolean addTask(AddTaskTable task) throws SQLException;
    boolean updateTask(AddTaskTable task) throws SQLException;
    boolean deleteTask(int id) throws SQLException;
    boolean completeTask(AddTaskTable task) throws SQLException;

}
