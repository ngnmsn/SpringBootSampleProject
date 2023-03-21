CREATE TABLE user_table (
  user_id serial PRIMARY KEY,
  user_name varchar(256),
  password varchar(256),
  mail_address varchar(256) UNIQUE
);