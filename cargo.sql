/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : cargo

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 17/04/2020 17:50:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
                           `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
                           `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
                           `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
                           `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
                           `mobile` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
                           `status` smallint(6) NULL DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
                           `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
                           `create_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
                           `create_time` datetime(0) NULL DEFAULT NULL COMMENT ' 创建时间',
                           `update_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
                           `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
                           `del_flag` tinyint(4) NULL DEFAULT NULL COMMENT '删除标记【 0：未删除，1：已删除】',
                           PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '管理员' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('12', 'admin', '123456', '15080156828@qq.com', '15080156828', 1, NULL, NULL, '2018-09-28 11:43:10', NULL, '2019-07-24 14:01:47', 0);

-- ----------------------------
-- Table structure for t_paper
-- ----------------------------
DROP TABLE IF EXISTS `t_paper`;
CREATE TABLE `t_paper`  (
                          `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
                          `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
                          `is_leaf` tinyint(1) NULL DEFAULT NULL COMMENT '1 文章 ; 0 菜单',
                          `type_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联类型ID',
                          `author` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '作者',
                          `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '文章内容',
                          `sort_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '排序编号',
                          `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
                          `create_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
                          `create_time` datetime(0) NULL DEFAULT NULL COMMENT ' 创建时间',
                          `update_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
                          `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
                          `del_flag` tinyint(4) NULL DEFAULT NULL COMMENT '删除标记【 0：未删除，1：已删除】',
                          PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文章表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_paper_type
-- ----------------------------
DROP TABLE IF EXISTS `t_paper_type`;
CREATE TABLE `t_paper_type`  (
                               `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
                               `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类型名',
                               `p_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父ID',
                               `sort_no` int(32) NULL DEFAULT NULL COMMENT '排序编号',
                               `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
                               `create_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
                               `create_time` datetime(0) NULL DEFAULT NULL COMMENT ' 创建时间',
                               `update_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
                               `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
                               `del_flag` tinyint(4) NULL DEFAULT NULL COMMENT '删除标记【 0：未删除，1：已删除】',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '类型表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_paper_type
-- ----------------------------
INSERT INTO `t_paper_type` VALUES ('1', '关于我们', '0', 1, NULL, NULL, '2020-04-17 11:25:58', NULL, '2020-04-17 11:26:02', 0);
INSERT INTO `t_paper_type` VALUES ('2', '国际运输', '0', 2, NULL, NULL, '2020-04-17 11:25:58', NULL, '2020-04-17 11:26:02', 0);
INSERT INTO `t_paper_type` VALUES ('3', '门到门运输专线', '0', 3, NULL, NULL, '2020-04-17 11:25:58', NULL, '2020-04-17 11:26:02', 0);
INSERT INTO `t_paper_type` VALUES ('4', '运输案例', '0', 4, NULL, NULL, '2020-04-17 11:25:58', NULL, '2020-04-17 11:26:02', 0);
INSERT INTO `t_paper_type` VALUES ('5', '货运知识', '0', 5, NULL, NULL, '2020-04-17 11:25:58', NULL, '2020-04-17 11:26:02', 0);
INSERT INTO `t_paper_type` VALUES ('6', '服务范围', '0', 6, NULL, NULL, '2020-04-17 11:25:58', NULL, '2020-04-17 11:26:02', 0);
INSERT INTO `t_paper_type` VALUES ('61', '东南亚', '6', 1, NULL, NULL, '2020-04-17 11:25:58', NULL, '2020-04-17 11:26:02', 0);
INSERT INTO `t_paper_type` VALUES ('62', '中东', '6', 2, NULL, NULL, '2020-04-17 11:25:58', NULL, '2020-04-17 11:26:02', 0);
INSERT INTO `t_paper_type` VALUES ('63', '澳洲', '6', 3, NULL, NULL, '2020-04-17 11:25:58', NULL, '2020-04-17 11:26:02', 0);
INSERT INTO `t_paper_type` VALUES ('7', '递接招聘', '0', 7, NULL, NULL, '2020-04-17 11:25:58', NULL, '2020-04-17 11:26:02', 0);
INSERT INTO `t_paper_type` VALUES ('8', '联系我们', '0', 8, NULL, NULL, '2020-04-17 11:25:58', NULL, '2020-04-17 11:26:02', 0);

SET FOREIGN_KEY_CHECKS = 1;
