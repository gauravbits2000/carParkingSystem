DELETE FROM CAR_PARKING_DETAILS;
MERGE INTO CAR_PARKING_DETAILS (CAR_PARKING_ID, PARKING_FLOOR, PARKING_TYPE) VALUES ('101', 'Lower Basement', 'pool_parking');
MERGE INTO CAR_PARKING_DETAILS (CAR_PARKING_ID, PARKING_FLOOR, PARKING_TYPE) VALUES ('102', 'Lower Basement', 'pool_parking');
MERGE INTO CAR_PARKING_DETAILS (CAR_PARKING_ID, PARKING_FLOOR, PARKING_TYPE) VALUES ('103', 'Lower Basement', 'pool_parking');
MERGE INTO CAR_PARKING_DETAILS (CAR_PARKING_ID, PARKING_FLOOR, PARKING_TYPE) VALUES ('104', 'Lower Basement', 'pool_parking');
MERGE INTO CAR_PARKING_DETAILS (CAR_PARKING_ID, PARKING_FLOOR, PARKING_TYPE) VALUES ('105', 'Lower Basement', 'pool_parking');
MERGE INTO CAR_PARKING_DETAILS (CAR_PARKING_ID, PARKING_FLOOR, PARKING_TYPE) VALUES ('106', 'Lower Basement', 'medical_emergency');
MERGE INTO CAR_PARKING_DETAILS (CAR_PARKING_ID, PARKING_FLOOR, PARKING_TYPE) VALUES ('107', 'Lower Basement', 'medical_emergency');
MERGE INTO CAR_PARKING_DETAILS (CAR_PARKING_ID, PARKING_FLOOR, PARKING_TYPE) VALUES ('108', 'Lower Basement', 'medical_emergency');
MERGE INTO CAR_PARKING_DETAILS (CAR_PARKING_ID, PARKING_FLOOR, PARKING_TYPE) VALUES ('109', 'Lower Basement', 'medical_emergency');
MERGE INTO CAR_PARKING_DETAILS (CAR_PARKING_ID, PARKING_FLOOR, PARKING_TYPE) VALUES ('110', 'Lower Basement', 'medical_emergency');
MERGE INTO CAR_PARKING_DETAILS (CAR_PARKING_ID, PARKING_FLOOR, PARKING_TYPE) VALUES ('111', 'Lower Basement', 'female_night_shift');
MERGE INTO CAR_PARKING_DETAILS (CAR_PARKING_ID,  PARKING_FLOOR, PARKING_TYPE) VALUES ('112', 'Lower Basement', 'female_night_shift');
MERGE INTO CAR_PARKING_DETAILS (CAR_PARKING_ID,  PARKING_FLOOR, PARKING_TYPE) VALUES ('113', 'Lower Basement', 'female_night_shift');
MERGE INTO CAR_PARKING_DETAILS (CAR_PARKING_ID,  PARKING_FLOOR, PARKING_TYPE) VALUES ('114', 'Lower Basement', 'female_night_shift');
MERGE INTO CAR_PARKING_DETAILS (CAR_PARKING_ID,  PARKING_FLOOR, PARKING_TYPE) VALUES ('115', 'Lower Basement', 'female_night_shift');
MERGE INTO CAR_PARKING_DETAILS (CAR_PARKING_ID,  PARKING_FLOOR, PARKING_TYPE) VALUES ('116', 'Lower Basement', 'Open');
MERGE INTO CAR_PARKING_DETAILS (CAR_PARKING_ID,  PARKING_FLOOR, PARKING_TYPE) VALUES ('117', 'Lower Basement', 'Open');
MERGE INTO CAR_PARKING_DETAILS (CAR_PARKING_ID,  PARKING_FLOOR, PARKING_TYPE) VALUES ('118', 'Lower Basement', 'Open');
MERGE INTO CAR_PARKING_DETAILS (CAR_PARKING_ID,  PARKING_FLOOR, PARKING_TYPE) VALUES ('119', 'Lower Basement', 'Open');
MERGE INTO CAR_PARKING_DETAILS (CAR_PARKING_ID,  PARKING_FLOOR, PARKING_TYPE) VALUES ('120', 'Lower Basement', 'Open');
MERGE INTO CAR_PARKING_DETAILS (CAR_PARKING_ID,  PARKING_FLOOR, PARKING_TYPE) VALUES ('121', 'Lower Basement', 'Open');
MERGE INTO CAR_PARKING_DETAILS (CAR_PARKING_ID,  PARKING_FLOOR, PARKING_TYPE) VALUES ('122', 'Lower Basement', 'Open');
MERGE INTO CAR_PARKING_DETAILS (CAR_PARKING_ID,  PARKING_FLOOR, PARKING_TYPE) VALUES ('123', 'Lower Basement', 'Open');
MERGE INTO CAR_PARKING_DETAILS (CAR_PARKING_ID,  PARKING_FLOOR, PARKING_TYPE) VALUES ('124', 'Lower Basement', 'Open');
MERGE INTO CAR_PARKING_DETAILS (CAR_PARKING_ID,  PARKING_FLOOR, PARKING_TYPE) VALUES ('125', 'Lower Basement', 'Open');

-- Reserved Parking
MERGE INTO CAR_PARKING_DETAILS (CAR_PARKING_ID,  PARKING_FLOOR, PARKING_TYPE) VALUES ('131', 'Lower Basement', 'Reserved');
MERGE INTO CAR_PARKING_DETAILS (CAR_PARKING_ID,  PARKING_FLOOR, PARKING_TYPE) VALUES ('132', 'Lower Basement', 'Reserved');
MERGE INTO CAR_PARKING_DETAILS (CAR_PARKING_ID,  PARKING_FLOOR, PARKING_TYPE) VALUES ('133', 'Lower Basement', 'Reserved');
MERGE INTO CAR_PARKING_DETAILS (CAR_PARKING_ID,  PARKING_FLOOR, PARKING_TYPE) VALUES ('134', 'Lower Basement', 'Reserved');
MERGE INTO CAR_PARKING_DETAILS (CAR_PARKING_ID,  PARKING_FLOOR, PARKING_TYPE) VALUES ('135', 'Lower Basement', 'Reserved');


DELETE FROM QUARTER_PARKING_RESULT ;
INSERT INTO QUARTER_PARKING_RESULT (EMPLOYEE_ID, QUARTER, EMPLOYEE_NAME, VEHICLE_REGISTRATION_NUMBER, CAR_PARKING_ID, Request_Category) VALUES(70012866,'Q3','Ramita Kaur','DL 2 CAL 2169 / 4779',101,'general_parking');
INSERT INTO QUARTER_PARKING_RESULT (EMPLOYEE_ID, QUARTER, EMPLOYEE_NAME, VEHICLE_REGISTRATION_NUMBER, CAR_PARKING_ID, Request_Category) VALUES(70019593,'Q3','Jayant Goel','UP16BX 6644 / UP16V 7152',102,'general_parking');
INSERT INTO QUARTER_PARKING_RESULT (EMPLOYEE_ID, QUARTER, EMPLOYEE_NAME, VEHICLE_REGISTRATION_NUMBER, CAR_PARKING_ID, Request_Category) VALUES(216477,'Q3','Mohammed Danish','HR26 BE 2164/UP AW 2052',103,'general_parking');
INSERT INTO QUARTER_PARKING_RESULT (EMPLOYEE_ID, QUARTER, EMPLOYEE_NAME, VEHICLE_REGISTRATION_NUMBER, CAR_PARKING_ID, Request_Category) VALUES(204340,'Q3','Saurabh Walia','PB11AR - 7917, PB11AV - 2501',104,'general_parking');
INSERT INTO QUARTER_PARKING_RESULT (EMPLOYEE_ID, QUARTER, EMPLOYEE_NAME, VEHICLE_REGISTRATION_NUMBER, CAR_PARKING_ID, Request_Category) VALUES(211001,'Q3','GAGAN DAWAR','UP14CD0888',105,'general_parking');
INSERT INTO QUARTER_PARKING_RESULT (EMPLOYEE_ID, QUARTER, EMPLOYEE_NAME, VEHICLE_REGISTRATION_NUMBER, CAR_PARKING_ID, Request_Category) VALUES(70012808,'Q3','Abhishek Sharma','UP15 BW 9193',106,'general_parking');
INSERT INTO QUARTER_PARKING_RESULT (EMPLOYEE_ID, QUARTER, EMPLOYEE_NAME, VEHICLE_REGISTRATION_NUMBER, CAR_PARKING_ID, Request_Category) VALUES(207823,'Q3','Gourav nahata','HR 16 N 9648',107,'general_parking');
INSERT INTO QUARTER_PARKING_RESULT (EMPLOYEE_ID, QUARTER, EMPLOYEE_NAME, VEHICLE_REGISTRATION_NUMBER, CAR_PARKING_ID, Request_Category) VALUES(213343,'Q3','Atul Kumar Mittal','DL2CAE9647',108,'general_parking');
INSERT INTO QUARTER_PARKING_RESULT (EMPLOYEE_ID, QUARTER, EMPLOYEE_NAME, VEHICLE_REGISTRATION_NUMBER, CAR_PARKING_ID, Request_Category) VALUES(251446,'Q3','Gagan deep','UP 15 BQ 9517',109,'general_parking');
INSERT INTO QUARTER_PARKING_RESULT (EMPLOYEE_ID, QUARTER, EMPLOYEE_NAME, VEHICLE_REGISTRATION_NUMBER, CAR_PARKING_ID, Request_Category) VALUES(70012828,'Q3','Shubhra Srivastava','UP14DH8689',110,'general_parking');
INSERT INTO QUARTER_PARKING_RESULT (EMPLOYEE_ID, QUARTER, EMPLOYEE_NAME, VEHICLE_REGISTRATION_NUMBER, CAR_PARKING_ID, Request_Category) VALUES(250556,'Q3','Ishita Sharma','DL2CAN0763',111,'general_parking');
INSERT INTO QUARTER_PARKING_RESULT (EMPLOYEE_ID, QUARTER, EMPLOYEE_NAME, VEHICLE_REGISTRATION_NUMBER, CAR_PARKING_ID, Request_Category) VALUES(207447,'Q3','Nikhil Verma','UP14CR9408',112,'general_parking');
INSERT INTO QUARTER_PARKING_RESULT (EMPLOYEE_ID, QUARTER, EMPLOYEE_NAME, VEHICLE_REGISTRATION_NUMBER, CAR_PARKING_ID, Request_Category) VALUES(208020,'Q3','Tarun Kumar','DL9CAE-0959',113,'general_parking');
INSERT INTO QUARTER_PARKING_RESULT (EMPLOYEE_ID, QUARTER, EMPLOYEE_NAME, VEHICLE_REGISTRATION_NUMBER, CAR_PARKING_ID, Request_Category) VALUES(70016881,'Q3','Vinita Singh','UP 16 AS 6797',114,'general_parking');
INSERT INTO QUARTER_PARKING_RESULT (EMPLOYEE_ID, QUARTER, EMPLOYEE_NAME, VEHICLE_REGISTRATION_NUMBER, CAR_PARKING_ID, Request_Category) VALUES(213625,'Q3','Paras Dhingra','DL 8C AP 1893',115,'general_parking');
INSERT INTO QUARTER_PARKING_RESULT (EMPLOYEE_ID, QUARTER, EMPLOYEE_NAME, VEHICLE_REGISTRATION_NUMBER, CAR_PARKING_ID, Request_Category) VALUES(250097,'Q3','ANKIT GUPTA','UP15-BU-1530',116,'general_parking');
INSERT INTO QUARTER_PARKING_RESULT (EMPLOYEE_ID, QUARTER, EMPLOYEE_NAME, VEHICLE_REGISTRATION_NUMBER, CAR_PARKING_ID, Request_Category) VALUES(70013730,'Q3','Manish Choudhary','DL 10CT 1040',117,'general_parking');
INSERT INTO QUARTER_PARKING_RESULT (EMPLOYEE_ID, QUARTER, EMPLOYEE_NAME, VEHICLE_REGISTRATION_NUMBER, CAR_PARKING_ID, Request_Category) VALUES(251662,'Q3','Ashish Goel','UP37J0384',118,'general_parking');
INSERT INTO QUARTER_PARKING_RESULT (EMPLOYEE_ID, QUARTER, EMPLOYEE_NAME, VEHICLE_REGISTRATION_NUMBER, CAR_PARKING_ID, Request_Category) VALUES(250395,'Q3','Akshay Gupta','UP 14DF 3244',119,'general_parking');
INSERT INTO QUARTER_PARKING_RESULT (EMPLOYEE_ID, QUARTER, EMPLOYEE_NAME, VEHICLE_REGISTRATION_NUMBER, CAR_PARKING_ID, Request_Category) VALUES(205191,'Q3','Snehil Srivastava','UP16 BR 3928',120,'general_parking');
INSERT INTO QUARTER_PARKING_RESULT (EMPLOYEE_ID, QUARTER, EMPLOYEE_NAME, VEHICLE_REGISTRATION_NUMBER, CAR_PARKING_ID, Request_Category) VALUES(207218,'Q3','Siraj Anwer Qureshi','DL2CAM3331',121,'general_parking');
INSERT INTO QUARTER_PARKING_RESULT (EMPLOYEE_ID, QUARTER, EMPLOYEE_NAME, VEHICLE_REGISTRATION_NUMBER, CAR_PARKING_ID, Request_Category) VALUES(70014971,'Q3','Rahul Nagpal','DL 5C L 0706',122,'general_parking');
INSERT INTO QUARTER_PARKING_RESULT (EMPLOYEE_ID, QUARTER, EMPLOYEE_NAME, VEHICLE_REGISTRATION_NUMBER, CAR_PARKING_ID, Request_Category) VALUES(70021267,'Q3','Neeshu Gupta','UP14DQ4822',123,'general_parking');
INSERT INTO QUARTER_PARKING_RESULT (EMPLOYEE_ID, QUARTER, EMPLOYEE_NAME, VEHICLE_REGISTRATION_NUMBER, CAR_PARKING_ID, Request_Category) VALUES(251817,'Q3','Akash Malik','HR10Z2994',124,'general_parking');
INSERT INTO QUARTER_PARKING_RESULT (EMPLOYEE_ID, QUARTER, EMPLOYEE_NAME, VEHICLE_REGISTRATION_NUMBER, CAR_PARKING_ID, Request_Category) VALUES(250497,'Q3','Meenakshi Nautiyal','UP16BB7175',125,'general_parking');
INSERT INTO QUARTER_PARKING_RESULT (EMPLOYEE_ID, QUARTER, EMPLOYEE_NAME, VEHICLE_REGISTRATION_NUMBER, CAR_PARKING_ID, Request_Category) VALUES(70015218,'Q3','Vibhor Agarwal','DL 7CT 7983',126,'general_parking');
INSERT INTO QUARTER_PARKING_RESULT (EMPLOYEE_ID, QUARTER, EMPLOYEE_NAME, VEHICLE_REGISTRATION_NUMBER, CAR_PARKING_ID, Request_Category) VALUES(205737,'Q3','Rohit Saxena','DL3CBC4184',127,'general_parking');
INSERT INTO QUARTER_PARKING_RESULT (EMPLOYEE_ID, QUARTER, EMPLOYEE_NAME, VEHICLE_REGISTRATION_NUMBER, CAR_PARKING_ID, Request_Category) VALUES(215133,'Q3','Ashish pal','UP16BR2650',128,'general_parking');
INSERT INTO QUARTER_PARKING_RESULT (EMPLOYEE_ID, QUARTER, EMPLOYEE_NAME, VEHICLE_REGISTRATION_NUMBER, CAR_PARKING_ID, Request_Category) VALUES(70020956,'Q3','Parv Johari','UP16BB1802',129,'general_parking');
INSERT INTO QUARTER_PARKING_RESULT (EMPLOYEE_ID, QUARTER, EMPLOYEE_NAME, VEHICLE_REGISTRATION_NUMBER, CAR_PARKING_ID, Request_Category) VALUES(208832,'Q3','Brijesh gupta','UP14DH5665',130,'general_parking');


UPDATE QUARTER_PARKING_RESULT Q SET Q.EMPLOYEE_EMAIL= ( SELECT E.EMPLOYEE_EMAIL FROM EMPLOYEE_DETAILS E WHERE Q.EMPLOYEE_ID = E.EMPLOYEE_ID );

DELETE FROM EMPLOYEE_REGISTRATION_DETAILS ;
INSERT INTO EMPLOYEE_REGISTRATION_DETAILS (EMPLOYEE_ID,EMPLOYEE_NAME,EMPLOYEE_EMAIL,VEHICLE_REGISTRATION_NUMBER,location ,REQUEST_CATEGORY) 
VALUES('250395','Akshay Gupta','Akshay.Gupta@ihsmarkit.com','DL 8C AP 1893','Noida','general_parking');
INSERT INTO EMPLOYEE_REGISTRATION_DETAILS (EMPLOYEE_ID,EMPLOYEE_NAME,EMPLOYEE_EMAIL,VEHICLE_REGISTRATION_NUMBER,location ,REQUEST_CATEGORY) 
VALUES('250448','Prince Jain','Prince.Jain@ihsmarkit.com','UP15-BU-1530','Noida','general_parking');
INSERT INTO EMPLOYEE_REGISTRATION_DETAILS (EMPLOYEE_ID,EMPLOYEE_NAME,EMPLOYEE_EMAIL,VEHICLE_REGISTRATION_NUMBER,location ,REQUEST_CATEGORY) 
VALUES('251662','Ashish Goel','Ashish.Goel@ihsmarkit.com','DL 10CT 1040','Noida','medical_emergency');
INSERT INTO EMPLOYEE_REGISTRATION_DETAILS (EMPLOYEE_ID,EMPLOYEE_NAME,EMPLOYEE_EMAIL,VEHICLE_REGISTRATION_NUMBER,location ,REQUEST_CATEGORY) 
VALUES('251817','Akash Malik','Akash.Malik@ihsmarkit.com','UP37J0384','Noida','medical_emergency');
INSERT INTO EMPLOYEE_REGISTRATION_DETAILS (EMPLOYEE_ID,EMPLOYEE_NAME,EMPLOYEE_EMAIL,VEHICLE_REGISTRATION_NUMBER,location ,REQUEST_CATEGORY) 
VALUES('70008673','Saurabh Sharma','Saurabh.Sharma@ihsmarkit.com','UP 14DF 3244','Noida','medical_emergency');
INSERT INTO EMPLOYEE_REGISTRATION_DETAILS (EMPLOYEE_ID,EMPLOYEE_NAME,EMPLOYEE_EMAIL,VEHICLE_REGISTRATION_NUMBER,location ,REQUEST_CATEGORY) 
VALUES('70008915','Neha Goyal','Neha.Goyal@ihsmarkit.com','UP16 BR 3928','Noida','general_parking');
INSERT INTO EMPLOYEE_REGISTRATION_DETAILS (EMPLOYEE_ID,EMPLOYEE_NAME,EMPLOYEE_EMAIL,VEHICLE_REGISTRATION_NUMBER,location ,REQUEST_CATEGORY) 
VALUES('70009165','Vikas JainM','Vikas.Jain2@ihsmarkit.com','DL2CAM3331','Noida','medical_emergency');
INSERT INTO EMPLOYEE_REGISTRATION_DETAILS (EMPLOYEE_ID,EMPLOYEE_NAME,EMPLOYEE_EMAIL,VEHICLE_REGISTRATION_NUMBER,location ,REQUEST_CATEGORY) 
VALUES('70011683','Sharad Srivastava','Sharad.Srivastava@ihsmarkit.com','DL 5C L 0706','Noida','medical_emergency');
INSERT INTO EMPLOYEE_REGISTRATION_DETAILS (EMPLOYEE_ID,EMPLOYEE_NAME,EMPLOYEE_EMAIL,VEHICLE_REGISTRATION_NUMBER,location ,REQUEST_CATEGORY) 
VALUES('70011922','Amit Gupta','Amit.Gupta@ihsmarkit.com','UP14DQ4822','Noida','medical_emergency');
INSERT INTO EMPLOYEE_REGISTRATION_DETAILS (EMPLOYEE_ID,EMPLOYEE_NAME,EMPLOYEE_EMAIL,VEHICLE_REGISTRATION_NUMBER,location ,REQUEST_CATEGORY) 
VALUES('70011949','Ambarish Kumar Gupta','AmbarishKumar.Gupta@ihsmarkit.com','HR10Z2994','Noida','general_parking');
INSERT INTO EMPLOYEE_REGISTRATION_DETAILS (EMPLOYEE_ID,EMPLOYEE_NAME,EMPLOYEE_EMAIL,VEHICLE_REGISTRATION_NUMBER,location ,REQUEST_CATEGORY) 
VALUES('70012808','Abhishek SharmaA','Abhishek.SharmaA@ihsmarkit.com','UP16BB7175','Noida','general_parking');
INSERT INTO EMPLOYEE_REGISTRATION_DETAILS (EMPLOYEE_ID,EMPLOYEE_NAME,EMPLOYEE_EMAIL,VEHICLE_REGISTRATION_NUMBER,location ,REQUEST_CATEGORY) 
VALUES('70012828','Shubhra Srivastava','Shubhra.Srivastava@ihsmarkit.com','DL 7CT 7983','Noida','female_night_shift');
INSERT INTO EMPLOYEE_REGISTRATION_DETAILS (EMPLOYEE_ID,EMPLOYEE_NAME,EMPLOYEE_EMAIL,VEHICLE_REGISTRATION_NUMBER,location ,REQUEST_CATEGORY) 
VALUES('70012866','Ramita Kaur','Ramita.Kaur@ihsmarkit.com','DL3CBC4184','Noida','general_parking');
INSERT INTO EMPLOYEE_REGISTRATION_DETAILS (EMPLOYEE_ID,EMPLOYEE_NAME,EMPLOYEE_EMAIL,VEHICLE_REGISTRATION_NUMBER,location ,REQUEST_CATEGORY) 
VALUES('70013730','Manish Choudhary','Manish.Choudhary@ihsmarkit.com','UP16BR2650','Noida','general_parking');
INSERT INTO EMPLOYEE_REGISTRATION_DETAILS (EMPLOYEE_ID,EMPLOYEE_NAME,EMPLOYEE_EMAIL,VEHICLE_REGISTRATION_NUMBER,location ,REQUEST_CATEGORY) 
VALUES('70014971','Rahul Nagpal','Rahul.Nagpal@ihsmarkit.com','UP16BB1802','Noida','general_parking');
INSERT INTO EMPLOYEE_REGISTRATION_DETAILS (EMPLOYEE_ID,EMPLOYEE_NAME,EMPLOYEE_EMAIL,VEHICLE_REGISTRATION_NUMBER,location ,REQUEST_CATEGORY) 
VALUES('70015218','Vibhor Agarwal','Vibhor.Agarwal@ihsmarkit.com','up14dh5665','Noida','general_parking');
INSERT INTO EMPLOYEE_REGISTRATION_DETAILS (EMPLOYEE_ID,EMPLOYEE_NAME,EMPLOYEE_EMAIL,VEHICLE_REGISTRATION_NUMBER,location ,REQUEST_CATEGORY) 
VALUES('70015610','Parag Garg','Parag.Garg@ihsmarkit.com','HR26DF3198','Noida','general_parking');
INSERT INTO EMPLOYEE_REGISTRATION_DETAILS (EMPLOYEE_ID,EMPLOYEE_NAME,EMPLOYEE_EMAIL,VEHICLE_REGISTRATION_NUMBER,location ,REQUEST_CATEGORY) 
VALUES('70016438','Mukul Singhal','Mukul.Singhal@ihsmarkit.com','HR26BD6514','Noida','general_parking');
INSERT INTO EMPLOYEE_REGISTRATION_DETAILS (EMPLOYEE_ID,EMPLOYEE_NAME,EMPLOYEE_EMAIL,VEHICLE_REGISTRATION_NUMBER,location ,REQUEST_CATEGORY) 
VALUES('70016508','Sumit Jaglan','Sumit.Jaglan@ihsmarkit.com','UP16 AV 2927','Noida','general_parking');
INSERT INTO EMPLOYEE_REGISTRATION_DETAILS (EMPLOYEE_ID,EMPLOYEE_NAME,EMPLOYEE_EMAIL,VEHICLE_REGISTRATION_NUMBER,location ,REQUEST_CATEGORY) 
VALUES('70016566','Utkarsh Singh Bisht','Utkarsh.Bisht@ihsmarkit.com','HR 27 G 7864','Noida','general_parking');
INSERT INTO EMPLOYEE_REGISTRATION_DETAILS (EMPLOYEE_ID,EMPLOYEE_NAME,EMPLOYEE_EMAIL,VEHICLE_REGISTRATION_NUMBER,location ,REQUEST_CATEGORY) 
VALUES('70016881','Vinita Singh','Vinita.Singh@ihsmarkit.com','UP16AX9433','Noida','female_night_shift');
INSERT INTO EMPLOYEE_REGISTRATION_DETAILS (EMPLOYEE_ID,EMPLOYEE_NAME,EMPLOYEE_EMAIL,VEHICLE_REGISTRATION_NUMBER,location ,REQUEST_CATEGORY) 
VALUES('70017035','Yatin Chawla','yatin.chawla@ihsmarkit.com','UP168268','Noida','general_parking');
INSERT INTO EMPLOYEE_REGISTRATION_DETAILS (EMPLOYEE_ID,EMPLOYEE_NAME,EMPLOYEE_EMAIL,VEHICLE_REGISTRATION_NUMBER,location ,REQUEST_CATEGORY) 
VALUES('70019472','Rahul Agarwal','Rahul.Agarwal@ihsmarkit.com','DL3CBE8975','Noida','general_parking');
INSERT INTO EMPLOYEE_REGISTRATION_DETAILS (EMPLOYEE_ID,EMPLOYEE_NAME,EMPLOYEE_EMAIL,VEHICLE_REGISTRATION_NUMBER,location ,REQUEST_CATEGORY) 
VALUES('70019549','Ambika Mathur','Ambika.Mathur@ihsmarkit.com','CH01AB4511','Noida','female_night_shift');
INSERT INTO EMPLOYEE_REGISTRATION_DETAILS (EMPLOYEE_ID,EMPLOYEE_NAME,EMPLOYEE_EMAIL,VEHICLE_REGISTRATION_NUMBER,location ,REQUEST_CATEGORY) 
VALUES('70019593','Jayant Goel','Jayant.Goel@ihsmarkit.com','UP14DR2816','Noida','general_parking');
INSERT INTO EMPLOYEE_REGISTRATION_DETAILS (EMPLOYEE_ID,EMPLOYEE_NAME,EMPLOYEE_EMAIL,VEHICLE_REGISTRATION_NUMBER,location ,REQUEST_CATEGORY) 
VALUES('70020132','Yogesh Arora','Yogesh.Arora@ihsmarkit.com','DL4CAX 0314','Gurgaon','general_parking');
INSERT INTO EMPLOYEE_REGISTRATION_DETAILS (EMPLOYEE_ID,EMPLOYEE_NAME,EMPLOYEE_EMAIL,VEHICLE_REGISTRATION_NUMBER,location ,REQUEST_CATEGORY) 
VALUES('70020746','Tribhawan Singh','Tribhawan.Singh@ihsmarkit.com','HR51AH2930','Noida','general_parking');
INSERT INTO EMPLOYEE_REGISTRATION_DETAILS (EMPLOYEE_ID,EMPLOYEE_NAME,EMPLOYEE_EMAIL,VEHICLE_REGISTRATION_NUMBER,location ,REQUEST_CATEGORY) 
VALUES('70020956','Parv Johari','Parv.Johari@ihsmarkit.com','HR51BT4674','Noida','general_parking');
INSERT INTO EMPLOYEE_REGISTRATION_DETAILS (EMPLOYEE_ID,EMPLOYEE_NAME,EMPLOYEE_EMAIL,VEHICLE_REGISTRATION_NUMBER,location ,REQUEST_CATEGORY) 
VALUES('70021267','Neeshu Gupta','Neeshu.Gupta@ihsmarkit.com','HR10X5260','Noida','general_parking');
INSERT INTO EMPLOYEE_REGISTRATION_DETAILS (EMPLOYEE_ID,EMPLOYEE_NAME,EMPLOYEE_EMAIL,VEHICLE_REGISTRATION_NUMBER,location ,REQUEST_CATEGORY) 
VALUES('70021375','Amit Kumar5','Amit.Kumar5@ihsmarkit.com','UK07 AL 2475','Noida','general_parking');




                  
