INSERT IGNORE INTO person (id, name, phone, person_type) VALUES
(1, 'Anna Müller', '0151-12345678', 'MELDEND'),
(2, 'Thomas Fischer', '0160-98765432', 'BERGEND'),
(3, 'Sarah Weber', '0170-11223344', 'BERGEND'),
(4, 'Klaus Hoffmann', '0152-55667788', 'MELDEND'),
(5, 'Marie Schmidt', '0176-33445566', 'BERGEND');

INSERT IGNORE INTO ghost_net (id, latitude, longitude, estimated_size, status, person_id) VALUES
(1, 54.823, 13.456, 'Groß (über 50m²)', 'GEMELDET', NULL),
(2, 55.234, 14.123, 'Mittel (10-50m²)', 'BERGUNG_BEVORSTEHEND', 2),
(3, 54.456, 10.876, 'Klein (unter 10m²)', 'GEMELDET', NULL),
(4, 56.123, 15.234, 'Groß (über 50m²)', 'GEMELDET', NULL),
(5, 57.234, 11.456, 'Mittel (10-50m²)', 'BERGUNG_BEVORSTEHEND', 3),
(6, 55.678, 12.789, 'Klein (unter 10m²)', 'GEBORGEN', 2),
(7, 54.123, 14.567, 'Groß (über 50m²)', 'GEMELDET', NULL),
(8, 56.789, 16.123, 'Mittel (10-50m²)', 'VERSCHOLLEN', NULL);