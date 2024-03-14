package edu.umiami.caneeats.utilities;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {


    //This is an example of Singleton Design.
    //There is only 1 object will be existing for the entire class.

    public Connection getDbConnect() {
        return dbConnect;
    }

    private Connection dbConnect;

    private static DBConnection dbConnection;

    private DBConnection()
    {
        String connectionURL = "jdbc:mysql://localhost:3306/";
        String schema = "CaneEats";
        String userName = "root";
        String password = "password";
        try {
            dbConnect = DriverManager.getConnection(connectionURL+schema, userName, password);
        }catch(Exception e)
        {
            System.out.println("Unable to get Connection !!");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    //This method will give the copy of existing object, if the object doesn't exist, it will create one.
    public static DBConnection getInstance()
    {
        if(dbConnection == null)
        {
            dbConnection = new DBConnection();
        }

        return dbConnection;
    }
}
