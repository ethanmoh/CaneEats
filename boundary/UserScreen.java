package edu.umiami.caneeats.boundary;

import edu.umiami.caneeats.controllers.UserController;
import edu.umiami.caneeats.entityobjects.User;

import java.util.Scanner;
import java.util.Set;

public class UserScreen {

    public UserController userController;

    UserScreen() {
        this.userController = new UserController();
    }

    public void searchUsers() {
        Scanner userNameScanner = new Scanner(System.in);
        System.out.println("Search User Screen");
        System.out.print("What is the name of the user : ");
        String userName = userNameScanner.nextLine();


        Set<User> userList = userController.getUsersList(userName);

        System.out.println("The list of users that match your query is :");

        if (userList == null || userList.isEmpty()) {
            System.out.println("Sorry no matches found!!");
        } else
            for (User r : userList)
                System.out.println(r);

    }

    public void addUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Add User Screen");

        System.out.print("What is the name of the user: ");
        String userName = scanner.nextLine();

        System.out.print("What is the phone number of the user: ");
        String userPhone = scanner.nextLine();

        System.out.print("What is the address of the user: ");
        String userAddress = scanner.nextLine();

        User newUser = new User();
        newUser.setCustomerName(userName);
        newUser.setCustomerPhone(userPhone);
        newUser.setCustomerAddress(userAddress);

        int result = userController.addUser(newUser);
        if (result == 1) {
            System.out.println("User added successfully.");
        } else {
            System.out.println("Failed to add user.");
        }
    }

    public void updateUser() {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Update User Screen");

        System.out.print("Enter the user ID to update: ");
        int userID = inputScanner.nextInt();

        System.out.print("Enter the new name of the user: ");
        String userName = inputScanner.next();

        System.out.print("Enter the new phone number of the user: ");
        String userPhone = inputScanner.next();

        System.out.print("Enter the new address of the user: ");
        String userAddress = inputScanner.next();

        User userToUpdate = new User();
        userToUpdate.setCustomerID(userID);
        userToUpdate.setCustomerName(userName);
        userToUpdate.setCustomerPhone(userPhone);
        userToUpdate.setCustomerAddress(userAddress);

        int updateCount = userController.modifyUser(userToUpdate);
        System.out.println("Updated " + updateCount + " user(s)");
    }

    public void deleteUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Delete User Screen");
        System.out.print("Enter the ID of the user you want to delete: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        int rowsAffected = userController.deleteUser(userId);
        if (rowsAffected == 0) {
            System.out.println("No user found with ID " + userId + ", no records deleted.");
        } else {
            System.out.println("User with ID " + userId + " deleted successfully.");
        }
    }



}