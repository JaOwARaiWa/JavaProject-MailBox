package condo.controller.staff;

import condo.model.Room;
import condo.model.Staff;
import condo.model.User;
import condo.process.ProgramDataSource;
import condo.process.ProgramDataSourceFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class RoomListController
{
    @FXML Button backBtn, refreshBtn;
    @FXML TableView residentTable;
    @FXML TableColumn buildingCol, floorCol, typeCol, roomNumCol, ownerCol, res1Col, res2Col, res3Col;

    private User currentUser;
    private Room currentRoom;
    private ObservableList<Room> residentList;
    private ArrayList<Room> rooms;
    private ProgramDataSource source = new ProgramDataSourceFile();

    @FXML public void initialize() throws IOException
    {
        residentList = FXCollections.observableArrayList(source.showResidentRoom());
        buildingCol.setCellValueFactory(new PropertyValueFactory<Room, String>("Building"));
        floorCol.setCellValueFactory(new PropertyValueFactory<Room, String>("Floor"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Room, String>("Type"));
        roomNumCol.setCellValueFactory(new PropertyValueFactory<Room, String>("RoomNumber"));
        ownerCol.setCellValueFactory(new PropertyValueFactory<Room, String>("Owner"));
        res1Col.setCellValueFactory(new PropertyValueFactory<Room, String>("Resident1"));
        res2Col.setCellValueFactory(new PropertyValueFactory<Room, String>("Resident2"));
        res3Col.setCellValueFactory(new PropertyValueFactory<Room, String>("Resident3"));
        residentTable.setItems(residentList);

        residentTable.setRowFactory( table ->
        {
            TableRow row = new TableRow();
            row.setOnMouseClicked(event ->
            {
                if (event.getClickCount() == 2 && (!row.isEmpty()))
                {
                    Room thisRoom = (Room) row.getItem();
                    setCurrentRoom(thisRoom);
                    try
                    {
                        doubleClicked();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }

    @FXML public void handleRefreshBtnOnAction(ActionEvent event) throws IOException
    {
        ObservableList refreshList = FXCollections.observableArrayList(source.showResidentRoom());
        residentTable.setItems(refreshList);
        residentTable.refresh();
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

    public void doubleClicked() throws IOException
    {
        System.out.println(currentRoom.getRoom());
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/addresidentwindow.fxml"));
        stage.setTitle("6210450032");
        stage.setScene(new Scene(loader.load(), 400, 500));

        AddResidentController arct = loader.getController();
        arct.setCurrentRoom(currentRoom);

        stage.show();
    }


    public void setCurrentRoom(Room currentRoom)
    {
        this.currentRoom = currentRoom;
    }

    public void setCurrentUser(User currentUser)
    {
        this.currentUser = currentUser;
    }
}
