INSERT INTO building (id, version, name, address, location, description, author, client_id, project_id, status) VALUES (1, 0, 'Building 1', 'Address_1', 'POINT(40.0 40.0)', 'Description 1', 'admin@mail.ru', 10, null, 'NEW');
INSERT INTO building (id, version, name, address, location, description, author, client_id, project_id, status) VALUES (2, 0, 'Building 2', 'Address_2', 'POINT(50.0 50.0)', 'Description 2', 'admin@mail.ru', 10, null, 'WORK');
INSERT INTO building (id, version, name, address, location, description, author, client_id, project_id, status) VALUES (3, 0, 'Building 3', 'Address_3', 'POINT(60.0 60.0)', 'Description 3', 'admin@mail.ru', 20, null, 'ARCHIVE');

alter sequence building_id_sequence restart with 4;