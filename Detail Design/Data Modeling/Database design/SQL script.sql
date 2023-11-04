-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2023-11-04 17:33:54.173

-- tables
-- Table: Book
CREATE TABLE Book (
    id int  NOT NULL COMMENT 'ID, same as ID of Media of
which type is Book',
    author varchar(100)  NOT NULL COMMENT 'Author''''name',
    publisher varchar(100)  NOT NULL COMMENT 'Publishing house',
    publisher_date datetime  NOT NULL COMMENT 'Date of publishing',
    numer_of_page int  NULL COMMENT 'Page number',
    book_category varchar(100)  NULL COMMENT 'Book category',
    cover_type varchar(100)  NOT NULL COMMENT 'Cover type',
    language varchar(20)  NULL,
    CONSTRAINT Book_pk PRIMARY KEY (id)
);

-- Table: CD
CREATE TABLE CD (
    id int  NOT NULL COMMENT 'ID, same as ID of Media of
which type is CD',
    artist varchar(50)  NOT NULL COMMENT 'Artist''''s name',
    music_type varchar(50)  NOT NULL COMMENT 'Music genres',
    record_label varchar(50)  NOT NULL COMMENT 'Record label',
    category_cd varchar(50)  NOT NULL COMMENT 'Category of CD',
    release_date datetime  NULL,
    CONSTRAINT CD_pk PRIMARY KEY (id)
);

-- Table: DVD
CREATE TABLE DVD (
    id int  NOT NULL COMMENT 'ID, same as ID of Media of
which type is DVD',
    studio varchar(100)  NOT NULL COMMENT 'Manufacturer',
    disc_type varchar(20)  NOT NULL COMMENT 'Disc type',
    subtitle varchar(100)  NOT NULL COMMENT 'Subtitles',
    language varchar(20)  NOT NULL,
    runtime varchar(20)  NOT NULL COMMENT 'Duration',
    director varchar(50)  NOT NULL,
    release_date datetime  NULL,
    dvd_category varchar(20)  NULL,
    CONSTRAINT DVD_pk PRIMARY KEY (id)
);

-- Table: DeliveryInfo
CREATE TABLE DeliveryInfo (
    id int  NOT NULL COMMENT 'ID, auto increment, same as ID of Order',
    name varchar(50)  NOT NULL COMMENT 'Receiver name',
    province varchar(20)  NOT NULL COMMENT 'Provinces',
    address varchar(100)  NOT NULL COMMENT 'Delivery address',
    instruction varchar(100)  NOT NULL COMMENT 'Delivery instructions',
    time_receive datetime  NULL COMMENT 'time receive when select rush delivery',
    CONSTRAINT DeliveryInfo_pk PRIMARY KEY (id)
);

-- Table: Invoice
CREATE TABLE Invoice (
    id int  NOT NULL COMMENT 'ID, auto increment, same as ID of Order',
    total_amount int  NOT NULL,
    CONSTRAINT Invoice_pk PRIMARY KEY (id)
);

-- Table: Media
CREATE TABLE Media (
    id int  NOT NULL AUTO_INCREMENT COMMENT 'ID, auto increment',
    title varchar(200)  NOT NULL COMMENT 'Product name',
    value float  NOT NULL COMMENT 'value of product',
    price float  NOT NULL COMMENT 'price of product',
    category varchar(100)  NOT NULL COMMENT 'Media type : CD, DVD,...',
    image_url varchar(200)  NOT NULL COMMENT 'path to image',
    rush_delivery bool  NOT NULL COMMENT 'rush delivery available ? ',
    CONSTRAINT Media_pk PRIMARY KEY (id)
);

-- Table: MediaOrder
CREATE TABLE MediaOrder (
    id int  NOT NULL,
    quantity int  NOT NULL COMMENT 'Number',
    Media_id int  NOT NULL,
    Order_id int  NOT NULL,
    CONSTRAINT MediaOrder_pk PRIMARY KEY (id)
);

-- Table: Order
CREATE TABLE `Order` (
    id int  NOT NULL AUTO_INCREMENT COMMENT 'ID, auto increment, same as ID of DeliveryInfo',
    shipping_fee int  NOT NULL,
    CONSTRAINT Order_pk PRIMARY KEY (id)
) COMMENT 'Shipping fee';

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

-- Table: VNPay
CREATE TABLE VNPay (
    id int  NOT NULL AUTO_INCREMENT COMMENT 'ID, auto increment',
    owner varchar(50)  NOT NULL COMMENT 'Cardholders',
    phone_number_of_vnpay int  NOT NULL COMMENT 'Phone number used for registration',
    CONSTRAINT VNPay_pk PRIMARY KEY (id)
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
    REFERENCES VNPay (id);

-- Reference: PaymentTransaction_Invoice (table: PaymentTransaction)
ALTER TABLE PaymentTransaction ADD CONSTRAINT PaymentTransaction_Invoice FOREIGN KEY PaymentTransaction_Invoice (Invoice_id)
    REFERENCES Invoice (id);

-- Reference: Track_CD (table: Track)
ALTER TABLE Track ADD CONSTRAINT Track_CD FOREIGN KEY Track_CD (CD_id)
    REFERENCES CD (id);

-- End of file.

