package com.itpersion.myimclient.config;

/**
 * 角色表里面数据表的字段配�?
 * 
 * @author 机器人编�?
 * 
 */
public class MyConfig {
	/**
	 * 
	 * @ClassName: MySQliteBaseInfo
	 * @Description: 基础数据库和表的配置信息
	 * @author 机器人编�?
	 * @date 2015�?0�?2�?上午10:09:02
	 * 
	 */
	public static class MySQliteBaseInfo {
		/**
		 * 数据库的名称
		 */
		public static String SqliteBaseDBname = "YJTClient.db";
		/**
		 * 数据库的版本�?
		 */
		public static int SqliteBaseDBversions = 3;
	}

	/**
	 * 用户表的字段
	 * 
	 * @author 机器人编�?
	 * 
	 */
	public static class RosterColumnName {
		/**
		 * 用户表表�?
		 */
		public static String RosterTableName = "user";
		public static String[] lanname = { "_id", "jid", "username",
				"nickname", "status", "status_message" };
		/**
		 * 数据表id
		 */
		public static String ID = "_id";
		/**
		 * 数据表jid
		 */
		public static String JID = "jid";
		/**
		 * 数据表的用户名称
		 */
		public static String USERNAME = "username";
		/**
		 * 数据表的用户昵称
		 */
		public static String NICKNAME = "nickname";
		/**
		 * 数据表的用户状�?英文�?��
		 */
		public static String STATUS = "status";
		/**
		 * 数据表的用户状�?中文�?���?
		 */
		public static String STATUS_MESSAGE = "status_message";

		// /**
		// * 数据�?
		// */
		// public static String MEMBERS = "members";
	}

	/**
	 * 组表�?
	 * 
	 * @author 机器人编�?
	 * 
	 */
	public static class GroupColumnName {
		/**
		 * 组表表名
		 */
		public static String GroupsTableName = "groups";
		public static String[] lanname = { "_id", "groupname" };
		/**
		 * 数据表id
		 */
		public static String ID = "_id";
		/**
		 * 数据表组�?
		 */
		public static String GROUPNAME = "groupname";
	}

	/**
	 * 组和用户对应关系的表
	 * 
	 * @author 机器人编�?
	 * 
	 */
	public static class GroupUserColumnName {
		/**
		 * 组和用户组和表表�?
		 */
		public static String GroupUsersTableName = "groupusers";
		public static String[] lanname = { "_id", "u_id", "g_id" };
		/**
		 * 数据表id
		 */
		public static String ID = "_id";
		/**
		 * 组的id
		 */
		public static String G_ID = "g_id";
		/**
		 * 用户的id�?
		 */
		public static String U_ID = "u_id";
	}

	/**
	 * Vcard个人信息的表的列�?
	 * 
	 * @author 机器人编�?
	 * 
	 */
	public static class VcardColumnName {
		/**
		 * Vcard表名和Vcard的列�?
		 */
		public static String VcardTableName = "vcards";
		public static String[] lanname = { "_id", "userName", "name",
				"department", "post", "workPhoneNum", "homePhoneNum", "email" };
		/**
		 * 数据表id
		 */
		public static String ID = "_id";
		/**
		 * 用户的jid
		 */
		public static String USERNAME = "userName";
		/**
		 * 用户的名�?
		 */
		public static String NAME = "name";
		/**
		 * 用户的部门�?
		 */
		public static String DEPARTMENT = "department";
		/**
		 * 用户的职务�?
		 */
		public static String POST = "post";
		/**
		 * 用户的手机号码�?
		 */
		public static String WORKPHONENUM = "workPhoneNum";
		/**
		 * 用户的座机号码�?
		 */
		public static String HOMEPHONENUM = "homePhoneNum";
		/**
		 * 用户的邮箱�?
		 */
		public static String EMAIL = "email";
		/**
		 * 头像类型
		 */
		public static String IMAGETYPE = "imageType";
		/**
		 * 用户的头像URL地址
		 */
		public static String IMAGEBINARYCODE = "imageBinaryCode";
	}

	/**
	 * 推�?附件的实体类
	 * 
	 * @author 机器人编�?
	 * 
	 */
	public static class AnnexMessageColumnName {
		/**
		 * 附件的表名和附件的列�?
		 */
		public static String AnnexMessageTableName = "annexmessage";
		public static String[] lanname = { "_id", "title", "time", "content",
				"file", "fwq_id", "user_jid" };
		/**
		 * 数据表id
		 */
		public static String ID = "_id";
		/**
		 * 消息标题
		 */
		public static String TITLE = "title";
		/**
		 * 消息的time
		 */
		public static String TIME = "time";
		/**
		 * 消息的内�?
		 */
		public static String CONTENT = "content";
		/**
		 * 消息的file附件
		 */
		public static String FILE = "file";
		/**
		 * 消息的回执id�?
		 */
		public static String FWQ_ID = "fwq_id";
		/**
		 * 当前登录用户的jid�?
		 */
		public static String USER_ID = "user_jid";
		/**
		 * 当前用户是否阅读�?
		 */
		public static String READ_STATS = "read_stats";
		/**
		 * 发�?者�?
		 */
		public static String ANNEX_SEND = "annex_send";
	}

	/**
	 * 推�?的组织机构组�?
	 * 
	 * @author 机器人编�?
	 * 
	 */
	public static class OfGroupColumnName {
		/**
		 * 组织机构的表名和组织的列�?
		 */
		public static String OfGroupTableName = "ofgroup";
		public static String[] lanname = { "_id", "addOrgID", "description",
				"groupname", "hasChildren", "orgTreeID", "porgTreeID", "sort",
				"treePointYJname", "treePointZWname" };
		/**
		 * 数据表id
		 */
		public static String ID = "_id";
		/**
		 * 备用字段
		 */
		public static String ADDORGID = "addOrgID";
		/**
		 * 组的描述
		 */
		public static String DESCRIPTION = "description";
		/**
		 * 当前节点的id
		 */
		public static String GROUPNAME = "groupname";
		/**
		 * 是否有子节点
		 */
		public static String HASCHILDERN = "hasChildren";
		/**
		 * 组的子节点id
		 */
		public static String ORGTREEID = "orgTreeID";
		/**
		 * 组的父节点id
		 * 
		 */
		public static String PORGTREEID = "porgTreeID";
		/**
		 * 组的排序字段
		 */
		public static String SORT = "sort";
		/**
		 * 备用字段
		 */
		public static String TREEPOINTYJNAME = "treePointYJname";
		/**
		 * 备用字段
		 */
		public static String TREEPOINTZWNAME = "treePointZWname";
	}

	/**
	 * 推�?的组织结构人员表名和列名
	 * 
	 * @author 机器人编�?
	 * 
	 */
	public static class OfUserColumnName {
		/**
		 * 组织机构的表名和组织的列�?
		 */
		public static String OfUserTableName = "ofuser";
		public static String[] lanname = { "_id", "fax", "groupDescription",
				"groupName", "mail", "mob", "name", "post", "tel", "userID",
				"userSort", "userVcard", "status","domain" };
		/**
		 * 数据表id
		 */
		public static String ID = "_id";
		/**
		 * 用户的传�?
		 */
		public static String FAX = "fax";
		/**
		 * �?��组的描述
		 */
		public static String GROUPDESCRIPTION = "groupDescription";
		/**
		 * �?���?
		 */
		public static String GROUPNAME = "groupName";
		/**
		 * 用户的邮�?
		 */
		public static String MAIL = "mail";
		/**
		 * 用户的手机号
		 */
		public static String MOB = "mob";
		/**
		 * 用户的姓�?
		 */
		public static String NAME = "name";
		/**
		 * 用户的职�?
		 */
		public static String POST = "post";
		/**
		 * 用户电话
		 */
		public static String TEL = "tel";
		/**
		 * 用户jid
		 */
		public static String USERID = "userID";
		/**
		 * 用户排序字段
		 */
		public static String USERSORT = "userSort";
		/**
		 * 备用字段
		 */
		public static String USERVCARD = "userVcard";
		/**
		 * 用户在线状�?
		 */
		public static String STATUS = "status";
		/**
		 * 用户的域的信�?
		 */
		public static String DOMAIN="domain";
	}

	/**
	 * 
	 * @ClassName: ChatUserColumnName
	 * @Description: 用户信息唯一标识
	 * @author 机器人编�?
	 * @date 2015�?0�?2�?下午2:13:41
	 * 
	 */
	public static class ChatUserColumnName {
		/**
		 * 区别手机唯一消息的数据库
		 */
		public static String ChatUserTableName = "userchat";
		public static String[] lanname = { "_id", "user_id" };
		/**
		 * 数据表id
		 */
		public static String ID = "_id";
		/**
		 * 用户使用的唯�?d
		 */
		public static final String USERID = "user_id";
	}

	/**
	 * 
	 * @ClassName: ChatsColumnName
	 * @Description: 单人聊天的数据表
	 * @author 机器人编�?
	 * @date 2015�?0�?2�?下午2:57:24
	 * 
	 */
	public static class ChatsColumnName {
		/**
		 * 单人聊天记录的数据表
		 */
		public static String ChatsTableName = "chats";
		public static String[] lanname = { "_id", "date", "from_me", "jid",
				"message", "read", "pid", "user_id" };
		/**
		 * 数据表id
		 */
		public static String ID = "_id";
		/**
		 * 聊天的时�?
		 */
		public static final String DATE = "date";
		/**
		 * 标识是自己发送还是别人发�?代表自己发的消息,0代表别人发�?的消�?
		 */
		public static final String DIRECTION = "from_me";
		/**
		 * 发�?者的jid
		 */
		public static final String JID = "jid";
		/**
		 * 发�?者发送的内容
		 */
		public static final String MESSAGE = "message";
		/**
		 * 消息的阅读状�?代表新的消息,1代表已阅读的消息
		 */
		public static final String DELIVERY_STATUS = "read";
		/**
		 * 消息的唯�?���?
		 */
		public static final String PACKET_ID = "pid";
		/**
		 * 数据表标识唯�?��户的标识�?
		 */
		public static final String USERID = "user_id";
	}

	/**
	 * 
	 * @ClassName: SaveContactsColumnName
	 * @Description: 常用联系�?
	 * @author 机器人编�?
	 * @date 2015�?0�?2�?下午3:39:29
	 * 
	 */
	public static class SaveContactsColumnName {
		/**
		 * 常用联系�?
		 */
		public static String SaveContactsTableName = "im_common_contacter";
		public static String[] lanname = { "_id", "jid", "name", "login_jid" };
		/**
		 * 数据表id
		 */
		public static String ID = "_id";
		/**
		 * 当前常用联系人的jid
		 */
		public static String JID = "jid";
		/**
		 * 当前常用联系人的名字
		 */
		public static String NAME = "name";
		/**
		 * 当前登陆人得jid
		 */
		public static String LOGIN_JID = "login_jid";
	}

	/**
	 * 
	 * @ClassName: StaffColumnName
	 * @Description: 应�?通部门中的人员表
	 * @author 机器人编�?
	 * @date 2016�?�?4�?下午7:32:23
	 * 
	 */
	public static class StaffColumnName {
		/**
		 * 应�?通部门中的人员表�?
		 */
		public static String StaffTableName = "staff";
		public static String[] lanname = { "_id", "userId", "groupid", "name",
				"post", "tel", "mob", "fax", "mail", "indexes", "isyj", "iszw" };
		public static String ID = "_id";
		public static String USERID = "userid";//
		public static String GROUPID = "groupid";//
		public static String NAME = "name";
		public static String POST = "post";
		public static String TEL = "tel";
		public static String MOB = "mob";
		public static String FAX = "fax";
		public static String MAIL = "mail";
		public static String INDEXES = "indexes";
		public static String ISYJ = "isyj";// 1应�?
		public static String ISZW = "iszw";// 1政务
	}

	/**
	 * 
	 * @ClassName: OrgBean
	 * @Description: 部门的组织结构表
	 * @author 机器人编�?
	 * @date 2016�?�?4�?下午7:43:38
	 * 
	 */
	public static class OrgBean {
		/**
		 * 应�?通部门中的结构表�?
		 */
		public static String OrgBeanTableName = "orgbean";
		public static String[] lanname = { "_id", "addorgid", "orgtreeid",
				"porgtreeid", "treepointyjname", "treepointzwname", "sort" };
		public static String ID = "_id";
		public static String ADDORGID = "addorgid"; // person_PID
		public static String ORGTREEID = "orgtreeid"; // id
		public static String PORGTREEID = "porgtreeid"; // pId
		public static String TREEPOINTYJNAME = "treepointyjname"; // 在应急的名称
		public static String TREEPOINTZWNAME = "treepointzwname"; // 在政务的名称
		public static String SORT = "sort";
	}
}
