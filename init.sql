-- --------------------------------------------------------
-- 主机:                           mysql.sqlpub.com
-- 服务器版本:                        8.0.33 - MySQL Community Server - GPL
-- 服务器操作系统:                      Linux
-- HeidiSQL 版本:                  12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- 导出 loveta 的数据库结构
DROP DATABASE IF EXISTS `loveta`;
CREATE DATABASE IF NOT EXISTS `loveta` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `loveta`;

-- 导出  表 loveta.auth_menu 结构
DROP TABLE IF EXISTS `auth_menu`;
CREATE TABLE IF NOT EXISTS `auth_menu` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pid` int unsigned NOT NULL COMMENT '父菜单ID',
  `type` tinyint NOT NULL COMMENT '类型',
  `label` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标签',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '路径',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='菜单表';

-- 正在导出表  loveta.auth_menu 的数据：~12 rows (大约)
DELETE FROM `auth_menu`;
INSERT INTO `auth_menu` (`id`, `pid`, `type`, `label`, `path`, `create_time`, `update_time`) VALUES
	(1, 0, 1, '菜单管理', '/menu', '2024-01-08 12:06:27', '2024-03-11 22:29:11'),
	(2, 0, 1, '用户管理', '/user', '2024-01-08 14:40:47', '2024-03-18 20:01:55'),
	(3, 0, 0, '开发书签', NULL, '2024-01-11 21:21:27', '2024-03-16 10:26:38'),
	(4, 3, 2, 'Vue 文档', 'https://cn.vuejs.org/guide/introduction.html', '2024-01-30 16:35:06', '2024-03-06 00:30:19'),
	(5, 3, 2, 'Naive UI 组件', 'https://www.naiveui.com/zh-CN/os-theme/components/button', '2024-01-08 12:06:27', '2024-03-06 00:07:06'),
	(6, 3, 2, 'Ionicons 图标', 'https://ionic.io/ionicons', '2024-01-30 17:14:02', '2024-03-06 00:08:20'),
	(7, 29, 2, 'OSS 控制台', 'https://oss.console.aliyun.com/bucket/oss-cn-hangzhou/loveta-dev/overview', '2024-01-11 19:43:44', '2024-03-14 16:33:00'),
	(8, 3, 2, 'TypeScript 教程', 'https://typescript.p6p.net/', '2024-03-06 15:22:09', '2024-03-12 21:42:43'),
	(9, 3, 0, '后端', NULL, '2024-03-13 23:53:29', '2024-03-18 00:54:42'),
	(10, 29, 2, '验证码', 'https://yundun.console.aliyun.com/?p=captcha', '2024-03-17 00:21:37', '2024-03-18 00:54:44'),
	(11, 0, 1, '角色管理', '/role', '2024-03-22 14:04:53', NULL),
	(13, 0, 1, '权限管理', '/permission', '2024-03-22 15:35:06', NULL);

-- 导出  表 loveta.auth_permission 结构
DROP TABLE IF EXISTS `auth_permission`;
CREATE TABLE IF NOT EXISTS `auth_permission` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限编码',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='权限表';

-- 正在导出表  loveta.auth_permission 的数据：~1 rows (大约)
DELETE FROM `auth_permission`;
INSERT INTO `auth_permission` (`id`, `code`, `description`, `create_time`, `update_time`) VALUES
	(1, '*', NULL, '2024-03-22 18:44:02', '2024-03-22 21:47:56');

-- 导出  表 loveta.auth_role 结构
DROP TABLE IF EXISTS `auth_role`;
CREATE TABLE IF NOT EXISTS `auth_role` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色编码',
  `name` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色名',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色表';

-- 正在导出表  loveta.auth_role 的数据：~3 rows (大约)
DELETE FROM `auth_role`;
INSERT INTO `auth_role` (`id`, `code`, `name`, `description`, `create_time`, `update_time`) VALUES
	(1, 'super-admin', '超级管理员', NULL, '2024-01-08 12:05:57', '2024-03-22 15:18:19'),
	(2, 'admin', '管理员', NULL, '2024-01-08 14:46:59', '2024-03-22 15:18:24'),
	(3, 'volunteer', '志愿者', '一般认为，志愿者是自愿贡献个人的时间和精力的人，在不计物质报酬的前提下为推动人类发展、社会进步和社会福利事业而提供服务的人员。', '2024-03-18 19:13:08', '2024-03-22 14:42:17');

-- 导出  表 loveta.auth_role_menu 结构
DROP TABLE IF EXISTS `auth_role_menu`;
CREATE TABLE IF NOT EXISTS `auth_role_menu` (
  `role_id` int unsigned NOT NULL COMMENT '角色ID',
  `menu_id` int unsigned NOT NULL COMMENT '菜单ID',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  PRIMARY KEY (`role_id`,`menu_id`),
  KEY `FK_auth_role_menu_auth_menu` (`menu_id`),
  CONSTRAINT `FK_auth_role_menu_auth_menu` FOREIGN KEY (`menu_id`) REFERENCES `auth_menu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_auth_role_menu_auth_role` FOREIGN KEY (`role_id`) REFERENCES `auth_role` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色菜单关系表';

-- 正在导出表  loveta.auth_role_menu 的数据：~0 rows (大约)
DELETE FROM `auth_role_menu`;

-- 导出  表 loveta.auth_role_permission 结构
DROP TABLE IF EXISTS `auth_role_permission`;
CREATE TABLE IF NOT EXISTS `auth_role_permission` (
  `role_id` int unsigned NOT NULL COMMENT '角色ID',
  `permission_id` int unsigned NOT NULL COMMENT '权限ID',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  PRIMARY KEY (`role_id`,`permission_id`),
  KEY `FK_auth_role_permission_auth_permission` (`permission_id`),
  CONSTRAINT `FK_auth_role_permission_auth_permission` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_auth_role_permission_auth_role` FOREIGN KEY (`role_id`) REFERENCES `auth_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色权限关系表';

-- 正在导出表  loveta.auth_role_permission 的数据：~1 rows (大约)
DELETE FROM `auth_role_permission`;
INSERT INTO `auth_role_permission` (`role_id`, `permission_id`, `create_time`) VALUES
	(1, 1, '2024-03-22 18:44:30');

-- 导出  表 loveta.auth_user 结构
DROP TABLE IF EXISTS `auth_user`;
CREATE TABLE IF NOT EXISTS `auth_user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '手机号',
  `password` char(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `wx_openid` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '微信唯一标识',
  `wx_unionid` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '微信统一标识',
  `status` tinyint unsigned NOT NULL COMMENT '状态',
  `nickname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '昵称',
  `realname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '姓名',
  `avatar` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '头像',
  `sex` tinyint unsigned DEFAULT NULL COMMENT '性别',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- 正在导出表  loveta.auth_user 的数据：~2 rows (大约)
DELETE FROM `auth_user`;
INSERT INTO `auth_user` (`id`, `phone`, `password`, `wx_openid`, `wx_unionid`, `status`, `nickname`, `realname`, `avatar`, `sex`, `create_time`, `update_time`) VALUES
	(1, '18888888888', '$2a$10$JNzmbAlTHNsEqVXjRHQEFuxZIUmtrwefrzfPsShcFFo5mZdST84ii', NULL, NULL, 1, 'lingkai5wu', '', NULL, 0, '2024-01-05 14:20:04', '2024-03-22 02:13:04'),
	(2, '18888888889', '$2a$10$4Tlz5qm3W8ShJfE3Et43qO7nrqtruaiFFLheBHNHV6oMM2Ahv2pgS', NULL, NULL, 1, NULL, NULL, NULL, 0, '2024-01-11 19:17:16', '2024-03-18 20:03:58');

-- 导出  表 loveta.auth_user_role 结构
DROP TABLE IF EXISTS `auth_user_role`;
CREATE TABLE IF NOT EXISTS `auth_user_role` (
  `user_id` bigint unsigned NOT NULL COMMENT '用户ID',
  `role_id` int unsigned NOT NULL COMMENT '角色ID',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_auth_user_role_auth_role` (`role_id`),
  CONSTRAINT `FK_auth_user_role_auth_role` FOREIGN KEY (`role_id`) REFERENCES `auth_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_auth_user_role_auth_user` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户角色关系表';

-- 正在导出表  loveta.auth_user_role 的数据：~4 rows (大约)
DELETE FROM `auth_user_role`;
INSERT INTO `auth_user_role` (`user_id`, `role_id`, `create_time`) VALUES
	(1, 1, '2024-03-20 16:10:13'),
	(1, 3, '2024-03-22 13:26:13'),
	(2, 2, '2024-03-22 13:26:23'),
	(2, 3, '2024-03-22 14:42:21');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
