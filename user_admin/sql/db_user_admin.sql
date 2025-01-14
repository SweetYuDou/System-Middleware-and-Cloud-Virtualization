/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : db_serverless_user_edu

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 10/2024
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tbl_group
-- ----------------------------
DROP TABLE IF EXISTS `tbl_group`;
CREATE TABLE `tbl_group`
(
    `id`               varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
    `name`             varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
    `description`      tinytext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
    `extension`        longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
    `creator_id`       varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
    `created_time`     bigint NULL DEFAULT NULL,
    `last_update_time` bigint NULL DEFAULT NULL,
    `last_operator_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_group
-- ----------------------------
INSERT INTO `tbl_group`
VALUES ('65087083848402406', 'Administrator', NULL, NULL, 'admin', 1730010770052, NULL, NULL);

-- ----------------------------
-- Table structure for tbl_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user`
(
    `id`               varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
    `account`          varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
    `name`             varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
    `password`         varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
    `salt`             varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
    `gender`           int NULL DEFAULT 0,
    `description`      tinytext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
    `mail`             varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
    `extension`        longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
    `status`           int NULL DEFAULT NULL,
    `creator_id`       varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
    `created_time`     bigint NULL DEFAULT NULL,
    `last_update_time` bigint NULL DEFAULT NULL,
    `last_operator_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `mobile_phone`(`account` ASC) USING BTREE,
    UNIQUE INDEX `mail`(`mail` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_user
-- ----------------------------
INSERT INTO `tbl_user`
VALUES ('65086510285720102', 'zhangsan', '张三', '8cceb5257bbde059fe109222909e046a', 'b1e75c5577a7e9a0ecae788f6113c760',
        1, NULL, 'zhangsan@example.com', NULL, 1, 'admin', 1730010633319, NULL, NULL);
INSERT INTO `tbl_user`
VALUES ('65086801592714995', 'lisi', '李四', 'fce3cda14087c8195fe45d37feb5f5e8', '59119c3e1650b4323275c2be72b45fae', 1,
        NULL, 'lis@example.com', NULL, 1, 'admin', 1730010702755, NULL, NULL);
INSERT INTO `tbl_user`
VALUES ('65086872090576958', 'wangwu', '王五', '5e0e9a278d7f6911c5881b698c740558', '2d17500abb1f579a89604f178e8c5361',
        0, NULL, 'wangwu@example.com', NULL, 1, 'admin', 1730010719562, NULL, NULL);

-- ----------------------------
-- Table structure for tbl_user_and_group
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user_and_group`;
CREATE TABLE `tbl_user_and_group`
(
    `user_id`          varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
    `group_id`         varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
    `extension`        longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
    `creator_id`       varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
    `created_time`     bigint NULL DEFAULT NULL,
    `last_update_time` bigint NULL DEFAULT NULL,
    `last_operator_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
    `description`      longtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
    INDEX              `LINK_USER`(`user_id` ASC) USING BTREE,
    INDEX              `LINK_GROUP`(`group_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tbl_user_and_group
-- ----------------------------
INSERT INTO `tbl_user_and_group`
VALUES ('65086872090576958', '65087083848402406', NULL, NULL, NULL, NULL, NULL, NULL);

SET
FOREIGN_KEY_CHECKS = 1;