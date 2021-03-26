CREATE TABLE address(
    id BIGINT NOT NULL AUTO_INCREMENT,
    country VARCHAR(50),
    county VARCHAR(50),
    zipCode SMALLINT,
    buildingNr SMALLINT,
    streetName VARCHAR(50),
    city VARCHAR(50),
    additionalNrs INT,
    PRIMARY KEY(id)
);

CREATE TABLE author(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50),
    addressID BIGINT,
    PRIMARY KEY(id),
    FOREIGN KEY (addressID) REFERENCES address(id)
);

CREATE TABLE book(
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(50),
    PRIMARY KEY(id)
);

CREATE TABLE authorBook(
    authorID BIGINT NOT NULL,
    bookID BIGINT NOT NULL,
    CONSTRAINT authorBook_pk PRIMARY KEY (authorID, bookID),
    FOREIGN KEY (authorID) REFERENCES author(id),
    FOREIGN KEY (bookID) REFERENCES book(id)
);
