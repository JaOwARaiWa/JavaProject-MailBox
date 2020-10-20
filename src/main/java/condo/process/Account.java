package condo.process;

import condo.model.Address;
import condo.model.Staff;
import condo.model.User;

import java.io.*;
import java.util.ArrayList;

public interface Account
{
    ArrayList readAcc() throws IOException;
    ArrayList readAddress(int index) throws IOException;
    void changePass(User currentuser, String newPass) throws IOException;
    boolean addStaff(ArrayList<String> newAccount) throws IOException;
    void writeNewStaff(ArrayList<String> newAccount) throws IOException;
    ArrayList<Staff> staffHistory() throws IOException;
    void updateLogIn(Staff currentStaff) throws IOException;
}
