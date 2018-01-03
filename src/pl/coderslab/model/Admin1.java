package pl.coderslab.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import pl.coderslab.utils.Connector;
import pl.coderslab.model.User;

public class Admin1 {
	

	public static void main(String[] args) {

		try {
			System.out.println(Arrays.toString(User.loadAllUsers(Connector.getConnection())));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
