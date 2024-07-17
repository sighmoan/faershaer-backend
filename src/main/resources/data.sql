INSERT INTO events (id, label) VALUES ('1', 'Picnic in the Park');

INSERT INTO persons (id, name) VALUES ('1', 'John');
INSERT INTO persons (id, name) VALUES ('2', 'Alice');
INSERT INTO persons (id, name) VALUES ('3', 'Bob');
INSERT INTO persons (id, name) VALUES ('4', 'Sara');
INSERT INTO persons (id, name) VALUES ('5', 'Mike');

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

INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('11', '2', '1', 'Appetizers', 25.0);
INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('12', '2', '2', 'Main Course', 60.0);
INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('13', '2', '3', 'Desserts', 20.0);
INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('14', '2', '4', 'Drinks', 35.0);
INSERT INTO transactions (id, event_id, payer_id, expense, sum) VALUES ('15', '2', '5', 'Tips', 15.0);
