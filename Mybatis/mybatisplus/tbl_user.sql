/*
 Navicat Premium Data Transfer

 Source Server         : MySQL_practice
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : mybatis_db

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 05/06/2023 13:31:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tbl_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user`  (
  `id` bigint(0) NOT NULL DEFAULT 1,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `pwd` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `age` int(0) NOT NULL,
  `tel` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `deleted` int(0) NULL DEFAULT 0,
  `version` int(0) NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tbl_user
-- ----------------------------
INSERT INTO `tbl_user` VALUES (1, 'Tom', 'tom', 3, '123455', 1, 1);
INSERT INTO `tbl_user` VALUES (2, 'Jerry', 'jerry', 41, '123445555', 0, 1);
INSERT INTO `tbl_user` VALUES (3, 'Jock888', '12345', 41, '123123412312412', 0, 3);
INSERT INTO `tbl_user` VALUES (4, 'Gacier', 'it', 15, '123123123', 0, 1);
INSERT INTO `tbl_user` VALUES (5, 'ytq', 'qty', 27, '434343', 0, 1);

SET FOREIGN_KEY_CHECKS = 1;
