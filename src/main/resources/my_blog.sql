/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : my_blog

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 16/06/2022 07:33:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `id` int NOT NULL,
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录用户名',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (1, 'admin', '21232f297a57a5a743894a0e4a801fc3');

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int NOT NULL,
  `title` tinytext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `view_count` int NOT NULL,
  `category` int NOT NULL,
  `summary` tinytext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT 0,
  `update_time` datetime NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_idx_name`(`name` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------

-- ----------------------------
-- Table structure for category_tree
-- ----------------------------
DROP TABLE IF EXISTS `category_tree`;
CREATE TABLE `category_tree`  (
  `ancestor` int NOT NULL COMMENT '祖先节点',
  `descendant` int NOT NULL COMMENT '后裔节点',
  `distance` int UNSIGNED NULL DEFAULT NULL COMMENT '间隔辈分',
  PRIMARY KEY (`ancestor`, `descendant`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category_tree
-- ----------------------------

-- ----------------------------
-- Table structure for discusstion
-- ----------------------------
DROP TABLE IF EXISTS `discusstion`;
CREATE TABLE `discusstion`  (
  `id` int NOT NULL,
  `type` tinyint(1) NOT NULL COMMENT '1-文章 2-其他',
  `object_id` int NOT NULL COMMENT '评论的实体 ID',
  `parent` int NOT NULL COMMENT '父评论 ID',
  `floor` int NOT NULL COMMENT '实体楼层数',
  `nest_id` int NOT NULL COMMENT '折叠楼层 ID',
  `nest_floor` int NOT NULL COMMENT '折叠楼层数',
  `nest_size` int NOT NULL COMMENT '折叠楼层大小',
  `user_id` int NOT NULL COMMENT '评论的用户',
  `nickname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '可以为空，则为匿名用户',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` binary(16) NOT NULL COMMENT '评论的ip地址',
  `state` tinyint(1) NOT NULL COMMENT '0-正常 1-待审核',
  `deleted` tinyint(1) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of discusstion
-- ----------------------------

-- ----------------------------
-- Table structure for draft
-- ----------------------------
DROP TABLE IF EXISTS `draft`;
CREATE TABLE `draft`  (
  `id` int NOT NULL,
  `article_id` int NULL DEFAULT NULL COMMENT '值 null 时表示未发布文章保存的草稿',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of draft
-- ----------------------------

-- ----------------------------
-- Table structure for draft_history
-- ----------------------------
DROP TABLE IF EXISTS `draft_history`;
CREATE TABLE `draft_history`  (
  `id` int NOT NULL,
  `save_count` int NOT NULL COMMENT '保存次数',
  `title` tinytext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `summary` tinytext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `keywords` tinytext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`, `save_count`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of draft_history
-- ----------------------------

-- ----------------------------
-- Table structure for keyword
-- ----------------------------
DROP TABLE IF EXISTS `keyword`;
CREATE TABLE `keyword`  (
  `id` int NOT NULL,
  `value` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_idx_val`(`value` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of keyword
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户显示名',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `email` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `auth` tinyint(1) NOT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT 0,
  `create_ip` binary(16) NOT NULL COMMENT '用户创建的 IP',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户个人信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', NULL, NULL, 0, 0, 0x00000000000000000000000000000000, '2022-06-07 00:21:02');

SET FOREIGN_KEY_CHECKS = 1;
