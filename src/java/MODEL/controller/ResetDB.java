
/*This stand-alone application to regenerate tables and data to the database.
 *It deletes existing tables and then recreates the tables and adds data to each table.
 *To use this app run this file.
 *
 *
 *@author Phong
**/
package MODEL.controller;
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
        query[count++] = "create table \"STAFF\" ( \"ID\" VARCHAR(50) NOT NULL, \"USERID\" VARCHAR(50) NOT NULL, \"EMAIL\" VARCHAR(50), \"NAME\" VARCHAR(50), \"POSITION\" VARCHAR(50), \"ADDRESS\" VARCHAR(100), \"STATUS\" VARCHAR(50), PRIMARY KEY (\"ID\"), FOREIGN KEY (\"USERID\") REFERENCES \"USER\" (\"ID\") )";
        query[count++] = "create table \"USERACCESSLOG\" ( \"ID\" VARCHAR(50) NOT NULL, \"USERID\" VARCHAR(50), \"ACCESSTYPE\" VARCHAR(50), \"TIMESTAMP\" TIMESTAMP, PRIMARY KEY (\"ID\"), FOREIGN KEY (\"USERID\") REFERENCES \"USER\" (\"ID\") )";
        query[count++] = "create table \"ORDER\" ( \"ID\" VARCHAR(50) NOT NULL, \"CUSTOMERID\" VARCHAR(50), \"MOVIEID\" VARCHAR(50), \"USERID\" VARCHAR(50), \"PRICE\" DECIMAL(19,2), \"AMOUNT\" INT, \"TOTALPRICE\" DECIMAL(19,2), \"DATE\" DATE, \"STATUS\" VARCHAR(50), PRIMARY KEY (\"ID\"), FOREIGN KEY (\"CUSTOMERID\") REFERENCES \"CUSTOMER\" (\"ID\"), FOREIGN KEY (\"MOVIEID\") REFERENCES \"MOVIE\" (\"ID\"), FOREIGN KEY (\"USERID\") REFERENCES \"USER\" (\"ID\") )";
        query[count++] = "create table \"PAYMENT\" ( \"ID\" VARCHAR(50) NOT NULL, \"ORDERID\" VARCHAR(50), \"METHOD\" VARCHAR(50), \"AMOUNT\" DECIMAL(19,2), \"DATE\" DATE,   PRIMARY KEY (\"ID\"), FOREIGN KEY (\"ORDERID\") REFERENCES \"ORDER\" (\"ID\") )";
        query[count++] = "create table \"SHIPMENT\" ( \"ID\" VARCHAR(50) NOT NULL, \"ORDERID\" VARCHAR(50), \"METHOD\" VARCHAR(50), \"DATE\" DATE,    PRIMARY KEY (\"ID\"), FOREIGN KEY (\"ORDERID\") REFERENCES \"ORDER\" (\"ID\") )";
        
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
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('612642668b89445b8a501aab7bec1cae', 'HaydenBainton@jourrapide.com', 'Hayden', 'aiTah9vuGu', '(08) 8294 1740', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('cecd7e5bce794962a41df648dea65774', 'HenryDodgshun@superrito.com', 'Henry', 'rei0She6hi', '(08) 9063 8276', 'DEACTIVE')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('2f4c3820b5504c7a89364c6de7c596fe', 'PatrickLetcher@teleworm.us', 'Patrick', 'Eef7aeYah4', '(03) 8865 6511', 'CANCELLED')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('75367dbce9df44edb688e52e1d1f5b3b', 'SpencerVirgo@teleworm.us', 'Spencer', 'mahH0poph', '(02) 6110 3269', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('4a0f9f2c23b8447c93aca7077e479bcb', 'SofiaBlundell@superrito.com', 'Sofia', 'Ooteedie6h', '(07) 4988 7100', 'DEACTIVE')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('5cb20405ec394f7db8e48bf355a97694', 'LilianShapcott@armyspy.com', 'Lilian', 'Ood0aesivi', '(03) 6259 7177', 'CANCELLED')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('7c1bbf71e6894c4a86490a05bd997675', 'AaronBrifman@rhyta.com', 'Aaron', 'Phaehoh1t', '(08) 8777 2381', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('72f1a472a1e74d789af1a609f7c8603c', 'ZacharyElkington@dayrep.com', 'Zachary', 'oB1en8Ch', '(07) 3329 3524', 'DEACTIVE')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('bd981d02630e45239c681b60fe2ce4da', 'AaronTrouette@einrot.com', 'Aaron', 'mah5Phohha', '(02) 6135 6661', 'CANCELLED')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('ce8c30fc727e40848b817fb6faeeba0c', 'OwenMacdonald@jourrapide.com', 'Owen', 'iePh4Quah6', '(08) 9313 2088', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('31776564f59e425aa78c406627e4040f', 'SiennaMcKeown@gustr.com', 'Sienna', 'UbeeNgi7b', '(03) 5348 8738', 'DEACTIVE')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('a4ae746520d8446f83f688c1e0b8596b', 'EllaWeindorfer@gustr.com', 'Ella', 'Yae5ait9ot', '(03) 9450 1815', 'CANCELLED')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('d28c39f9c29a49bf81a84fa95451f017', 'LucindaCouvreur@jourrapide.com', 'Lucinda', 'Eech8shie', '(08) 9071 7248', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('e1dacdae59bc4b97a447e3cbcbc5bc40', 'MiaBurges@cuvox.de', 'Mia', 'oocai2Ot', '(08) 8706 8402', 'DEACTIVE')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('24c09d16ae774a329e8afbe7faa092ec', 'NoahODonovan@armyspy.com', 'Noah', 'Sichaiw2hoo', '(07) 4099 4645', 'CANCELLED')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('a653a00538624ebfad7b1037618ac1dd', 'StephanieFereday@einrot.com', 'Stephanie', 'IP8thath', '(03) 5362 2712', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('c7c19534a61149ce9ac8810962ab567c', 'DylanSmeaton@einrot.com', 'Dylan', 'ieMoov8oo', '(02) 6128 3045', 'DEACTIVE')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('9416d8322e3f457d861e1af286bf13ab', 'GabrielForlong@einrot.com', 'Gabriel', 'Aeh0eeghoo', '(02) 6187 5620', 'CANCELLED')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('3b878551b0484feaad94c9aad58fdd79', 'PoppySuttor@gustr.com', 'Poppy', 'eej1Eb2sie', '(03) 5383 1919', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('efa6d2c0fef649e7b2b5c51c970bc941', 'FinnBroome@gustr.com', 'Finn', 'lohp8Iewi', '(02) 6197 5870', 'DEACTIVE')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('0c59bb02e3fe4427a05d4339889f692b', 'GabrielAlgeranoff@fleckens.hu', 'Gabriel', 'bai2en2aH', '(02) 6720 2273', 'CANCELLED')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('d61a8be61d8a4e6cb26e1653667618ca', 'RebeccaFreame@einrot.com', 'Rebecca', 'eePeo5ee', '(07) 4983 3842', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('927745da10f941f68050e5a8d9205b4a', 'OliviaBodenwieser@einrot.com', 'Olivia', 'koov1yaiThoh', '(03) 5308 1256', 'DEACTIVE')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('81f185047e4a47908642cbcdebcfa084', 'JamieToRot@gustr.com', 'Jamie', 'juv5ahhooSh', '(02) 4900 6411', 'CANCELLED')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('1a3e86099a7241518d8b875c9d02ec81', 'LolaHeagney@rhyta.com', 'Lola', 'ooca7puJ0aing', '(02) 9656 2195', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('d921975b3e004c199e3e5d747c14dec9', 'LeahBury@superrito.com', 'Leah', 'euh5wai5Dah', '(08) 8250 5485', 'DEACTIVE')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('ee14aead9724409f9acfe4a13c0a63e3', 'TaylahTench@fleckens.hu', 'Taylah', 'Ohy4aeNgoh', '(02) 4087 5051', 'CANCELLED')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('cc1b34ef85a7457ca0df6a41efab6432', 'GraceLillico@gustr.com', 'Grace', 'ahjiZahl7th', '(03) 5381 4041', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('6f89748fd92449d6a7ba16b409cf2ea6', 'BenEdmondstone@armyspy.com', 'Ben', 'Ove4moh8ai', '(02) 9071 0770', 'DEACTIVE')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('823e7a6915ba454880eadc21740afcf9', 'KatieGarratt@jourrapide.com', 'Katie', 'ood6IShoe', '(03) 5317 1485', 'CANCELLED')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('e1621df5c057448d8134d5dd9d1cd9c8', 'KatieHoulding@gustr.com', 'Katie', 'Cheifie6u', '(08) 8351 9475', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('6e2dd4606f3a4c2a80eee6abd4da55cf', 'JordanMcClelland@fleckens.hu', 'Jordan', 'Eekoh2ua', '(03) 5370 8582', 'DEACTIVE')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('7d6ebaaf3e5b46d6bdad67c5158aab5a', 'LucySharman@fleckens.hu', 'Lucy', 'aiqu2Equ', '(02) 6743 0974', 'CANCELLED')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('a4641976f1324e08972efa001b604cd1', 'JettColdham@superrito.com', 'Jett', 'JoaSh4aex', '(02) 6125 0280', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('bad0302da6cd4630945f32e98fd96cef', 'BethanySwanston@teleworm.us', 'Bethany', 'tha9eeK8', '(02) 4910 9600', 'DEACTIVE')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('fd992bc50c5045a6bbc47589f4d9915b', 'CalebBertie@fleckens.hu', 'Caleb', 'ohnauNg7', '(08) 9432 7422', 'CANCELLED')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('0877d21a05a246a2a5171350dc6996f1', 'JamieCook@rhyta.com', 'Jamie', 'iepho7giX0Shee', '(02) 4252 0343', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('7be9d5236deb4c768279cfe059717d06', 'ClaireVarley@teleworm.us', 'Claire', 'Tohd0hu7Oh', '(08) 9386 7941', 'DEACTIVE')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('26ee526a615e41b59df3244fca34f9c0', 'LukeAvery@teleworm.us', 'Luke', 'Iiceiqu5', '(07) 3530 7293', 'CANCELLED')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('b6f11b48a5444b15897784655d12db9d', 'TristanColdham@dayrep.com', 'Tristan', 'naiKaez3ai', '(02) 4982 8480', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('5938f43f11954be5848bf506e28f8c04', 'TajRose@gustr.com', 'Taj', 'thohRieH5oe', '(07) 4519 8510', 'DEACTIVE')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('940364886ace4793a7b0a389ad34ef8f', 'AmySwayne@fleckens.hu', 'Amy', 'uBahje0oo', '(02) 4960 8672', 'CANCELLED')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('54cb39552c9f48a8962d514098a9d77d', 'SophiaBoothman@dayrep.com', 'Sophia', 'adi8ieXah', '(08) 9271 7352', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('5c414230ec5441f89cefcdf32cd71bfa', 'GeorgeFlanagan@fleckens.hu', 'George', 'ohR9chaach5', '(08) 9097 1171', 'DEACTIVE')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('3edea877311e49e190f43fc11206ed73', 'AbbyKiek@cuvox.de', 'Abby', 'Ahz8ohnaeG', '(02) 6798 2777', 'CANCELLED')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('bae0af1695fa4b0494660c3d93efb176', 'AnnabelleLucas@armyspy.com', 'Annabelle', 'eiZo7cup', '(08) 8396 8937', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('ffc5d3e9c15a4494a2e5589836385306', 'IsaacFitchett@dayrep.com', 'Isaac', 'uree1thooH', '(08) 8387 4380', 'DEACTIVE')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('82bc71e328694aa4ae9c597f47215ac7', 'AbbyTate@fleckens.hu', 'Abby', 'Gokahr6alae', '(07) 3786 0444', 'CANCELLED')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('accdb209cf074016a67663e3c3c8b2cf', 'NoahRiley@gustr.com', 'Noah', 'Ez5oo6wae3m', '(03) 5366 1405', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.\"USER\" (ID, EMAIL, \"NAME\", PASSWORD, PHONENUMBER, STATUS) VALUES ('dd0c5aa81eb74272bb804fa732134881', 'admin@oms.com', 'Admin', 'password', '(02) 4068 1547', 'ACTIVE')";

        // CUSTOMER DATA 
        query[count++] = "INSERT INTO MOVIE.CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\", ADDRESS, STATUS) VALUES ('396e2232dc9849feb7a6132fd61e3276', 'Hayden', 'HaydenBainton@jourrapide.com', 'INDIVIDUAL', '63 Point Walter Road O''CONNOR, Western Australia, 6163', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\", ADDRESS, STATUS) VALUES ('44a2f0b26f744ab6828e659402b026a9', 'Henry', 'HenryDodgshun@superrito.com', 'COMPANY', '8 Link Road MYRTLE BANK, Tasmania, 7259', 'DEACTIVE')";
        query[count++] = "INSERT INTO MOVIE.CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\", ADDRESS, STATUS) VALUES ('19849e2f6c584f0ea2df6f86623dd7b1', 'Patrick', 'PatrickLetcher@teleworm.us', 'INDIVIDUAL', '12 Amiens Road ILFORD, New South Wales, 2850', 'CANCELLED')";
        query[count++] = "INSERT INTO MOVIE.CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\", ADDRESS, STATUS) VALUES ('c19ac8611254470db582ede20393e421', 'Spencer', 'SpencerVirgo@teleworm.us', 'COMPANY', '38 Lane Street GREYTHORN, Victoria, 3104', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\", ADDRESS, STATUS) VALUES ('ec4e1204053343f989eec92aada06d99', 'Sofia', 'SofiaBlundell@superrito.com', 'INDIVIDUAL', '81 Railway Street LINTHORPE, Queensland, 4356', 'DEACTIVE')";
        query[count++] = "INSERT INTO MOVIE.CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\", ADDRESS, STATUS) VALUES ('ed0783a38401474c84e2175c89ad3d86', 'Lilian', 'LilianShapcott@armyspy.com', 'COMPANY', '35 Dalgarno Street PILLIGA, New South Wales, 2388', 'CANCELLED')";
        query[count++] = "INSERT INTO MOVIE.CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\", ADDRESS, STATUS) VALUES ('abf96c87f1a94843b4404bdfe9a97efe', 'Aaron', 'AaronBrifman@rhyta.com', 'INDIVIDUAL', '7 Millicent Drive BANDON GROVE, New South Wales, 2420', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\", ADDRESS, STATUS) VALUES ('9a17197e38574926a073453ae8d324fd', 'Zachary', 'ZacharyElkington@dayrep.com', 'COMPANY', '12 Girvan Grove ROBINVALE IRRIGATION DISTRICT SECTION C, Victoria, 3549', 'DEACTIVE')";
        query[count++] = "INSERT INTO MOVIE.CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\", ADDRESS, STATUS) VALUES ('be64c559e4684952a04dbf65ce99ecda', 'Aaron', 'AaronTrouette@einrot.com', 'INDIVIDUAL', '30 Carba Road EIGHT MILE CREEK, South Australia, 5291', 'CANCELLED')";
        query[count++] = "INSERT INTO MOVIE.CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\", ADDRESS, STATUS) VALUES ('41ea72d2c05d4ecc9d019a002b107011', 'Owen', 'OwenMacdonald@jourrapide.com', 'COMPANY', '85 Saggers Road MAGENTA, Western Australia, 6355', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\", ADDRESS, STATUS) VALUES ('e9ea2552f7b0499492785615ef6c7f19', 'Sienna', 'SiennaMcKeown@gustr.com', 'INDIVIDUAL', '11 Chapman Avenue YOSEMITE, New South Wales, 2780', 'DEACTIVE')";
        query[count++] = "INSERT INTO MOVIE.CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\", ADDRESS, STATUS) VALUES ('c9b4285d1c8c444cbac0716fa0a9d778', 'Ella', 'EllaWeindorfer@gustr.com', 'COMPANY', '40 Flax Court CORAL BANK, Victoria, 3691', 'CANCELLED')";
        query[count++] = "INSERT INTO MOVIE.CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\", ADDRESS, STATUS) VALUES ('f0d8ef0c9be84642801858d206dbf934', 'Lucinda', 'LucindaCouvreur@jourrapide.com', 'INDIVIDUAL', '11 Black Point Drive PORT GIBBON, South Australia, 5602', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\", ADDRESS, STATUS) VALUES ('dd3b1d91bdc247928fb60d8e233d398f', 'Mia', 'MiaBurges@cuvox.de', 'COMPANY', '29 Peterho Boulevard HOUGHTON, South Australia, 5131', 'DEACTIVE')";
        query[count++] = "INSERT INTO MOVIE.CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\", ADDRESS, STATUS) VALUES ('24abcd6db13d44e6b7ec6a0a44a0470d', 'Noah', 'NoahODonovan@armyspy.com', 'INDIVIDUAL', '50 Dossiter Street LEMONT, Tasmania, 7120', 'CANCELLED')";
        query[count++] = "INSERT INTO MOVIE.CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\", ADDRESS, STATUS) VALUES ('64491c513a5d4af6a77ff65152841789', 'Stephanie', 'StephanieFereday@einrot.com', 'COMPANY', '47 Badgery Road DODSWORTH, New South Wales, 2620', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\", ADDRESS, STATUS) VALUES ('6cdb70df26eb4c1cad88c827ded8664c', 'Dylan', 'DylanSmeaton@einrot.com', 'INDIVIDUAL', '65 Correa Place NIGHTCLIFF, Northern Territory, 810', 'DEACTIVE')";
        query[count++] = "INSERT INTO MOVIE.CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\", ADDRESS, STATUS) VALUES ('46d6e0cad9b54463bcbfacde04f81fbf', 'Gabriel', 'GabrielForlong@einrot.com', 'COMPANY', '69 Henry Moss Court HORNSDALE, South Australia, 5491', 'CANCELLED')";
        query[count++] = "INSERT INTO MOVIE.CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\", ADDRESS, STATUS) VALUES ('638ff9fa794c4bf296e8700fb09065b7', 'Poppy', 'PoppySuttor@gustr.com', 'INDIVIDUAL', '99 Kaesler Road KOORINE, South Australia, 5279', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\", ADDRESS, STATUS) VALUES ('3da767e9772445f98508e135774a2719', 'Finn', 'FinnBroome@gustr.com', 'COMPANY', '98 Davis Street BROOKFIELD, Queensland, 4069', 'DEACTIVE')";
        query[count++] = "INSERT INTO MOVIE.CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\", ADDRESS, STATUS) VALUES ('aaddbf23e6a3456a811a6b29d3aab532', 'Gabriel', 'GabrielAlgeranoff@fleckens.hu', 'INDIVIDUAL', '71 Murphy Street MURESK, Western Australia, 6401', 'CANCELLED')";
        query[count++] = "INSERT INTO MOVIE.CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\", ADDRESS, STATUS) VALUES ('73f817bb0bdd46299d2a4921c1fe413b', 'Rebecca', 'RebeccaFreame@einrot.com', 'COMPANY', '30 Reynolds Road CALGOA, Queensland, 4570', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\", ADDRESS, STATUS) VALUES ('6fba37ab91af4139ba79c065875fc164', 'Olivia', 'OliviaBodenwieser@einrot.com', 'INDIVIDUAL', '53 Crofts Road MARTINS CREEK, Victoria, 3888', 'DEACTIVE')";
        query[count++] = "INSERT INTO MOVIE.CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\", ADDRESS, STATUS) VALUES ('8557c9ccd35c40f3a7d2e200e2a7c311', 'Jamie', 'JamieToRot@gustr.com', 'COMPANY', '52 Wynyard Street MIDWAY, New South Wales, 2720', 'CANCELLED')";
        query[count++] = "INSERT INTO MOVIE.CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\", ADDRESS, STATUS) VALUES ('3adf9e632ff147e3b0b99f61f10ed9d3', 'Lola', 'LolaHeagney@rhyta.com', 'INDIVIDUAL', '20 Queen Street HARBORD, New South Wales, 2096', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\", ADDRESS, STATUS) VALUES ('71c9c58feec84b75bfa58a1783f2b4c4', 'Leah', 'LeahBury@superrito.com', 'COMPANY', '82 Boonah Qld BRAEMORE, Queensland, 4313', 'DEACTIVE')";
        query[count++] = "INSERT INTO MOVIE.CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\", ADDRESS, STATUS) VALUES ('81de4f3b3a604f2e8f779d029cf8b816', 'Taylah', 'TaylahTench@fleckens.hu', 'INDIVIDUAL', '21 Millicent Drive UPPER CHICHESTER, New South Wales, 2420', 'CANCELLED')";
        query[count++] = "INSERT INTO MOVIE.CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\", ADDRESS, STATUS) VALUES ('51b7f66bca0b401bb2ecabdd0b9fd242', 'Grace', 'GraceLillico@gustr.com', 'COMPANY', '99 Feather Street LANDSBOROUGH, Queensland, 4550', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\", ADDRESS, STATUS) VALUES ('037a22eb2c514bfea5b18d594a4de63c', 'Ben', 'BenEdmondstone@armyspy.com', 'INDIVIDUAL', '96 Rockhampton Qld SAPPHIRE, Queensland, 4702', 'DEACTIVE')";
        query[count++] = "INSERT INTO MOVIE.CUSTOMER (ID, \"NAME\", EMAIL, \"TYPE\", ADDRESS, STATUS) VALUES ('c173c28979db4060be92340a3463958c', 'Katie', 'KatieGarratt@jourrapide.com', 'COMPANY', '65 Hill Street MIDDLETON, Tasmania, 7163', 'CANCELLED')";

        // MOVIE DATA
        query[count++] = "INSERT INTO MOVIE.MOVIE (ID, TITLE, GENRE, PRICE, STOCK, STATUS) VALUES ('0d144111d62c432f8e8869ef17c5ad44', 'Escape Room', 'Horror', 20, 0, 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.MOVIE (ID, TITLE, GENRE, PRICE, STOCK, STATUS) VALUES ('70692f110408493a8670f186c001adbe', 'Call of the Wild', 'Documentary', 15, 73, 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.MOVIE (ID, TITLE, GENRE, PRICE, STOCK, STATUS) VALUES ('a7b6142b914f4c59bb26e287e6b64c66', 'The Age of Adaline', 'Romance', 20, 99, 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.MOVIE (ID, TITLE, GENRE, PRICE, STOCK, STATUS) VALUES ('f33de08a98d44893ac040ebbb77d3e41', 'Robin Hood', 'Adventure', 20, 0, 'DEACTIVE')";
        query[count++] = "INSERT INTO MOVIE.MOVIE (ID, TITLE, GENRE, PRICE, STOCK, STATUS) VALUES ('3147f440a92a4f02ad5525e364cb0e87', 'Megamind', 'Science Fiction', 10, 38, 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.MOVIE (ID, TITLE, GENRE, PRICE, STOCK, STATUS) VALUES ('3b5320784ed24442b9e2d1cf42efa396', 'That Awkward Moment', 'Comedy', 5, 0, 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.MOVIE (ID, TITLE, GENRE, PRICE, STOCK, STATUS) VALUES ('b38da87ea9244906ba4a08698066cae3', 'Happy Death Day 2U', 'Horror', 20, 67, 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.MOVIE (ID, TITLE, GENRE, PRICE, STOCK, STATUS) VALUES ('a65f6d8b4af64474958aba33426f3b6d', 'Aliens of the Deep', 'Documentary', 20, 41, 'DEACTIVE')";
        query[count++] = "INSERT INTO MOVIE.MOVIE (ID, TITLE, GENRE, PRICE, STOCK, STATUS) VALUES ('743a7631c5a942a18267b5a746211e9f', 'City of Angels', 'Romance', 30, 33, 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.MOVIE (ID, TITLE, GENRE, PRICE, STOCK, STATUS) VALUES ('0e92f5f0e6f14aafa5eb1a02fcec473d', 'Gulliver''s Travels', 'Adventure', 20, 29, 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.MOVIE (ID, TITLE, GENRE, PRICE, STOCK, STATUS) VALUES ('63d330acf71643d5bf26a84626d11626', 'Dark Phoenix', 'Science Fiction', 10, 18, 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.MOVIE (ID, TITLE, GENRE, PRICE, STOCK, STATUS) VALUES ('2767c136b5a747d39ef7d39545c974cf', 'Deadpool 2', 'Comedy', 10, 0, 'DEACTIVE')";
        query[count++] = "INSERT INTO MOVIE.MOVIE (ID, TITLE, GENRE, PRICE, STOCK, STATUS) VALUES ('ba91abfd99314c1ebb2026fe0d6fd490', 'Pet Sematary', 'Horror', 10, 17, 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.MOVIE (ID, TITLE, GENRE, PRICE, STOCK, STATUS) VALUES ('9422ec0fd4714288a11dc307e47aeaed', 'Hearts and Minds', 'Documentary', 10, 17, 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.MOVIE (ID, TITLE, GENRE, PRICE, STOCK, STATUS) VALUES ('32cbe82e9f074e3184d06940d0a70c67', 'The Fault in Our Stars', 'Romance', 5, 21, 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.MOVIE (ID, TITLE, GENRE, PRICE, STOCK, STATUS) VALUES ('242fb2998b9d4b079c5c933488b399fe', 'The Last Airbender', 'Adventure', 20, 63, 'DEACTIVE')";
        query[count++] = "INSERT INTO MOVIE.MOVIE (ID, TITLE, GENRE, PRICE, STOCK, STATUS) VALUES ('7a0358ad402c4e8f9ee5f9c95b391c7a', 'Brightburn', 'Science Fiction', 15, 45, 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.MOVIE (ID, TITLE, GENRE, PRICE, STOCK, STATUS) VALUES ('ac2d579f26ff4890b483e205379ef034', 'Gringo', 'Comedy', 20, 0, 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.MOVIE (ID, TITLE, GENRE, PRICE, STOCK, STATUS) VALUES ('efca610d28144a3bb1c32948f255bb97', 'Us', 'Horror', 20, 89, 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.MOVIE (ID, TITLE, GENRE, PRICE, STOCK, STATUS) VALUES ('b5cb536a768340eaada7f45c3add8f0c', 'Man from Plains', 'Documentary', 10, 122, 'DEACTIVE')";
        query[count++] = "INSERT INTO MOVIE.MOVIE (ID, TITLE, GENRE, PRICE, STOCK, STATUS) VALUES ('6547d087a6ae48c399538ce64008ad67', 'Lovestruck', 'Romance', 5, 9, 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.MOVIE (ID, TITLE, GENRE, PRICE, STOCK, STATUS) VALUES ('5bf786eec8194a9c8f6db396d3f29fe7', 'Immortals', 'Adventure', 20, 16, 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.MOVIE (ID, TITLE, GENRE, PRICE, STOCK, STATUS) VALUES ('502ce854037d460cbe1a3dd3508cb6de', 'Terminator: Dark Fate', 'Science Fiction', 20, 2, 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.MOVIE (ID, TITLE, GENRE, PRICE, STOCK, STATUS) VALUES ('21c809d808b34f9ab73d0e4539501de2', 'The Boss Baby', 'Comedy', 30, 0, 'DEACTIVE')";
        query[count++] = "INSERT INTO MOVIE.MOVIE (ID, TITLE, GENRE, PRICE, STOCK, STATUS) VALUES ('7a7b912bb1574a728a15aa196ae1fb51', 'Velvet Buzzsaw', 'Horror', 20, 8, 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.MOVIE (ID, TITLE, GENRE, PRICE, STOCK, STATUS) VALUES ('d1b69db11d1e456cb5dde2abfa6da68b', 'Predictions of Fire', 'Documentary', 10, 20, 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.MOVIE (ID, TITLE, GENRE, PRICE, STOCK, STATUS) VALUES ('8243a8b110da4cd0bf02523975f09eee', 'Something Borrowed', 'Romance', 10, 33, 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.MOVIE (ID, TITLE, GENRE, PRICE, STOCK, STATUS) VALUES ('eb727a26e38b44529368230442aa668e', 'Wrath of the Titans', 'Adventure', 10, 0, 'DEACTIVE')";
        query[count++] = "INSERT INTO MOVIE.MOVIE (ID, TITLE, GENRE, PRICE, STOCK, STATUS) VALUES ('7df1eadd6b484e8eae3b8607772de8f3', 'The Three-Body Problem', 'Science Fiction', 10, 10, 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.MOVIE (ID, TITLE, GENRE, PRICE, STOCK, STATUS) VALUES ('64c2d8576174441b912de4e12ab734c9', 'Zootopia', 'Comedy', 5, 4, 'ACTIVE')";

        // STAFF DATA
        query[count++] = "INSERT INTO MOVIE.STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\", ADDRESS, STATUS) VALUES ('0f55f79f61854efb8b8be644db4a4ed7', '0c59bb02e3fe4427a05d4339889f692b', 'GabrielAlgeranoff@fleckens.hu', 'Gabriel', 'STAFF', '71 Murphy Street MURESK, Western Australia, 6401', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\", ADDRESS, STATUS) VALUES ('e6006593baad4e33ad1905ea3738cd6b', 'd61a8be61d8a4e6cb26e1653667618ca', 'RebeccaFreame@einrot.com', 'Rebecca', 'STAFF', '30 Reynolds Road CALGOA, Queensland, 4570', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\", ADDRESS, STATUS) VALUES ('cff35730f2b44cc5ae2c5e02f03b3d16', '927745da10f941f68050e5a8d9205b4a', 'OliviaBodenwieser@einrot.com', 'Olivia', 'STAFF', '53 Crofts Road MARTINS CREEK, Victoria, 3888', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\", ADDRESS, STATUS) VALUES ('7d8b17f7070445e0a8e97a4da60f1072', '81f185047e4a47908642cbcdebcfa084', 'JamieToRot@gustr.com', 'Jamie', 'STAFF', '52 Wynyard Street MIDWAY, New South Wales, 2720', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\", ADDRESS, STATUS) VALUES ('fda0233fcc2c4f8a89047523a77fff91', '1a3e86099a7241518d8b875c9d02ec81', 'LolaHeagney@rhyta.com', 'Lola', 'STAFF', '20 Queen Street HARBORD, New South Wales, 2096', 'DEACTIVE')";
        query[count++] = "INSERT INTO MOVIE.STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\", ADDRESS, STATUS) VALUES ('6ce84871ec9f4ef9bf758953f2c9f6f2', 'd921975b3e004c199e3e5d747c14dec9', 'LeahBury@superrito.com', 'Leah', 'STAFF', '82 Boonah Qld BRAEMORE, Queensland, 4313', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\", ADDRESS, STATUS) VALUES ('1449f2fdd19640d9a303f5ece09f2ed2', 'ee14aead9724409f9acfe4a13c0a63e3', 'TaylahTench@fleckens.hu', 'Taylah', 'STAFF', '21 Millicent Drive UPPER CHICHESTER, New South Wales, 2420', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\", ADDRESS, STATUS) VALUES ('b6f961d85e7d40d4b839e215ed4b7ce8', 'cc1b34ef85a7457ca0df6a41efab6432', 'GraceLillico@gustr.com', 'Grace', 'STAFF', '99 Feather Street LANDSBOROUGH, Queensland, 4550', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\", ADDRESS, STATUS) VALUES ('859482c73d7446d781a39feb72e09763', '6f89748fd92449d6a7ba16b409cf2ea6', 'BenEdmondstone@armyspy.com', 'Ben', 'STAFF', '96 Rockhampton Qld SAPPHIRE, Queensland, 4702', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\", ADDRESS, STATUS) VALUES ('78c2b6caccd44f19b1c0704eece10ff0', '823e7a6915ba454880eadc21740afcf9', 'KatieGarratt@jourrapide.com', 'Katie', 'STAFF', '65 Hill Street MIDDLETON, Tasmania, 7163', 'DEACTIVE')";
        query[count++] = "INSERT INTO MOVIE.STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\", ADDRESS, STATUS) VALUES ('3e98b0a839ae47cfa081488e2709cfa5', 'e1621df5c057448d8134d5dd9d1cd9c8', 'KatieHoulding@gustr.com', 'Katie', 'STAFF', '71 Flinstone Drive JERICHO, TAS, 7030', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\", ADDRESS, STATUS) VALUES ('411170f90a3b4709af16a5c24f15f499', '6e2dd4606f3a4c2a80eee6abd4da55cf', 'JordanMcClelland@fleckens.hu', 'Jordan', 'STAFF', '34 Inglewood Court TARILTA, VIC, 3451', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\", ADDRESS, STATUS) VALUES ('f088895423fb4dfc9a62ad7ff5644b79', '7d6ebaaf3e5b46d6bdad67c5158aab5a', 'LucySharman@fleckens.hu', 'Lucy', 'STAFF', '55 Hillsdale Road COONAMBULA, QLD, 4626', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\", ADDRESS, STATUS) VALUES ('54574c3151344859b94e4107ee4ff0ae', 'a4641976f1324e08972efa001b604cd1', 'JettColdham@superrito.com', 'Jett', 'STAFF', '35 Maritime Avenue ALEXANDRA BRIDGE, WA, 6288', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\", ADDRESS, STATUS) VALUES ('e97c4ed0e7ec42389852bb18540d5235', 'bad0302da6cd4630945f32e98fd96cef', 'BethanySwanston@teleworm.us', 'Bethany', 'STAFF', '5 Norton Street NEUTRAL BAY, NSW, 2089', 'DEACTIVE')";
        query[count++] = "INSERT INTO MOVIE.STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\", ADDRESS, STATUS) VALUES ('2320486acb8f45f5862d18239e69aea7', 'fd992bc50c5045a6bbc47589f4d9915b', 'CalebBertie@fleckens.hu', 'Caleb', 'STAFF', '35 Cherokee Road SANDON, VIC, 3462', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\", ADDRESS, STATUS) VALUES ('ddc81455e4ef43d0a84f0c39b814c8a2', '0877d21a05a246a2a5171350dc6996f1', 'JamieCook@rhyta.com', 'Jamie', 'STAFF', '53 Peterho Boulevard GAWLER WEST, SA, 5118', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\", ADDRESS, STATUS) VALUES ('c801a403d20344f9aaa64aa18164f87b', '7be9d5236deb4c768279cfe059717d06', 'ClaireVarley@teleworm.us', 'Claire', 'STAFF', '82 Rockhampton Qld ULOGIE, QLD, 4702', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\", ADDRESS, STATUS) VALUES ('c59b1041cdb4459991bb2cade03b120d', '26ee526a615e41b59df3244fca34f9c0', 'LukeAvery@teleworm.us', 'Luke', 'STAFF', '7 Horsington Street RANGEVIEW, VIC, 3132', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\", ADDRESS, STATUS) VALUES ('60e2e8f7386a4df1844b3223ac243737', 'b6f11b48a5444b15897784655d12db9d', 'TristanColdham@dayrep.com', 'Tristan', 'STAFF', '57 Patton Street CROYDON, VIC, 3136', 'DEACTIVE')";
        query[count++] = "INSERT INTO MOVIE.STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\", ADDRESS, STATUS) VALUES ('5b61f7c61c16480383a33bfee27abefb', '5938f43f11954be5848bf506e28f8c04', 'TajRose@gustr.com', 'Taj', 'STAFF', '30 Weigall Avenue LEIGHTON, SA, 5417', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\", ADDRESS, STATUS) VALUES ('7cccc06988da4baba2c1a4050618895a', '940364886ace4793a7b0a389ad34ef8f', 'AmySwayne@fleckens.hu', 'Amy', 'STAFF', '47 Bass Street DUNGARUBBA, NSW, 2480', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\", ADDRESS, STATUS) VALUES ('7efec9c767b5462da0e2dd40d967ce2a', '54cb39552c9f48a8962d514098a9d77d', 'SophiaBoothman@dayrep.com', 'Sophia', 'STAFF', '97 Inglewood Court REDESDALE, VIC, 3444', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\", ADDRESS, STATUS) VALUES ('6b3c5b54c48e4016ac0c1c56d15311db', '5c414230ec5441f89cefcdf32cd71bfa', 'GeorgeFlanagan@fleckens.hu', 'George', 'STAFF', '56 Boughtman Street BELGRAVE, VIC, 3160', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\", ADDRESS, STATUS) VALUES ('6b0d8752043f439290bd2df6aaa70a76', '3edea877311e49e190f43fc11206ed73', 'AbbyKiek@cuvox.de', 'Abby', 'STAFF', '64 Normans Road KADNOOK, VIC, 3318', 'DEACTIVE')";
        query[count++] = "INSERT INTO MOVIE.STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\", ADDRESS, STATUS) VALUES ('e1d1a2e944514de69d38f3f247ce50e8', 'bae0af1695fa4b0494660c3d93efb176', 'AnnabelleLucas@armyspy.com', 'Annabelle', 'ADMIN', '91 Corny Court NUNJIKOMPITA, SA, 5680', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\", ADDRESS, STATUS) VALUES ('5f76b796526945fbb1f9d1f837a5bcaa', 'ffc5d3e9c15a4494a2e5589836385306', 'IsaacFitchett@dayrep.com', 'Isaac', 'ADMIN', '59 Ocean Pde TORRENS CREEK, QLD, 4816', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\", ADDRESS, STATUS) VALUES ('be795c889f534f0ba4fe510ac3dd5c2d', '82bc71e328694aa4ae9c597f47215ac7', 'AbbyTate@fleckens.hu', 'Abby', 'ADMIN', '19 Edgecliff Road GOLDEN GROVE, NSW, 2008', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\", ADDRESS, STATUS) VALUES ('db10342b42a64532abf972d5a3d39231', 'accdb209cf074016a67663e3c3c8b2cf', 'NoahRiley@gustr.com', 'Noah', 'ADMIN', '50 Crofts Road CORRINGLE, VIC, 3888', 'ACTIVE')";
        query[count++] = "INSERT INTO MOVIE.STAFF (ID, USERID, EMAIL, \"NAME\", \"POSITION\", ADDRESS, STATUS) VALUES ('ae25289380b14bacbce6788ab16de4b4', 'dd0c5aa81eb74272bb804fa732134881', 'admin@oms.com', 'Admin', 'ADMIN', '91 Shell Road DEANS MARSH, VIC, 3235', 'ACTIVE')";

        // USERACCESSLOG DATA
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('dac3afbf-e531-4112-bc00-7c119a0233e1', '612642668b89445b8a501aab7bec1cae', 'LOGIN', '2019-05-22 00:53:32.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('f5d9b47d-190b-441e-bc37-c1528a1ecc7f', 'cecd7e5bce794962a41df648dea65774', 'LOGIN', '2019-05-23 11:45:53.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('a2c25e49-0ce2-497d-b43f-dbb5697d58d7', '2f4c3820b5504c7a89364c6de7c596fe', 'LOGIN', '2019-05-24 22:38:14.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('245a8441-aa76-4879-898b-a350c3a2046f', '75367dbce9df44edb688e52e1d1f5b3b', 'LOGIN', '2019-05-26 09:30:35.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('44cd6f05-c6b8-4cf8-989c-6f37cef67d50', '4a0f9f2c23b8447c93aca7077e479bcb', 'LOGIN', '2019-05-27 20:22:56.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('9a0881eb-655c-4834-b18b-553552b6d331', '5cb20405ec394f7db8e48bf355a97694', 'LOGIN', '2019-05-29 07:15:17.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('37c8689b-4a92-4e92-8c80-27a12867fcce', '7c1bbf71e6894c4a86490a05bd997675', 'LOGIN', '2019-05-30 18:07:38.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('ef3394eb-2a7c-4d3a-a956-a7305a769804', '72f1a472a1e74d789af1a609f7c8603c', 'LOGIN', '2019-06-01 04:59:59.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('fbcba2c0-e335-48aa-8082-b59059c74947', 'bd981d02630e45239c681b60fe2ce4da', 'LOGIN', '2019-06-02 15:52:20.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('af70c9d6-0185-479c-a81a-fcf99e8c6cc0', 'ce8c30fc727e40848b817fb6faeeba0c', 'LOGIN', '2019-06-04 02:44:41.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('e1a1bcc4-e5e0-48f0-bbf4-929959406e3d', '31776564f59e425aa78c406627e4040f', 'LOGIN', '2019-06-05 13:37:02.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('ed04442b-5d48-4df9-a91a-669b3545b205', 'a4ae746520d8446f83f688c1e0b8596b', 'LOGIN', '2019-06-07 00:29:23.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('101dc0fd-dbb7-436f-a949-8778a68918cb', 'd28c39f9c29a49bf81a84fa95451f017', 'LOGIN', '2019-06-08 11:21:44.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('86e4a623-9c13-42f8-9759-44af6cb93e3d', 'e1dacdae59bc4b97a447e3cbcbc5bc40', 'LOGIN', '2019-06-09 22:14:05.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('ccef1ab7-ba8e-4b3d-9361-e72ae011e529', '24c09d16ae774a329e8afbe7faa092ec', 'LOGIN', '2019-06-11 09:06:26.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('88259bf4-8a71-4d63-bc28-d6d2165abe71', 'a653a00538624ebfad7b1037618ac1dd', 'LOGIN', '2019-06-12 19:58:47.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('d413e401-bd50-4d4f-8fe3-5e2e0323edfc', 'c7c19534a61149ce9ac8810962ab567c', 'LOGIN', '2019-06-14 06:51:08.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('7340a4c6-6c15-4c0d-b091-55ed2b7f3c04', '9416d8322e3f457d861e1af286bf13ab', 'LOGIN', '2019-06-15 17:43:29.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('3d8b754b-0db6-45aa-8820-bcd3101da1a5', '3b878551b0484feaad94c9aad58fdd79', 'LOGIN', '2019-06-17 04:35:50.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('e5704246-b8e3-45fe-b85b-468edcc3adc3', 'efa6d2c0fef649e7b2b5c51c970bc941', 'LOGIN', '2019-06-18 15:28:11.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('7e922972-79bd-4f26-9469-aa4d2ee19301', '0c59bb02e3fe4427a05d4339889f692b', 'LOGIN', '2019-06-20 02:20:32.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('ebea4048-c3d4-4128-8ebb-443f33fd4afd', 'd61a8be61d8a4e6cb26e1653667618ca', 'LOGIN', '2019-06-21 13:12:53.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('481da59f-df67-4064-98d5-df1737d8fc8d', '927745da10f941f68050e5a8d9205b4a', 'LOGIN', '2019-06-23 00:05:14.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('55631b6b-04b1-4ed0-9a73-9d0a974420a6', '81f185047e4a47908642cbcdebcfa084', 'LOGIN', '2019-06-24 10:57:35.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('5cc8f844-e5f0-4b15-b4d4-96afef7e3ec7', '1a3e86099a7241518d8b875c9d02ec81', 'LOGIN', '2019-06-25 21:49:56.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('867e4601-9bee-40ca-ab35-88d32a363493', 'd921975b3e004c199e3e5d747c14dec9', 'LOGIN', '2019-06-27 08:42:17.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('0fb3c7f4-12a7-40a3-8415-2d1eb428a3f6', 'ee14aead9724409f9acfe4a13c0a63e3', 'LOGIN', '2019-06-28 19:34:38.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('80373dc1-a062-4580-9939-777c6ce0ed87', 'cc1b34ef85a7457ca0df6a41efab6432', 'LOGIN', '2019-06-30 06:26:59.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('24c6d390-d760-49cd-bfb4-91b2788fa078', '6f89748fd92449d6a7ba16b409cf2ea6', 'LOGIN', '2019-07-01 17:19:20.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('23f8ec80-36bc-4c40-9001-21781720acf6', '823e7a6915ba454880eadc21740afcf9', 'LOGIN', '2019-07-03 04:11:41.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('ef96305d-6c97-4a8c-b1c9-2c175ecb7b79', 'e1621df5c057448d8134d5dd9d1cd9c8', 'LOGIN', '2019-07-04 15:04:02.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('18770e6b-ab75-41c1-a1bc-8a78e5289976', '6e2dd4606f3a4c2a80eee6abd4da55cf', 'LOGIN', '2019-07-06 01:56:23.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('334fca37-6758-4061-b33c-1312f93e3fe6', '7d6ebaaf3e5b46d6bdad67c5158aab5a', 'LOGIN', '2019-07-07 12:48:44.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('e3892a8a-bd5a-4058-b64e-9b3d0c11353e', 'a4641976f1324e08972efa001b604cd1', 'LOGIN', '2019-07-08 23:41:05.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('89a10816-eae7-4bf7-8a76-cc83ed223bd7', 'bad0302da6cd4630945f32e98fd96cef', 'LOGIN', '2019-07-10 10:33:26.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('b59e50fa-f432-4227-91f5-0882835f724e', 'fd992bc50c5045a6bbc47589f4d9915b', 'LOGIN', '2019-07-11 21:25:47.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('2b3bb000-fe63-4392-badb-bde1c262fdeb', '0877d21a05a246a2a5171350dc6996f1', 'LOGIN', '2019-07-13 08:18:08.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('be9371f2-354c-40a8-aa97-d2fd5c96a6ba', '7be9d5236deb4c768279cfe059717d06', 'LOGIN', '2019-07-14 19:10:29.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('7cf90989-d138-40ff-bd6f-ac2519108258', '26ee526a615e41b59df3244fca34f9c0', 'LOGIN', '2019-07-16 06:02:50.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('fcd14f38-72c3-4237-a4a3-0076d092f3b8', 'b6f11b48a5444b15897784655d12db9d', 'LOGIN', '2019-07-17 16:55:11.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('aae9867e-1f5e-4e89-ad35-a0a1761aab01', '5938f43f11954be5848bf506e28f8c04', 'LOGIN', '2019-07-19 03:47:32.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('d508f02e-f35f-4182-b168-7e5dd97f5f35', '940364886ace4793a7b0a389ad34ef8f', 'LOGIN', '2019-07-20 14:39:53.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('389a6bfc-962a-44cb-8c93-19fc836c9a61', '54cb39552c9f48a8962d514098a9d77d', 'LOGIN', '2019-07-22 01:32:14.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('ad81d371-5b4b-45c0-a3b2-b11b093dfe30', '5c414230ec5441f89cefcdf32cd71bfa', 'LOGIN', '2019-07-23 12:24:35.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('c53c8aeb-ff7c-455b-a8b7-96d9ca3d1fc3', '3edea877311e49e190f43fc11206ed73', 'LOGIN', '2019-07-24 23:16:56.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('45a73ca6-0dd3-4f3e-babe-32abd74f47b0', 'bae0af1695fa4b0494660c3d93efb176', 'LOGIN', '2019-07-26 10:09:17.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('44669c41-1493-4a99-b7db-47f043f1ab14', 'ffc5d3e9c15a4494a2e5589836385306', 'LOGIN', '2019-07-27 21:01:38.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('cca8b814-f510-4bfd-8457-e99050f64b3f', '82bc71e328694aa4ae9c597f47215ac7', 'LOGIN', '2019-07-29 07:53:59.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('4f7cebe2-f6be-4b6c-9b3c-e89284120053', 'accdb209cf074016a67663e3c3c8b2cf', 'LOGIN', '2019-07-30 18:46:20.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('7942d339-f02b-4ab8-8c99-4174042d314b', 'dd0c5aa81eb74272bb804fa732134881', 'LOGIN', '2019-08-01 05:38:41.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('c9a07291-7987-4773-aeb1-e68eb8c35d69', '612642668b89445b8a501aab7bec1cae', 'LOGOUT', '2019-08-02 16:31:02.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('c69b4383-8b6e-43c6-995b-ba15c1e90613', 'cecd7e5bce794962a41df648dea65774', 'LOGOUT', '2019-08-04 03:23:23.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('ecea72c8-c312-4db5-b203-3da460df4c29', '2f4c3820b5504c7a89364c6de7c596fe', 'LOGOUT', '2019-08-05 14:15:44.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('373b8f62-63cd-4248-b34b-6e3b16ff247e', '75367dbce9df44edb688e52e1d1f5b3b', 'LOGOUT', '2019-08-07 01:08:05.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('5e29819c-704d-474c-aff0-3fabd0610782', '4a0f9f2c23b8447c93aca7077e479bcb', 'LOGOUT', '2019-08-08 12:00:26.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('82b38acf-ecbb-41c6-850c-f9969aec64a4', '5cb20405ec394f7db8e48bf355a97694', 'LOGOUT', '2019-08-09 22:52:47.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('89876047-39ef-45e9-a84f-089cc38d2d23', '7c1bbf71e6894c4a86490a05bd997675', 'LOGOUT', '2019-08-11 09:45:08.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('63833624-6e30-4cad-b73e-134a82dbb75f', '72f1a472a1e74d789af1a609f7c8603c', 'LOGOUT', '2019-08-12 20:37:29.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('2475571e-e444-4b15-95b2-cb08c1ea2be3', 'bd981d02630e45239c681b60fe2ce4da', 'LOGOUT', '2019-08-14 07:29:50.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('6a89a4b4-80e9-441b-878a-7278784c86e5', 'ce8c30fc727e40848b817fb6faeeba0c', 'LOGOUT', '2019-08-15 18:22:11.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('01282554-5cfa-4670-9b86-8814cf38f59d', '31776564f59e425aa78c406627e4040f', 'LOGOUT', '2019-08-17 05:14:32.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('9ab9406b-4be8-4f69-91c9-30dd86094328', 'a4ae746520d8446f83f688c1e0b8596b', 'LOGOUT', '2019-08-18 16:06:53.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('abb15dbb-7d45-4aa6-8627-de564b387422', 'd28c39f9c29a49bf81a84fa95451f017', 'LOGOUT', '2019-08-20 02:59:14.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('cca4658c-b77c-4de1-9fdf-55e46edfadf3', 'e1dacdae59bc4b97a447e3cbcbc5bc40', 'LOGOUT', '2019-08-21 13:51:35.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('60ae7002-9396-4556-9136-74d391ef76a0', '24c09d16ae774a329e8afbe7faa092ec', 'LOGOUT', '2019-08-23 00:43:56.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('bafcdfe2-07c0-4e80-9aec-7e524312862a', 'a653a00538624ebfad7b1037618ac1dd', 'LOGOUT', '2019-08-24 11:36:17.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('e0c44a4c-547c-4516-b05c-b95f2a509396', 'c7c19534a61149ce9ac8810962ab567c', 'LOGOUT', '2019-08-25 22:28:38.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('f4c039fc-ff30-46a8-954d-5d9a94be0785', '9416d8322e3f457d861e1af286bf13ab', 'LOGOUT', '2019-08-27 09:20:59.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('1dab5c04-ed10-4100-b629-9cc845f935ab', '3b878551b0484feaad94c9aad58fdd79', 'LOGOUT', '2019-08-28 20:13:20.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('b4d51355-3682-4a6b-aa1d-cfefe7658b90', 'efa6d2c0fef649e7b2b5c51c970bc941', 'LOGOUT', '2019-08-30 07:05:41.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('bd629caf-180b-4154-9f14-5b922c16369f', '0c59bb02e3fe4427a05d4339889f692b', 'LOGOUT', '2019-08-31 17:58:02.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('ead7ce17-910c-424b-8657-952e4097d9d3', 'd61a8be61d8a4e6cb26e1653667618ca', 'LOGOUT', '2019-09-02 04:50:23.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('6a5a434d-8cc5-4424-9dec-85027c1755b0', '927745da10f941f68050e5a8d9205b4a', 'LOGOUT', '2019-09-03 15:42:44.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('ef50ca35-2c4d-4580-a3dd-c2dd42f475af', '81f185047e4a47908642cbcdebcfa084', 'LOGOUT', '2019-09-05 02:35:05.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('5f6f121c-6d32-4274-8862-650e9fe62afa', '1a3e86099a7241518d8b875c9d02ec81', 'LOGOUT', '2019-09-06 13:27:26.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('9868b2cc-32b9-4d21-9dc0-5a109401671c', 'd921975b3e004c199e3e5d747c14dec9', 'LOGOUT', '2019-09-08 00:19:47.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('019153d1-6d40-4063-85a9-8f8ff4966751', 'ee14aead9724409f9acfe4a13c0a63e3', 'LOGOUT', '2019-09-09 11:12:08.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('13c6cc12-84f2-4a96-a61f-06758df3e84e', 'cc1b34ef85a7457ca0df6a41efab6432', 'LOGOUT', '2019-09-10 22:04:29.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('998f61cb-df82-4038-8ff3-aa7dda0a8972', '6f89748fd92449d6a7ba16b409cf2ea6', 'LOGOUT', '2019-09-12 08:56:50.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('ed688ee3-9593-41d5-b573-701f46451e74', '823e7a6915ba454880eadc21740afcf9', 'LOGOUT', '2019-09-13 19:49:11.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('65f6bb2a-b5a4-462d-8209-aff7b78c2303', 'e1621df5c057448d8134d5dd9d1cd9c8', 'LOGOUT', '2019-09-15 06:41:32.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('763dab99-44b1-46c1-b20a-7ee41867a9f6', '6e2dd4606f3a4c2a80eee6abd4da55cf', 'LOGOUT', '2019-09-16 17:33:53.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('3273079d-a17b-4a8c-bf8a-4dd7576649e9', '7d6ebaaf3e5b46d6bdad67c5158aab5a', 'LOGOUT', '2019-09-18 04:26:14.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('1438b633-0146-47df-b364-ba143c527e85', 'a4641976f1324e08972efa001b604cd1', 'LOGOUT', '2019-09-19 15:18:35.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('e02d63d1-b07b-4c1c-995b-5367742c7f6c', 'bad0302da6cd4630945f32e98fd96cef', 'LOGOUT', '2019-09-21 02:10:56.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('2fc784ff-52f7-4267-bf29-94755f4ccfaa', 'fd992bc50c5045a6bbc47589f4d9915b', 'LOGOUT', '2019-09-22 13:03:17.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('0e53fe21-e5f0-487a-8156-8973876f0ee9', '0877d21a05a246a2a5171350dc6996f1', 'LOGOUT', '2019-09-23 23:55:38.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('74ef6f7f-bc41-4712-a630-7d2358e5d690', '7be9d5236deb4c768279cfe059717d06', 'LOGOUT', '2019-09-25 10:47:59.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('90f0f2c8-b52a-4432-9fde-135348bf7c0d', '26ee526a615e41b59df3244fca34f9c0', 'LOGOUT', '2019-09-26 21:40:20.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('c1494150-aea9-4e12-9f7f-73d4cde16789', 'b6f11b48a5444b15897784655d12db9d', 'LOGOUT', '2019-09-28 08:32:41.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('a1f0e366-839f-4f17-8fc5-57b96cee6d11', '5938f43f11954be5848bf506e28f8c04', 'LOGOUT', '2019-09-29 19:25:02.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('77460048-43d4-403b-a765-3d89ba068547', '940364886ace4793a7b0a389ad34ef8f', 'LOGOUT', '2019-10-01 06:17:23.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('e53f7895-60c5-49a5-bc1a-d59fedb8238e', '54cb39552c9f48a8962d514098a9d77d', 'LOGOUT', '2019-10-02 17:09:44.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('9764e9b0-6062-47cb-af6d-b1cda57cd4ed', '5c414230ec5441f89cefcdf32cd71bfa', 'LOGOUT', '2019-10-04 04:02:05.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('d80871cd-fd89-48a3-83a6-81f3b0caaa48', '3edea877311e49e190f43fc11206ed73', 'LOGOUT', '2019-10-05 14:54:26.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('3aca2eb4-2b92-45e4-9d60-ce8079c6f5d6', 'bae0af1695fa4b0494660c3d93efb176', 'LOGOUT', '2019-10-07 01:46:47.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('74358b92-91e4-49a4-a81b-ec6bd92bd015', 'ffc5d3e9c15a4494a2e5589836385306', 'LOGOUT', '2019-10-08 12:39:08.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('f0898468-d722-419c-91eb-2ae4595b5cef', '82bc71e328694aa4ae9c597f47215ac7', 'LOGOUT', '2019-10-09 23:31:29.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('d341b39c-2584-4e71-a1cc-0faca42ed1af', 'accdb209cf074016a67663e3c3c8b2cf', 'LOGOUT', '2019-10-11 10:23:50.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('efc1f7ba-c671-4e08-a788-33762b0c292d', 'dd0c5aa81eb74272bb804fa732134881', 'LOGOUT', '2019-10-12 21:16:11.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('a8147aa2-1c7d-4188-ad1c-111b66a3b842', '612642668b89445b8a501aab7bec1cae', 'LOGIN', '2019-10-14 08:08:32.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('0b17127d-ecf9-44a0-b53e-2781e8aea72e', 'cecd7e5bce794962a41df648dea65774', 'LOGIN', '2019-10-15 19:00:53.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('607e1144-4b46-4e08-b024-4fa49deca7fa', '2f4c3820b5504c7a89364c6de7c596fe', 'LOGIN', '2019-10-17 05:53:14.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('2377f82c-8053-4dc7-91ba-9148386a1581', '75367dbce9df44edb688e52e1d1f5b3b', 'LOGIN', '2019-10-18 16:45:35.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('c9261876-9337-4c25-899b-fc60366d4e43', '4a0f9f2c23b8447c93aca7077e479bcb', 'LOGIN', '2019-10-20 03:37:56.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('5d540a02-ed61-4620-ac07-51a47507b42f', '5cb20405ec394f7db8e48bf355a97694', 'LOGIN', '2019-10-21 14:30:17.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('8b550950-600d-42e7-a70d-835e82c29985', '7c1bbf71e6894c4a86490a05bd997675', 'LOGIN', '2019-10-23 01:22:38.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('7943f996-fa7c-42e0-8689-206faca3f1a9', '72f1a472a1e74d789af1a609f7c8603c', 'LOGIN', '2019-10-24 12:14:59.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('7c8026e4-b04d-48b7-ac05-9a5a81f27a41', 'bd981d02630e45239c681b60fe2ce4da', 'LOGIN', '2019-10-25 23:07:20.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('f36f4484-fa89-44f1-8c30-ce2ade17bd86', 'ce8c30fc727e40848b817fb6faeeba0c', 'LOGIN', '2019-10-27 09:59:41.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('660bb809-48af-462c-bdd5-2da153a7f70b', '31776564f59e425aa78c406627e4040f', 'LOGIN', '2019-10-28 20:52:02.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('ebeccc2f-a3f4-48c7-a76e-47cafd980ce4', 'a4ae746520d8446f83f688c1e0b8596b', 'LOGIN', '2019-10-30 07:44:23.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('cf90068b-4c70-4cdd-a166-f6153ff315d9', 'd28c39f9c29a49bf81a84fa95451f017', 'LOGIN', '2019-10-31 18:36:44.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('e428c80b-a7bc-4557-9154-ad178d1e39b9', 'e1dacdae59bc4b97a447e3cbcbc5bc40', 'LOGIN', '2019-11-02 05:29:05.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('a22b9530-0125-4130-aaa0-1a03b771d709', '24c09d16ae774a329e8afbe7faa092ec', 'LOGIN', '2019-11-03 16:21:26.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('25f335cc-6b7f-4248-bc06-42d1fe2b6d8a', 'a653a00538624ebfad7b1037618ac1dd', 'LOGIN', '2019-11-05 03:13:47.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('7682f18b-c8eb-405f-8f38-b812d26656f5', 'c7c19534a61149ce9ac8810962ab567c', 'LOGIN', '2019-11-06 14:06:08.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('1241c342-0e1b-4a3f-8455-c252aba98104', '9416d8322e3f457d861e1af286bf13ab', 'LOGIN', '2019-11-08 00:58:29.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('aba3f1af-265d-4a02-a318-88650b48e90f', '3b878551b0484feaad94c9aad58fdd79', 'LOGIN', '2019-11-09 11:50:50.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('e7cb787a-6eeb-4a09-ad61-0255a33a4a40', 'efa6d2c0fef649e7b2b5c51c970bc941', 'LOGIN', '2019-11-10 22:43:11.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('38ef65ac-c212-4da9-b7a9-fc72ae0e9080', '0c59bb02e3fe4427a05d4339889f692b', 'LOGIN', '2019-11-12 09:35:32.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('bcc96119-1173-46e9-9172-57fd390fa7ab', 'd61a8be61d8a4e6cb26e1653667618ca', 'LOGIN', '2019-11-13 20:27:53.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('54b1859d-5f96-4b17-b114-55cf67235a7d', '927745da10f941f68050e5a8d9205b4a', 'LOGIN', '2019-11-15 07:20:14.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('f4bd2860-3eae-4831-bfde-235aa6c990e9', '81f185047e4a47908642cbcdebcfa084', 'LOGIN', '2019-11-16 18:12:35.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('da348d9b-75a0-413c-8333-1bca386f19b1', '1a3e86099a7241518d8b875c9d02ec81', 'LOGIN', '2019-11-18 05:04:56.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('790f3ebb-d238-4a3f-8ca8-c0fcd9e5ad94', 'd921975b3e004c199e3e5d747c14dec9', 'LOGIN', '2019-11-19 15:57:17.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('a2415411-bf63-4911-9008-045a45db507f', 'ee14aead9724409f9acfe4a13c0a63e3', 'LOGIN', '2019-11-21 02:49:38.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('ad128005-26a6-4ffb-8099-c6ff9ee8f271', 'cc1b34ef85a7457ca0df6a41efab6432', 'LOGIN', '2019-11-22 13:41:59.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('57e1877b-c44c-4231-907d-ff56b1eb4c4f', '6f89748fd92449d6a7ba16b409cf2ea6', 'LOGIN', '2019-11-24 00:34:20.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('8143c826-e13d-40d1-b987-63eba9ad94bc', '823e7a6915ba454880eadc21740afcf9', 'LOGIN', '2019-11-25 11:26:41.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('f0478381-46fb-416b-a901-6d389eb89013', 'e1621df5c057448d8134d5dd9d1cd9c8', 'LOGIN', '2019-11-26 22:19:02.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('70ea9e1f-b376-4af9-9f93-14c8da6db513', '6e2dd4606f3a4c2a80eee6abd4da55cf', 'LOGIN', '2019-11-28 09:11:23.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('7a7e0655-211f-48a6-b035-d695fd23352d', '7d6ebaaf3e5b46d6bdad67c5158aab5a', 'LOGIN', '2019-11-29 20:03:44.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('1142dd3d-48a4-4adb-b602-49af8c4e5704', 'a4641976f1324e08972efa001b604cd1', 'LOGIN', '2019-12-01 06:56:05.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('cfe47115-271c-4a9c-89eb-a96581456edb', 'bad0302da6cd4630945f32e98fd96cef', 'LOGIN', '2019-12-02 17:48:26.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('f72dba3f-3dcd-4410-bb5c-bf4c925eb2f8', 'fd992bc50c5045a6bbc47589f4d9915b', 'LOGIN', '2019-12-04 04:40:47.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('506d2ef0-e2e4-4bef-a676-a440a32a0b5c', '0877d21a05a246a2a5171350dc6996f1', 'LOGIN', '2019-12-05 15:33:08.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('2a12dac1-78d2-4fc7-86d7-9ee5a12b2f8b', '7be9d5236deb4c768279cfe059717d06', 'LOGIN', '2019-12-07 02:25:29.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('7707594b-600b-4689-a283-e18b1db12698', '26ee526a615e41b59df3244fca34f9c0', 'LOGIN', '2019-12-08 13:17:50.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('2d4325e3-386c-496e-8bef-fa74f8b74bd3', 'b6f11b48a5444b15897784655d12db9d', 'LOGIN', '2019-12-10 00:10:11.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('952de555-53cb-45a7-97a5-2949de071312', '5938f43f11954be5848bf506e28f8c04', 'LOGIN', '2019-12-11 11:02:32.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('793894ce-dc19-433b-a553-d44b00e9c79f', '940364886ace4793a7b0a389ad34ef8f', 'LOGIN', '2019-12-12 21:54:53.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('03ef0e9f-842f-4ac5-a636-d3862a69f933', '54cb39552c9f48a8962d514098a9d77d', 'LOGIN', '2019-12-14 08:47:14.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('822cee96-bc7d-494e-b52a-23720f84f06d', '5c414230ec5441f89cefcdf32cd71bfa', 'LOGIN', '2019-12-15 19:39:35.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('8b1ed916-0a2c-42c4-81a2-0469665ac6e2', '3edea877311e49e190f43fc11206ed73', 'LOGIN', '2019-12-17 06:31:56.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('c17a1e24-e276-417f-8d57-d32cea92ea0f', 'bae0af1695fa4b0494660c3d93efb176', 'LOGIN', '2019-12-18 17:24:17.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('da40d4b9-d170-4267-9bfa-eea4fc71c103', 'ffc5d3e9c15a4494a2e5589836385306', 'LOGIN', '2019-12-20 04:16:38.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('ac78fca3-e375-4035-9c8a-9d2486a036e6', '82bc71e328694aa4ae9c597f47215ac7', 'LOGIN', '2019-12-21 15:08:59.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('0974f94f-7ed3-49fd-a86d-08c4b11eaa65', 'accdb209cf074016a67663e3c3c8b2cf', 'LOGIN', '2019-12-23 02:01:20.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('0d53bec8-bc59-434e-8ac4-df0e96dd5a63', 'dd0c5aa81eb74272bb804fa732134881', 'LOGIN', '2019-12-24 12:53:41.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('d41cf8ed-dd67-4e42-a30b-32cbcc666dcc', '612642668b89445b8a501aab7bec1cae', 'LOGOUT', '2019-12-25 23:46:02.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('7db6548e-ee72-4b8d-9704-f8f06deae371', 'cecd7e5bce794962a41df648dea65774', 'LOGOUT', '2019-12-27 10:38:23.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('37134d3d-a6da-4f98-88c7-845113e58ab3', '2f4c3820b5504c7a89364c6de7c596fe', 'LOGOUT', '2019-12-28 21:30:44.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('bca4ce4e-d009-4438-b3dc-ef5317996dbb', '75367dbce9df44edb688e52e1d1f5b3b', 'LOGOUT', '2019-12-30 08:23:05.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('a8ce6b93-6aa0-4e18-9ee4-4b46fc5da48a', '4a0f9f2c23b8447c93aca7077e479bcb', 'LOGOUT', '2019-12-31 19:15:26.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('84c6d321-ffec-415b-8ccc-3db24033177e', '5cb20405ec394f7db8e48bf355a97694', 'LOGOUT', '2020-01-02 06:07:47.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('b442f8d7-6ba8-4bc7-b567-ada150a14380', '7c1bbf71e6894c4a86490a05bd997675', 'LOGOUT', '2020-01-03 17:00:08.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('f8967999-7da8-4eae-a0aa-b482f91d2f81', '72f1a472a1e74d789af1a609f7c8603c', 'LOGOUT', '2020-01-05 03:52:29.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('856ae243-fbd8-43bb-97c4-0d4b171ec81c', 'bd981d02630e45239c681b60fe2ce4da', 'LOGOUT', '2020-01-06 14:44:50.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('d6886cff-1453-4ead-89dd-02767b9b3869', 'ce8c30fc727e40848b817fb6faeeba0c', 'LOGOUT', '2020-01-08 01:37:11.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('f1d0be45-4662-4019-bb8e-c5008177f49f', '31776564f59e425aa78c406627e4040f', 'LOGOUT', '2020-01-09 12:29:32.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('7aaa91d0-94ec-40ba-903a-901fe0d09a7a', 'a4ae746520d8446f83f688c1e0b8596b', 'LOGOUT', '2020-01-10 23:21:53.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('4196ab61-7aa7-4593-803d-4c508a4c0f4e', 'd28c39f9c29a49bf81a84fa95451f017', 'LOGOUT', '2020-01-12 10:14:14.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('6713b9d0-e463-47cc-ad80-a6dab40aba10', 'e1dacdae59bc4b97a447e3cbcbc5bc40', 'LOGOUT', '2020-01-13 21:06:35.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('71e9c1ac-55e4-42c4-9150-e05299ed1a81', '24c09d16ae774a329e8afbe7faa092ec', 'LOGOUT', '2020-01-15 07:58:56.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('8be300f5-4a57-44f2-820b-0707dc687e57', 'a653a00538624ebfad7b1037618ac1dd', 'LOGOUT', '2020-01-16 18:51:17.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('709a4b2d-b1f7-445f-b1f5-b8bedb9bb27c', 'c7c19534a61149ce9ac8810962ab567c', 'LOGOUT', '2020-01-18 05:43:38.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('a3da6993-f220-4181-9ed9-937e7d7bac93', '9416d8322e3f457d861e1af286bf13ab', 'LOGOUT', '2020-01-19 16:35:59.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('6664f746-3595-4e0d-9bcc-de30002d4d86', '3b878551b0484feaad94c9aad58fdd79', 'LOGOUT', '2020-01-21 03:28:20.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('af3ac359-6812-460e-836d-e0106eb69e58', 'efa6d2c0fef649e7b2b5c51c970bc941', 'LOGOUT', '2020-01-22 14:20:41.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('2247a364-6284-4dde-8eb5-af4cbe1178ec', '0c59bb02e3fe4427a05d4339889f692b', 'LOGOUT', '2020-01-24 01:13:02.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('40f2e514-519a-43b8-a4ff-c4242ed73447', 'd61a8be61d8a4e6cb26e1653667618ca', 'LOGOUT', '2020-01-25 12:05:23.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('8fd0dc77-3b34-4568-8390-10d290644bae', '927745da10f941f68050e5a8d9205b4a', 'LOGOUT', '2020-01-26 22:57:44.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('9c6a6561-0eb0-499a-a3de-0b954fc8def8', '81f185047e4a47908642cbcdebcfa084', 'LOGOUT', '2020-01-28 09:50:05.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('6e96b071-d0e3-4c73-a8ee-11801bf7e4c1', '1a3e86099a7241518d8b875c9d02ec81', 'LOGOUT', '2020-01-29 20:42:26.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('027c7768-fd83-4172-9337-e04f9da348c9', 'd921975b3e004c199e3e5d747c14dec9', 'LOGOUT', '2020-01-31 07:34:47.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('5af234c8-eddb-4c83-a2bf-f3d582fd07d5', 'ee14aead9724409f9acfe4a13c0a63e3', 'LOGOUT', '2020-02-01 18:27:08.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('fdf15854-2da1-41d4-af4d-cac3505da12e', 'cc1b34ef85a7457ca0df6a41efab6432', 'LOGOUT', '2020-02-03 05:19:29.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('58a183fa-dd49-4fd2-a62e-a60f3005f70f', '6f89748fd92449d6a7ba16b409cf2ea6', 'LOGOUT', '2020-02-04 16:11:50.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('a0714b32-0ade-4d43-aac1-30a56c740e1f', '823e7a6915ba454880eadc21740afcf9', 'LOGOUT', '2020-02-06 03:04:11.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('8b6ec638-1615-40f3-be5f-5e6cc9f4d3f1', 'e1621df5c057448d8134d5dd9d1cd9c8', 'LOGOUT', '2020-02-07 13:56:32.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('225109d6-2d61-4647-9d77-c120b6565853', '6e2dd4606f3a4c2a80eee6abd4da55cf', 'LOGOUT', '2020-02-09 00:48:53.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('0efc1d2a-b5f0-4f8e-8bcb-3d3cbe9d007e', '7d6ebaaf3e5b46d6bdad67c5158aab5a', 'LOGOUT', '2020-02-10 11:41:14.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('bd95f986-9c50-4dae-a4e3-17923fc57cc9', 'a4641976f1324e08972efa001b604cd1', 'LOGOUT', '2020-02-11 22:33:35.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('937d9e6b-480f-4823-adac-a9b33ff05798', 'bad0302da6cd4630945f32e98fd96cef', 'LOGOUT', '2020-02-13 09:25:56.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('c06e40ba-e66a-42b8-abc3-6dd310e0ff76', 'fd992bc50c5045a6bbc47589f4d9915b', 'LOGOUT', '2020-02-14 20:18:17.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('aec0b973-4f21-4577-bfaf-c898d40b44fb', '0877d21a05a246a2a5171350dc6996f1', 'LOGOUT', '2020-02-16 07:10:38.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('1d89b72a-2de9-4590-b285-b46b53a05d5a', '7be9d5236deb4c768279cfe059717d06', 'LOGOUT', '2020-02-17 18:02:59.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('a7ef025c-9946-4159-a46d-f14bbfa8646f', '26ee526a615e41b59df3244fca34f9c0', 'LOGOUT', '2020-02-19 04:55:20.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('83c3623c-e6c1-435e-b239-edd88a970ade', 'b6f11b48a5444b15897784655d12db9d', 'LOGOUT', '2020-02-20 15:47:41.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('23cd077b-7b78-4a41-afc5-1d4d7be3791b', '5938f43f11954be5848bf506e28f8c04', 'LOGOUT', '2020-02-22 02:40:02.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('75bd9d39-25e5-4f17-ab86-97dd4f3a5c5c', '940364886ace4793a7b0a389ad34ef8f', 'LOGOUT', '2020-02-23 13:32:23.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('46728e0e-5d28-42d0-a51a-01195956f450', '54cb39552c9f48a8962d514098a9d77d', 'LOGOUT', '2020-02-25 00:24:44.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('d2a930c3-f029-4d45-83f3-7b014ca9cefd', '5c414230ec5441f89cefcdf32cd71bfa', 'LOGOUT', '2020-02-26 11:17:05.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('7080febf-e4cd-4d53-a579-65b040be1ae3', '3edea877311e49e190f43fc11206ed73', 'LOGOUT', '2020-02-27 22:09:26.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('a8d68bec-cec9-4a2a-b796-debd69b9a2b1', 'bae0af1695fa4b0494660c3d93efb176', 'LOGOUT', '2020-02-29 09:01:47.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('255a8779-c371-4ec6-998a-2ab071b24c14', 'ffc5d3e9c15a4494a2e5589836385306', 'LOGOUT', '2020-03-01 19:54:08.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('58c4a565-7c65-4d19-8ef4-43ff8473d1cb', '82bc71e328694aa4ae9c597f47215ac7', 'LOGOUT', '2020-03-03 06:46:29.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('5650985d-6895-421b-97b7-6f93ca6d4555', 'accdb209cf074016a67663e3c3c8b2cf', 'LOGOUT', '2020-03-04 17:38:50.0')";
        query[count++] = "INSERT INTO MOVIE.USERACCESSLOG (ID, USERID, ACCESSTYPE, \"TIMESTAMP\") VALUES ('0e89747f-9435-48cd-a5ca-042260f7b574', 'dd0c5aa81eb74272bb804fa732134881', 'LOGOUT', '2020-03-06 04:31:11.0')";

        // ORDER DATA
        query[count++] = "INSERT INTO MOVIE.\"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('37134d3d-a6da-4f98-88c7-845113e58ab3', '396e2232dc9849feb7a6132fd61e3276', '0d144111d62c432f8e8869ef17c5ad44', '612642668b89445b8a501aab7bec1cae', 20, 1, 20, '2019-05-24', 'COMPLETED')";
        query[count++] = "INSERT INTO MOVIE.\"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('bca4ce4e-d009-4438-b3dc-ef5317996dbb', '44a2f0b26f744ab6828e659402b026a9', '70692f110408493a8670f186c001adbe', 'cecd7e5bce794962a41df648dea65774', 15, 1, 15, '2019-05-22', 'SAVED')";
        query[count++] = "INSERT INTO MOVIE.\"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('a8ce6b93-6aa0-4e18-9ee4-4b46fc5da48a', '19849e2f6c584f0ea2df6f86623dd7b1', 'a7b6142b914f4c59bb26e287e6b64c66', '2f4c3820b5504c7a89364c6de7c596fe', 20, 5, 100, '2019-05-20', 'CANCELLED')";
        query[count++] = "INSERT INTO MOVIE.\"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('84c6d321-ffec-415b-8ccc-3db24033177e', 'c19ac8611254470db582ede20393e421', 'f33de08a98d44893ac040ebbb77d3e41', '75367dbce9df44edb688e52e1d1f5b3b', 20, 1, 20, '2019-05-18', 'COMPLETED')";
        query[count++] = "INSERT INTO MOVIE.\"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('b442f8d7-6ba8-4bc7-b567-ada150a14380', 'ec4e1204053343f989eec92aada06d99', '3147f440a92a4f02ad5525e364cb0e87', '4a0f9f2c23b8447c93aca7077e479bcb', 10, 1, 10, '2019-05-16', 'SAVED')";
        query[count++] = "INSERT INTO MOVIE.\"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('f8967999-7da8-4eae-a0aa-b482f91d2f81', 'ed0783a38401474c84e2175c89ad3d86', '3b5320784ed24442b9e2d1cf42efa396', '5cb20405ec394f7db8e48bf355a97694', 5, 4, 20, '2019-05-14', 'CANCELLED')";
        query[count++] = "INSERT INTO MOVIE.\"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('856ae243-fbd8-43bb-97c4-0d4b171ec81c', 'abf96c87f1a94843b4404bdfe9a97efe', 'b38da87ea9244906ba4a08698066cae3', '7c1bbf71e6894c4a86490a05bd997675', 20, 1, 20, '2019-05-12', 'COMPLETED')";
        query[count++] = "INSERT INTO MOVIE.\"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('d6886cff-1453-4ead-89dd-02767b9b3869', '9a17197e38574926a073453ae8d324fd', 'a65f6d8b4af64474958aba33426f3b6d', '72f1a472a1e74d789af1a609f7c8603c', 20, 1, 20, '2019-05-10', 'SAVED')";
        query[count++] = "INSERT INTO MOVIE.\"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('f1d0be45-4662-4019-bb8e-c5008177f49f', 'be64c559e4684952a04dbf65ce99ecda', '743a7631c5a942a18267b5a746211e9f', 'bd981d02630e45239c681b60fe2ce4da', 30, 1, 30, '2019-05-08', 'CANCELLED')";
        query[count++] = "INSERT INTO MOVIE.\"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('7aaa91d0-94ec-40ba-903a-901fe0d09a7a', '41ea72d2c05d4ecc9d019a002b107011', '0e92f5f0e6f14aafa5eb1a02fcec473d', 'ce8c30fc727e40848b817fb6faeeba0c', 20, 3, 60, '2019-05-06', 'COMPLETED')";
        query[count++] = "INSERT INTO MOVIE.\"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('4196ab61-7aa7-4593-803d-4c508a4c0f4e', 'e9ea2552f7b0499492785615ef6c7f19', '63d330acf71643d5bf26a84626d11626', '31776564f59e425aa78c406627e4040f', 10, 1, 10, '2019-05-04', 'SAVED')";
        query[count++] = "INSERT INTO MOVIE.\"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('6713b9d0-e463-47cc-ad80-a6dab40aba10', 'c9b4285d1c8c444cbac0716fa0a9d778', '2767c136b5a747d39ef7d39545c974cf', 'a4ae746520d8446f83f688c1e0b8596b', 10, 1, 10, '2019-05-02', 'CANCELLED')";
        query[count++] = "INSERT INTO MOVIE.\"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('71e9c1ac-55e4-42c4-9150-e05299ed1a81', 'f0d8ef0c9be84642801858d206dbf934', 'ba91abfd99314c1ebb2026fe0d6fd490', 'd28c39f9c29a49bf81a84fa95451f017', 10, 2, 20, '2019-04-30', 'COMPLETED')";
        query[count++] = "INSERT INTO MOVIE.\"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('8be300f5-4a57-44f2-820b-0707dc687e57', 'dd3b1d91bdc247928fb60d8e233d398f', '9422ec0fd4714288a11dc307e47aeaed', 'e1dacdae59bc4b97a447e3cbcbc5bc40', 10, 1, 10, '2019-04-28', 'SAVED')";
        query[count++] = "INSERT INTO MOVIE.\"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('709a4b2d-b1f7-445f-b1f5-b8bedb9bb27c', '24abcd6db13d44e6b7ec6a0a44a0470d', '32cbe82e9f074e3184d06940d0a70c67', '24c09d16ae774a329e8afbe7faa092ec', 5, 1, 5, '2019-04-26', 'CANCELLED')";
        query[count++] = "INSERT INTO MOVIE.\"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('a3da6993-f220-4181-9ed9-937e7d7bac93', '64491c513a5d4af6a77ff65152841789', '242fb2998b9d4b079c5c933488b399fe', 'a653a00538624ebfad7b1037618ac1dd', 20, 1, 20, '2019-04-24', 'COMPLETED')";
        query[count++] = "INSERT INTO MOVIE.\"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('6664f746-3595-4e0d-9bcc-de30002d4d86', '6cdb70df26eb4c1cad88c827ded8664c', '7a0358ad402c4e8f9ee5f9c95b391c7a', 'c7c19534a61149ce9ac8810962ab567c', 15, 3, 45, '2019-04-22', 'SAVED')";
        query[count++] = "INSERT INTO MOVIE.\"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('af3ac359-6812-460e-836d-e0106eb69e58', '46d6e0cad9b54463bcbfacde04f81fbf', 'ac2d579f26ff4890b483e205379ef034', '9416d8322e3f457d861e1af286bf13ab', 20, 1, 20, '2019-04-20', 'CANCELLED')";
        query[count++] = "INSERT INTO MOVIE.\"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('2247a364-6284-4dde-8eb5-af4cbe1178ec', '638ff9fa794c4bf296e8700fb09065b7', 'efca610d28144a3bb1c32948f255bb97', '3b878551b0484feaad94c9aad58fdd79', 20, 1, 20, '2019-04-18', 'COMPLETED')";
        query[count++] = "INSERT INTO MOVIE.\"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('40f2e514-519a-43b8-a4ff-c4242ed73447', '3da767e9772445f98508e135774a2719', 'b5cb536a768340eaada7f45c3add8f0c', 'efa6d2c0fef649e7b2b5c51c970bc941', 10, 1, 10, '2019-04-16', 'SAVED')";
        query[count++] = "INSERT INTO MOVIE.\"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('8fd0dc77-3b34-4568-8390-10d290644bae', 'aaddbf23e6a3456a811a6b29d3aab532', '6547d087a6ae48c399538ce64008ad67', '0c59bb02e3fe4427a05d4339889f692b', 5, 2, 10, '2019-04-14', 'CANCELLED')";
        query[count++] = "INSERT INTO MOVIE.\"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('9c6a6561-0eb0-499a-a3de-0b954fc8def8', '73f817bb0bdd46299d2a4921c1fe413b', '5bf786eec8194a9c8f6db396d3f29fe7', 'd61a8be61d8a4e6cb26e1653667618ca', 20, 1, 20, '2019-04-12', 'COMPLETED')";
        query[count++] = "INSERT INTO MOVIE.\"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('6e96b071-d0e3-4c73-a8ee-11801bf7e4c1', '6fba37ab91af4139ba79c065875fc164', '502ce854037d460cbe1a3dd3508cb6de', '927745da10f941f68050e5a8d9205b4a', 20, 1, 20, '2019-04-10', 'SAVED')";
        query[count++] = "INSERT INTO MOVIE.\"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('027c7768-fd83-4172-9337-e04f9da348c9', '8557c9ccd35c40f3a7d2e200e2a7c311', '21c809d808b34f9ab73d0e4539501de2', '81f185047e4a47908642cbcdebcfa084', 30, 1, 30, '2019-04-08', 'CANCELLED')";
        query[count++] = "INSERT INTO MOVIE.\"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('5af234c8-eddb-4c83-a2bf-f3d582fd07d5', '3adf9e632ff147e3b0b99f61f10ed9d3', '7a7b912bb1574a728a15aa196ae1fb51', '1a3e86099a7241518d8b875c9d02ec81', 20, 2, 40, '2019-04-06', 'COMPLETED')";
        query[count++] = "INSERT INTO MOVIE.\"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('fdf15854-2da1-41d4-af4d-cac3505da12e', '71c9c58feec84b75bfa58a1783f2b4c4', 'd1b69db11d1e456cb5dde2abfa6da68b', 'd921975b3e004c199e3e5d747c14dec9', 10, 1, 10, '2019-04-04', 'SAVED')";
        query[count++] = "INSERT INTO MOVIE.\"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('58a183fa-dd49-4fd2-a62e-a60f3005f70f', '81de4f3b3a604f2e8f779d029cf8b816', '8243a8b110da4cd0bf02523975f09eee', 'ee14aead9724409f9acfe4a13c0a63e3', 10, 1, 10, '2019-04-02', 'CANCELLED')";
        query[count++] = "INSERT INTO MOVIE.\"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('a0714b32-0ade-4d43-aac1-30a56c740e1f', '51b7f66bca0b401bb2ecabdd0b9fd242', 'eb727a26e38b44529368230442aa668e', 'cc1b34ef85a7457ca0df6a41efab6432', 10, 1, 10, '2019-03-31', 'COMPLETED')";
        query[count++] = "INSERT INTO MOVIE.\"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('8b6ec638-1615-40f3-be5f-5e6cc9f4d3f1', '037a22eb2c514bfea5b18d594a4de63c', '7df1eadd6b484e8eae3b8607772de8f3', '6f89748fd92449d6a7ba16b409cf2ea6', 10, 1, 10, '2019-03-29', 'SAVED')";
        query[count++] = "INSERT INTO MOVIE.\"ORDER\" (ID, CUSTOMERID, MOVIEID, USERID, PRICE, AMOUNT, TOTALPRICE, \"DATE\", STATUS) VALUES ('225109d6-2d61-4647-9d77-c120b6565853', 'c173c28979db4060be92340a3463958c', '64c2d8576174441b912de4e12ab734c9', '823e7a6915ba454880eadc21740afcf9', 5, 1, 5, '2019-03-27', 'CANCELLED')";

        // PAYMENT DATA
        query[count++] = "INSERT INTO MOVIE.PAYMENT (ID, ORDERID, \"METHOD\", AMOUNT, \"DATE\") VALUES ('74ef6f7f-bc41-4712-a630-7d2358e5d690', '37134d3d-a6da-4f98-88c7-845113e58ab3', 'Paypal', 20, '2019-05-24')";
        query[count++] = "INSERT INTO MOVIE.PAYMENT (ID, ORDERID, \"METHOD\", AMOUNT, \"DATE\") VALUES ('90f0f2c8-b52a-4432-9fde-135348bf7c0d', 'bca4ce4e-d009-4438-b3dc-ef5317996dbb', 'Credit Card', 15, '2019-05-22')";
        query[count++] = "INSERT INTO MOVIE.PAYMENT (ID, ORDERID, \"METHOD\", AMOUNT, \"DATE\") VALUES ('c1494150-aea9-4e12-9f7f-73d4cde16789', 'a8ce6b93-6aa0-4e18-9ee4-4b46fc5da48a', 'Paypal', 100, '2019-05-20')";
        query[count++] = "INSERT INTO MOVIE.PAYMENT (ID, ORDERID, \"METHOD\", AMOUNT, \"DATE\") VALUES ('a1f0e366-839f-4f17-8fc5-57b96cee6d11', '84c6d321-ffec-415b-8ccc-3db24033177e', 'Credit Card', 20, '2019-05-18')";
        query[count++] = "INSERT INTO MOVIE.PAYMENT (ID, ORDERID, \"METHOD\", AMOUNT, \"DATE\") VALUES ('77460048-43d4-403b-a765-3d89ba068547', 'b442f8d7-6ba8-4bc7-b567-ada150a14380', 'Paypal', 10, '2019-05-16')";
        query[count++] = "INSERT INTO MOVIE.PAYMENT (ID, ORDERID, \"METHOD\", AMOUNT, \"DATE\") VALUES ('e53f7895-60c5-49a5-bc1a-d59fedb8238e', 'f8967999-7da8-4eae-a0aa-b482f91d2f81', 'Credit Card', 20, '2019-05-14')";
        query[count++] = "INSERT INTO MOVIE.PAYMENT (ID, ORDERID, \"METHOD\", AMOUNT, \"DATE\") VALUES ('9764e9b0-6062-47cb-af6d-b1cda57cd4ed', '856ae243-fbd8-43bb-97c4-0d4b171ec81c', 'Paypal', 20, '2019-05-12')";
        query[count++] = "INSERT INTO MOVIE.PAYMENT (ID, ORDERID, \"METHOD\", AMOUNT, \"DATE\") VALUES ('d80871cd-fd89-48a3-83a6-81f3b0caaa48', 'd6886cff-1453-4ead-89dd-02767b9b3869', 'Credit Card', 20, '2019-05-10')";
        query[count++] = "INSERT INTO MOVIE.PAYMENT (ID, ORDERID, \"METHOD\", AMOUNT, \"DATE\") VALUES ('3aca2eb4-2b92-45e4-9d60-ce8079c6f5d6', 'f1d0be45-4662-4019-bb8e-c5008177f49f', 'Paypal', 30, '2019-05-08')";
        query[count++] = "INSERT INTO MOVIE.PAYMENT (ID, ORDERID, \"METHOD\", AMOUNT, \"DATE\") VALUES ('74358b92-91e4-49a4-a81b-ec6bd92bd015', '7aaa91d0-94ec-40ba-903a-901fe0d09a7a', 'Credit Card', 60, '2019-05-06')";
        query[count++] = "INSERT INTO MOVIE.PAYMENT (ID, ORDERID, \"METHOD\", AMOUNT, \"DATE\") VALUES ('f0898468-d722-419c-91eb-2ae4595b5cef', '4196ab61-7aa7-4593-803d-4c508a4c0f4e', 'Paypal', 10, '2019-05-04')";
        query[count++] = "INSERT INTO MOVIE.PAYMENT (ID, ORDERID, \"METHOD\", AMOUNT, \"DATE\") VALUES ('d341b39c-2584-4e71-a1cc-0faca42ed1af', '6713b9d0-e463-47cc-ad80-a6dab40aba10', 'Credit Card', 10, '2019-05-02')";
        query[count++] = "INSERT INTO MOVIE.PAYMENT (ID, ORDERID, \"METHOD\", AMOUNT, \"DATE\") VALUES ('efc1f7ba-c671-4e08-a788-33762b0c292d', '71e9c1ac-55e4-42c4-9150-e05299ed1a81', 'Paypal', 20, '2019-04-30')";
        query[count++] = "INSERT INTO MOVIE.PAYMENT (ID, ORDERID, \"METHOD\", AMOUNT, \"DATE\") VALUES ('a8147aa2-1c7d-4188-ad1c-111b66a3b842', '8be300f5-4a57-44f2-820b-0707dc687e57', 'Credit Card', 10, '2019-04-28')";
        query[count++] = "INSERT INTO MOVIE.PAYMENT (ID, ORDERID, \"METHOD\", AMOUNT, \"DATE\") VALUES ('0b17127d-ecf9-44a0-b53e-2781e8aea72e', '709a4b2d-b1f7-445f-b1f5-b8bedb9bb27c', 'Paypal', 5, '2019-04-26')";
        query[count++] = "INSERT INTO MOVIE.PAYMENT (ID, ORDERID, \"METHOD\", AMOUNT, \"DATE\") VALUES ('607e1144-4b46-4e08-b024-4fa49deca7fa', 'a3da6993-f220-4181-9ed9-937e7d7bac93', 'Credit Card', 20, '2019-04-24')";
        query[count++] = "INSERT INTO MOVIE.PAYMENT (ID, ORDERID, \"METHOD\", AMOUNT, \"DATE\") VALUES ('2377f82c-8053-4dc7-91ba-9148386a1581', '6664f746-3595-4e0d-9bcc-de30002d4d86', 'Paypal', 45, '2019-04-22')";
        query[count++] = "INSERT INTO MOVIE.PAYMENT (ID, ORDERID, \"METHOD\", AMOUNT, \"DATE\") VALUES ('c9261876-9337-4c25-899b-fc60366d4e43', 'af3ac359-6812-460e-836d-e0106eb69e58', 'Credit Card', 20, '2019-04-20')";
        query[count++] = "INSERT INTO MOVIE.PAYMENT (ID, ORDERID, \"METHOD\", AMOUNT, \"DATE\") VALUES ('5d540a02-ed61-4620-ac07-51a47507b42f', '2247a364-6284-4dde-8eb5-af4cbe1178ec', 'Paypal', 20, '2019-04-18')";
        query[count++] = "INSERT INTO MOVIE.PAYMENT (ID, ORDERID, \"METHOD\", AMOUNT, \"DATE\") VALUES ('8b550950-600d-42e7-a70d-835e82c29985', '40f2e514-519a-43b8-a4ff-c4242ed73447', 'Credit Card', 10, '2019-04-16')";
        query[count++] = "INSERT INTO MOVIE.PAYMENT (ID, ORDERID, \"METHOD\", AMOUNT, \"DATE\") VALUES ('7943f996-fa7c-42e0-8689-206faca3f1a9', '8fd0dc77-3b34-4568-8390-10d290644bae', 'Paypal', 10, '2019-04-14')";
        query[count++] = "INSERT INTO MOVIE.PAYMENT (ID, ORDERID, \"METHOD\", AMOUNT, \"DATE\") VALUES ('7c8026e4-b04d-48b7-ac05-9a5a81f27a41', '9c6a6561-0eb0-499a-a3de-0b954fc8def8', 'Credit Card', 20, '2019-04-12')";
        query[count++] = "INSERT INTO MOVIE.PAYMENT (ID, ORDERID, \"METHOD\", AMOUNT, \"DATE\") VALUES ('f36f4484-fa89-44f1-8c30-ce2ade17bd86', '6e96b071-d0e3-4c73-a8ee-11801bf7e4c1', 'Paypal', 20, '2019-04-10')";
        query[count++] = "INSERT INTO MOVIE.PAYMENT (ID, ORDERID, \"METHOD\", AMOUNT, \"DATE\") VALUES ('660bb809-48af-462c-bdd5-2da153a7f70b', '027c7768-fd83-4172-9337-e04f9da348c9', 'Credit Card', 30, '2019-04-08')";
        query[count++] = "INSERT INTO MOVIE.PAYMENT (ID, ORDERID, \"METHOD\", AMOUNT, \"DATE\") VALUES ('ebeccc2f-a3f4-48c7-a76e-47cafd980ce4', '5af234c8-eddb-4c83-a2bf-f3d582fd07d5', 'Paypal', 40, '2019-04-06')";
        query[count++] = "INSERT INTO MOVIE.PAYMENT (ID, ORDERID, \"METHOD\", AMOUNT, \"DATE\") VALUES ('cf90068b-4c70-4cdd-a166-f6153ff315d9', 'fdf15854-2da1-41d4-af4d-cac3505da12e', 'Credit Card', 10, '2019-04-04')";
        query[count++] = "INSERT INTO MOVIE.PAYMENT (ID, ORDERID, \"METHOD\", AMOUNT, \"DATE\") VALUES ('e428c80b-a7bc-4557-9154-ad178d1e39b9', '58a183fa-dd49-4fd2-a62e-a60f3005f70f', 'Paypal', 10, '2019-04-02')";
        query[count++] = "INSERT INTO MOVIE.PAYMENT (ID, ORDERID, \"METHOD\", AMOUNT, \"DATE\") VALUES ('a22b9530-0125-4130-aaa0-1a03b771d709', 'a0714b32-0ade-4d43-aac1-30a56c740e1f', 'Credit Card', 10, '2019-03-31')";
        query[count++] = "INSERT INTO MOVIE.PAYMENT (ID, ORDERID, \"METHOD\", AMOUNT, \"DATE\") VALUES ('25f335cc-6b7f-4248-bc06-42d1fe2b6d8a', '8b6ec638-1615-40f3-be5f-5e6cc9f4d3f1', 'Paypal', 10, '2019-03-29')";
        query[count++] = "INSERT INTO MOVIE.PAYMENT (ID, ORDERID, \"METHOD\", AMOUNT, \"DATE\") VALUES ('7682f18b-c8eb-405f-8f38-b812d26656f5', '225109d6-2d61-4647-9d77-c120b6565853', 'Credit Card', 5, '2019-03-27')";

        // SHIPMENT DATA
        query[count++] = "INSERT INTO MOVIE.SHIPMENT (ID, ORDERID, \"METHOD\", \"DATE\") VALUES ('a2c25e49-0ce2-497d-b43f-dbb5697d58d7', '37134d3d-a6da-4f98-88c7-845113e58ab3', 'DELIVERY', '2019-05-24')";
        query[count++] = "INSERT INTO MOVIE.SHIPMENT (ID, ORDERID, \"METHOD\", \"DATE\") VALUES ('245a8441-aa76-4879-898b-a350c3a2046f', 'bca4ce4e-d009-4438-b3dc-ef5317996dbb', 'PICKUP', '2019-05-22')";
        query[count++] = "INSERT INTO MOVIE.SHIPMENT (ID, ORDERID, \"METHOD\", \"DATE\") VALUES ('44cd6f05-c6b8-4cf8-989c-6f37cef67d50', 'a8ce6b93-6aa0-4e18-9ee4-4b46fc5da48a', 'DELIVERY', '2019-05-20')";
        query[count++] = "INSERT INTO MOVIE.SHIPMENT (ID, ORDERID, \"METHOD\", \"DATE\") VALUES ('9a0881eb-655c-4834-b18b-553552b6d331', '84c6d321-ffec-415b-8ccc-3db24033177e', 'PICKUP', '2019-05-18')";
        query[count++] = "INSERT INTO MOVIE.SHIPMENT (ID, ORDERID, \"METHOD\", \"DATE\") VALUES ('37c8689b-4a92-4e92-8c80-27a12867fcce', 'b442f8d7-6ba8-4bc7-b567-ada150a14380', 'DELIVERY', '2019-05-16')";
        query[count++] = "INSERT INTO MOVIE.SHIPMENT (ID, ORDERID, \"METHOD\", \"DATE\") VALUES ('ef3394eb-2a7c-4d3a-a956-a7305a769804', 'f8967999-7da8-4eae-a0aa-b482f91d2f81', 'PICKUP', '2019-05-14')";
        query[count++] = "INSERT INTO MOVIE.SHIPMENT (ID, ORDERID, \"METHOD\", \"DATE\") VALUES ('fbcba2c0-e335-48aa-8082-b59059c74947', '856ae243-fbd8-43bb-97c4-0d4b171ec81c', 'DELIVERY', '2019-05-12')";
        query[count++] = "INSERT INTO MOVIE.SHIPMENT (ID, ORDERID, \"METHOD\", \"DATE\") VALUES ('af70c9d6-0185-479c-a81a-fcf99e8c6cc0', 'd6886cff-1453-4ead-89dd-02767b9b3869', 'PICKUP', '2019-05-10')";
        query[count++] = "INSERT INTO MOVIE.SHIPMENT (ID, ORDERID, \"METHOD\", \"DATE\") VALUES ('e1a1bcc4-e5e0-48f0-bbf4-929959406e3d', 'f1d0be45-4662-4019-bb8e-c5008177f49f', 'DELIVERY', '2019-05-08')";
        query[count++] = "INSERT INTO MOVIE.SHIPMENT (ID, ORDERID, \"METHOD\", \"DATE\") VALUES ('ed04442b-5d48-4df9-a91a-669b3545b205', '7aaa91d0-94ec-40ba-903a-901fe0d09a7a', 'PICKUP', '2019-05-06')";
        query[count++] = "INSERT INTO MOVIE.SHIPMENT (ID, ORDERID, \"METHOD\", \"DATE\") VALUES ('101dc0fd-dbb7-436f-a949-8778a68918cb', '4196ab61-7aa7-4593-803d-4c508a4c0f4e', 'DELIVERY', '2019-05-04')";
        query[count++] = "INSERT INTO MOVIE.SHIPMENT (ID, ORDERID, \"METHOD\", \"DATE\") VALUES ('86e4a623-9c13-42f8-9759-44af6cb93e3d', '6713b9d0-e463-47cc-ad80-a6dab40aba10', 'PICKUP', '2019-05-02')";
        query[count++] = "INSERT INTO MOVIE.SHIPMENT (ID, ORDERID, \"METHOD\", \"DATE\") VALUES ('ccef1ab7-ba8e-4b3d-9361-e72ae011e529', '71e9c1ac-55e4-42c4-9150-e05299ed1a81', 'DELIVERY', '2019-04-30')";
        query[count++] = "INSERT INTO MOVIE.SHIPMENT (ID, ORDERID, \"METHOD\", \"DATE\") VALUES ('88259bf4-8a71-4d63-bc28-d6d2165abe71', '8be300f5-4a57-44f2-820b-0707dc687e57', 'PICKUP', '2019-04-28')";
        query[count++] = "INSERT INTO MOVIE.SHIPMENT (ID, ORDERID, \"METHOD\", \"DATE\") VALUES ('d413e401-bd50-4d4f-8fe3-5e2e0323edfc', '709a4b2d-b1f7-445f-b1f5-b8bedb9bb27c', 'DELIVERY', '2019-04-26')";
        query[count++] = "INSERT INTO MOVIE.SHIPMENT (ID, ORDERID, \"METHOD\", \"DATE\") VALUES ('7340a4c6-6c15-4c0d-b091-55ed2b7f3c04', 'a3da6993-f220-4181-9ed9-937e7d7bac93', 'PICKUP', '2019-04-24')";
        query[count++] = "INSERT INTO MOVIE.SHIPMENT (ID, ORDERID, \"METHOD\", \"DATE\") VALUES ('3d8b754b-0db6-45aa-8820-bcd3101da1a5', '6664f746-3595-4e0d-9bcc-de30002d4d86', 'DELIVERY', '2019-04-22')";
        query[count++] = "INSERT INTO MOVIE.SHIPMENT (ID, ORDERID, \"METHOD\", \"DATE\") VALUES ('e5704246-b8e3-45fe-b85b-468edcc3adc3', 'af3ac359-6812-460e-836d-e0106eb69e58', 'PICKUP', '2019-04-20')";
        query[count++] = "INSERT INTO MOVIE.SHIPMENT (ID, ORDERID, \"METHOD\", \"DATE\") VALUES ('7e922972-79bd-4f26-9469-aa4d2ee19301', '2247a364-6284-4dde-8eb5-af4cbe1178ec', 'DELIVERY', '2019-04-18')";
        query[count++] = "INSERT INTO MOVIE.SHIPMENT (ID, ORDERID, \"METHOD\", \"DATE\") VALUES ('ebea4048-c3d4-4128-8ebb-443f33fd4afd', '40f2e514-519a-43b8-a4ff-c4242ed73447', 'PICKUP', '2019-04-16')";
        query[count++] = "INSERT INTO MOVIE.SHIPMENT (ID, ORDERID, \"METHOD\", \"DATE\") VALUES ('481da59f-df67-4064-98d5-df1737d8fc8d', '8fd0dc77-3b34-4568-8390-10d290644bae', 'DELIVERY', '2019-04-14')";
        query[count++] = "INSERT INTO MOVIE.SHIPMENT (ID, ORDERID, \"METHOD\", \"DATE\") VALUES ('55631b6b-04b1-4ed0-9a73-9d0a974420a6', '9c6a6561-0eb0-499a-a3de-0b954fc8def8', 'PICKUP', '2019-04-12')";
        query[count++] = "INSERT INTO MOVIE.SHIPMENT (ID, ORDERID, \"METHOD\", \"DATE\") VALUES ('5cc8f844-e5f0-4b15-b4d4-96afef7e3ec7', '6e96b071-d0e3-4c73-a8ee-11801bf7e4c1', 'DELIVERY', '2019-04-10')";
        query[count++] = "INSERT INTO MOVIE.SHIPMENT (ID, ORDERID, \"METHOD\", \"DATE\") VALUES ('867e4601-9bee-40ca-ab35-88d32a363493', '027c7768-fd83-4172-9337-e04f9da348c9', 'PICKUP', '2019-04-08')";
        query[count++] = "INSERT INTO MOVIE.SHIPMENT (ID, ORDERID, \"METHOD\", \"DATE\") VALUES ('0fb3c7f4-12a7-40a3-8415-2d1eb428a3f6', '5af234c8-eddb-4c83-a2bf-f3d582fd07d5', 'DELIVERY', '2019-04-06')";
        query[count++] = "INSERT INTO MOVIE.SHIPMENT (ID, ORDERID, \"METHOD\", \"DATE\") VALUES ('80373dc1-a062-4580-9939-777c6ce0ed87', 'fdf15854-2da1-41d4-af4d-cac3505da12e', 'PICKUP', '2019-04-04')";
        query[count++] = "INSERT INTO MOVIE.SHIPMENT (ID, ORDERID, \"METHOD\", \"DATE\") VALUES ('24c6d390-d760-49cd-bfb4-91b2788fa078', '58a183fa-dd49-4fd2-a62e-a60f3005f70f', 'DELIVERY', '2019-04-02')";
        query[count++] = "INSERT INTO MOVIE.SHIPMENT (ID, ORDERID, \"METHOD\", \"DATE\") VALUES ('23f8ec80-36bc-4c40-9001-21781720acf6', 'a0714b32-0ade-4d43-aac1-30a56c740e1f', 'PICKUP', '2019-03-31')";
        query[count++] = "INSERT INTO MOVIE.SHIPMENT (ID, ORDERID, \"METHOD\", \"DATE\") VALUES ('ef96305d-6c97-4a8c-b1c9-2c175ecb7b79', '8b6ec638-1615-40f3-be5f-5e6cc9f4d3f1', 'DELIVERY', '2019-03-29')";
        query[count++] = "INSERT INTO MOVIE.SHIPMENT (ID, ORDERID, \"METHOD\", \"DATE\") VALUES ('18770e6b-ab75-41c1-a1bc-8a78e5289976', '225109d6-2d61-4647-9d77-c120b6565853', 'PICKUP', '2019-03-27')";
        
        for (int i = 0; i < count; i++) {
            try {
                st.execute(query[i]);    
            } catch (SQLException e){}
        }
    }
}