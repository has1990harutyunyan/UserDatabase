package main;


import manager.UserManager;
import model.User;
import constants.commands;

import java.sql.SQLException;
import java.util.Scanner;

public class FirstExample {

    private static Scanner scanner = new Scanner(System.in);
    private static UserManager userManager = new UserManager();


    public static void main(String[] args) throws SQLException {


        while (true) {
            runProject();
            int inputNum = scanner.nextInt();
            if (inputNum == commands.EXIT)
                System.exit(0);
            switch (inputNum) {
                case commands.ADD_USER:
                    addUser();
                    break;
                case commands.DELETE_BY_ID:
                    deleteById();
                    break;
                case commands.UPDATE_USER_DATA:
                    updateUserData();
                    break;
                case commands.PRINT_USERS:
                    System.out.println(userManager.getUserList());
                    break;
                default:
                    System.out.println("Invalid number.");

            }


        }


    }

    static void runProject() {
        System.out.println("Input " + commands.EXIT + " to exit.");
        System.out.println("Input " + commands.ADD_USER + " to add users.");
        System.out.println("Input " + commands.DELETE_BY_ID + " to delete user by id.");
        System.out.println("Input " + commands.UPDATE_USER_DATA + " to update userdata.");
        System.out.println("Input " + commands.PRINT_USERS + " to print all the users.");

    }


    static void addUser() {
        System.out.println("Enter your name, surname, age, email and password.");
        String data = scanner.next();
        String[] dataArray = data.split(",");
        User user = new User();
        user.setName(dataArray[0]);
        user.setSurname(dataArray[1]);
        try {
            user.setAge(Integer.parseInt(dataArray[2]));
        } catch (NumberFormatException e) {
            System.out.println("Input NUMBERS for age.");
        }
        user.setEmail(dataArray[3]);
        user.setPassword(dataArray[4]);

        try {
            userManager.addUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    static void updateUserData() {
        System.out.println("Enter the id of the user whose info should be updated " + "and input the new age.");
        String ageUpdate = scanner.next();
        String[] ageUpdateArray = ageUpdate.split(",");
        int id = Integer.parseInt(ageUpdateArray[0]);
        try {
            int age = Integer.parseInt(ageUpdateArray[1]);
            userManager.updateUserData(id, age);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    static void deleteById() {
        System.out.println("Input id to delete the user.");
        int id = scanner.nextInt();
        try {
            userManager.deleteUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}
