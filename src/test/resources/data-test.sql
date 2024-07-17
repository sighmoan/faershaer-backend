INSERT INTO events (id, label) VALUES ('1', 'Pique-nique');

INSERT INTO persons (id, name) VALUES ('1', 'Jean');
INSERT INTO persons (id, name) VALUES ('2', 'Alyzee');
INSERT INTO persons (id, name) VALUES ('3', 'Robain');
INSERT INTO persons (id, name) VALUES ('4', 'Seraphine');
INSERT INTO persons (id, name) VALUES ('5', 'Miguel');

INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('1', '1', '1', 'Stationnement', 10.0);
INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('2', '1', '2', 'Grignoter', 20.75);
INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('4', '1', '4', 'Boissons', 15.0);
INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('5', '1', '5', 'Glace', 7.5);
INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('6', '1', '1', 'SPF', 8.0);
INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('7', '1', '2', 'Supplies a Pique-Nique', 25.0);
INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('8', '1', '3', 'Souvenir', 12.0);
INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('9', '1', '4', 'Jeux', 5.0);
