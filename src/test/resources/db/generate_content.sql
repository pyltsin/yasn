DELETE FROM ACCOUNTS;
DELETE FROM TELEPHONES;

INSERT INTO ACCOUNTS (ID, LOGIN, PASSWORD, FIRST_NAME, MIDDLE_NAME,
                      LAST_NAME, EMAIL, BIRTH_DAY)
VALUES (1, 'Vasya', '123', 'VASYa', 'LITTLE', 'PONY', 'a@a', '1988-11-11'),
  (2, 'Fedya', '13', 'Fedya', 'LITTLE', 'HOME', 'b@a', '1988-12-11'),
  (3, 'PETROV', '213', 'Fedya', 'LITTLE', 'PETROV', 'a@b', '1988-11-21');

INSERT INTO TELEPHONES (TELEPHONE, ID_ACCOUNT, TYPE)
VALUES ('123', 1, 'WORK'),
  ('333', 1, 'HOME'),
  ('456', 2, 'HOME'),
  ('789', 3, 'HOME');

INSERT INTO FRIENDS (ID_ACCOUNT1, ID_ACCOUNT2) VALUES
  (1, 2),
  (2, 3);
