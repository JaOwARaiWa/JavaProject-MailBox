package condo.controller.staff;

import condo.model.*;
import condo.process.ProgramDataSource;
import condo.process.ProgramDataSourceFile;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AddMailController
{
    @FXML
    Button addMailBtn, cancelBtn, upLoadPhotoBtn;
    @FXML
    ChoiceBox typeBox, sizeBox;
    @FXML
    TextField residentField, roomField, senderField, addressField, priorityField, serviceField, trackNumField;
    @FXML
    ImageView mailImage;

    private String type;
    private String resident;
    private String room;
    private String staff;
    private String sender;
    private String address;
    private String size;
    private String image;
    private String date;
    private String time;
    private boolean status;
    private String priority;
    private String service;
    private String trackNum;

    private User currentUser;
    private Path target;
    private Mail mail;
    private int check = 0;

    private Alert popup = new Alert(Alert.AlertType.NONE);
    private ProgramDataSource source = new ProgramDataSourceFile();

    @FXML public void initialize()
    {
        priorityField.setDisable(true);
        serviceField.setDisable(true);
        trackNumField.setDisable(true);

        typeBox.getItems().addAll("Letter", "Document", "Parcel");

        sizeBox.getItems().addAll("S", "M", "L", "XL", "XXL");

        typeBox.getSelectionModel().selectedItemProperty().addListener((observable, before, after) ->
        {
            if (after.toString().equals("Letter"))
            {
                priorityField.setDisable(true);
                serviceField.setDisable(true);
                trackNumField.setDisable(true);
                File file = new File(String.valueOf(target));
                file.delete();
                roomField.setText("");
            }
            if (after.toString().equals("Document"))
            {
                priorityField.setDisable(false);
                serviceField.setDisable(true);
                trackNumField.setDisable(true);
                File file = new File(String.valueOf(target));
                file.delete();
                roomField.setText("");
            }
            else if (after.toString().equals("Parcel"))
            {
                priorityField.setDisable(true);
                serviceField.setDisable(false);
                trackNumField.setDisable(false);
                File file = new File(String.valueOf(target));
                file.delete();
                roomField.setText("");
            }
        });

        upLoadPhotoBtn.setOnAction(event ->
        {
            if (typeBox.getValue() == null || roomField.getText().equals(""))
            {
                popup.setAlertType(Alert.AlertType.WARNING);
                popup.setTitle("Warning");
                popup.setHeaderText("Can't upload the photo");
                popup.setContentText("Please enter type and room before upload photo");
                popup.showAndWait();
            }
            else
            {
                if (check != 0)
                {
                    File file = new File(String.valueOf(target));
                    file.delete();
                }

                FileChooser chooser = new FileChooser();
                chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
                chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images PNG JPG", "*.png", "*.jpg"));
                File file = chooser.showOpenDialog(upLoadPhotoBtn.getScene().getWindow());
                if (file != null)
                {
                    try
                    {
                        File destDir = new File("images");
                        destDir.mkdirs();
                        String filename = roomField.getText() + "_" + typeBox.getValue().toString() + "_" + LocalDate.now() + "_" + LocalTime.now().format(DateTimeFormatter.ofPattern("HH-mm-ss")) + ".jpg";
                        target = FileSystems.getDefault().getPath(destDir.getPath() + System.getProperty("file.separator") + filename);
                        Files.copy(file.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
                        mailImage.setImage(new Image(target.toUri().toString()));
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    check-=-1;
                }
            }
        });
    }

    @FXML public void handleAddMailBtnOnAction(ActionEvent event) throws IOException
    {
        if (typeBox.getValue() == null || residentField.getText().equals("") ||
                roomField.getText().equals("") || senderField.getText().equals("") || addressField.getText().equals("") ||
                sizeBox.getValue().toString().equals("") || mailImage.getImage() == null || target.toUri().toString().equals("") ||
                (typeBox.getValue().toString().equals("Document") && priorityField.getText().equals("")) ||
                (typeBox.getValue().toString().equals("Parcel") && (serviceField.getText().equals("") ||
                        trackNumField.getText().equals(""))))
        {
            popup.setAlertType(Alert.AlertType.ERROR);
            popup.setTitle("Failed");
            popup.setHeaderText("Can't add this mail");
            popup.setContentText("Please choose all the box or fill all the information");
            popup.showAndWait();
        }
        else
        {
            type = typeBox.getValue().toString();
            resident = residentField.getText();
            room = roomField.getText();
            staff = currentUser.getName();
            sender = senderField.getText();
            address = addressField.getText();
            size = sizeBox.getValue().toString();
            image = String.valueOf(target);
            date = LocalDate.now().toString();
            time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            status = true;
            priority = priorityField.getText();
            service = serviceField.getText();
            trackNum = trackNumField.getText();

            if (source.checkRoomAndOwner(room))
            {
                switch (type)
                {
                    case "Letter":
                        mail = new Letter(type, resident, room, staff, sender, address, size, image, date, time, "-",true);
                        break;
                    case "Document":
                        mail = new Document(type, resident, room, staff, sender, address, size, image, date, time, "-", true, priority);
                        break;
                    case "Parcel":
                        mail = new Parcel(type, resident, room, staff, sender, address, size, image, date, time, "-" ,true, service, trackNum);
                        break;
                }

                source.addMail(mail);

                popup.setAlertType(Alert.AlertType.INFORMATION);
                popup.setTitle("Done");
                popup.setHeaderText("Adding mail successful");
                popup.setContentText("Please check the mail");
                popup.showAndWait();

                Button b = (Button) event.getSource();
                Stage stage = (Stage) b.getScene().getWindow();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/staffmenupage.fxml"));
                stage.setScene(new Scene(loader.load(), 800, 600));

                StaffMenuController smct = loader.getController();
                smct.setCurrentUser((Staff) currentUser);

                stage.show();


            }
            else
            {
                popup.setAlertType(Alert.AlertType.ERROR);
                popup.setTitle("Failed");
                popup.setHeaderText("This room is not available");
                popup.setContentText("Please check the room number and try again");
                popup.showAndWait();
                roomField.setText("");
                File image = new File(String.valueOf(target));
                image.delete();
                mailImage.setImage(null);
            }
        }
    }

    @FXML public void handleCancelBtnOnAction(ActionEvent event) throws IOException
    {
        File file = new File(String.valueOf(target));
        file.delete();

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