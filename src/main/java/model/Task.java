package model;

import javafx.scene.control.CheckBox;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Task {
    private Integer taskId;
    private String  taskTitle;
    private String taskDescription;
    private LocalDate completeDate;
    private Boolean isComplete;
    private CheckBox checkBox;

}
