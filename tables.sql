CREATE TABLE owner (
  user_id TEXT primary key ,
  display_name VARCHAR(255)  ,
  reputation VARCHAR,
  user_type VARCHAR(255),
  accept_rate INTEGER,
  profile_image VARCHAR(255),
  link VARCHAR(255)
);
CREATE TABLE question (
  question_id INTEGER PRIMARY KEY,
  title TEXT,
  body TEXT ,
  tags TEXT[],
  owner_id TEXT,
  owner_reputation INTEGER,
  is_answered BOOLEAN,
  view_count INTEGER,
  favorite_count INTEGER,
  down_vote_count INTEGER,
  up_vote_count INTEGER,
  answer_count INTEGER,
  score INTEGER,
  last_activity_date TIMESTAMP,
  creation_date TIMESTAMP,
  last_edit_date TIMESTAMP,
  link TEXT,
  FOREIGN KEY (owner_id) REFERENCES owner(user_id)
);
CREATE TABLE comment (
comment_id INTEGER PRIMARY KEY,
question_id INTEGER,
post_id INTEGER,
owner_id TEXT,
edited BOOLEAN,
creation_date BIGINT,
link TEXT,
body TEXT,
FOREIGN KEY (owner_id) REFERENCES owner(user_id),
FOREIGN KEY (question_id) REFERENCES question(question_id)
);
CREATE TABLE answer (
answer_id INTEGER PRIMARY KEY,
question_id INTEGER,
owner_id TEXT,
down_vote_count INTEGER,
up_vote_count INTEGER,
is_accepted BOOLEAN,
score INTEGER,
last_activity_date BIGINT,
last_edit_date BIGINT,
creation_date BIGINT,
link TEXT,
title TEXT,
body TEXT,
FOREIGN KEY (owner_id) REFERENCES owner(user_id),
FOREIGN KEY (question_id) REFERENCES question(question_id)
);