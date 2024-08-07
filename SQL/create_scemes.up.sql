CREATE TYPE roles AS ENUM ('автор', 'читатель');

CREATE TABLE users (
	id SERIAL PRIMARY KEY,
	username VARCHAR(60) UNIQUE NOT NULL,
	password VARCHAR(50) NOT NULL,
	role roles NOT NULL DEFAULT 'читатель',
	avatar VARCHAR(300) DEFAULT 'https://i.imgur.com/DGgiYhO.png',
	register_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE articles (
	id SERIAL PRIMARY KEY,
	created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
	author VARCHAR(60) NOT NULL REFERENCES users(username) ON UPDATE CASCADE,
	title VARCHAR(300) NOT NULL DEFAULT 'Заголовок статьи',
	contents VARCHAR(5000),
	image VARCHAR(300)
);

CREATE TABLE comments (
	id SERIAL PRIMARY KEY,
	created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
	author VARCHAR(60) NOT NULL REFERENCES users(username) ON UPDATE CASCADE,
	article_id INTEGER NOT NULL REFERENCES articles(id) ON DELETE CASCADE,
	contents VARCHAR(3000) NOT NULL
);