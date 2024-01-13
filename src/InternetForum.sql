USE InternetForum

CREATE TABLE [user] (
	user_id int IDENTITY(100, 1) CONSTRAINT pk_user_id PRIMARY KEY,
	user_name nvarchar(30) NOT NULL UNIQUE,
	mail_address nvarchar(50) NOT NULL UNIQUE,
	hashed_password nvarchar(300) NOT NULL,
	registration_date datetime NOT NULL,
	delete_flag bit NOT NULL DEFAULT 1,
	profile nvarchar(512),
	report bit NOT NULL DEFAULT 0
)

CREATE TABLE [admin] (
	admin_id int IDENTITY(1, 1) CONSTRAINT pk_admin_id PRIMARY KEY,
	user_id int NOT NULL CONSTRAINT fk_admin_user FOREIGN KEY  (user_id) REFERENCES [user] (user_id)
)
CREATE TABLE [genre] (
     genre_id int IDENTITY(1, 1) CONSTRAINT pk_genre_id PRIMARY KEY,
     genre_name nvarchar(30) NOT NULL
)
CREATE TABLE [thread] (
	thread_id int IDENTITY(100, 1) CONSTRAINT pk_thread_id PRIMARY KEY,
	user_id int NOT NULL CONSTRAINT fk_thread_user FOREIGN KEY (user_id) REFERENCES [user] (user_id),
	create_day datetime NOT NULL,
	title nvarchar(64) NOT NULL UNIQUE,
	description nvarchar(1024) NOT NULL,
	update_day datetime,
	delete_flag bit NOT NULL DEFAULT 1,
	genre_id int NOT NULL CONSTRAINT fk_thread_genre FOREIGN KEY (genre_id) REFERENCES [genre] (genre_id),
	report bit NOT NULL DEFAULT 0,
	last_written_date datetime
)

CREATE TABLE [response] (
	response_id int IDENTITY(1, 1) CONSTRAINT pk_response_id PRIMARY KEY,
	user_id int NOT NULL CONSTRAINT fk_response_user FOREIGN KEY (user_id) REFERENCES [user] (user_id),
	thread_id int NOT NULL CONSTRAINT fk_response_thread FOREIGN KEY (thread_id) REFERENCES [thread] (thread_id),
	description nvarchar(1024) NOT NULL,
	posted_date datetime NOT NULL,
	update_day datetime,
	delete_flag bit NOT NULL DEFAULT 1,
	report bit NOT NULL DEFAULT 0
)

select * from [user]
SELECT * FROM [genre]
SELECT * FROM [thread]

INSERT INTO [genre] VALUES (N'ジャンル1'),(N'ジャンル2')

ALTER TABLE thread
    ADD last_written_date DATETIME;
