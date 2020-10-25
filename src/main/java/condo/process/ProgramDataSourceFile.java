package condo.process;

import condo.model.Room;
import condo.model.Staff;
import condo.model.User;

import java.io.*;
import java.util.ArrayList;

public class ProgramDataSourceFile implements ProgramDataSource
{
    public ArrayList readAllAccount() throws IOException
    {
        File file = new File("csv_file/account.csv");
        FileReader reader = new FileReader(file);
        BufferedReader buffered = new BufferedReader(reader);
        String profile;
        ArrayList<String> readiden = new ArrayList<>();
        ArrayList<String> readuser = new ArrayList<>();
        ArrayList<String> readpass = new ArrayList<>();
        ArrayList<String> readname = new ArrayList<>();

        while ((profile = buffered.readLine()) != null)
        {
            String[] al_read = profile.split(",");
            readiden.add(al_read[0]);
            readuser.add(al_read[1]);
            readpass.add(al_read[2]);
            readname.add(al_read[3]);
        }
        reader.close();

        ArrayList acc = new ArrayList<>();
        acc.add(readiden);
        acc.add(readuser);
        acc.add(readpass);
        acc.add(readname);

        return acc;
    }

    public void writeNewStaff(ArrayList<String> newAccount) throws IOException
    {
        ArrayList acc = readAllAccount();
        ArrayList<String> idList = (ArrayList<String>) acc.get(0);
        ArrayList<String> usernameList = (ArrayList<String>) acc.get(1);
        ArrayList<String> passwordList = (ArrayList<String>) acc.get(2);
        ArrayList<String> nameList = (ArrayList<String>) acc.get(3);

        File writefile = new File("csv_file/account.csv");
        FileWriter fileWriter = new FileWriter(writefile);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        int limit = idList.size();
        for (int i = 0; i < limit; i-=-1)
        {
            writer.write(idList.get(i) + "," + usernameList.get(i) + "," + passwordList.get(i) + "," + nameList.get(i));
            writer.newLine();
        }
        writer.write("STAFF" + "," + newAccount.get(0) + "," + newAccount.get(1) + "," + newAccount.get(2));
        writer.close();
    }

    public void updateLogInStaff(Staff currentStaff) throws IOException
    {
        File readfile = new File("csv_file/staffacc.csv");
        FileReader reader = new FileReader(readfile);
        BufferedReader buffered = new BufferedReader(reader);
        String profile;
        ArrayList<Staff> staffList = new ArrayList<>();
        Staff staff;
        Staff updateStaff = null;
        while ((profile = buffered.readLine()) != null)
        {
            String[] al_read = profile.split(",");
            staff = new Staff(al_read[0], al_read[1], al_read[2], al_read[3], al_read[4], al_read[5]);
            staffList.add(staff);
        }
        reader.close();

        int limit = staffList.size();
        for (int i = 0; i < limit; i-=-1)
        {
            if (staffList.get(i).getUsername().equals(currentStaff.getUsername()))
            {
                updateStaff = new Staff(staffList.get(i).getUsername(),
                        staffList.get(i).getPassword(),
                        staffList.get(i).getName(),
                        staffList.get(i).getSurname(),
                        currentStaff.getDate(),
                        currentStaff.getTime());
                staffList.remove(i);
                limit-=1;
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

        for (int i = 0; i < limit; i-=-1)
        {
            writer.write(staffList.get(i).getUsername() + "," + staffList.get(i).getPassword() + "," + staffList.get(i).getName() + "," + staffList.get(i).getSurname() + "," + staffList.get(i).getDate() + "," + staffList.get(i).getTime());
            writer.newLine();
        }
        writer.close();
    }

    public ArrayList<Staff> showStaffHistory() throws IOException
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

    public boolean addStaff(ArrayList<String> newAccount) throws IOException
    {
        File readfile = new File("csv_file/staffacc.csv");
        FileReader reader = new FileReader(readfile);
        BufferedReader buffered = new BufferedReader(reader);
        String profile;
        ArrayList<String> usernameList = new ArrayList<>();
        ArrayList<String> passwordList = new ArrayList<>();
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<String> surnameList = new ArrayList<>();
        ArrayList<String> dateList = new ArrayList<>();
        ArrayList<String> timeList = new ArrayList<>();
        while ((profile = buffered.readLine()) != null)
        {
            String[] al_read = profile.split(",");
            usernameList.add(al_read[0]);
            passwordList.add(al_read[1]);
            nameList.add(al_read[2]);
            surnameList.add(al_read[3]);
            dateList.add(al_read[4]);
            timeList.add(al_read[5]);
        }
        reader.close();
        int limit = usernameList.size();
        int i = 0;
        int check = 0;
        for (i = 0; i < limit; i-=-1)
        {
            if (newAccount.get(0).equals(usernameList.get(i)))
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
            File writefile = new File("csv_file/staffacc.csv");
            FileWriter fileWriter = new FileWriter(writefile);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (i = 0; i < limit; i-=-1)
            {
                writer.write(usernameList.get(i) + "," + passwordList.get(i) + "," + nameList.get(i) + "," + surnameList.get(i) + "," + dateList.get(i) + "," + timeList.get(i));
                writer.newLine();
            }
            writer.write(newAccount.get(0) + "," + newAccount.get(1) + "," + newAccount.get(2) + "," + newAccount.get(3) + "," + newAccount.get(4) + "," + newAccount.get(5));
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
            writer.write(idList.get(i) + "," + usernameList.get(i) + "," + changedList.get(i) + "," + nameList.get(i));
            writer.newLine();
        }
        writer.close();
    }

    public boolean addNewRoom(Room newRoom) throws IOException
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

        int check = 0;
        for (int i = 0; i < roomsList.size(); i-=-1)
        {
            if (roomsList.get(i).getRoom().equals(newRoom.getRoom()))
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

            for (int i = 0; i < roomsList.size(); i-=-1)
            {
                if (roomsList.get(i).getType().equals("Deluxe"))
                {
                    writer.write(roomsList.get(i).getBuilding() + "," +
                            roomsList.get(i).getFloor() + "," +
                            roomsList.get(i).getType() + "," +
                            roomsList.get(i).getRoomNumber() + "," +
                            roomsList.get(i).getRoom() + "," +
                            roomsList.get(i).getAllResident().get(0) + "," +
                            roomsList.get(i).getAllResident().get(1));
                }
                else if (roomsList.get(i).getType().equals("Suite"))
                {
                    writer.write(roomsList.get(i).getBuilding() + "," +
                            roomsList.get(i).getFloor() + "," +
                            roomsList.get(i).getType() + "," +
                            roomsList.get(i).getRoomNumber() + "," +
                            roomsList.get(i).getRoom() + "," +
                            roomsList.get(i).getAllResident().get(0) + "," +
                            roomsList.get(i).getAllResident().get(1) + "," +
                            roomsList.get(i).getAllResident().get(2) + "," +
                            roomsList.get(i).getAllResident().get(3));
                }
                writer.newLine();

               /* writer.write(roomsList.get(i).getBuilding() + "," +
                        roomsList.get(i).getFloor() + "," +
                        roomsList.get(i).getType() + "," +
                        roomsList.get(i).getRoomNumber() + "," +
                        roomsList.get(i).getRoom());
                writer.newLine();*/
            }

            /*writer.write(newRoom.getBuilding() + "," +
                    newRoom.getFloor() + "," +
                    newRoom.getType() + "," +
                    newRoom.getRoomNumber() + "," +
                    newRoom.getRoom());*/
            writer.close();

            return true;
        }
        else
        {
            return false;
        }
    }

    public ArrayList<Room> showResidentRoom() throws IOException
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

        int check = 0;
        for (int i = 0; i < roomsList.size(); i-=-1)
        {
            if (roomsList.get(i).getRoom().equals(currentRoom.getRoom()))
            {
                roomsList.remove(i);
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

            for (int i = 0; i < roomsList.size(); i-=-1)
            {
                if (roomsList.get(i).getType().equals("Deluxe"))
                {
                    writer.write(roomsList.get(i).getBuilding() + "," +
                            roomsList.get(i).getFloor() + "," +
                            roomsList.get(i).getType() + "," +
                            roomsList.get(i).getRoomNumber() + "," +
                            roomsList.get(i).getRoom() + "," +
                            roomsList.get(i).getAllResident().get(0) + "," +
                            roomsList.get(i).getAllResident().get(1));
                }
                else if (roomsList.get(i).getType().equals("Suite"))
                {
                    writer.write(roomsList.get(i).getBuilding() + "," +
                            roomsList.get(i).getFloor() + "," +
                            roomsList.get(i).getType() + "," +
                            roomsList.get(i).getRoomNumber() + "," +
                            roomsList.get(i).getRoom() + "," +
                            roomsList.get(i).getAllResident().get(0) + "," +
                            roomsList.get(i).getAllResident().get(1) + "," +
                            roomsList.get(i).getAllResident().get(2) + "," +
                            roomsList.get(i).getAllResident().get(3));
                }
                writer.newLine();
            }
            writer.close();
        }
    }

}
