package de.hrw.wi.databaseSetup;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Sinan Ayten
 *
 */
public class InitialDatabaseSetup {
    public static void main(String[] args) throws SQLException {

        Connection c = DriverManager
                .getConnection("jdbc:hsqldb:file:../wawi-db-layer/database/wawi_db", "sa", "");
        c.setAutoCommit(false);
        System.out.println("Autocommit " + (c.getAutoCommit() ? "on" : "off"));

        c.createStatement().executeQuery("DROP TABLE STOCK IF EXISTS CASCADE");
        c.createStatement().executeQuery("DROP TABLE WAREHOUSES IF EXISTS CASCADE");
        c.createStatement().executeQuery("DROP TABLE PRODUCTS IF EXISTS CASCADE");
        c.createStatement().executeQuery("DROP TABLE CUSTOMER IF EXISTS CASCADE");
        c.createStatement().executeQuery("DROP TABLE ORDERS IF EXISTS CASCADE");
        c.createStatement().executeQuery("DROP TABLE STORNOORDERS IF EXISTS CASCADE");

        c.createStatement().executeQuery(
                "CREATE TABLE PRODUCTS (articleCode varchar(13) PRIMARY KEY, name varchar(255), size INTEGER, status varchar(255)) ");

        c.createStatement().executeQuery(
                "CREATE TABLE WAREHOUSES (number INTEGER PRIMARY KEY, maxBin INTEGER, maxSize INTEGER) ");
        c.createStatement().executeQuery(
                "CREATE TABLE STOCK (number INTEGER, bin INTEGER, articleCode varchar(13), amount INTEGER,"
                        + " constraint PK_STOCK PRIMARY KEY (number, bin),"
                        + " constraint FK_PRODUCTS FOREIGN KEY (articleCode) REFERENCES PRODUCTS(articleCode),"
                        + " constraint FK_WAREHOUSE FOREIGN KEY (number) REFERENCES WAREHOUSES(number))");

        c.createStatement().executeQuery(
                "CREATE TABLE CUSTOMER (customerID INTEGER PRIMARY KEY, firstName VARCHAR(255), lastName VARCHAR(255), street VARCHAR(255),"
                        + " houseNr INTEGER, city VARCHAR(255), country VARCHAR(255), postalCode INTEGER, yearOfBirth INTEGER, gender VARCHAR(1), sales INTEGER, eMail VARCHAR(255), phoneNumber VARCHAR(255))");

        c.createStatement().executeQuery(
                "INSERT INTO PRODUCTS VALUES ('8806085948587','Samsung BD-H5500 3D Blu-ray-Player',3, 'normal')");
        c.createStatement().executeQuery(
                "INSERT INTO PRODUCTS VALUES ('0885909560462','Apple TV MD199FD/A',2, 'normal')");
        c.createStatement().executeQuery(
                "INSERT INTO PRODUCTS VALUES ('0636926062442','TomTom Start 25 M Europe Traffic',2, 'normal')");
        c.createStatement().executeQuery(
                "INSERT INTO PRODUCTS VALUES ('8806084893826','LG 40UB800V 101 cm (40 Zoll) LED-Backlight-Fernseher',8, 'normal')");
        c.createStatement().executeQuery(
                "INSERT INTO PRODUCTS VALUES ('4250366833286','Gigaset C430 A Duo Dect-Schnurlostelefon mit Anrufbeantworter',2, 'normal')");
        c.createStatement().executeQuery(
                "INSERT INTO PRODUCTS VALUES ('0799637096608','Ipow schwarz Selfie Stange',1, 'normal')");

        c.createStatement().executeQuery("INSERT INTO WAREHOUSES VALUES (0,30,5)");
        c.createStatement().executeQuery("INSERT INTO WAREHOUSES VALUES (1,30,10)");

        c.createStatement().executeQuery("INSERT INTO STOCK VALUES (0,0,'8806085948587',1)");
        c.createStatement().executeQuery("INSERT INTO STOCK VALUES (0,1,'8806085948587',1)");
        c.createStatement().executeQuery("INSERT INTO STOCK VALUES (0,2,'8806085948587',1)");
        c.createStatement().executeQuery("INSERT INTO STOCK VALUES (0,3,'0885909560462',2)");
        c.createStatement().executeQuery("INSERT INTO STOCK VALUES (0,4,'0636926062442',2)");
        c.createStatement().executeQuery("INSERT INTO STOCK VALUES (0,5,'4250366833286',2)");
        c.createStatement().executeQuery("INSERT INTO STOCK VALUES (0,6,'0799637096608',4)");
        c.createStatement().executeQuery("INSERT INTO STOCK VALUES (1,0,'8806084893826',1)");
        c.createStatement().executeQuery("INSERT INTO STOCK VALUES (1,1,'8806084893826',1)");
        c.createStatement().executeQuery("INSERT INTO STOCK VALUES (1,2,'0636926062442',5)");

        c.createStatement().executeQuery(
                "CREATE TABLE HAFT (customerID INTEGER PRIMARY KEY, firstName VARCHAR(255), lastName VARCHAR(255), street VARCHAR(255),"
                        + " houseNr INTEGER, city VARCHAR(255), country VARCHAR(255), postalCode INTEGER, yearOfBirth INTEGER, gender VARCHAR(1), sales INTEGER, eMail VARCHAR(255), phoneNumber VARCHAR(255))");

        c.createStatement().executeQuery(
                "INSERT INTO CUSTOMER VALUES (1001,'Sinan','Ayten','Ebenstrasse',22,'Nürtingen','Deutschland',72622,1995,'M',222,'ebengami@gmx.net','01767262222')");
        c.createStatement().executeQuery(
                "INSERT INTO CUSTOMER VALUES (1002,'Alexander','Böhm','Ravensburgerstrasse',69,'Ravensburg','Deutschland',82622,1994,'M',11007,'alex.böhm@gmx.net','0176123456')");
        c.createStatement().executeQuery(
                "INSERT INTO CUSTOMER VALUES (1003,'Mateo','Kevric','Blenderstrasse',31,'Weingarten','Kroatien',88250,1997,'D',106,'m.kevric@web.de','0172626652')");
        c.createStatement().executeQuery(
                "INSERT INTO CUSTOMER VALUES (1004,'Aykut','Topal','Traubenstrasse',3,'Baienfurt','Türkei',88250,1996,'M',22,'a.topal@gmail.com','015767736')");
        c.createStatement().executeQuery(
                "INSERT INTO CUSTOMER VALUES (1005,'Denis','Rammstein','Im Höfle',11,'Bad Waldsee','Deutschland',88251,1995,'M',69,'dramm@gmx.de','016260982')");
        c.createStatement().executeQuery(
                "INSERT INTO CUSTOMER VALUES (1006,'Rammstein','Mond','Jordery Platz',56,'Trauben','Österreich',88250,1998,'W',29,'laura.mond@web.de','0172967215')");

        c.createStatement().executeQuery(
                "CREATE TABLE ORDERS (ordernumber INTEGER, customerID INTEGER, articleCode varchar(13), orderDate varchar(12), amount INTEGER, price double, priceTotal double,"

                        + "PRIMARY KEY (ordernumber, customerID, articleCode),"
                        + "FOREIGN KEY (articleCode) REFERENCES PRODUCTS(articleCode) ON DELETE CASCADE,"
                        + "FOREIGN KEY (customerID) REFERENCES CUSTOMER(customerID) ON DELETE CASCADE)");

        c.createStatement().executeQuery(
                "CREATE TABLE STORNOORDERS (ordernumber INTEGER, customerID INTEGER, articleCode varchar(13), orderDate varchar(12), amount INTEGER, price double, priceTotal double, reason varchar(20), stornoDate varchar(12),"

                        + "PRIMARY KEY (ordernumber, customerID, articleCode),"
                        + "FOREIGN KEY (articleCode) REFERENCES PRODUCTS(articleCode) ON DELETE CASCADE,"
                        + "FOREIGN KEY (customerID) REFERENCES CUSTOMER(customerID) ON DELETE CASCADE)");

        c.createStatement().executeQuery(
                "INSERT INTO ORDERS VALUES (1, 1005, '8806085948587', '2019.05.13', 1, 200, 200)");
        c.createStatement().executeQuery(
                "INSERT INTO ORDERS VALUES (2, 1001, '0885909560462', '2019.04.07', 2, 300, 600)");
        c.createStatement().executeQuery(
                "INSERT INTO ORDERS VALUES (3, 1003, '0636926062442', '2018.11.22', 1, 400, 400)");
        c.createStatement().executeQuery(
                "INSERT INTO ORDERS VALUES (4, 1004, '0799637096608', '2019.01.18', 3, 100, 300)");

        c.createStatement().executeQuery(
                "INSERT INTO ORDERS VALUES (5, 1004, '0885909560462', '2019.01.18', 2, 300, 600)");
        c.createStatement().executeQuery(
                "INSERT INTO ORDERS VALUES (6, 1004, '0799637096608', '2019.05.18', 7, 100, 700)");
        c.createStatement().executeQuery(
                "INSERT INTO ORDERS VALUES (7, 1004, '0885909560462', '2019.01.18', 5, 300, 1500)");
        c.createStatement().executeQuery(
                "INSERT INTO ORDERS VALUES (8, 1005, '0799637096608', '2019.01.18', 7, 100, 700)");

        /**
         * @author Mateo Kevric
         */

      
        c.createStatement().executeQuery(
                "INSERT INTO ORDERS VALUES (9, 1002, '0885909560462', '2019.06.13', 1, 200, 200)");
        c.createStatement().executeQuery(
                "INSERT INTO ORDERS VALUES (10, 1002, '0885909560462', '2019.06.07', 6, 300, 600)");
        c.createStatement().executeQuery(
                "INSERT INTO ORDERS VALUES (11, 1002, '0885909560462', '2018.06.22', 1, 400, 400)");
        c.createStatement().executeQuery(
                "INSERT INTO ORDERS VALUES (12, 1002, '0885909560462', '2019.06.18', 3, 100, 300)");

        c.createStatement().executeQuery(
                "INSERT INTO ORDERS VALUES (13, 1006, '0799637096608', '2019.06.18', 8, 300, 600)");
        c.createStatement().executeQuery(
                "INSERT INTO ORDERS VALUES (14, 1006, '0799637096608', '2019.06.18', 7, 100, 700)");
        c.createStatement().executeQuery(
                "INSERT INTO ORDERS VALUES (15, 1006, '0799637096608', '2019.06.18', 5, 300, 1500)");
        c.createStatement().executeQuery(
                "INSERT INTO ORDERS VALUES (16, 1006, '0799637096608', '2019.06.18', 2, 100, 700)");
        c.createStatement().executeQuery(
                "INSERT INTO ORDERS VALUES (17, 1002, '0636926062442', '2019.06.23', 0, 300, 600)");
        c.createStatement().executeQuery(
                "INSERT INTO ORDERS VALUES (18, 1002, '0636926062442', '2019.06.23', 1, 100, 700)");
        c.createStatement().executeQuery(
                "INSERT INTO ORDERS VALUES (30, 1006, '0636926062442', '2019.06.23', 6, 300, 1500)");
        c.createStatement().executeQuery(
                "INSERT INTO ORDERS VALUES (31, 1006, '0636926062442', '2019.06.23', 2, 100, 700)");

        /**
         * @author Alexander Böhm
         */
        // Stonierte Bestellungen

        c.createStatement().executeQuery(
                "INSERT INTO STORNOORDERS VALUES (19, 1005, '8806085948587', '2019.03.13', 1, 200, 200, 'DEFEKT', '2019.03.20')");
        c.createStatement().executeQuery(
                "INSERT INTO STORNOORDERS VALUES (20, 1001, '0885909560462', '2019.03.13', 5, 300, 1500, 'Zu Spaete Lieferung', '2019.03.20')");
        c.createStatement().executeQuery(
                "INSERT INTO STORNOORDERS VALUES (21, 1003, '0636926062442', '2018.05.22', 2, 400, 800, 'Falscher Artikel', '2018.05.28')");
        c.createStatement().executeQuery(
                "INSERT INTO STORNOORDERS VALUES (22, 1001, '0799637096608', '2019.05.18', 5, 300, 1500, 'Falscher Artikel', '2019.06.20')");
        c.createStatement().executeQuery(
                "INSERT INTO STORNOORDERS VALUES (23, 1001, '0885909560462', '2019.05.18', 5, 500, 2500, 'DEFEKT', '2019.06.20')");
        c.createStatement().executeQuery(
                "INSERT INTO STORNOORDERS VALUES (24, 1001, '0799637096608', '2019.05.18', 7, 100, 700, 'Falscher Artikel', '2019.06.20')");
        c.createStatement().executeQuery(
                "INSERT INTO STORNOORDERS VALUES (25, 1004, '0885909560462', '2019.01.18', 5, 300, 1500, 'DEFEKT', '2019.03.22')");
        c.createStatement().executeQuery(
                "INSERT INTO STORNOORDERS VALUES (26, 1005, '0799637096608', '2019.02.21', 7, 100, 700, 'DEFEKT', '2019.03.22')");
        c.createStatement().executeQuery(
                "INSERT INTO STORNOORDERS VALUES (27, 1001, '0799637096608', '2019.05.18', 7, 100, 700, 'Falscher Artikel', '2019.05.25')");
        c.createStatement().executeQuery(
                "INSERT INTO STORNOORDERS VALUES (28, 1004, '0885909560462', '2019.01.18', 5, 300, 1500, 'Zu Spaete Lieferung', '2019.03.20')");
        c.createStatement().executeQuery(
                "INSERT INTO STORNOORDERS VALUES (29, 1005, '0799637096608', '2019.02.21', 7, 100, 700, 'Zu Spaete Lieferung', '2019.03.20')");

        c.commit();
        c.close();
        System.out.println("ready");

    }

}