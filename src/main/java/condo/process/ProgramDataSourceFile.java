package condo.process;

import condo.model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ProgramDataSourceFile implements ProgramDataSource
{
    public ArrayList readAllAccount() throws IOException
    {
        File file = new File("csv_file/account.csv");
        FileReader reader = new FileReader(file);
        BufferedReader buffered = new BufferedReader(reader);
        String profile;
        ArrayList<String> readIden = new ArrayList<>();
        ArrayList<String> readUser = new ArrayList<>();
        ArrayList<String> readPass = new ArrayList<>();
        ArrayList<String> readName = new ArrayList<>();
        ArrayList<String> readRoom = new ArrayList<>();

        while ((profile = buffered.readLine()) != null)
        {
            String[] al_read = profile.split(",");
            readIden.add(al_read[0]);
            readUser.add(al_read[1]);
            readPass.add(al_read[2]);
            readName.add(al_read[3]);

            if (al_read[0].equals("RESIDENT"))
            {
                readRoom.add(al_read[4]);
            }
            else
            {
                readRoom.add("-");
            }
        }
        reader.close();

        ArrayList acc = new ArrayList<>();
        acc.add(readIden);
        acc.add(readUser);
        acc.add(readPass);
        acc.add(readName);
        acc.add(readRoom);

        return acc;
    }

    public void writeNewStaff(Staff newStaff) throws IOException
    {
        ArrayList acc = readAllAccount();
        ArrayList<String> idList = (ArrayList<String>) acc.get(0);
        ArrayList<String> usernameList = (ArrayList<String>) acc.get(1);
        ArrayList<String> passwordList = (ArrayList<String>) acc.get(2);
        ArrayList<String> nameList = (ArrayList<String>) acc.get(3);
        ArrayList<String> roomList = (ArrayList<String>) acc.get(4);

        File writefile = new File("csv_file/account.csv");
        FileWriter fileWriter = new FileWriter(writefile);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        int limit = idList.size();
        for (int i = 0; i < limit; i-=-1)
        {
            if (idList.get(i).equals("RESIDENT"))
            {
                writer.write(idList.get(i) + "," + usernameList.get(i) + "," + passwordList.get(i) + "," + nameList.get(i) + "," + roomList.get(i));
            }
            else
            {
                writer.write(idList.get(i) + "," + usernameList.get(i) + "," + passwordList.get(i) + "," + nameList.get(i));
            }
            writer.newLine();
        }
        writer.write(newStaff.getId() + "," + newStaff.getUsername() + "," + newStaff.getPassword() + "," + newStaff.getName());
        writer.close();
    }

    public void updateLogInStaff(Staff currentStaff) throws IOException
    {
        ArrayList<Staff> staffList = readStaffAccount();
        Staff updateStaff = null;

        for (Staff staff : staffList)
        {
            if (staff.getUsername().equals(currentStaff.getUsername()))
            {
                updateStaff = new Staff(staff.getUsername(),
                        staff.getPassword(),
                        staff.getName(),
                        staff.getSurname(),
                        currentStaff.getDate(),
                        currentStaff.getTime());
                staffList.remove(staff);
                break;
            }
        }

        File writefile = new File("csv_file/staffacc.csv");
        FileWriter fileWriter = new FileWriter(writefile);
        BufferedWriter writer = new BufferedWriter(fileWriter);

        writer.write(updateStaff.getUsername() + "," +
                updateStaff.getPassword() + "," +
                updateStaff.getName() + "," +
                updateStaff.getSurname() + "," +
                updateStaff.getDate() + "," +
                updateStaff.getTime());
        writer.newLine();

        for (Staff staff : staffList)
        {
            writer.write(staff.getUsername() + ","
                    + staff.getPassword() + "," +
                    staff.getName() + "," +
                    staff.getSurname() + "," +
                    staff.getDate() + "," +
                    staff.getTime());
            writer.newLine();
        }
        writer.close();
    }

    public ArrayList<Staff> readStaffAccount() throws IOException
    {
        File readfile = new File("csv_file/staffacc.csv");
        FileReader reader = new FileReader(readfile);
        BufferedReader buffered = new BufferedReader(reader);
        String profile;
        ArrayList<Staff> staffList = new ArrayList<>();
        Staff staff;
        while ((profile = buffered.readLine()) != null)
        {
            String[] al_read = profile.split(",");
            staff = new Staff(al_read[0], al_read[1], al_read[2], al_read[3], al_read[4], al_read[5]);
            staffList.add(staff);
        }
        reader.close();

        return staffList;
    }

    public boolean addStaff(Staff newStaff) throws IOException
    {
        ArrayList<Staff> staffList = readStaffAccount();
        int check = 0;
        for (Staff staff : staffList)
        {
            if (staff.getUsername().equals(newStaff.getUsername()))
            {
                check = -1;
            }
            else
            {
                continue;
            }
        }

        if (check == 0)
        {
            File writeFile = new File("csv_file/staffacc.csv");
            FileWriter fileWriter = new FileWriter(writeFile);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            for (Staff staff : staffList)
            {
                writer.write(staff.getUsername() + "," + staff.getPassword() + "," + staff.getName() + "," +
                        staff.getSurname() + "," + staff.getDate() + "," + staff.getTime());
                writer.newLine();
            }
            writer.write(newStaff.getUsername() + "," + newStaff.getPassword() + "," + newStaff.getName() + "," +
                    newStaff.getSurname() + "," + newStaff.getDate() + "," + newStaff.getTime());
            writer.newLine();
            writer.close();

            return true;
        }
        else
        {
            return false;
        }
    }

    public void changePass(User currentuser, String newPass) throws IOException
    {
        ArrayList acc = readAllAccount();

        ArrayList<String> idList = (ArrayList<String>) acc.get(0);
        ArrayList<String> usernameList = (ArrayList<String>) acc.get(1);
        ArrayList<String> passwordList = (ArrayList<String>) acc.get(2);
        ArrayList<String> nameList = (ArrayList<String>) acc.get(3);
        ArrayList<String> roomList = (ArrayList<String>) acc.get(4);
        ArrayList<String> changedList = new ArrayList<>();

        int limit = idList.size();

        File file = new File("csv_file/account.csv");
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter writer = new BufferedWriter(fileWriter);

        for (int i = 0; i < limit; i-=-1)
        {
            if (usernameList.get(i).equals(currentuser.getUsername()))
            {
                currentuser.setPassword(newPass);
                changedList.add(currentuser.getPassword());
            }
            else
            {
                changedList.add(passwordList.get(i));
            }
        }

        for (int i = 0; i < limit; i-=-1)
        {
            if (idList.get(i).equals("RESIDENT") || idList.get(i).equals("Identity"))
            {
                writer.write(idList.get(i) + "," + usernameList.get(i) + "," + changedList.get(i) + "," + nameList.get(i) + "," + roomList.get(i));
            }
            else
            {
                writer.write(idList.get(i) + "," + usernameList.get(i) + "," + changedList.get(i) + "," + nameList.get(i));
            }
            writer.newLine();
        }
        writer.close();

        if (currentuser.getId().equals("STAFF"))
        {
            ArrayList<Staff> staffList = readStaffAccount();

            File fileAgain = new File("csv_file/staffacc.csv");
            FileWriter fileWriterAgain = new FileWriter(fileAgain);
            BufferedWriter writerAgain = new BufferedWriter(fileWriterAgain);

            for (Staff staff : staffList)
            {
                if (staff.getUsername().equals(currentuser.getUsername()))
                {
                    staff.setPassword(newPass);
                }
            }

            for (Staff staff : staffList)
            {
                writerAgain.write(staff.getUsername() + "," + staff.getPassword() + "," + staff.getName() + "," +
                        staff.getSurname() + "," + staff.getDate() + "," + staff.getTime());
                writerAgain.newLine();
            }
            writerAgain.close();
        }
    }

    public boolean addNewRoom(Room newRoom) throws IOException
    {
        ArrayList<Room> roomsList = readCondoRoom();

        int check = 0;
        for (Room room : roomsList)
        {
            if (room.getRoom().equals(newRoom.getRoom()))
            {
                check = -1;
                break;
            }
            else
            {
                continue;
            }
        }

        if (check == 0)
        {
            File writefile = new File("csv_file/condoroom.csv");
            FileWriter fileWriter = new FileWriter(writefile);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            if (newRoom.getType().equals("Deluxe"))
            {
                writer.write(newRoom.getBuilding() + "," +
                        newRoom.getFloor() + "," +
                        newRoom.getType() + "," +
                        newRoom.getRoomNumber() + "," +
                        newRoom.getRoom() + "," +
                        "None" + "," + "None");
            }
            else if (newRoom.getType().equals("Suite"))
            {
                writer.write(newRoom.getBuilding() + "," +
                        newRoom.getFloor() + "," +
                        newRoom.getType() + "," +
                        newRoom.getRoomNumber() + "," +
                        newRoom.getRoom() + "," +
                        "None" + "," + "None" + "," + "None" + "," + "None");
            }
            writer.newLine();

            for (Room room : roomsList)
            {
                if (room.getType().equals("Deluxe"))
                {
                    writer.write(room.getBuilding() + "," +
                            room.getFloor() + "," +
                            room.getType() + "," +
                            room.getRoomNumber() + "," +
                            room.getRoom() + "," +
                            room.getAllResident().get(0) + "," +
                            room.getAllResident().get(1));
                }
                else if (room.getType().equals("Suite"))
                {
                    writer.write(room.getBuilding() + "," +
                            room.getFloor() + "," +
                            room.getType() + "," +
                            room.getRoomNumber() + "," +
                            room.getRoom() + "," +
                            room.getAllResident().get(0) + "," +
                            room.getAllResident().get(1) + "," +
                            room.getAllResident().get(2) + "," +
                            room.getAllResident().get(3));
                }
                writer.newLine();
            }
            writer.close();

            return true;
        }
        else
        {
            return false;
        }
    }

    public ArrayList<Room> readCondoRoom() throws IOException
    {
        File readfile = new File("csv_file/condoroom.csv");
        FileReader reader = new FileReader(readfile);
        BufferedReader buffered = new BufferedReader(reader);
        String profile;
        ArrayList<Room> roomsList = new ArrayList<>();
        ArrayList<String> resident = new ArrayList<>();
        Room room;
        while ((profile = buffered.readLine()) != null)
        {
            String[] al_read = profile.split(",");
            room = new Room(al_read[0], al_read[1], al_read[2], al_read[3]);
            room.setRoom();

            if (al_read[2].equals("Deluxe"))
            {
                resident.add(al_read[5]);
                resident.add(al_read[6]);
            }
            else if (al_read[2].equals("Suite"))
            {
                resident.add(al_read[5]);
                resident.add(al_read[6]);
                resident.add(al_read[7]);
                resident.add(al_read[8]);
            }
            room.setResident(resident);

            roomsList.add(room);
            resident.clear();
        }
        reader.close();

        return roomsList;
    }

    public void updateResidentInRoom(Room currentRoom) throws IOException
    {
        ArrayList<Room> roomsList = readCondoRoom();

        int check = 0;
        for (Room room : roomsList)
        {
            if (room.getRoom().equals(currentRoom.getRoom()))
            {
                roomsList.remove(room);
                check = -1;
                break;
            }
            else
            {
                continue;
            }
        }

        if (check == -1)
        {
            File writefile = new File("csv_file/condoroom.csv");
            FileWriter fileWriter = new FileWriter(writefile);
            BufferedWriter writer = new BufferedWriter(fileWriter);

            if (currentRoom.getType().equals("Deluxe"))
            {
                writer.write(currentRoom.getBuilding() + "," +
                        currentRoom.getFloor() + "," +
                        currentRoom.getType() + "," +
                        currentRoom.getRoomNumber() + "," +
                        currentRoom.getRoom() + "," +
                        currentRoom.getAllResident().get(0) + "," +
                        currentRoom.getAllResident().get(1));
            }
            else if (currentRoom.getType().equals("Suite"))
            {
                writer.write(currentRoom.getBuilding() + "," +
                        currentRoom.getFloor() + "," +
                        currentRoom.getType() + "," +
                        currentRoom.getRoomNumber() + "," +
                        currentRoom.getRoom() + "," +
                        currentRoom.getAllResident().get(0) + "," +
                        currentRoom.getAllResident().get(1) + "," +
                        currentRoom.getAllResident().get(2) + "," +
                        currentRoom.getAllResident().get(3));
            }
            writer.newLine();

            for (Room room : roomsList)
            {
                if (room.getType().equals("Deluxe"))
                {
                    writer.write(room.getBuilding() + "," +
                            room.getFloor() + "," +
                            room.getType() + "," +
                            room.getRoomNumber() + "," +
                            room.getRoom() + "," +
                            room.getAllResident().get(0) + "," +
                            room.getAllResident().get(1));
                }
                else if (room.getType().equals("Suite"))
                {
                    writer.write(room.getBuilding() + "," +
                            room.getFloor() + "," +
                            room.getType() + "," +
                            room.getRoomNumber() + "," +
                            room.getRoom() + "," +
                            room.getAllResident().get(0) + "," +
                            room.getAllResident().get(1) + "," +
                            room.getAllResident().get(2) + "," +
                            room.getAllResident().get(3));
                }
                writer.newLine();
            }
            writer.close();
        }
    }

    public boolean checkRoomAndOwner(String thisRoom) throws IOException
    {
        ArrayList<Room> roomsList = readCondoRoom();

        int check = 0;
        for (Room runRoom : roomsList)
        {
            if (runRoom.getRoom().equals(thisRoom))
            {
                if (!runRoom.getOwner().equals(""))
                {
                    check = 1;
                    break;
                }
                else
                {
                    check = -1;
                }
            }
            else
            {
                check = -1;
            }
        }
        return check == 1;
    }

    public ArrayList<Letter> readMail(String show) throws IOException
    {
        File readFile = new File("csv_file/mail.csv");
        FileReader reader = new FileReader(readFile);
        BufferedReader buffered = new BufferedReader(reader);
        String profile;
        ArrayList<Letter> pickedUpList = new ArrayList<>();
        ArrayList<Letter> inStockList = new ArrayList<>();
        ArrayList<Letter> allLetterList = new ArrayList<>();
        Letter letter = null;
        while ((profile = buffered.readLine()) != null)
        {
            String[] al_read = profile.split(",");

            if (al_read[11].equals("in stock"))
            {
                if (al_read[0].equals("Letter"))
                {
                    letter = new Letter(al_read[0], al_read[1], al_read[2], al_read[3], al_read[4], al_read[5], al_read[6],
                            al_read[7], al_read[8], al_read[9], al_read[10], al_read[11]);
                }
                else if (al_read[0].equals("Document"))
                {
                    letter = new Document(al_read[0], al_read[1], al_read[2], al_read[3], al_read[4], al_read[5], al_read[6],
                            al_read[7], al_read[8], al_read[9], al_read[10], al_read[11], al_read[12]);
                }
                else if (al_read[0].equals("Parcel"))
                {
                    letter = new Parcel(al_read[0], al_read[1], al_read[2], al_read[3], al_read[4], al_read[5], al_read[6],
                            al_read[7], al_read[8], al_read[9], al_read[10], al_read[11], al_read[12], al_read[13]);
                }
                inStockList.add(letter);
            }
            else if (al_read[11].equals("picked up"))
            {
                if (al_read[0].equals("Letter"))
                {
                    letter = new Letter(al_read[0], al_read[1], al_read[2], al_read[3], al_read[4], al_read[5], al_read[6],
                            al_read[7], al_read[8], al_read[9], al_read[10], al_read[11]);
                }
                else if (al_read[0].equals("Document"))
                {
                    letter = new Document(al_read[0], al_read[1], al_read[2], al_read[3], al_read[4], al_read[5], al_read[6],
                            al_read[7], al_read[8], al_read[9], al_read[10], al_read[11], al_read[12]);
                }
                else if (al_read[0].equals("Parcel"))
                {
                    letter = new Parcel(al_read[0], al_read[1], al_read[2], al_read[3], al_read[4], al_read[5], al_read[6],
                            al_read[7], al_read[8], al_read[9], al_read[10], al_read[11], al_read[12], al_read[13]);
                }
                pickedUpList.add(letter);
            }
            else
            {
                if (al_read[0].equals("Letter"))
                {
                    letter = new Letter(al_read[0], al_read[1], al_read[2], al_read[3], al_read[4], al_read[5], al_read[6],
                            al_read[7], al_read[8], al_read[9], al_read[10], al_read[11]);
                }
                else if (al_read[0].equals("Document"))
                {
                    letter = new Document(al_read[0], al_read[1], al_read[2], al_read[3], al_read[4], al_read[5], al_read[6],
                            al_read[7], al_read[8], al_read[9], al_read[10], al_read[11], al_read[12]);
                }
                else if (al_read[0].equals("Parcel"))
                {
                    letter = new Parcel(al_read[0], al_read[1], al_read[2], al_read[3], al_read[4], al_read[5], al_read[6],
                            al_read[7], al_read[8], al_read[9], al_read[10], al_read[11], al_read[12], al_read[13]);
                }
            }

            allLetterList.add(letter);
        }
        reader.close();

        if (show.equals("in stock"))
        {
            return inStockList;
        }
        else if (show.equals("picked up"))
        {
            return pickedUpList;
        }
        else
        {
            return allLetterList;
        }
    }

    public void addMail(Letter newLetter) throws IOException
    {
        ArrayList<Letter> letterList = readMail("all");

        File writefile = new File("csv_file/mail.csv");
        FileWriter fileWriter = new FileWriter(writefile);
        BufferedWriter writer = new BufferedWriter(fileWriter);

        letterList.add(0, newLetter);

        for (Letter writeLetter : letterList)
        {
            if (writeLetter.getType().equals("Letter"))
            {
                writer.write(writeLetter.getType() + "," + writeLetter.getTo() + "," + writeLetter.getRoom() + "," +
                        writeLetter.getStaff() + "," + writeLetter.getSender() + "," + writeLetter.getAddress() + "," +
                        writeLetter.getSize() + "," + writeLetter.getImage() + "," + writeLetter.getDate() + "," +
                        writeLetter.getTime() + "," + writeLetter.getReceiver() + "," + writeLetter.getStatus());
            }
            else if (writeLetter.getType().equals("Document"))
            {
                writer.write(writeLetter.getType() + "," + writeLetter.getTo() + "," + writeLetter.getRoom() + "," +
                        writeLetter.getStaff() + "," + writeLetter.getSender() + "," + writeLetter.getAddress() + "," +
                        writeLetter.getSize() + "," + writeLetter.getImage() + "," + writeLetter.getDate() + "," +
                        writeLetter.getTime() + "," + writeLetter.getReceiver() + "," + writeLetter.getStatus() + "," + ((Document) writeLetter).getPriority());
            }
            else if (writeLetter.getType().equals("Parcel"))
            {
                writer.write(writeLetter.getType() + "," + writeLetter.getTo() + "," + writeLetter.getRoom() + "," +
                        writeLetter.getStaff() + "," + writeLetter.getSender() + "," + writeLetter.getAddress() + "," +
                        writeLetter.getSize() + "," + writeLetter.getImage() + "," + writeLetter.getDate() + "," +
                        writeLetter.getTime() + "," + writeLetter.getReceiver() + "," + writeLetter.getStatus() + "," + ((Parcel) writeLetter).getService() + "," + ((Parcel) writeLetter).getTracknum());
            }
            writer.newLine();
        }
        writer.close();
    }

    public void pickUpMail(Letter currentLetter) throws IOException
    {
        ArrayList<Letter> allLetterList = readMail("all");

        File writeFile = new File("csv_file/mail.csv");
        FileWriter fileWriter = new FileWriter(writeFile);
        BufferedWriter writer = new BufferedWriter(fileWriter);

        for (Letter checkLetter : allLetterList)
        {
            if (checkLetter.getDate().equals(currentLetter.getDate()) && checkLetter.getTime().equals(currentLetter.getTime()))
            {
                currentLetter.setDate(LocalDate.now().toString());
                currentLetter.setTime(LocalTime.now().toString());
                allLetterList.add(currentLetter);
                allLetterList.remove(checkLetter);
                break;
            }
        }

        for (Letter writeLetter : allLetterList)
        {
            if (writeLetter.getType().equals("Letter"))
            {
                writer.write(writeLetter.getType() + "," + writeLetter.getTo() + "," + writeLetter.getRoom() + "," +
                        writeLetter.getStaff() + "," + writeLetter.getSender() + "," + writeLetter.getAddress() + "," +
                        writeLetter.getSize() + "," + writeLetter.getImage() + "," + writeLetter.getDate() + "," +
                        writeLetter.getTime() + "," + writeLetter.getReceiver() + "," + writeLetter.getStatus());
            }
            else if (writeLetter.getType().equals("Document"))
            {
                writer.write(writeLetter.getType() + "," + writeLetter.getTo() + "," + writeLetter.getRoom() + "," +
                        writeLetter.getStaff() + "," + writeLetter.getSender() + "," + writeLetter.getAddress() + "," +
                        writeLetter.getSize() + "," + writeLetter.getImage() + "," + writeLetter.getDate() + "," +
                        writeLetter.getTime() + "," + writeLetter.getReceiver() + "," + writeLetter.getStatus() + "," + ((Document) writeLetter).getPriority());
            }
            else if (writeLetter.getType().equals("Parcel"))
            {
                writer.write(writeLetter.getType() + "," + writeLetter.getTo() + "," + writeLetter.getRoom() + "," +
                        writeLetter.getStaff() + "," + writeLetter.getSender() + "," + writeLetter.getAddress() + "," +
                        writeLetter.getSize() + "," + writeLetter.getImage() + "," + writeLetter.getDate() + "," +
                        writeLetter.getTime() + "," + writeLetter.getReceiver() + "," + writeLetter.getStatus() + "," + ((Parcel) writeLetter).getService() + "," + ((Parcel) writeLetter).getTracknum());
            }
            writer.newLine();
        }
        writer.close();
    }

    public void register(Resident newUser) throws IOException
    {
        File readFile = new File("csv_file/account.csv");
        FileReader reader = new FileReader(readFile);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String profile;
        ArrayList<User> userList = new ArrayList<>();
        User user = null;
        while ((profile = bufferedReader.readLine()) != null)
        {
            String[] al_read = profile.split(",");

            if (al_read[0].equals("RESIDENT") || al_read[0].equals("Identity"))
            {
                user = new Resident(al_read[1], al_read[2], al_read[3], al_read[0], al_read[4]);
            }
            else
            {
                user = new User(al_read[1], al_read[2], al_read[3], al_read[0]);
            }
            userList.add(user);
        }
        reader.close();

        userList.add(newUser);

        File writeFile = new File("csv_file/account.csv");
        FileWriter fileWriter = new FileWriter(writeFile);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        for (User writeUser : userList)
        {
            if (writeUser.getId().equals("RESIDENT") || writeUser.getId().equals("Identity"))
            {
                writer.write(writeUser.getId() + "," + writeUser.getUsername() + "," + writeUser.getPassword() + "," + writeUser.getName() + "," + ((Resident) writeUser).getRoom());
            }
            else
            {
                writer.write(writeUser.getId() + "," + writeUser.getUsername() + "," + writeUser.getPassword() + "," + writeUser.getName());
            }
            writer.newLine();
        }
        writer.close();
    }

    public ArrayList<Letter> searchMail(String partial) throws IOException
    {
        ArrayList<Letter> letterList = readMail("in stock");
        ArrayList<Letter> showList = new ArrayList<>();

        for (Letter checkLetter : letterList)
        {
            if (checkLetter.getRoom().contains(partial.toUpperCase()))
            {
                showList.add(checkLetter);
            }
        }

        return showList;
    }

}
