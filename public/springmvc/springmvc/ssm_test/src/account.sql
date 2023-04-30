CREATE databse IF NOT EXISTS ssm_test;
CREATE TABLE account(
	username VARCHAR(30),
	PASSWORD VARCHAR(30)
);
INSERT INTO account (username,PASSWORD) VALUES ('root','root');