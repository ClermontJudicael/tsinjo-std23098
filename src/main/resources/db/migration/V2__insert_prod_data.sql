INSERT INTO donor (email, full_name) VALUES ('prod1@example.com', 'Prod Donor One');
INSERT INTO payment (amount, payment_method, status) VALUES (100.0, 'Credit Card', 'SUCCEEDED');
INSERT INTO donation (donor_id, payment_id, donation_date) VALUES (1, 1, '2025-08-01 10:00:00');

INSERT INTO donor (email, full_name) VALUES ('prod2@example.com', 'Prod Donor Two');
INSERT INTO payment (amount, payment_method, status) VALUES (200.0, 'Bank Transfer', 'SUCCEEDED');
INSERT INTO donation (donor_id, payment_id, donation_date) VALUES (2, 2, '2025-08-02 12:00:00');

INSERT INTO beneficiary (email, full_name) VALUES ('prod.beneficiary@example.com', 'Prod Beneficiary');
INSERT INTO payment (amount, payment_method, status) VALUES (150.0, 'Bank Transfer', 'SUCCEEDED');
INSERT INTO help (beneficiary_id, payment_id, aid_date, accident_description) VALUES (1, 3, '2025-08-03 14:00:00', 'Medical emergency');