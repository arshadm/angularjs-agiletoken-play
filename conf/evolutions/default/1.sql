# schema

# --- !Ups

CREATE TABLE ag_queue (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    version bigint(20) NOT NULL,
    name varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE ag_queue_item (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    version bigint(20) NOT NULL,
    queue_id bigint(20) NOT NULL REFERENCES ag_queue(id),
    reference varchar(255),
    title varchar(255),
    author varchar(255),
    mergedInd boolean
    PRIMARY KEY (id)
);

# --- !Downs

DROP TABLE ag_queue_item;
DROP TABLE ag_queue;
