CREATE TYPE orientation AS ENUM (
    'E', 'W', 'S', 'N'
);

create table ROBOTS(
  id int not null AUTO_INCREMENT,
  x_position int not null,
  y_position int not null,
  orientation orientation not null,
  PRIMARY KEY ( ID )
);

