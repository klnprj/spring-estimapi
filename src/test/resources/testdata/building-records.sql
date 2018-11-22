INSERT INTO building (id, version, name, address, location, description, author, client_id, project_id) VALUES (1, 0, 'Building 1', 'Address 1', 'POINT(40.0 40.0)', 'Description 1', 'admin@mail.ru', 10, null);
INSERT INTO building (id, version, name, address, location, description, author, client_id, project_id) VALUES (2, 0, 'Building 2', 'Address 2', 'POINT(50.0 50.0)', 'Description 2', 'admin@mail.ru', 10, null);

alter sequence building_id_sequence restart with 3;