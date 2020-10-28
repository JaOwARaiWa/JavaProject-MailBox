package condo.controller.staff;

import condo.model.Room;
import condo.model.Staff;
import condo.model.User;
import condo.process.ProgramDataSource;
import condo.process.ProgramDataSourceFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class AddRoomController
{
    @FXML Button backBtn, addBtn;
    @FXML ChoiceBox buildingBox, floorBox, typeBox, roomBox;

    private User currentUser;
    private Room newRoom;
    private Alert popup = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
    private ProgramDataSource source = new ProgramDataSourceFile();

    @FXML public void initialize()
    {
        buildingBox.getItems().add("A");
        buildingBox.getItems().add("B");
        for (int i = 1; i <= 8; i-=-1)
        {
            floorBox.getItems().add(String.valueOf(i));
        }
        typeBox.getItems().add("Deluxe");
        typeBox.getItems().add("Suite");

        typeBox.getSelectionModel().selectedItemProperty().addListener((observable, before, after) ->
        {
            if (after == null)
            {
                typeBox.setValue(null);
            }
            else if (after.toString().equals("Deluxe"))
            {
                if (roomBox.getItems().size() == 0)
                {
                    for (int i = 1; i <= 6; i-=-1)
                    {
                        roomBox.getItems().add("0" + i);
                    }
                }
                else
                {
                    roomBox.getItems().remove(0, roomBox.getItems().size());
                    for (int i = 1; i <= 6; i-=-1)
                    {
                        roomBox.getItems().add("0" + i);
                    }
                }
            }
            else if (after.toString().equals("Suite"))
            {
                if (roomBox.getItems().size() == 0)
                {
                    for (int i = 7; i < 10; i-=-1)
                    {
                        roomBox.getItems().add("0" + i);
                    }
                    roomBox.getItems().add("10");
                }
                else
                {
                    roomBox.getItems().remove(0, roomBox.getItems().size());
                    for (int i = 7; i < 10; i-=-1)
                    {
                        roomBox.getItems().add("0" + i);
                    }
                    roomBox.getItems().add("10");
                }
            }
        });
    }

    @FXML public void handleAddRoomBtnOnAction(ActionEvent event) throws IOException
    {
        if (buildingBox.getValue() == null ||
                floorBox.getValue() == null ||
                typeBox.getValue() == null ||
                roomBox.getValue() == null)
        {
            popup.setAlertType(Alert.AlertType.ERROR);
            popup.setTitle("Failed");
            popup.setHeaderText("Can't add this room");
            popup.setContentText("Please choose all the box or fill the information");
            popup.showAndWait();
        }
        else
        {
            popup.setHeaderText("Do you want to add this room?");
            popup.setContentText("Building : " + buildingBox.getValue().toString() +
                    "\nFloor : " + floorBox.getValue().toString() +
                    "\nType : " + typeBox.getValue().toString() +
                    "\nRoom Number : " + roomBox.getValue().toString());
            popup.showAndWait();

            if (popup.getResult() == ButtonType.YES)
            {
                newRoom = new Room(buildingBox.getValue().toString(),
                        floorBox.getValue().toString(),
                        typeBox.getValue().toString(),
                        roomBox.getValue().toString());
                newRoom.setRoom();
                if (source.addNewRoom(newRoom))
                {
                    popup.setAlertType(Alert.AlertType.NONE);
                    popup.setTitle("Successful");
                    popup.setHeaderText("Adding successful");
                    popup.setContentText("You have add room " + newRoom.getRoom());
                }
                else
                {
                    popup.setAlertType(Alert.AlertType.ERROR);
                    popup.setTitle("Failed");
                    popup.setHeaderText("Adding failed");
                    popup.setContentText(newRoom.getRoom() + " is not available, please change the room");
                }
                popup.showAndWait();
                buildingBox.setValue(null);
                floorBox.setValue(null);
                typeBox.setValue(null);
                roomBox.setValue(null);
                newRoom.reset();
            }
        }
        popup.setAlertType(Alert.AlertType.CONFIRMATION);
    }

    @FXML public void handleBackBtnOnAction(ActionEvent event) throws IOException
    {
        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/staffmenupage.fxml"));
        stage.setScene(new Scene(loader.load(), 800, 600));

        StaffMenuController smct = loader.getController();
        smct.setCurrentUser((Staff) currentUser);

        stage.show();
    }

    public void setCurrentUser(User currentUser)
    {
        this.currentUser = currentUser;
    }

}
