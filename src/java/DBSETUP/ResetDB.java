
/*This stand-alone application to regenerate tables and data to the database.
 *It deletes existing tables and then recreates the tables and adds data to each table.
 *To use this app run this file.
 *
 *
 *@author Phong
**/
package DBSETUP;
import MODEL.DAO.DatabaseConnector;
import java.sql.*;
import java.util.logging.*;

public class ResetDB {
    public static void main(String[] args) {
        try {
            DatabaseConnector connector = new DatabaseConnector();
            Connection conn = connector.openConnection();
            Statement st = conn.createStatement();
            
            // Drop Tables
            System.out.println("Dropping tables.");
            deleteTables(st);
            
            // Create Tables
            System.out.println("Creating tables.");
            createTables(st);
            
            // Add Data
            System.out.println("Adding data to tables.");
            addData(st);
            
            System.out.println("Done.");
            System.out.println("Remember to refresh database.");
            
            connector.closeConnection(); //Professional practice is to close connection to database once operations are finalized
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ResetDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void deleteTables(Statement st) throws SQLException {
        
        // Order matters due to foreign key
        String[] query = new String[99];
        int count = 0;
        
        query[count++] = "DROP TABLE \"SHIPMENT\"";
        query[count++] = "DROP TABLE \"PAYMENT\"";
        query[count++] = "DROP TABLE \"ORDER\"";
        query[count++] = "DROP TABLE \"USERACCESSLOG\"";
        query[count++] = "DROP TABLE \"STAFF\"";
        query[count++] = "DROP TABLE \"MOVIE\"";
        query[count++] = "DROP TABLE \"CUSTOMER\"";
        query[count++] = "DROP TABLE \"USER\"";
        
        for (int i = 0; i < count; i++) {
            try {
                st.execute(query[i]);    
            } catch (SQLException e){}
        }
    }
    
    private static void createTables(Statement st) throws SQLException {   
        
        // Order matters due to foreign key
        String[] query = new String[99];
        int count = 0;
        
        query[count++] = "create table \"USER\" ( \"ID\" VARCHAR(50) NOT NULL, \"EMAIL\" VARCHAR(50), \"NAME\" VARCHAR(50), \"PASSWORD\" VARCHAR(50), \"PHONENUMBER\" VARCHAR(50), \"STATUS\" VARCHAR(50), PRIMARY KEY (\"ID\"))";
        query[count++] = "create table \"CUSTOMER\" ( \"ID\" VARCHAR(50) NOT NULL, \"NAME\" VARCHAR(50), \"EMAIL\" VARCHAR(50), \"TYPE\" VARCHAR(50), \"ADDRESS\" VARCHAR(100), \"STATUS\" VARCHAR(50), PRIMARY KEY (\"ID\"))";
        query[count++] = "create table \"MOVIE\" ( \"ID\" VARCHAR(50) NOT NULL, \"TITLE\" VARCHAR(50), \"GENRE\" VARCHAR(50), \"PRICE\" DECIMAL(19,2),\"STOCK\" INT, \"STATUS\" VARCHAR(50), PRIMARY KEY (\"ID\") )";
        //query[count++] = "create table \"STAFF\" ( \"ID\" VARCHAR(50) NOT NULL, \"USERID\" VARCHAR(50), \"EMAIL\" VARCHAR(50), \"NAME\" VARCHAR(50), \"POSITION\" VARCHAR(50), \"ADDRESS\" VARCHAR(100), \"STATUS\" VARCHAR(50), PRIMARY KEY (\"ID\"), FOREIGN KEY (\"USERID\") REFERENCES \"USER\" (\"ID\")  on delete set null )";
        query[count++] = "create table \"USERACCESSLOG\" ( \"ID\" VARCHAR(50) NOT NULL, \"USERID\" VARCHAR(50), \"ACCESSTYPE\" VARCHAR(50), \"TIMESTAMP\" TIMESTAMP, PRIMARY KEY (\"ID\"), FOREIGN KEY (\"USERID\") REFERENCES \"USER\" (\"ID\")  on delete set null )";
        query[count++] = "create table \"ORDER\" ( \"ID\" VARCHAR(50) NOT NULL, \"CUSTOMERID\" VARCHAR(50), \"MOVIEID\" VARCHAR(50), \"USERID\" VARCHAR(50), \"PRICE\" DECIMAL(19,2), \"AMOUNT\" INT, \"TOTALPRICE\" DECIMAL(19,2), \"DATE\" DATE, \"STATUS\" VARCHAR(50), PRIMARY KEY (\"ID\"), FOREIGN KEY (\"CUSTOMERID\") REFERENCES \"CUSTOMER\" (\"ID\") on delete set null , FOREIGN KEY (\"MOVIEID\") REFERENCES \"MOVIE\" (\"ID\") on delete set null, FOREIGN KEY (\"USERID\") REFERENCES \"USER\" (\"ID\") on delete set null  )";
        
        //query[count++] = "create table \"PAYMENT\" ( \"ID\" VARCHAR(50) NOT NULL, \"ORDERID\" VARCHAR(50), \"METHOD\" VARCHAR(50), \"AMOUNT\" DECIMAL(19,2), \"DATE\" DATE,   PRIMARY KEY (\"ID\"), FOREIGN KEY (\"ORDERID\") REFERENCES \"ORDER\" (\"ID\") on delete set null  )";
        //query[count++] = "create table \"SHIPMENT\" ( \"ID\" VARCHAR(50) NOT NULL, \"ORDERID\" VARCHAR(50), \"METHOD\" VARCHAR(50), \"DATE\" DATE,    PRIMARY KEY (\"ID\"), FOREIGN KEY (\"ORDERID\") REFERENCES \"ORDER\" (\"ID\")  on delete set null )";
        
        for (int i = 0; i < count; i++) {
            try {
                st.execute(query[i]);    
            } catch (SQLException e){}
        }
        
    }
    
    private static void addData(Statement st) throws SQLException{
        String[] query = new String[999];
        int count = 0;

        // USER DATA
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456789', 'HaydenBainton@jourrapide.com',    'Hayden', 'aiTah9vuGu',     '0282941740', 'ACTIVE')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456790', 'HenryDodgshun@superrito.com',     'Henry', 'rei0She6hi',      '0290638276', 'DEACTIVE')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456791', 'PatrickLetcher@teleworm.us',      'Patrick', 'Eef7aeYah4',    '0288656511', 'CANCELLED')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456792', 'SpencerVirgo@teleworm.us',        'Spencer', 'mahH0poph',     '0261103269', 'ACTIVE')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456793', 'SofiaBlundell@superrito.com', 'Sofia', 'Ooteedie6h',          '0249887100', 'DEACTIVE')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456794', 'LilianShapcott@armyspy.com', 'Lilian', 'Ood0aesivi',          '0262597177', 'CANCELLED')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456795', 'AaronBrifman@rhyta.com', 'Aaron', 'Phaehoh1t',                '0287772381', 'ACTIVE')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456796', 'ZacharyElkington@dayrep.com', 'Zachary', 'oB1en8Ch',          '0233293524', 'DEACTIVE')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456797', 'AaronTrouette@einrot.com', 'Aaron', 'mah5Phohha',             '0261356661', 'CANCELLED')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456798', 'OwenMacdonald@jourrapide.com', 'Owen', 'iePh4Quah6',          '0293132088', 'ACTIVE')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456799', 'SiennaMcKeown@gustr.com', 'Sienna', 'UbeeNgi7b',              '0253488738', 'DEACTIVE')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456800', 'EllaWeindorfer@gustr.com', 'Ella', 'Yae5ait9ot',              '0294501815', 'CANCELLED')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456801', 'LucindaCouvreur@jourrapide.com', 'Lucinda', 'Eech8shie',      '(08) 9071 7248', 'ACTIVE')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456802', 'MiaBurges@cuvox.de', 'Mia', 'oocai2Ot', '0287068402',  'DEACTIVE')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456803', 'NoahODonovan@armyspy.com', 'Noah', 'Sichaiw2hoo', '0740994645', 'CANCELLED')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456804', 'StephanieFereday@einrot.com', 'Stephanie', 'IP8thath', '5362012712', 'ACTIVE')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456805', 'DylanSmeaton@einrot.com', 'Dylan', 'ieMoov8oo', '0233293524', 'DEACTIVE')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456806', 'GabrielForlong@einrot.com', 'Gabriel', 'Aeh0eeghoo', '0233293524', 'CANCELLED')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456807', 'PoppySuttor@gustr.com', 'Poppy', 'eej1Eb2sie', '0253831919', 'ACTIVE')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456808', 'FinnBroome@gustr.com', 'Finn', 'lohp8Iewi', '02538315870', 'DEACTIVE')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456809', 'GabrielAlgeranoff@fleckens.hu', 'Gabriel', 'bai2en2aH', '02538312273', 'CANCELLED')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456810', 'RebeccaFreame@einrot.com', 'Rebecca', 'eePeo5ee', '02538313842', 'ACTIVE')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456811', 'OliviaBodenwieser@einrot.com', 'Olivia', 'koov1yaiThoh', '02538311256', 'DEACTIVE')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456812', 'JamieToRot@gustr.com', 'Jamie', 'juv5ahhooSh', '02538316411', 'CANCELLED')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456813', 'LolaHeagney@rhyta.com', 'Lola', 'ooca7puJ0aing', '02538312195', 'ACTIVE')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456814', 'LeahBury@superrito.com', 'Leah', 'euh5wai5Dah', '02538315485', 'DEACTIVE')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456815', 'TaylahTench@fleckens.hu', 'Taylah', 'Ohy4aeNgoh', '02538315051', 'CANCELLED')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456816', 'GraceLillico@gustr.com', 'Grace', 'ahjiZahl7th', '02538314041', 'ACTIVE')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456817', 'BenEdmondstone@armyspy.com', 'Ben', 'Ove4moh8ai', '02538310770', 'DEACTIVE')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456818', 'KatieGarratt@jourrapide.com', 'Katie', 'ood6IShoe', '02538311485', 'CANCELLED')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456819', 'KatieHoulding@gustr.com', 'Katie', 'Cheifie6u', '02538319475', 'ACTIVE')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456820', 'JordanMcClelland@fleckens.hu', 'Jordan', 'Eekoh2ua', '02538318582', 'DEACTIVE')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456821', 'LucySharman@fleckens.hu', 'Lucy', 'aiqu2Equ', '02538310974', 'CANCELLED')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456822', 'JettColdham@superrito.com', 'Jett', 'JoaSh4aex', '02538310280', 'ACTIVE')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456823', 'BethanySwanston@teleworm.us', 'Bethany', 'tha9eeK8', '02538319600', 'DEACTIVE')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456824', 'CalebBertie@fleckens.hu', 'Caleb', 'ohnauNg7', '02538317422', 'CANCELLED')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456825', 'JamieCook@rhyta.com', 'Jamie', 'iepho7giX0Shee', '02538310343', 'ACTIVE')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456826', 'ClaireVarley@teleworm.us', 'Claire', 'Tohd0hu7Oh', '02538317941', 'DEACTIVE')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456827', 'LukeAvery@teleworm.us', 'Luke', 'Iiceiqu5', '02538317293', 'CANCELLED')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456828', 'TristanColdham@dayrep.com', 'Tristan', 'naiKaez3ai', '02538318480', 'ACTIVE')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456829', 'TajRose@gustr.com', 'Taj', 'thohRieH5oe', '02538318510', 'DEACTIVE')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456830', 'AmySwayne@fleckens.hu', 'Amy', 'uBahje0oo', '02538318672', 'CANCELLED')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456831', 'SophiaBoothman@dayrep.com', 'Sophia', 'adi8ieXah', '0292717352', 'ACTIVE')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456832', 'GeorgeFlanagan@fleckens.hu', 'George', 'ohR9chaach5', '0290971171', 'DEACTIVE')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456833', 'AbbyKiek@cuvox.de', 'Abby', 'Ahz8ohnaeG', '0267982777', 'CANCELLED')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456834', 'AnnabelleLucas@armyspy.com', 'Annabelle', 'eiZo7cup', '0883968937', 'ACTIVE')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456835', 'IsaacFitchett@dayrep.com', 'Isaac', 'uree1thooH', '0283874380', 'DEACTIVE')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456836', 'AbbyTate@fleckens.hu', 'Abby', 'Gokahr6alae', '0237860444', 'CANCELLED')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456837', 'NoahRiley@gustr.com', 'Noah', 'Ez5oo6wae3m', '0253661405', 'ACTIVE')";
        query[count++] = "INSERT INTO \"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('123456838', 'admin@oms.com', 'Admin', 'password', '0240681547', 'ACTIVE')";

        // CUSTOMER DATA 
        query[count++] = "INSERT INTO CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\" ,ADDRESS, STATUS) VALUES ('223456789', 'Hayden', 'HaydenBainton@jourrapide.com', 'INDIVIDUAL', '63 Point Walter Road O'CONNOR, Western Australia, 6163', 'ACTIVE')";
query[count++] = "INSERT INTO CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\" ,ADDRESS, STATUS) VALUES ('223456790', 'Henry', 'HenryDodgshun@superrito.com', 'COMPANY', '8 Link Road MYRTLE BANK, Tasmania, 7259', 'DEACTIVE')";
query[count++] = "INSERT INTO CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\" ,ADDRESS, STATUS) VALUES ('223456791', 'Patrick', 'PatrickLetcher@teleworm.us', 'INDIVIDUAL', '12 Amiens Road ILFORD, New South Wales, 2850', 'ACTIVE')";
query[count++] = "INSERT INTO CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\" ,ADDRESS, STATUS) VALUES ('223456792', 'Spencer', 'SpencerVirgo@teleworm.us', 'COMPANY', '38 Lane Street GREYTHORN, Victoria, 3104', 'ACTIVE')";
query[count++] = "INSERT INTO CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\" ,ADDRESS, STATUS) VALUES ('223456793', 'Sofia', 'SofiaBlundell@superrito.com', 'INDIVIDUAL', '81 Railway Street LINTHORPE, Queensland, 4356', 'DEACTIVE')";
query[count++] = "INSERT INTO CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\" ,ADDRESS, STATUS) VALUES ('223456794', 'Lilian', 'LilianShapcott@armyspy.com', 'COMPANY', '35 Dalgarno Street PILLIGA, New South Wales, 2388', 'ACTIVE')";
query[count++] = "INSERT INTO CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\" ,ADDRESS, STATUS) VALUES ('223456795', 'Aaron', 'AaronBrifman@rhyta.com', 'INDIVIDUAL', '7 Millicent Drive BANDON GROVE, New South Wales, 2420', 'ACTIVE')";
query[count++] = "INSERT INTO CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\" ,ADDRESS, STATUS) VALUES ('223456796', 'Zachary', 'ZacharyElkington@dayrep.com', 'COMPANY', '12 Girvan Grove ROBINVALE IRRIGATION DISTRICT SECTION C, Victoria, 3549', 'DEACTIVE')";
query[count++] = "INSERT INTO CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\" ,ADDRESS, STATUS) VALUES ('223456797', 'Aaron', 'AaronTrouette@einrot.com', 'INDIVIDUAL', '30 Carba Road EIGHT MILE CREEK, South Australia, 5291', 'ACTIVE')";
query[count++] = "INSERT INTO CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\" ,ADDRESS, STATUS) VALUES ('223456798', 'Owen', 'OwenMacdonald@jourrapide.com', 'COMPANY', '85 Saggers Road MAGENTA, Western Australia, 6355', 'ACTIVE')";
query[count++] = "INSERT INTO CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\" ,ADDRESS, STATUS) VALUES ('223456799', 'Sienna', 'SiennaMcKeown@gustr.com', 'INDIVIDUAL', '11 Chapman Avenue YOSEMITE, New South Wales, 2780', 'DEACTIVE')";
query[count++] = "INSERT INTO CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\" ,ADDRESS, STATUS) VALUES ('223456800', 'Ella', 'EllaWeindorfer@gustr.com', 'COMPANY', '40 Flax Court CORAL BANK, Victoria, 3691', 'ACTIVE')";
query[count++] = "INSERT INTO CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\" ,ADDRESS, STATUS) VALUES ('223456801', 'Lucinda', 'LucindaCouvreur@jourrapide.com', 'INDIVIDUAL', '11 Black Point Drive PORT GIBBON, South Australia, 5602', 'ACTIVE')";
query[count++] = "INSERT INTO CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\" ,ADDRESS, STATUS) VALUES ('223456802', 'Mia', 'MiaBurges@cuvox.de', 'COMPANY', '29 Peterho Boulevard HOUGHTON, South Australia, 5131', 'DEACTIVE')";
query[count++] = "INSERT INTO CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\" ,ADDRESS, STATUS) VALUES ('223456803', 'Noah', 'NoahODonovan@armyspy.com', 'INDIVIDUAL', '50 Dossiter Street LEMONT, Tasmania, 7120', 'ACTIVE')";
query[count++] = "INSERT INTO CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\" ,ADDRESS, STATUS) VALUES ('223456804', 'Stephanie', 'StephanieFereday@einrot.com', 'COMPANY', '47 Badgery Road DODSWORTH, New South Wales, 2620', 'ACTIVE')";
query[count++] = "INSERT INTO CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\" ,ADDRESS, STATUS) VALUES ('223456805', 'Dylan', 'DylanSmeaton@einrot.com', 'INDIVIDUAL', '65 Correa Place NIGHTCLIFF, Northern Territory, 810', 'DEACTIVE')";
query[count++] = "INSERT INTO CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\" ,ADDRESS, STATUS) VALUES ('223456806', 'Gabriel', 'GabrielForlong@einrot.com', 'COMPANY', '69 Henry Moss Court HORNSDALE, South Australia, 5491', 'ACTIVE')";
query[count++] = "INSERT INTO CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\" ,ADDRESS, STATUS) VALUES ('223456807', 'Poppy', 'PoppySuttor@gustr.com', 'INDIVIDUAL', '99 Kaesler Road KOORINE, South Australia, 5279', 'ACTIVE')";
query[count++] = "INSERT INTO CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\" ,ADDRESS, STATUS) VALUES ('223456808', 'Finn', 'FinnBroome@gustr.com', 'COMPANY', '98 Davis Street BROOKFIELD, Queensland, 4069', 'DEACTIVE')";
query[count++] = "INSERT INTO CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\" ,ADDRESS, STATUS) VALUES ('223456809', 'Gabriel', 'GabrielAlgeranoff@fleckens.hu', 'INDIVIDUAL', '71 Murphy Street MURESK, Western Australia, 6401', 'ACTIVE')";
query[count++] = "INSERT INTO CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\" ,ADDRESS, STATUS) VALUES ('223456810', 'Rebecca', 'RebeccaFreame@einrot.com', 'COMPANY', '30 Reynolds Road CALGOA, Queensland, 4570', 'ACTIVE')";
query[count++] = "INSERT INTO CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\" ,ADDRESS, STATUS) VALUES ('223456811', 'Olivia', 'OliviaBodenwieser@einrot.com', 'INDIVIDUAL', '53 Crofts Road MARTINS CREEK, Victoria, 3888', 'DEACTIVE')";
query[count++] = "INSERT INTO CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\" ,ADDRESS, STATUS) VALUES ('223456812', 'Jamie', 'JamieToRot@gustr.com', 'COMPANY', '52 Wynyard Street MIDWAY, New South Wales, 2720', 'ACTIVE')";
query[count++] = "INSERT INTO CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\" ,ADDRESS, STATUS) VALUES ('223456813', 'Lola', 'LolaHeagney@rhyta.com', 'INDIVIDUAL', '20 Queen Street HARBORD, New South Wales, 2096', 'ACTIVE')";
query[count++] = "INSERT INTO CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\" ,ADDRESS, STATUS) VALUES ('223456814', 'Leah', 'LeahBury@superrito.com', 'COMPANY', '82 Boonah Qld BRAEMORE, Queensland, 4313', 'DEACTIVE')";
query[count++] = "INSERT INTO CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\" ,ADDRESS, STATUS) VALUES ('223456815', 'Taylah', 'TaylahTench@fleckens.hu', 'INDIVIDUAL', '21 Millicent Drive UPPER CHICHESTER, New South Wales, 2420', 'ACTIVE')";
query[count++] = "INSERT INTO CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\" ,ADDRESS, STATUS) VALUES ('223456816', 'Grace', 'GraceLillico@gustr.com', 'COMPANY', '99 Feather Street LANDSBOROUGH, Queensland, 4550', 'ACTIVE')";
query[count++] = "INSERT INTO CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\" ,ADDRESS, STATUS) VALUES ('223456817', 'Ben', 'BenEdmondstone@armyspy.com', 'INDIVIDUAL', '96 Rockhampton Qld SAPPHIRE, Queensland, 4702', 'DEACTIVE')";
query[count++] = "INSERT INTO CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\" ,ADDRESS, STATUS) VALUES ('223456818', 'Katie', 'KatieGarratt@jourrapide.com', 'COMPANY', '65 Hill Street MIDDLETON, Tasmania, 7163', 'ACTIVE')";
query[count++] = "INSERT INTO CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\" ,ADDRESS, STATUS) VALUES ('223456819', 'Admin', 'admin@oms.com', 'INDIVIDUAL', '65 Correa Place NIGHTCLIFF, Northern Territory, 810', 'ACTIVE')";
query[count++] = "INSERT INTO CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\" ,ADDRESS, STATUS) VALUES ('223456820', 'Staff', 'staff@oms.com', 'INDIVIDUAL', '65 Correa Place NIGHTCLIFF, Northern Territory, 810', 'ACTIVE')";


        // MOVIE DATA
        query[count++] = "INSERT INTO MOVIE (ID, TITLE, GENRE, PRICE, STOCK) VALUES ('323456789', 'Escape Room', 'Horror', 20, 0)";
query[count++] = "INSERT INTO MOVIE (ID, TITLE, GENRE, PRICE, STOCK) VALUES ('323456790', 'Call of the Wild', 'Documentary', 15, 73)";
query[count++] = "INSERT INTO MOVIE (ID, TITLE, GENRE, PRICE, STOCK) VALUES ('323456791', 'The Age of Adaline', 'Romance', 20, 99)";
query[count++] = "INSERT INTO MOVIE (ID, TITLE, GENRE, PRICE, STOCK) VALUES ('323456792', 'Robin Hood', 'Adventure', 20, 0)";
query[count++] = "INSERT INTO MOVIE (ID, TITLE, GENRE, PRICE, STOCK) VALUES ('323456793', 'Megamind', 'Science Fiction', 10, 38)";
query[count++] = "INSERT INTO MOVIE (ID, TITLE, GENRE, PRICE, STOCK)VALUES ('323456794', 'That Awkward Moment', 'Comedy', 5, 0)";
query[count++] = "INSERT INTO MOVIE (ID, TITLE, GENRE, PRICE, STOCK) VALUES ('323456795', 'Happy Death Day 2U', 'Horror', 20, 67)";
query[count++] = "INSERT INTO MOVIE (ID, TITLE, GENRE, PRICE, STOCK) VALUES ('323456796', 'Aliens of the Deep', 'Documentary', 20, 41)";
query[count++] = "INSERT INTO MOVIE (ID, TITLE, GENRE, PRICE, STOCK) VALUES ('323456797', 'City of Angels', 'Romance', 30, 33)";
query[count++] = "INSERT INTO MOVIE (ID, TITLE, GENRE, PRICE, STOCK) VALUES ('323456798', 'Gulliver's Travels', 'Adventure', 20, 29)";
query[count++] = "INSERT INTO MOVIE (ID, TITLE, GENRE, PRICE, STOCK) VALUES ('323456799', 'Dark Phoenix', 'Science Fiction', 10, 18)";
query[count++] = "INSERT INTO MOVIE (ID, TITLE, GENRE, PRICE, STOCK) VALUES ('323456800', 'Deadpool 2', 'Comedy', 10, 0)";
query[count++] = "INSERT INTO MOVIE (ID, TITLE, GENRE, PRICE, STOCK) VALUES ('323456801', 'Pet Sematary', 'Horror', 10, 17)";
query[count++] = "INSERT INTO MOVIE (ID, TITLE, GENRE, PRICE, STOCK) VALUES ('323456802', 'Hearts and Minds', 'Documentary', 10, 17)";
query[count++] = "INSERT INTO MOVIE (ID, TITLE, GENRE, PRICE, STOCK) VALUES ('323456803', 'The Fault in Our Stars', 'Romance', 5, 21)";
query[count++] = "INSERT INTO MOVIE (ID, TITLE, GENRE, PRICE, STOCK) VALUES ('323456804', 'The Last Airbender', 'Adventure', 20, 63)";
query[count++] = "INSERT INTO MOVIE (ID, TITLE, GENRE, PRICE, STOCK) VALUES ('323456805', 'Brightburn', 'Science Fiction', 15, 45)";
query[count++] = "INSERT INTO MOVIE (ID, TITLE, GENRE, PRICE, STOCK) VALUES ('323456806', 'Gringo', 'Comedy', 20, 0)";
query[count++] = "INSERT INTO MOVIE (ID, TITLE, GENRE, PRICE, STOCK) VALUES ('323456807', 'Us', 'Horror', 20, 89)";
query[count++] = "INSERT INTO MOVIE (ID, TITLE, GENRE, PRICE, STOCK) VALUES ('323456808', 'Man from Plains', 'Documentary', 10, 122)";
query[count++] = "INSERT INTO MOVIE (ID, TITLE, GENRE, PRICE, STOCK) VALUES ('323456809', 'Lovestruck', 'Romance', 5, 9)";
query[count++] = "INSERT INTO MOVIE (ID, TITLE, GENRE, PRICE, STOCK) VALUES ('323456810', 'Immortals', 'Adventure', 20, 16)";
query[count++] = "INSERT INTO MOVIE (ID, TITLE, GENRE, PRICE, STOCK) VALUES ('323456811', 'Terminator: Dark Fate', 'Science Fiction', 20, 2)";
query[count++] = "INSERT INTO MOVIE (ID, TITLE, GENRE, PRICE, STOCK) VALUES ('323456812', 'The Boss Baby', 'Comedy', 30, 0)";
query[count++] = "INSERT INTO MOVIE (ID, TITLE, GENRE, PRICE, STOCK) VALUES ('323456813', 'Velvet Buzzsaw', 'Horror', 20, 8)";
query[count++] = "INSERT INTO MOVIE (ID, TITLE, GENRE, PRICE, STOCK) VALUES ('323456814', 'Predictions of Fire', 'Documentary', 10, 20)";
query[count++] = "INSERT INTO MOVIE (ID, TITLE, GENRE, PRICE, STOCK) VALUES ('323456815', 'Something Borrowed', 'Romance', 10, 33)";
query[count++] = "INSERT INTO MOVIE (ID, TITLE, GENRE, PRICE, STOCK) VALUES ('323456816', 'Wrath of the Titans', 'Adventure', 10, 0)";
query[count++] = "INSERT INTO MOVIE (ID, TITLE, GENRE, PRICE, STOCK) VALUES ('323456817', 'The Three-Body Problem', 'Science Fiction', 10, 10)";
query[count++] = "INSERT INTO MOVIE (ID, TITLE, GENRE, PRICE, STOCK) VALUES ('323456818', 'Zootopia', 'Comedy', 5, 4)";

        // STAFF DATA
        query[count++] = "INSERT INTO STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\" ,ADDRESS, STATUS) VALUES ('423456789', '123456809', 'GabrielAlgeranoff@fleckens.hu', 'Gabriel', 'STAFF', '71 Murphy Street MURESK, Western Australia, 6401', 'ACTIVE')";
query[count++] = "INSERT INTO STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\" ,ADDRESS, STATUS) VALUES ('423456790', '123456810', 'RebeccaFreame@einrot.com', 'Rebecca', 'STAFF', '30 Reynolds Road CALGOA, Queensland, 4570', 'ACTIVE')";
query[count++] = "INSERT INTO STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\" ,ADDRESS, STATUS) VALUES ('423456791', '123456811', 'OliviaBodenwieser@einrot.com', 'Olivia', 'STAFF', '53 Crofts Road MARTINS CREEK, Victoria, 3888', 'ACTIVE')";
query[count++] = "INSERT INTO STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\" ,ADDRESS, STATUS) VALUES ('423456792', '123456812', 'JamieToRot@gustr.com', 'Jamie', 'STAFF', '52 Wynyard Street MIDWAY, New South Wales, 2720', 'ACTIVE')";
query[count++] = "INSERT INTO STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\" ,ADDRESS, STATUS) VALUES ('423456793', '123456813', 'LolaHeagney@rhyta.com', 'Lola', 'STAFF', '20 Queen Street HARBORD, New South Wales, 2096', 'DEACTIVE')";
query[count++] = "INSERT INTO STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\" ,ADDRESS, STATUS) VALUES ('423456794', '123456814', 'LeahBury@superrito.com', 'Leah', 'STAFF', '82 Boonah Qld BRAEMORE, Queensland, 4313', 'ACTIVE')";
query[count++] = "INSERT INTO STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\" ,ADDRESS, STATUS) VALUES ('423456795', '123456815', 'TaylahTench@fleckens.hu', 'Taylah', 'STAFF', '21 Millicent Drive UPPER CHICHESTER, New South Wales, 2420', 'ACTIVE')";
query[count++] = "INSERT INTO STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\" ,ADDRESS, STATUS) VALUES ('423456796', '123456816', 'GraceLillico@gustr.com', 'Grace', 'STAFF', '99 Feather Street LANDSBOROUGH, Queensland, 4550', 'ACTIVE')";
query[count++] = "INSERT INTO STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\" ,ADDRESS, STATUS) VALUES ('423456797', '123456817', 'BenEdmondstone@armyspy.com', 'Ben', 'STAFF', '96 Rockhampton Qld SAPPHIRE, Queensland, 4702', 'ACTIVE')";
query[count++] = "INSERT INTO STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\" ,ADDRESS, STATUS) VALUES ('423456798', '123456818', 'KatieGarratt@jourrapide.com', 'Katie', 'STAFF', '65 Hill Street MIDDLETON, Tasmania, 7163', 'DEACTIVE')";
query[count++] = "INSERT INTO STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\" ,ADDRESS, STATUS) VALUES ('423456799', '123456819', 'KatieHoulding@gustr.com', 'Katie', 'STAFF', '71 Flinstone Drive JERICHO, TAS, 7030', 'ACTIVE')";
query[count++] = "INSERT INTO STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\" ,ADDRESS, STATUS) VALUES ('423456800', '123456820', 'JordanMcClelland@fleckens.hu', 'Jordan', 'STAFF', '34 Inglewood Court TARILTA, VIC, 3451', 'ACTIVE')";
query[count++] = "INSERT INTO STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\" ,ADDRESS, STATUS) VALUES ('423456801', '123456821', 'LucySharman@fleckens.hu', 'Lucy', 'STAFF', '55 Hillsdale Road COONAMBULA, QLD, 4626', 'ACTIVE')";
query[count++] = "INSERT INTO STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\" ,ADDRESS, STATUS) VALUES ('423456802', '123456822', 'JettColdham@superrito.com', 'Jett', 'STAFF', '35 Maritime Avenue ALEXANDRA BRIDGE, WA, 6288', 'ACTIVE')";
query[count++] = "INSERT INTO STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\" ,ADDRESS, STATUS) VALUES ('423456803', '123456823', 'BethanySwanston@teleworm.us', 'Bethany', 'STAFF', '5 Norton Street NEUTRAL BAY, NSW, 2089', 'DEACTIVE')";
query[count++] = "INSERT INTO STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\" ,ADDRESS, STATUS) VALUES ('423456804', '123456824', 'CalebBertie@fleckens.hu', 'Caleb', 'STAFF', '35 Cherokee Road SANDON, VIC, 3462', 'ACTIVE')";
query[count++] = "INSERT INTO STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\" ,ADDRESS, STATUS) VALUES ('423456805', '123456825', 'JamieCook@rhyta.com', 'Jamie', 'STAFF', '53 Peterho Boulevard GAWLER WEST, SA, 5118', 'ACTIVE')";
query[count++] = "INSERT INTO STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\" ,ADDRESS, STATUS) VALUES ('423456806', '123456826', 'ClaireVarley@teleworm.us', 'Claire', 'STAFF', '82 Rockhampton Qld ULOGIE, QLD, 4702', 'ACTIVE')";
query[count++] = "INSERT INTO STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\" ,ADDRESS, STATUS) VALUES ('423456807', '123456827', 'LukeAvery@teleworm.us', 'Luke', 'STAFF', '7 Horsington Street RANGEVIEW, VIC, 3132', 'ACTIVE')";
query[count++] = "INSERT INTO STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\" ,ADDRESS, STATUS) VALUES ('423456808', '123456828', 'TristanColdham@dayrep.com', 'Tristan', 'STAFF', '57 Patton Street CROYDON, VIC, 3136', 'DEACTIVE')";
query[count++] = "INSERT INTO STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\" ,ADDRESS, STATUS) VALUES ('423456809', '123456829', 'TajRose@gustr.com', 'Taj', 'STAFF', '30 Weigall Avenue LEIGHTON, SA, 5417', 'ACTIVE')";
query[count++] = "INSERT INTO STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\" ,ADDRESS, STATUS) VALUES ('423456810', '123456830', 'AmySwayne@fleckens.hu', 'Amy', 'STAFF', '47 Bass Street DUNGARUBBA, NSW, 2480', 'ACTIVE')";
query[count++] = "INSERT INTO STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\" ,ADDRESS, STATUS) VALUES ('423456811', '123456831', 'SophiaBoothman@dayrep.com', 'Sophia', 'STAFF', '97 Inglewood Court REDESDALE, VIC, 3444', 'ACTIVE')";
query[count++] = "INSERT INTO STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\" ,ADDRESS, STATUS) VALUES ('423456812', '123456832', 'GeorgeFlanagan@fleckens.hu', 'George', 'STAFF', '56 Boughtman Street BELGRAVE, VIC, 3160', 'ACTIVE')";
query[count++] = "INSERT INTO STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\" ,ADDRESS, STATUS) VALUES ('423456813', '123456833', 'AbbyKiek@cuvox.de', 'Abby', 'STAFF', '64 Normans Road KADNOOK, VIC, 3318', 'DEACTIVE')";
query[count++] = "INSERT INTO STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\" ,ADDRESS, STATUS) VALUES ('423456814', '123456834', 'AnnabelleLucas@armyspy.com', 'Annabelle', 'ADMIN', '91 Corny Court NUNJIKOMPITA, SA, 5680', 'ACTIVE')";
query[count++] = "INSERT INTO STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\" ,ADDRESS, STATUS) VALUES ('423456815', '123456835', 'IsaacFitchett@dayrep.com', 'Isaac', 'ADMIN', '59 Ocean Pde TORRENS CREEK, QLD, 4816', 'ACTIVE')";
query[count++] = "INSERT INTO STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\" ,ADDRESS, STATUS) VALUES ('423456816', '123456836', 'AbbyTate@fleckens.hu', 'Abby', 'ADMIN', '19 Edgecliff Road GOLDEN GROVE, NSW, 2008', 'ACTIVE')";
query[count++] = "INSERT INTO STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\" ,ADDRESS, STATUS) VALUES ('423456817', '123456837', 'NoahRiley@gustr.com', 'Noah', 'ADMIN', '50 Crofts Road CORRINGLE, VIC, 3888', 'ACTIVE')";
query[count++] = "INSERT INTO STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\" ,ADDRESS, STATUS) VALUES ('423456818', '123456838', 'admin@oms.com', 'Admin', 'ADMIN', '91 Shell Road DEANS MARSH, VIC, 3235', 'ACTIVE')";

        // USERACCESSLOG DATA
        query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456789', '123456789', 'LOGIN', '2018-05-22 00:53:32.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456790', '123456790', 'LOGIN', '2018-05-23 11:45:53.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456791', '123456791', 'LOGIN', '2018-05-24 22:38:14.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456792', '123456792', 'LOGIN', '2018-05-26 09:30:35.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456793', '123456793', 'LOGIN', '2018-05-27 20:22:56.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456794', '123456794', 'LOGIN', '2018-05-29 07:15:17.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456795', '123456795', 'LOGIN', '2018-05-30 18:07:38.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456796', '123456796', 'LOGIN', '2018-06-01 04:59:59.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456797', '123456797', 'LOGIN', '2018-06-02 15:52:20.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456798', '123456798', 'LOGIN', '2018-06-04 02:44:41.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456799', '123456799', 'LOGIN', '2018-06-05 13:37:02.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456800', '123456800', 'LOGIN', '2018-06-07 00:29:23.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456801', '123456801', 'LOGIN', '2018-06-08 11:21:44.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456802', '123456802', 'LOGIN', '2018-06-09 22:14:05.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456803', '123456803', 'LOGIN', '2018-06-11 09:06:26.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456804', '123456804', 'LOGIN', '2018-06-12 19:58:47.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456805', '123456805', 'LOGIN', '2018-06-14 06:51:08.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456806', '123456806', 'LOGIN', '2018-06-15 17:43:29.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456807', '123456807', 'LOGIN', '2018-06-17 04:35:50.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456808', '123456808', 'LOGIN', '2018-06-18 15:28:11.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456809', '123456809', 'LOGIN', '2018-06-20 02:20:32.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456810', '123456810', 'LOGIN', '2018-06-21 13:12:53.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456811', '123456811', 'LOGIN', '2018-06-23 00:05:14.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456812', '123456812', 'LOGIN', '2018-06-24 10:57:35.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456813', '123456813', 'LOGIN', '2018-06-25 21:49:56.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456814', '123456814', 'LOGIN', '2018-06-27 08:42:17.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456815', '123456815', 'LOGIN', '2018-06-28 19:34:38.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456816', '123456816', 'LOGIN', '2018-06-30 06:26:59.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456817', '123456817', 'LOGIN', '2018-07-01 17:19:20.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456818', '123456818', 'LOGIN', '2018-07-03 04:11:41.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456819', '123456819', 'LOGIN', '2018-07-04 15:04:02.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456820', '123456820', 'LOGIN', '2018-07-06 01:56:23.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456821', '123456821', 'LOGIN', '2018-07-07 12:48:44.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456822', '123456822', 'LOGIN', '2018-07-08 23:41:05.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456823', '123456823', 'LOGIN', '2018-07-10 10:33:26.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456824', '123456824', 'LOGIN', '2018-07-11 21:25:47.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456825', '123456825', 'LOGIN', '2018-07-13 08:18:08.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456826', '123456826', 'LOGIN', '2018-07-14 19:10:29.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456827', '123456827', 'LOGIN', '2018-07-16 06:02:50.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456828', '123456828', 'LOGIN', '2018-07-17 16:55:11.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456829', '123456829', 'LOGIN', '2018-07-19 03:47:32.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456830', '123456830', 'LOGIN', '2018-07-20 14:39:53.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456831', '123456831', 'LOGIN', '2018-07-22 01:32:14.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456832', '123456832', 'LOGIN', '2018-07-23 12:24:35.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456833', '123456833', 'LOGIN', '2018-07-24 23:16:56.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456834', '123456834', 'LOGIN', '2018-07-26 10:09:17.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456835', '123456835', 'LOGIN', '2018-07-27 21:01:38.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456836', '123456836', 'LOGIN', '2018-07-29 07:53:59.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456837', '123456837', 'LOGIN', '2018-07-30 18:46:20.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456838', '123456838', 'LOGIN', '2018-08-01 05:38:41.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456839', '123456789', 'LOGOUT', '2018-08-02 16:31:02.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456840', '123456790', 'LOGOUT', '2018-08-04 03:23:23.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456841', '123456791', 'LOGOUT', '2018-08-05 14:15:44.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456842', '123456792', 'LOGOUT', '2018-08-07 01:08:05.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456843', '123456793', 'LOGOUT', '2018-08-08 12:00:26.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456844', '123456794', 'LOGOUT', '2018-08-09 22:52:47.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456845', '123456795', 'LOGOUT', '2018-08-11 09:45:08.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456846', '123456796', 'LOGOUT', '2018-08-12 20:37:29.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456847', '123456797', 'LOGOUT', '2018-08-14 07:29:50.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456848', '123456798', 'LOGOUT', '2018-08-15 18:22:11.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456849', '123456799', 'LOGOUT', '2018-08-17 05:14:32.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456850', '123456800', 'LOGOUT', '2018-08-18 16:06:53.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456851', '123456801', 'LOGOUT', '2018-08-20 02:59:14.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456852', '123456802', 'LOGOUT', '2018-08-21 13:51:35.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456853', '123456803', 'LOGOUT', '2018-08-23 00:43:56.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456854', '123456804', 'LOGOUT', '2018-08-24 11:36:17.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456855', '123456805', 'LOGOUT', '2018-08-25 22:28:38.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456856', '123456806', 'LOGOUT', '2018-08-27 09:20:59.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456857', '123456807', 'LOGOUT', '2018-08-28 20:13:20.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456858', '123456808', 'LOGOUT', '2018-08-30 07:05:41.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456859', '123456809', 'LOGOUT', '2018-08-31 17:58:02.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456860', '123456810', 'LOGOUT', '2018-09-02 04:50:23.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456861', '123456811', 'LOGOUT', '2018-09-03 15:42:44.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456862', '123456812', 'LOGOUT', '2018-09-05 02:35:05.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456863', '123456813', 'LOGOUT', '2018-09-06 13:27:26.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456864', '123456814', 'LOGOUT', '2018-09-08 00:19:47.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456865', '123456815', 'LOGOUT', '2018-09-09 11:12:08.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456866', '123456816', 'LOGOUT', '2018-09-10 22:04:29.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456867', '123456817', 'LOGOUT', '2018-09-12 08:56:50.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456868', '123456818', 'LOGOUT', '2018-09-13 19:49:11.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456869', '123456819', 'LOGOUT', '2018-09-15 06:41:32.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456870', '123456820', 'LOGOUT', '2018-09-16 17:33:53.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456871', '123456821', 'LOGOUT', '2018-09-18 04:26:14.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456872', '123456822', 'LOGOUT', '2018-09-19 15:18:35.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456873', '123456823', 'LOGOUT', '2018-09-21 02:10:56.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456874', '123456824', 'LOGOUT', '2018-09-22 13:03:17.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456875', '123456825', 'LOGOUT', '2018-09-23 23:55:38.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456876', '123456826', 'LOGOUT', '2018-09-25 10:47:59.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456877', '123456827', 'LOGOUT', '2018-09-26 21:40:20.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456878', '123456828', 'LOGOUT', '2018-09-28 08:32:41.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456879', '123456829', 'LOGOUT', '2018-09-29 19:25:02.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456880', '123456830', 'LOGOUT', '2018-10-01 06:17:23.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456881', '123456831', 'LOGOUT', '2018-10-02 17:09:44.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456882', '123456832', 'LOGOUT', '2018-10-04 04:02:05.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456883', '123456833', 'LOGOUT', '2018-10-05 14:54:26.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456884', '123456834', 'LOGOUT', '2018-10-07 01:46:47.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456885', '123456835', 'LOGOUT', '2018-10-08 12:39:08.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456886', '123456836', 'LOGOUT', '2018-10-09 23:31:29.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456887', '123456837', 'LOGOUT', '2018-10-11 10:23:50.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456888', '123456838', 'LOGOUT', '2018-10-12 21:16:11.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456889', '123456789', 'LOGIN', '2018-10-14 08:08:32.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456890', '123456790', 'LOGIN', '2018-10-15 19:00:53.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456891', '123456791', 'LOGIN', '2018-10-17 05:53:14.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456892', '123456792', 'LOGIN', '2018-10-18 16:45:35.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456893', '123456793', 'LOGIN', '2018-10-20 03:37:56.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456894', '123456794', 'LOGIN', '2018-10-21 14:30:17.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456895', '123456795', 'LOGIN', '2018-10-23 01:22:38.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456896', '123456796', 'LOGIN', '2018-10-24 12:14:59.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456897', '123456797', 'LOGIN', '2018-10-25 23:07:20.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456898', '123456798', 'LOGIN', '2018-10-27 09:59:41.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456899', '123456799', 'LOGIN', '2018-10-28 20:52:02.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456900', '123456800', 'LOGIN', '2018-10-30 07:44:23.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456901', '123456801', 'LOGIN', '2018-10-31 18:36:44.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456902', '123456802', 'LOGIN', '2018-11-02 05:29:05.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456903', '123456803', 'LOGIN', '2018-11-03 16:21:26.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456904', '123456804', 'LOGIN', '2018-11-05 03:13:47.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456905', '123456805', 'LOGIN', '2018-11-06 14:06:08.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456906', '123456806', 'LOGIN', '2018-11-08 00:58:29.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456907', '123456807', 'LOGIN', '2018-11-09 11:50:50.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456908', '123456808', 'LOGIN', '2018-11-10 22:43:11.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456909', '123456809', 'LOGIN', '2018-11-12 09:35:32.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456910', '123456810', 'LOGIN', '2018-11-13 20:27:53.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456911', '123456811', 'LOGIN', '2018-11-15 07:20:14.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456912', '123456812', 'LOGIN', '2018-11-16 18:12:35.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456913', '123456813', 'LOGIN', '2018-11-18 05:04:56.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456914', '123456814', 'LOGIN', '2018-11-19 15:57:17.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456915', '123456815', 'LOGIN', '2018-11-21 02:49:38.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456916', '123456816', 'LOGIN', '2018-11-22 13:41:59.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456917', '123456817', 'LOGIN', '2018-11-24 00:34:20.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456918', '123456818', 'LOGIN', '2018-11-25 11:26:41.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456919', '123456819', 'LOGIN', '2018-11-26 22:19:02.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456920', '123456820', 'LOGIN', '2018-11-28 09:11:23.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456921', '123456821', 'LOGIN', '2018-11-29 20:03:44.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456922', '123456822', 'LOGIN', '2018-12-01 06:56:05.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456923', '123456823', 'LOGIN', '2018-12-02 17:48:26.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456924', '123456824', 'LOGIN', '2018-12-04 04:40:47.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456925', '123456825', 'LOGIN', '2018-12-05 15:33:08.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456926', '123456826', 'LOGIN', '2018-12-07 02:25:29.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456927', '123456827', 'LOGIN', '2018-12-08 13:17:50.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456928', '123456828', 'LOGIN', '2018-12-10 00:10:11.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456929', '123456829', 'LOGIN', '2018-12-11 11:02:32.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456930', '123456830', 'LOGIN', '2018-12-12 21:54:53.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456931', '123456831', 'LOGIN', '2018-12-14 08:47:14.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456932', '123456832', 'LOGIN', '2018-12-15 19:39:35.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456933', '123456833', 'LOGIN', '2018-12-17 06:31:56.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456934', '123456834', 'LOGIN', '2018-12-18 17:24:17.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456935', '123456835', 'LOGIN', '2018-12-20 04:16:38.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456936', '123456836', 'LOGIN', '2018-12-21 15:08:59.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456937', '123456837', 'LOGIN', '2018-12-23 02:01:20.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456938', '123456838', 'LOGIN', '2018-12-24 12:53:41.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456939', '123456789', 'LOGOUT', '2018-12-25 23:46:02.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456940', '123456790', 'LOGOUT', '2018-12-27 10:38:23.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456941', '123456791', 'LOGOUT', '2018-12-28 21:30:44.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456942', '123456792', 'LOGOUT', '2018-12-30 08:23:05.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456943', '123456793', 'LOGOUT', '2018-12-31 19:15:26.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456944', '123456794', 'LOGOUT', '2019-01-02 06:07:47.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456945', '123456795', 'LOGOUT', '2019-01-03 17:00:08.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456946', '123456796', 'LOGOUT', '2019-01-05 03:52:29.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456947', '123456797', 'LOGOUT', '2019-01-06 14:44:50.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456948', '123456798', 'LOGOUT', '2019-01-08 01:37:11.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456949', '123456799', 'LOGOUT', '2019-01-09 12:29:32.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456950', '123456800', 'LOGOUT', '2019-01-10 23:21:53.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456951', '123456801', 'LOGOUT', '2019-01-12 10:14:14.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456952', '123456802', 'LOGOUT', '2019-01-13 21:06:35.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456953', '123456803', 'LOGOUT', '2019-01-15 07:58:56.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456954', '123456804', 'LOGOUT', '2019-01-16 18:51:17.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456955', '123456805', 'LOGOUT', '2019-01-18 05:43:38.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456956', '123456806', 'LOGOUT', '2019-01-19 16:35:59.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456957', '123456807', 'LOGOUT', '2019-01-21 03:28:20.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456958', '123456808', 'LOGOUT', '2019-01-22 14:20:41.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456959', '123456809', 'LOGOUT', '2019-01-24 01:13:02.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456960', '123456810', 'LOGOUT', '2019-01-25 12:05:23.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456961', '123456811', 'LOGOUT', '2019-01-26 22:57:44.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456962', '123456812', 'LOGOUT', '2019-01-28 09:50:05.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456963', '123456813', 'LOGOUT', '2019-01-29 20:42:26.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456964', '123456814', 'LOGOUT', '2019-01-31 07:34:47.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456965', '123456815', 'LOGOUT', '2019-02-01 18:27:08.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456966', '123456816', 'LOGOUT', '2019-02-03 05:19:29.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456967', '123456817', 'LOGOUT', '2019-02-04 16:11:50.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456968', '123456818', 'LOGOUT', '2019-02-06 03:04:11.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456969', '123456819', 'LOGOUT', '2019-02-07 13:56:32.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456970', '123456820', 'LOGOUT', '2019-02-09 00:48:53.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456971', '123456821', 'LOGOUT', '2019-02-10 11:41:14.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456972', '123456822', 'LOGOUT', '2019-02-11 22:33:35.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456973', '123456823', 'LOGOUT', '2019-02-13 09:25:56.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456974', '123456824', 'LOGOUT', '2019-02-14 20:18:17.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456975', '123456825', 'LOGOUT', '2019-02-16 07:10:38.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456976', '123456826', 'LOGOUT', '2019-02-17 18:02:59.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456977', '123456827', 'LOGOUT', '2019-02-19 04:55:20.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456978', '123456828', 'LOGOUT', '2019-02-20 15:47:41.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456979', '123456829', 'LOGOUT', '2019-02-22 02:40:02.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456980', '123456830', 'LOGOUT', '2019-02-23 13:32:23.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456981', '123456831', 'LOGOUT', '2019-02-25 00:24:44.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456982', '123456832', 'LOGOUT', '2019-02-26 11:17:05.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456983', '123456833', 'LOGOUT', '2019-02-27 22:09:26.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456984', '123456834', 'LOGOUT', '2019-03-01 09:01:47.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456985', '123456835', 'LOGOUT', '2019-03-02 19:54:08.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456986', '123456836', 'LOGOUT', '2019-03-04 06:46:29.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456987', '123456837', 'LOGOUT', '2019-03-05 17:38:50.000')";
query[count++] = "INSERT INTO USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('523456988', '123456838', 'LOGOUT', '2019-03-07 04:31:11.000')";

        // ORDER DATA
        query[count++] = "INSERT INTO \"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('623456789', '223456819', '323456789', '123456838', 20, 1, 20, '2019-05-24', ' SUBMITTED')";
query[count++] = "INSERT INTO \"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('623456790', '223456819', '323456790', '123456838', 15, 1, 15, '2019-05-22', 'SAVED')";
query[count++] = "INSERT INTO \"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('623456791', '223456819', '323456791', '123456838', 20, 5, 100, '2019-05-20', 'CANCELLED')";
query[count++] = "INSERT INTO \"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('623456792', '223456819', '323456792', '123456838', 20, 1, 20, '2019-05-18', 'SUBMITTED')";
query[count++] = "INSERT INTO \"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('623456793', '223456819', '323456793', '123456838', 10, 1, 10, '2019-05-16', 'SAVED')";
query[count++] = "INSERT INTO \"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('623456794', '223456819', '323456794', '123456838', 5, 4, 20, '2019-05-14', 'CANCELLED')";
query[count++] = "INSERT INTO \"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('623456795', '223456819', '323456795', '123456838', 20, 1, 20, '2019-05-12', 'SUBMITTED')";
query[count++] = "INSERT INTO \"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('623456796', '223456819', '323456796', '123456838', 20, 1, 20, '2019-05-10', 'SAVED')";
query[count++] = "INSERT INTO \"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('623456797', '223456789', '323456797', '123456789', 30, 1, 30, '2019-05-08', 'CANCELLED')";
query[count++] = "INSERT INTO \"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('623456798', '223456790', '323456798', '123456790', 20, 3, 60, '2019-05-06', 'SUBMITTED')";
query[count++] = "INSERT INTO \"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('623456799', '223456791', '323456799', '123456791', 10, 1, 10, '2019-05-04', 'SAVED')";
query[count++] = "INSERT INTO \"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('623456800', '223456792', '323456800', '123456792', 10, 1, 10, '2019-05-02', 'CANCELLED')";
query[count++] = "INSERT INTO \"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('623456801', '223456793', '323456801', '123456793', 10, 2, 20, '2019-04-30', 'SUBMITTED')";
query[count++] = "INSERT INTO \"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('623456802', '223456794', '323456802', '123456794', 10, 1, 10, '2019-04-28', 'SAVED')";
query[count++] = "INSERT INTO \"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('623456803', '223456795', '323456803', '123456795', 5, 1, 5, '2019-04-26', 'CANCELLED')";
query[count++] = "INSERT INTO \"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('623456804', '223456796', '323456804', '123456796', 20, 1, 20, '2019-04-24', 'SUBMITTED')";
query[count++] = "INSERT INTO \"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('623456805', '223456797', '323456805', '123456797', 15, 3, 45, '2019-04-22', 'SAVED')";
query[count++] = "INSERT INTO \"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('623456806', '223456798', '323456806', '123456798', 20, 1, 20, '2019-04-20', 'CANCELLED')";
query[count++] = "INSERT INTO \"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('623456807', '223456799', '323456807', '123456799', 20, 1, 20, '2019-04-18', 'SUBMITTED')";
query[count++] = "INSERT INTO \"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('623456808', '223456800', '323456808', '123456800', 10, 1, 10, '2019-04-16', 'SAVED')";
query[count++] = "INSERT INTO \"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('623456809', '223456801', '323456809', '123456801', 5, 2, 10, '2019-04-14', 'CANCELLED')";
query[count++] = "INSERT INTO \"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('623456810', '223456802', '323456810', '123456802', 20, 1, 20, '2019-04-12', 'SUBMITTED')";
query[count++] = "INSERT INTO \"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('623456811', '223456803', '323456811', '123456803', 20, 1, 20, '2019-04-10', 'SAVED')";
query[count++] = "INSERT INTO \"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('623456812', '223456804', '323456812', '123456804', 30, 1, 30, '2019-04-08', 'CANCELLED')";
query[count++] = "INSERT INTO \"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('623456813', '223456805', '323456813', '123456805', 20, 2, 40, '2019-04-06', 'SUBMITTED')";
query[count++] = "INSERT INTO \"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('623456814', '223456806', '323456814', '123456806', 10, 1, 10, '2019-04-04', 'SAVED')";
query[count++] = "INSERT INTO \"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('623456815', '223456807', '323456815', '123456807', 10, 1, 10, '2019-04-02', 'CANCELLED')";
query[count++] = "INSERT INTO \"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('623456816', '223456808', '323456816', '123456808', 10, 1, 10, '2019-03-31', 'SUBMITTED')";
query[count++] = "INSERT INTO \"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('623456817', '223456809', '323456817', '123456809', 10, 1, 10, '2019-03-29', 'SAVED')";
query[count++] = "INSERT INTO \"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('623456818', '223456810', '323456818', '123456810', 5, 1, 5, '2019-03-27', 'CANCELLED')";


        for (int i = 0; i < count; i++) {
            try {
                st.execute(query[i]);    
            } catch (SQLException e){}
        }
        
        //st.execute(query[count++] = "INSERT INTO ORDER (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('623456818', '223456810', '323456818', '123456810', 5, 1, 5, '2019-03-27', ' CANCELLED')");
        
        
    }
    
}