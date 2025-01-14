CREATE DATABASE IF NOT EXISTS db_serverless_device;

USE db_serverless_device;

CREATE TABLE if NOT EXISTS tbl_device (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(30) NULL,
    sn VARCHAR(64) NOT NULL,
    passwd VARCHAR(64) NOT NULL
);