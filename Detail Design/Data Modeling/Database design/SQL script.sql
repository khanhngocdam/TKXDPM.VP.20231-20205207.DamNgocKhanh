-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2023-11-04 06:47:14.171

-- tables
-- Table: Book
create database AIMS;
use AIMS;
CREATE TABLE Book (
    id int  NOT NULL,
    author varchar(100)  NOT NULL,
    publisher varchar(100)  NOT NULL,
    publisher_date datetime  NOT NULL,
    numer_of_page int  NULL,
    book_category varchar(100)  NULL,
    cover_type varchar(100)  NOT NULL,
    language varchar(20)  NULL,
    CONSTRAINT Book_pk PRIMARY KEY (id)
);

-- Table: CD
CREATE TABLE CD (
    id int  NOT NULL,
    artist varchar(50)  NOT NULL,
    music_type varchar(50)  NOT NULL,
    record_label varchar(50)  NOT NULL,
    category_cd varchar(50)  NOT NULL,
    release_date datetime  NULL,
    CONSTRAINT CD_pk PRIMARY KEY (id)
);

-- Table: Card
CREATE TABLE Card (
    id int  NOT NULL AUTO_INCREMENT,
    cvv_code varchar(50)  NOT NULL,
    date_expired datetime  NOT NULL,
    owner varchar(50)  NOT NULL,
    card_code varchar(50)  NOT NULL,
    CONSTRAINT Card_pk PRIMARY KEY (id)
);

-- Table: DVD
CREATE TABLE DVD (
    id int  NOT NULL,
    studio varchar(100)  NOT NULL,
    disc_type varchar(20)  NOT NULL,
    subtitle varchar(100)  NOT NULL,
    language varchar(20)  NOT NULL,
    runtime varchar(20)  NOT NULL,
    director varchar(50)  NOT NULL,
    release_date datetime  NULL,
    dvd_category varchar(20)  NULL,
    CONSTRAINT DVD_pk PRIMARY KEY (id)
);

-- Table: DeliveryInfo
CREATE TABLE DeliveryInfo (
    id int  NOT NULL,
    name varchar(50)  NOT NULL,
    province varchar(20)  NOT NULL,
    address varchar(100)  NOT NULL,
    instruction varchar(100)  NOT NULL,
    time_receive datetime  NULL,
    CONSTRAINT DeliveryInfo_pk PRIMARY KEY (id)
);

-- Table: Invoice
CREATE TABLE Invoice (
    id int  NOT NULL,
    total_amount int  NOT NULL,
    CONSTRAINT Invoice_pk PRIMARY KEY (id)
);

-- Table: Media
CREATE TABLE Media (
    id int  NOT NULL AUTO_INCREMENT,
    title varchar(200)  NOT NULL,
    value float  NOT NULL,
    price float  NOT NULL,
    category varchar(100)  NOT NULL,
    image_url varchar(200)  NOT NULL,
    rush_delivery bool  NOT NULL,
    CONSTRAINT Media_pk PRIMARY KEY (id)
);

-- Table: MediaOrder
CREATE TABLE MediaOrder (
    id int  NOT NULL,
    quantity int  NOT NULL,
    Media_id int  NOT NULL,
    Order_id int  NOT NULL,
    CONSTRAINT MediaOrder_pk PRIMARY KEY (id)
);

-- Table: Order
CREATE TABLE `Order` (
    id int  NOT NULL AUTO_INCREMENT,
    shipping_fee int  NOT NULL,
    CONSTRAINT Order_pk PRIMARY KEY (id)
);

-- Table: PaymentTransaction
CREATE TABLE PaymentTransaction (
    id int  NOT NULL AUTO_INCREMENT,
    createdAt varchar(100)  NOT NULL,
    content varchar(200)  NOT NULL,
    method varchar(100)  NOT NULL,
    Card_id int  NOT NULL,
    Invoice_id int  NOT NULL,
    CONSTRAINT PaymentTransaction_pk PRIMARY KEY (id)
);

-- Table: Track
CREATE TABLE Track (
    id int  NOT NULL AUTO_INCREMENT,
    name varchar(100)  NOT NULL,
    CD_id int  NOT NULL,
    CONSTRAINT Track_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: Book_Media (table: Book)
ALTER TABLE Book ADD CONSTRAINT Book_Media FOREIGN KEY Book_Media (id)
    REFERENCES Media (id);

-- Reference: CD_Media (table: CD)
ALTER TABLE CD ADD CONSTRAINT CD_Media FOREIGN KEY CD_Media (id)
    REFERENCES Media (id);

-- Reference: DVD_Media (table: DVD)
ALTER TABLE DVD ADD CONSTRAINT DVD_Media FOREIGN KEY DVD_Media (id)
    REFERENCES Media (id);

-- Reference: DeliveryInfo_Order (table: DeliveryInfo)
ALTER TABLE DeliveryInfo ADD CONSTRAINT DeliveryInfo_Order FOREIGN KEY DeliveryInfo_Order (id)
    REFERENCES `Order` (id);

-- Reference: Invoice_Order (table: Invoice)
ALTER TABLE Invoice ADD CONSTRAINT Invoice_Order FOREIGN KEY Invoice_Order (id)
    REFERENCES `Order` (id);

-- Reference: MediaOrder_Media (table: MediaOrder)
ALTER TABLE MediaOrder ADD CONSTRAINT MediaOrder_Media FOREIGN KEY MediaOrder_Media (Media_id)
    REFERENCES Media (id);

-- Reference: MediaOrder_Order (table: MediaOrder)
ALTER TABLE MediaOrder ADD CONSTRAINT MediaOrder_Order FOREIGN KEY MediaOrder_Order (Order_id)
    REFERENCES `Order` (id);

-- Reference: PaymentTransaction_Card (table: PaymentTransaction)
ALTER TABLE PaymentTransaction ADD CONSTRAINT PaymentTransaction_Card FOREIGN KEY PaymentTransaction_Card (Card_id)
    REFERENCES Card (id);

-- Reference: PaymentTransaction_Invoice (table: PaymentTransaction)
ALTER TABLE PaymentTransaction ADD CONSTRAINT PaymentTransaction_Invoice FOREIGN KEY PaymentTransaction_Invoice (Invoice_id)
    REFERENCES Invoice (id);

-- Reference: Track_CD (table: Track)
ALTER TABLE Track ADD CONSTRAINT Track_CD FOREIGN KEY Track_CD (CD_id)
    REFERENCES CD (id);

-- End of file.

