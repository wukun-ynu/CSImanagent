/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : csi

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 09/07/2020 19:46:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dept_inf
-- ----------------------------
DROP TABLE IF EXISTS `dept_inf`;
CREATE TABLE `dept_inf`  (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `dept_remark` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dept_inf
-- ----------------------------
INSERT INTO `dept_inf` VALUES (1, '技术部', '创新，思维，进取');
INSERT INTO `dept_inf` VALUES (2, '办公室', '务实，勤劳');
INSERT INTO `dept_inf` VALUES (3, '组织部', '合作');
INSERT INTO `dept_inf` VALUES (5, '组织部', '组织就是一切');
INSERT INTO `dept_inf` VALUES (7, '456', '4568');
INSERT INTO `dept_inf` VALUES (8, 'jishu', '466');

-- ----------------------------
-- Table structure for document_inf
-- ----------------------------
DROP TABLE IF EXISTS `document_inf`;
CREATE TABLE `document_inf`  (
  `doc_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  `file_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`doc_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of document_inf
-- ----------------------------
INSERT INTO `document_inf` VALUES (1, 2, '测试', '二是', '十世', '2019-09-24 15:02:08', 'D:/csi/\\files\\103-160PQ4235IW.jpg');
INSERT INTO `document_inf` VALUES (2, 3, '哈哈', '很好', '方法', '2019-09-24 15:02:25', 'D:/csi/\\files\\103-160PQ4235IW.jpg');
INSERT INTO `document_inf` VALUES (3, 2, '哈', '3发', '哈哈', '2019-09-24 15:02:42', 'D:/csi/\\files\\103-160PQ4235IW.jpg');
INSERT INTO `document_inf` VALUES (4, 2, '哈哈', '103-160PQ4235IW.jpg', '公告', '2019-09-24 13:05:34', 'D:/csi/\\files\\103-160PQ4235IW.jpg');
INSERT INTO `document_inf` VALUES (5, 2, 'ceshi', '27163020311.jpg', '我想上传文档成功', '2019-09-24 13:06:46', 'D:/csi/\\files\\27163020311.jpg');
INSERT INTO `document_inf` VALUES (6, 3, '我来了', '74ebe0bf8b246922-e44f78b4b2d8f20a-27d82b21f44803018d827c7d47116ce9.jpg', '我又来了', '2019-09-24 14:06:48', 'D:/csi/\\files\\74ebe0bf8b246922-e44f78b4b2d8f20a-27d82b21f44803018d827c7d47116ce9.jpg');
INSERT INTO `document_inf` VALUES (8, 3, '12', '1155116333216805_b.jpg', '12', '2019-09-25 03:54:18', 'D:/csi/\\files\\1155116333216805_b.jpg');
INSERT INTO `document_inf` VALUES (9, 3, '78', '271340107.jpg', '78', '2019-09-25 06:23:06', 'D:/csi/\\files\\271340107.jpg');
INSERT INTO `document_inf` VALUES (10, 3, '45', 'b6f93130fcf2656f.jpg', '45', '2019-09-25 06:38:33', 'D:\\vue\\csi\\static\\files\\b6f93130fcf2656f.jpg');
INSERT INTO `document_inf` VALUES (11, 2, '45698', '烟雾.jpg', '45698', '2019-09-25 15:28:41', 'D:\\vue\\csi\\static\\files\\烟雾.jpg');
INSERT INTO `document_inf` VALUES (12, 3, '哈哈哈', '27163020311.jpg', '哈哈哈', '2019-09-26 07:57:25', 'D:\\vue\\csi\\static\\files\\27163020311.jpg');
INSERT INTO `document_inf` VALUES (13, 3, '4', '37z58PICwD3_1024.jpg', '2', '2019-10-29 03:30:37', 'D:\\vue\\csi\\static\\files\\37z58PICwD3_1024.jpg');

-- ----------------------------
-- Table structure for employee_inf
-- ----------------------------
DROP TABLE IF EXISTS `employee_inf`;
CREATE TABLE `employee_inf`  (
  `employee_id` int(11) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
  `dept_id` int(11) NULL DEFAULT NULL,
  `job_id` int(11) NULL DEFAULT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `card_id` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `post_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `tel` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `qq_num` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sex` int(11) NULL DEFAULT NULL,
  `party` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `birthday` timestamp(0) NULL DEFAULT NULL,
  `race` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `education` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `specility` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `hobby` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`employee_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee_inf
-- ----------------------------
INSERT INTO `employee_inf` VALUES (00000000001, 1, 2, '吴坤', '532128199703055314', '云南大学', '657218', '15287707645', '15287707645', '2514826923', '2514826923@qq.com', 1, '团员', '2019-09-16 15:53:01', '汉族', '本科', '软件工程', 'IT', '以上信息不得泄露');
INSERT INTO `employee_inf` VALUES (00000000002, 2, 1, '胡歌', '532128199703055314', '云南大学', '657218', '15287707645', '15287707645', '2514826923', '2514826923@qq.com', 2, '团员', '2019-09-15 16:00:00', '汉族', '本科', '软件工程', '演戏', '始于颜值，忠于人品');
INSERT INTO `employee_inf` VALUES (00000000004, 2, 3, 'wukun23', '15464567453', '', '', '4517412', '15563421524', '', '12325327@qq.com', 1, '团员', NULL, '', '本科', '', '唱跳rap', '哈哈');
INSERT INTO `employee_inf` VALUES (00000000005, 1, 8, '吴坤', '532128199703055314', '云南大学', '62534', '15287707645', '15287707645', '2514826923', '2514826923@qq.com', 1, '团员', NULL, '汉族', '本科', '软件工程', '武', '供');
INSERT INTO `employee_inf` VALUES (00000000008, 1, 2, 'huhu', '15265', '', '', '', '', '', '25148269@qq.com', 1, '', '2019-09-15 16:00:00', '', '', '', '', '');
INSERT INTO `employee_inf` VALUES (00000000010, 2, 2, 'wukun123', '532128199703055314', '云南大学', '', '', '15287707645', '', '2514286923@qq.com', 1, '', '2019-09-25 16:00:00', '汉族', '', '', '', '');
INSERT INTO `employee_inf` VALUES (00000000011, 7, 2, '；l', '5555', '111', '', '', '111', '', '11', 1, '', NULL, '11', '', '', '', '');

-- ----------------------------
-- Table structure for facekey_inf
-- ----------------------------
DROP TABLE IF EXISTS `facekey_inf`;
CREATE TABLE `facekey_inf`  (
  `face_id` int(11) NOT NULL AUTO_INCREMENT,
  `app_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `api_key` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `secret_key` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `threshold` int(3) NULL DEFAULT NULL,
  PRIMARY KEY (`face_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for job_inf
-- ----------------------------
DROP TABLE IF EXISTS `job_inf`;
CREATE TABLE `job_inf`  (
  `job_id` int(11) NOT NULL AUTO_INCREMENT,
  `job_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `job_remark` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`job_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of job_inf
-- ----------------------------
INSERT INTO `job_inf` VALUES (1, 'python', 'python');
INSERT INTO `job_inf` VALUES (2, 'java', 'java');
INSERT INTO `job_inf` VALUES (3, 'c++', 'c++');
INSERT INTO `job_inf` VALUES (7, 'C#', 'c#');
INSERT INTO `job_inf` VALUES (8, 'javascript', 'var');
INSERT INTO `job_inf` VALUES (10, 'R语言', 'R语言');

-- ----------------------------
-- Table structure for notice_inf
-- ----------------------------
DROP TABLE IF EXISTS `notice_inf`;
CREATE TABLE `notice_inf`  (
  `notice_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`notice_id`) USING BTREE,
  INDEX `notice-user`(`user_id`) USING BTREE,
  CONSTRAINT `notice-user` FOREIGN KEY (`user_id`) REFERENCES `user_inf` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice_inf
-- ----------------------------
INSERT INTO `notice_inf` VALUES (2, 2, '测试', '测试内容', '2019-09-12 00:00:00');
INSERT INTO `notice_inf` VALUES (3, 3, 'weisha', 'wo buzhi dao', '2019-09-12 00:00:00');
INSERT INTO `notice_inf` VALUES (4, 2, '今天也是元气满满', '哈哈,这是在测试更新信息', '2019-09-16 00:00:00');
INSERT INTO `notice_inf` VALUES (19, 2, '测试', '测试', '2019-09-23 00:00:00');
INSERT INTO `notice_inf` VALUES (20, 3, 'weisha', '测试', '2019-09-23 00:00:00');
INSERT INTO `notice_inf` VALUES (21, 3, '烦烦烦', '反反复复烦烦烦', '2019-09-24 11:50:11');
INSERT INTO `notice_inf` VALUES (23, 2, '4569', '4569', '2019-09-25 15:24:08');
INSERT INTO `notice_inf` VALUES (24, 3, '111', '111', '2019-09-26 01:30:07');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'user');
INSERT INTO `sys_role` VALUES (2, 'admin');

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES (1, 1, 3);
INSERT INTO `sys_role_user` VALUES (2, 2, 2);
INSERT INTO `sys_role_user` VALUES (3, 2, 4);

-- ----------------------------
-- Table structure for user_inf
-- ----------------------------
DROP TABLE IF EXISTS `user_inf`;
CREATE TABLE `user_inf`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `create_date` timestamp(0) NULL DEFAULT NULL,
  `user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `face_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `face_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_inf
-- ----------------------------
INSERT INTO `user_inf` VALUES (2, '看你很萌', '123456', 1, '2019-09-09 09:32:04', 'wukun', NULL, NULL);
INSERT INTO `user_inf` VALUES (3, '古月哥欠', '123456', 0, '2019-09-12 09:57:30', 'hugea', NULL, NULL);
INSERT INTO `user_inf` VALUES (4, '彭于', '123456', 1, '2019-09-12 09:57:58', 'wukun2', NULL, NULL);
INSERT INTO `user_inf` VALUES (5, '古月哥欠口合', '0305', 1, '2019-09-21 13:24:28', 'wukun3', NULL, NULL);
INSERT INTO `user_inf` VALUES (6, '古月哥欠口合', '0305', 1, '2019-09-21 13:24:31', 'wukun4', NULL, NULL);
INSERT INTO `user_inf` VALUES (7, '古月哥欠口阿', '', 0, '2019-09-21 13:24:35', 'huge1', NULL, NULL);
INSERT INTO `user_inf` VALUES (8, '即将拥有人鱼线的PDD', '', 0, '2019-09-21 13:24:38', 'PDD', NULL, NULL);
INSERT INTO `user_inf` VALUES (9, 'loser', '789', 1, '2019-09-18 07:27:59', 'fail', NULL, NULL);
INSERT INTO `user_inf` VALUES (12, 'hugw', '', 0, '2019-09-23 01:10:01', 'huge2', NULL, NULL);
INSERT INTO `user_inf` VALUES (13, '橙子', '', 1, '2019-09-24 08:50:21', 'chengzi', NULL, NULL);
INSERT INTO `user_inf` VALUES (14, '玩完我那', '123456', 1, '2019-09-25 02:52:54', 'huzi', NULL, NULL);
INSERT INTO `user_inf` VALUES (15, 'knih', '', 0, '2019-09-25 15:00:12', 'w369', NULL, NULL);
INSERT INTO `user_inf` VALUES (16, 'lee', '', 0, '2019-09-26 01:25:55', '李李瓒', NULL, NULL);
INSERT INTO `user_inf` VALUES (17, 'pdd', '', 0, '2019-09-26 07:52:35', 'pdd', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
