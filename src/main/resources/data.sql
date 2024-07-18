INSERT INTO users (id, name, portrait_url) VALUES ('1', 'Alek', 'https://ca.slack-edge.com/TA01UCHBN-U068H00G83Z-a66138e76622-512');
INSERT INTO users (id, name, portrait_url) VALUES ('2', 'Alice', 'https://ca.slack-edge.com/TA01UCHBN-U06SCMV1RUP-6c1a7224a5c7-512');
INSERT INTO users (id, name, portrait_url) VALUES ('3', 'Bob', 'https://ca.slack-edge.com/TA01UCHBN-U06TG2P5L80-9509cd9eb3a9-512');
INSERT INTO users (id, name, portrait_url) VALUES ('4', 'Sara', 'https://tailwindcss.com/_next/static/media/sarah-dayan.de9b3815.jpg');
INSERT INTO users (id, name, portrait_url) VALUES ('5', 'Mike', 'https://ca.slack-edge.com/TA01UCHBN-U06SQBAEV1B-cfda066fb8cf-512');

INSERT INTO events (id, label) VALUES ('1', 'Picnic in the Park');

INSERT INTO persons (id, name, user_id, event_id) VALUES ('1', 'Alek', '1', '1');
INSERT INTO persons (id, name, user_id, event_id) VALUES ('2', 'Alice', '2', '1');
INSERT INTO persons (id, name, user_id, event_id) VALUES ('3', 'Bob', '3', '1');
INSERT INTO persons (id, name, user_id, event_id) VALUES ('4', 'Sara', '4', '1');
INSERT INTO persons (id, name, user_id, event_id) VALUES ('5', 'Mike', '5', '1');

INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('1', '1', '1', 'Parking', 10.0);
INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('2', '1', '2', 'Snacks', 20.75);
INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('3', '1', '3', 'Entrance Fee', 30.0);
INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('4', '1', '4', 'Drinks', 15.0);
INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('5', '1', '5', 'Ice Cream', 7.5);
INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('6', '1', '1', 'Sunscreen', 8.0);
INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('7', '1', '2', 'Picnic Supplies', 25.0);
INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('8', '1', '3', 'Souvenirs', 12.0);
INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('9', '1', '4', 'Games', 5.0);
INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('10', '1', '5', 'Parking', 10.0);

INSERT INTO events (id, label) VALUES ('2', 'Dinner at the Restaurant');

INSERT INTO persons (id, name, event_id) VALUES ('6', 'Stinker McGee', '1');
INSERT INTO persons (id, name, event_id) VALUES ('7', 'Guts Swanson', '1');
INSERT INTO persons (id, name, event_id) VALUES ('8', 'Gus Gristly', '1');

INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('11', '2', '1', 'Appetizers', 25.0);
INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('12', '2', '2', 'Main Course', 60.0);
INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('13', '2', '3', 'Desserts', 20.0);
INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('14', '2', '4', 'Drinks', 35.0);
INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('15', '2', '5', 'Tips', 15.0);
