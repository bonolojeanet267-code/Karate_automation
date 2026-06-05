INSERT INTO orders
VALUES(2, 'Standard User', 'Sauce Labs Onesie', 7.99, 7.99, CURRENT_TIMESTAMP, '0712345678'),
      (3, 'Mpho User', 'Bolt T-Shirt', 15.99, 15.99,CURRENT_TIMESTAMP, '0762384122');

UPDATE orders
SET phone_number = '0674860705'
WHERE order_id = 1;