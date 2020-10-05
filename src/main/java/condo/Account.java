package condo;

import java.io.*;
import java.util.ArrayList;

public interface Account
{
    static ArrayList readAcc() throws IOException
    {
        File file = new File("csv_file/account.csv");
        FileReader reader = new FileReader(file);
        BufferedReader buffered = new BufferedReader(reader);
        String profile;
        ArrayList<String> readuser = new ArrayList<>();
        ArrayList<String> readpass = new ArrayList<>();
        ArrayList<String> readiden = new ArrayList<>();

        while ((profile = buffered.readLine()) != null)
        {
            String[] al_read = profile.split(",");
            readuser.add(al_read[0]);
            readpass.add(al_read[1]);
            readiden.add(al_read[2]);
        }
        reader.close();

        ArrayList acc = new ArrayList<>();
        acc.add(readuser);
        acc.add(readpass);
        acc.add(readiden);

        return acc;
    }

    static void changePass(int index, String newPass) throws IOException
    {
        ArrayList acc = readAcc();
        ArrayList<String> userList = (ArrayList<String>) acc.get(0);
        ArrayList<String> passList = (ArrayList<String>) acc.get(1);
        ArrayList<String> idenList = (ArrayList<String>) acc.get(2);
        ArrayList<String> newPList = new ArrayList<>();

        File file = new File("csv_file/account.csv");
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter writer = new BufferedWriter(fileWriter);

        for (int i = 0; i < userList.size(); i-=-1)
        {
            if (i == index)
            {
                newPList.add(newPass);
            }
            else
            {
                newPList.add(passList.get(i));
            }
        }

        for (int i = 0; i < userList.size(); i-=-1)
        {
            writer.write(userList.get(i) + "," + newPList.get(i) + "," + idenList.get(i));
            writer.newLine();
        }
        writer.close();
    }

}
