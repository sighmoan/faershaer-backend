INSERT INTO users (id, name, portrait_url) VALUES ('1', 'Jean', 'https://ca.slack-edge.com/TA01UCHBN-U068H00G83Z-a66138e76622-512');
INSERT INTO users (id, name, portrait_url) VALUES ('2', 'Alyzee', 'https://ca.slack-edge.com/TA01UCHBN-U06SCMV1RUP-6c1a7224a5c7-512');
INSERT INTO users (id, name, portrait_url) VALUES ('3', 'Robain', 'https://ca.slack-edge.com/TA01UCHBN-U06TG2P5L80-9509cd9eb3a9-512');
INSERT INTO users (id, name, portrait_url) VALUES ('4', 'Seraphine', 'https://tailwindcss.com/_next/static/media/sarah-dayan.de9b3815.jpg');
INSERT INTO users (id, name, portrait_url) VALUES ('5', 'Miguel', 'https://ca.slack-edge.com/TA01UCHBN-U06SQBAEV1B-cfda066fb8cf-512');

INSERT INTO events (id, label) VALUES ('1', 'Pique-nique');

INSERT INTO persons (id, name, event_id, user_id) VALUES ('1', 'Jean', '1', '1');
INSERT INTO persons (id, name, event_id, user_id) VALUES ('2', 'Alyzee', '1', '2');
INSERT INTO persons (id, name, event_id, user_id) VALUES ('3', 'Robain', '1', '3');
INSERT INTO persons (id, name, event_id, user_id) VALUES ('4', 'Seraphine', '1', '4');
INSERT INTO persons (id, name, event_id, user_id) VALUES ('5', 'Miguel', '1', '5');

INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('1', '1', '1', 'Stationnement', 10.0);
INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('2', '1', '2', 'Grignoter', 20.75);
INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('4', '1', '4', 'Boissons', 15.0);
INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('5', '1', '5', 'Glace', 7.5);
INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('6', '1', '1', 'SPF', 8.0);
INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('7', '1', '2', 'Supplies a Pique-Nique', 25.0);
INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('8', '1', '3', 'Souvenir', 12.0);
INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('9', '1', '4', 'Jeux', 5.0);

INSERT INTO events (id, label) VALUES ('2', 'Dîner au restaurant');

INSERT INTO persons (id, name, event_id, user_id) VALUES ('6', 'Jean', '2', '1');
INSERT INTO persons (id, name, event_id, user_id) VALUES ('7', 'Alyzee', '2', '2');
INSERT INTO persons (id, name, event_id) VALUES ('8', 'Escobar', '2');
INSERT INTO persons (id, name, event_id) VALUES ('9', 'Helene', '2');


INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('10', '2', '1', 'Entrées', 30.0);
INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('11', '2', '2', 'Plats principaux', 70.0);
INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('12', '2', '3', 'Desserts', 25.0);
INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('13', '2', '4', 'Boissons', 40.0);
INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('14', '2', '5', 'Pourboire', 20.0);
