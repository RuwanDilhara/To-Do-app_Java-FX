package model;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class AddTaskTable {
    private Integer taskId;
    private String title;
    private String description;
    private LocalDate completeDate;
    private String category;
    private CheckBox checkBox;


}
