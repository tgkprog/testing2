package org.s2n.ddt.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbutils.DbUtils;
import org.apache.log4j.Logger;
import org.apache.tomcat.dbcp.dbcp.BasicDataSourceFactory;

/**
 * This class is used to instantiate the data source using the properties file.
 * Pooled connection creation, closing implementation is provided with this
 * class.
 */
public class DBUtils {
	static{
		org.s2n.ddt.util.LangUtils.log4Default(true);
	}
	private static final Logger logger = Logger.getLogger(DBUtils.class);
	private static volatile DataSource dsObj;

	static {
		initialize();
	}

	/**
	 * Method to initialize the properties and the datasource by using the
	 * database properties configured in user defined file
	 */
	public static void initialize() {
	
		try {
			logger.info("Reinitialize db pool ");
			Properties mainProps = org.s2n.ddt.bean.UtlConf.getProperties();
			dsObj = BasicDataSourceFactory.createDataSource(mainProps);
		} catch (Exception e) {
			logger.warn("initialize " + e, e);
		}
	}

	

	/**
	 * Method to get the pooled connection.
	 * 
	 * @return connection
	 */
	public static Connection getConnection() {
		Connection connection = null;
		if (null == dsObj) {
			synchronized (DBUtils.class) {
				if (null == dsObj) {
					initialize();
				}
			}
		}
		try {
			connection = dsObj.getConnection();
		} catch (SQLException e) {
			logger.warn("initialize " + e, e);
		}
		return connection;
	}

	/**
	 * Method to get the data-source instance
	 * 
	 * @return DataSource
	 */
	public static DataSource getDataSource() {
		if (null == dsObj) {
			synchronized (DBUtils.class) {
				if (null == dsObj) {
					initialize();
				}
			}
		}
		return dsObj;
	}

	/**
	 * Method to close the connection, Statement and Result Set
	 * 
	 * @param conection
	 * @param statement
	 * @param resultset
	 */
	public static void closeConnection(Connection conection,
			Statement statement, ResultSet resultset) {
		if (null != resultset){
			DbUtils.closeQuietly(resultset);
		}
		if (null != statement){
			DbUtils.closeQuietly(statement);
		}
		if (null != conection){
			DbUtils.closeQuietly(conection);
		}
	}
	
	public static void main(String[] args) {
		
		Connection con = null;
		System.out.println("s con ");
		try{
			con = getConnection();
		}catch(Throwable e){
			System.out.println(e);
			e.printStackTrace();
		}
		System.out.println("Got con "+ con);
	}
}
