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

-- 导出  表 loveta.auth_group 结构
DROP TABLE IF EXISTS `auth_group`;
CREATE TABLE IF NOT EXISTS `auth_group` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户组名',
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户组';

-- 正在导出表  loveta.auth_group 的数据：~2 rows (大约)
DELETE FROM `auth_group`;
INSERT INTO `auth_group` (`id`, `name`, `description`, `create_time`, `update_time`) VALUES
	(1, 'super-admin', '超级管理员', '2024-01-08 12:05:57', '2024-01-08 14:47:03'),
	(2, 'admin', '管理员', '2024-01-08 14:46:59', '2024-01-08 14:47:06');

-- 导出  表 loveta.auth_group_menu 结构
DROP TABLE IF EXISTS `auth_group_menu`;
CREATE TABLE IF NOT EXISTS `auth_group_menu` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `group_id` int unsigned NOT NULL COMMENT '用户组ID',
  `menu_id` int unsigned NOT NULL COMMENT '菜单ID',
  `sort` int unsigned NOT NULL DEFAULT '0' COMMENT '排序标识',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `group_id` (`group_id`),
  KEY `menu_id` (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户组 - 菜单 关系表';

-- 正在导出表  loveta.auth_group_menu 的数据：~0 rows (大约)
DELETE FROM `auth_group_menu`;

-- 导出  表 loveta.auth_group_permission 结构
DROP TABLE IF EXISTS `auth_group_permission`;
CREATE TABLE IF NOT EXISTS `auth_group_permission` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `group_id` int unsigned NOT NULL COMMENT '用户组ID',
  `permission_id` int unsigned NOT NULL COMMENT '权限ID',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `group_id` (`group_id`),
  KEY `permission_id` (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户组 - 权限 关系表';

-- 正在导出表  loveta.auth_group_permission 的数据：~1 rows (大约)
DELETE FROM `auth_group_permission`;
INSERT INTO `auth_group_permission` (`id`, `group_id`, `permission_id`, `create_time`, `update_time`) VALUES
	(1, 1, 1, '2024-01-08 12:48:46', NULL);

-- 导出  表 loveta.auth_group_user 结构
DROP TABLE IF EXISTS `auth_group_user`;
CREATE TABLE IF NOT EXISTS `auth_group_user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` bigint unsigned NOT NULL COMMENT '用户ID',
  `group_id` int unsigned NOT NULL COMMENT '用户组ID',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `group_id` (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户组 - 用户 关系表';

-- 正在导出表  loveta.auth_group_user 的数据：~1 rows (大约)
DELETE FROM `auth_group_user`;
INSERT INTO `auth_group_user` (`id`, `user_id`, `group_id`, `create_time`, `update_time`) VALUES
	(1, 1, 1, '2024-01-08 12:49:54', NULL);

-- 导出  表 loveta.auth_menu 结构
DROP TABLE IF EXISTS `auth_menu`;
CREATE TABLE IF NOT EXISTS `auth_menu` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pid` int unsigned NOT NULL COMMENT '父菜单ID',
  `type` tinyint NOT NULL COMMENT '类型',
  `label` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标签',
  `icon` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图标',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '路径',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '组件',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='菜单';

-- 正在导出表  loveta.auth_menu 的数据：~9 rows (大约)
DELETE FROM `auth_menu`;
INSERT INTO `auth_menu` (`id`, `pid`, `type`, `label`, `icon`, `path`, `component`, `create_time`, `update_time`) VALUES
	(1, 0, 1, '仪表盘', NULL, '/dashboard', '/src/views/BaseDashboard.vue', '2024-01-08 12:06:27', '2024-01-14 13:08:37'),
	(2, 0, 1, '菜单管理', NULL, '/menu', '/src/views/MenuList.vue', '2024-01-08 12:06:27', '2024-01-14 13:08:40'),
	(3, 7, 2, 'Naive UI 组件', NULL, 'https://www.naiveui.com/zh-CN/os-theme/components/button', NULL, '2024-01-08 12:06:27', '2024-01-14 21:38:02'),
	(4, 0, 1, '我测', NULL, '/test', '/src/views/TestView.vue', '2024-01-08 12:06:27', '2024-01-14 13:08:44'),
	(5, 0, 1, '用户管理', NULL, '/user', '/src/views/UserList.vue', '2024-01-08 14:40:47', '2024-01-14 13:08:47'),
	(6, 7, 2, 'OSS 控制台', NULL, 'https://oss.console.aliyun.com/bucket/oss-cn-hangzhou/loveta-dev/overview', NULL, '2024-01-11 19:43:44', '2024-01-14 21:38:04'),
	(7, 0, 0, '开发书签', NULL, NULL, NULL, '2024-01-11 21:21:27', '2024-01-12 14:31:11'),
	(8, 7, 2, 'Vue', NULL, 'https://cn.vuejs.org/guide/introduction.html', NULL, '2024-01-30 16:35:06', NULL),
	(9, 7, 2, '图标', NULL, 'https://www.xicons.org/#/zh-CN', NULL, '2024-01-30 17:14:02', NULL);

-- 导出  表 loveta.auth_permission 结构
DROP TABLE IF EXISTS `auth_permission`;
CREATE TABLE IF NOT EXISTS `auth_permission` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限码',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='权限';

-- 正在导出表  loveta.auth_permission 的数据：~1 rows (大约)
DELETE FROM `auth_permission`;
INSERT INTO `auth_permission` (`id`, `code`, `description`, `create_time`, `update_time`) VALUES
	(1, '*', NULL, '2024-01-08 12:49:41', NULL);

-- 导出  表 loveta.auth_user 结构
DROP TABLE IF EXISTS `auth_user`;
CREATE TABLE IF NOT EXISTS `auth_user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机号',
  `password` char(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `wx_openid` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '微信唯一标识',
  `wx_unionid` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '微信统一标识',
  `status` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '状态',
  `nickname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '昵称',
  `realname` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '姓名',
  `avatar` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '头像',
  `sex` tinyint unsigned DEFAULT NULL COMMENT '性别',
  `create_time` datetime NOT NULL DEFAULT (now()) COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户';

-- 正在导出表  loveta.auth_user 的数据：~2 rows (大约)
DELETE FROM `auth_user`;
INSERT INTO `auth_user` (`id`, `phone`, `password`, `wx_openid`, `wx_unionid`, `status`, `nickname`, `realname`, `avatar`, `sex`, `create_time`, `update_time`) VALUES
	(1, '18888888888', '$2a$10$JNzmbAlTHNsEqVXjRHQEFuxZIUmtrwefrzfPsShcFFo5mZdST84ii', NULL, NULL, 0, NULL, NULL, NULL, 0, '2024-01-05 14:20:04', '2024-01-08 12:01:41'),
	(2, '18888888889', '$2a$10$4Tlz5qm3W8ShJfE3Et43qO7nrqtruaiFFLheBHNHV6oMM2Ahv2pgS', NULL, NULL, 0, NULL, NULL, NULL, NULL, '2024-01-11 19:17:16', NULL);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
