/*
 Navicat Premium Data Transfer

 Source Server         : testdb
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : toeat

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 04/11/2020 17:54:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `menu_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '菜品id',
  `menu_store_id` int(0) NULL DEFAULT NULL COMMENT '商家关联id',
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜品名称',
  `menu_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '菜品价格',
  `menu_discount` decimal(10, 2) NULL DEFAULT 1.00 COMMENT '菜品折扣',
  `menu_sales` bigint(0) NULL DEFAULT NULL COMMENT '菜品销量',
  `menu_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜品图片',
  `menu_type` int(0) NULL DEFAULT NULL COMMENT '菜品类型',
  `menu_flavor` int(0) NULL DEFAULT 0 COMMENT '菜品口味 1不辣/2微辣/3中辣/特辣',
  `menu_star` int(0) NULL DEFAULT NULL COMMENT '好评量',
  `menu_star_pecent` decimal(10, 2) NULL DEFAULT NULL COMMENT '好评百分比',
  `menu_del` int(0) NULL DEFAULT NULL COMMENT '删除 0未删除/1已删除',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜品信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for order_menu
-- ----------------------------
DROP TABLE IF EXISTS `order_menu`;
CREATE TABLE `order_menu`  (
  `order_menu_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `order_id` int(0) NULL DEFAULT NULL COMMENT '订单id',
  `menu_id` int(0) NULL DEFAULT NULL COMMENT '菜品id',
  `menu_num` int(0) NULL DEFAULT NULL COMMENT '菜品数量',
  `order_menu_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '该菜品的总价',
  PRIMARY KEY (`order_menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单中菜品信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_menu
-- ----------------------------
INSERT INTO `order_menu` VALUES (3, NULL, 1, 1, 15.00);
INSERT INTO `order_menu` VALUES (4, NULL, 2, 1, 15.00);
INSERT INTO `order_menu` VALUES (5, 6, 1, 1, 15.00);
INSERT INTO `order_menu` VALUES (6, 6, 2, 1, 15.00);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `order_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `order_user_id` int(0) NULL DEFAULT NULL COMMENT '用户id',
  `order_create_time` datetime(0) NULL DEFAULT NULL COMMENT '订单创建时间',
  `order_state` int(0) NULL DEFAULT 0 COMMENT '0未支付/1已支付',
  `order_store_id` int(0) NULL DEFAULT NULL COMMENT '商家id',
  `order_sum_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '总价格',
  `order_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `order_evaluate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评价',
  `order_del` int(0) NULL DEFAULT 0 COMMENT '删除 0未删除/1已删除',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (5, 1, '2020-11-04 09:35:57', 0, 1, 100.00, 'asdasd', NULL, 0);
INSERT INTO `orders` VALUES (6, 1, '2020-11-04 09:38:18', 0, 1, 100.00, 'asdasd', NULL, 0);

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store`  (
  `store_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '商家id',
  `store_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商家名称',
  `store_collect` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '商家被收藏数',
  `store_star` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '商家星级',
  `store_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商家备注信息',
  `store_sales` bigint(0) NULL DEFAULT NULL COMMENT '商家销量',
  `store_state` int(0) NULL DEFAULT NULL COMMENT '商家状态 0未营业/1正在营业',
  `store_del` int(0) NULL DEFAULT NULL COMMENT '删除 0未删除/1已删除',
  PRIMARY KEY (`store_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商家信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `user_pass_word` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `user_nick` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `user_sex` int(0) NULL DEFAULT NULL COMMENT '用户性别',
  `user_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `user_mail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `user_type` int(0) NULL DEFAULT NULL COMMENT '0管理员/1顾客/2商家',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'zdb', '123', NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
