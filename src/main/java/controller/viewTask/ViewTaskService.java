package controller.viewTask;

import model.ViewTable;

import java.sql.SQLException;
import java.util.List;

public interface ViewTaskService {
    List<ViewTable> getAll() throws SQLException;
}
