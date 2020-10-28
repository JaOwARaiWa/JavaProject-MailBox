package condo.controller.staff;

import condo.model.Room;
import condo.process.ProgramDataSource;
import condo.process.ProgramDataSourceFile;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AddResidentController
{
    @FXML Label roomLabel, typeLabel;
    @FXML Button addBtn;
    @FXML TextField ownerField, resident1Field, resident2Field, resident3Field;

    private Room currentRoom;
    private ArrayList<String> resident = new ArrayList<>();
    private Alert popup = new Alert(Alert.AlertType.ERROR);
    private ProgramDataSource source = new ProgramDataSourceFile();

    @FXML public void initialize()
    {
        Platform.runLater(() -> {
            roomLabel.setText(currentRoom.getRoom());
            typeLabel.setText(currentRoom.getType());
            if (currentRoom.getType().equals("Deluxe"))
            {
                resident2Field.setDisable(true);
                resident3Field.setDisable(true);
            }
        });
    }

    @FXML public void handleAddBtnOnAction(ActionEvent event) throws IOException
    {
        if (currentRoom.getType().equals("Deluxe"))
        {
            if (currentRoom.getAllResident().size() == 2 && !currentRoom.getOwner().equals("None") && !currentRoom.getResident1().equals("None"))
            {
                popup.setTitle("Adding failed");
                popup.setHeaderText("Not available!");
                popup.setContentText("This room is full can't add more resident");
                popup.showAndWait();

                ownerField.setText("");
                resident1Field.setText("");
            }
            else
            {
                if (ownerField.getText().equals("") || resident1Field.getText().equals(""))
                {
                    popup.setTitle("Adding failed");
                    popup.setHeaderText("Invalid value!");
                    popup.setContentText("Please fill all the field");
                    popup.showAndWait();
                }
                else
                {
                    resident.add(ownerField.getText());
                    resident.add(resident1Field.getText());
                    currentRoom.setResident(resident);
                    source.updateResidentInRoom(currentRoom);

                    popup.setAlertType(Alert.AlertType.INFORMATION);
                    popup.setTitle("Done");
                    popup.setHeaderText("Add resident successful");
                    popup.setContentText("You have added " + ownerField.getText() + " and " + resident1Field.getText() + " to room " + currentRoom.getRoom() +
                            "\nPlease press \"Refresh\" button in Room list page");
                    popup.showAndWait();

                    ownerField.setText("");
                    resident1Field.setText("");
                }
            }
        }
        else if (currentRoom.getType().equals("Suite"))
        {
            if (currentRoom.getAllResident().size() == 4 && !currentRoom.getOwner().equals("None") && !currentRoom.getResident1().equals("None")&& !currentRoom.getResident2().equals("None") && !currentRoom.getResident3().equals("None"))
            {
                popup.setAlertType(Alert.AlertType.ERROR);
                popup.setTitle("Adding failed");
                popup.setHeaderText("Not available!");
                popup.setContentText("This room is full, can't add more resident");
                popup.showAndWait();

                ownerField.setText("");
                resident1Field.setText("");
                resident2Field.setText("");
                resident3Field.setText("");
            }
            else
            {
                if (ownerField.getText().equals("") || resident1Field.getText().equals("") || resident2Field.getText().equals("") || resident3Field.getText().equals(""))
                {
                    popup.setTitle("Adding failed");
                    popup.setHeaderText("Invalid value!");
                    popup.setContentText("Please fill all the field");
                    popup.showAndWait();
                }
                else
                {
                    resident.add(ownerField.getText());
                    resident.add(resident1Field.getText());
                    resident.add(resident2Field.getText());
                    resident.add(resident3Field.getText());
                    currentRoom.setResident(resident);
                    source.updateResidentInRoom(currentRoom);

                    popup.setAlertType(Alert.AlertType.INFORMATION);
                    popup.setTitle("Done");
                    popup.setHeaderText("Add resident successful");
                    popup.setContentText("You have added " + ownerField.getText()+ ", " + resident1Field.getText() + ", " + resident2Field.getText() + ", and " + resident3Field.getText() + " to room " + currentRoom.getRoom() +
                            "\nPlease press \"Refresh\" button in Room list page");
                    popup.showAndWait();

                    Button b = (Button) event.getSource();
                    Stage stage = (Stage) b.getScene().getWindow();
                    stage.close();


                    ownerField.setText("");
                    resident1Field.setText("");
                    resident2Field.setText("");
                    resident3Field.setText("");
                }
            }
        }
    }

    public void setCurrentRoom(Room currentRoom)
    {
        this.currentRoom = currentRoom;
    }
}
