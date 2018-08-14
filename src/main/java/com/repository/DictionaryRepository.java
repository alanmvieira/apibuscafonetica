package com.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Repository;

@Repository
public class DictionaryRepository {

	private static final String JDBC_DRIVER = "org.h2.Driver";
	private static final String DB_URL = "jdbc:h2:~/phoneticsearch";

	private static final String USER = "phoneticsearch";
	private static final String PASS = "phoneticsearch";

	public Set<String> getWords() {

		Set<String> words = new HashSet<String>();

		Connection conn = null;
		Statement statement = null;
		try {
			
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			statement = conn.createStatement();
			String sql = "Select * from dictionary";

			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {

				String nome = rs.getString("word");
				words.add(nome);

			}

			rs.close();
			statement.close();
			conn.close();

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return words;
	}

}
