/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

/**
 *
 * @author fatema
 */
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class DataSource_1 {

    public Connection createConnection() {
        Connection connection = null;
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            javax.sql.DataSource ds = (javax.sql.DataSource) envCtx.lookup("jdbc/Registrar");
            connection = ds.getConnection();
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(DataSource_1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
}
