package by.bsk.oracle.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.bsk.oracle.dao.exception.DAOException;

/**
 * ��������� ����� {@code DBUtil} ��������� ��������������� ������ ��� ������ ��
 * ����� DAO;
 * 
 * @author Vladislav
 *
 */
public final class DBUtil {

	private static final Logger logger = LogManager.getLogger(DBUtil.class);

	private static DBUtil dbUtil;

	private String dbDriver;
	private String dbUsername;
	private String dbPassword;
	private String dbUrl;

	/**
	 * ����� {@code getInstance} �������� ������� Singleton
	 * 
	 * @return
	 */
	public static synchronized DBUtil getInstance() {
		if (dbUtil == null) {
			synchronized (DBUtil.class) {
				if (dbUtil == null) {
					dbUtil = new DBUtil();
				}
			}
		}
		return dbUtil;
	}

	private DBUtil() {
	}

	/**
	 * ����� {@code  openConnection} �������������� ���������� � ����� ������.
	 * 
	 * @return connection
	 * @throws DAOException
	 *             ���� � ���� ���������� �� ������� �������� ��� ������������
	 *             ������ ����� ������ ��� ���� ������;
	 */
	public Connection openConnection() throws DAOException {
		try {
			logger.debug("DBUtil.openConnection()");
			DBResourceManager dbResourceManager = DBResourceManager.getInstance();
			dbDriver = dbResourceManager.getProperty(DBParameter.DB_DRIVER);
			dbUsername = dbResourceManager.getProperty(DBParameter.DB_USERNAME);
			dbPassword = dbResourceManager.getProperty(DBParameter.DB_PASSWORD);
			dbUrl = dbResourceManager.getProperty(DBParameter.DB_URL);

			Class.forName(dbDriver);
			Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
			return connection;
		} catch (SQLException e) {
			logger.debug("DBUtil.openConnection() cant connect");
			throw new DAOException("DAO layer: can't connect to database", e);

		} catch (Exception e) {
			logger.debug("DBUtil.openConnection() no driver");
			throw new DAOException("DAO layer: can't find driver", e);
		}
	}
}
