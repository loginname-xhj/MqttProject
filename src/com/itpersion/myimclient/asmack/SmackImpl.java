package com.itpersion.myimclient.asmack;

import java.util.ArrayList;
import java.util.Collection;

import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.RosterGroup;
import org.jivesoftware.smack.RosterListener;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Presence.Mode;
import org.jivesoftware.smack.provider.ProviderManager;
import org.jivesoftware.smack.sasl.SASLMechanism.Failure;
import org.jivesoftware.smack.sasl.SASLMechanism.Response;
import org.jivesoftware.smack.sasl.SASLMechanism.Success;
import org.jivesoftware.smackx.ServiceDiscoveryManager;
import org.jivesoftware.smackx.carbons.Carbon;
import org.jivesoftware.smackx.forward.Forwarded;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smackx.ping.provider.PingProvider;
import org.jivesoftware.smackx.provider.DelayInfoProvider;
import org.jivesoftware.smackx.provider.DiscoverInfoProvider;
import org.jivesoftware.smackx.receipts.DeliveryReceipt;
import org.jivesoftware.smackx.receipts.DeliveryReceiptRequest;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;

import com.itpersion.myimclient.app.ConfigApplication;
import com.itpersion.myimclient.config.Config;
import com.itpersion.myimclient.config.RosterColumnName;
import com.itpersion.myimclient.domain.Msg;
import com.itpersion.myimclient.imp.Smack;
import com.itpersion.myimclient.service.ClientConnService;

@SuppressWarnings("all")
public class SmackImpl implements Smack {

	// 客户端名称和类型。主要是向服务器登记，有点类似QQ显示iphone或者Android手机在线的功能
	public static final String XMPP_IDENTITY_NAME = "XMPP"; // 客户端名称
	public static final String XMPP_IDENTITY_TYPE = "phone"; // 客户端类型
	private static final int PACKET_TIMEOUT = 3000; // 超时时间
	public XMPPConnection myconn;
	// 联系人在线情况相关
	private Roster mRoster; // 联系人对象
	private RosterListener mRosterListener; // 联系人动态监听
	private ClientConnService myservice;
	private ContentResolver contentResolver;
	private ConfigApplication configapp;
	private PacketListener mPacketListener; // 消息动态监听
	static {

		registerSmackProviders();

	}

	// 做一些基本的配置
	static void registerSmackProviders() {
		ProviderManager pm = ProviderManager.getInstance();
		// add IQ handling
		pm.addIQProvider("query", "http://jabber.org/protocol/disco#info",
				new DiscoverInfoProvider());
		// add delayed delivery notifications
		pm.addExtensionProvider("delay", "urn:xmpp:delay",
				new DelayInfoProvider());
		pm.addExtensionProvider("x", "jabber:x:delay", new DelayInfoProvider());
		// add carbons and forwarding
		pm.addExtensionProvider("forwarded", Forwarded.NAMESPACE,
				new Forwarded.Provider());
		pm.addExtensionProvider("sent", Carbon.NAMESPACE, new Carbon.Provider());
		pm.addExtensionProvider("received", Carbon.NAMESPACE,
				new Carbon.Provider());
		// add delivery receipts
		pm.addExtensionProvider(DeliveryReceipt.ELEMENT,
				DeliveryReceipt.NAMESPACE, new DeliveryReceipt.Provider());
		pm.addExtensionProvider(DeliveryReceiptRequest.ELEMENT,
				DeliveryReceipt.NAMESPACE,
				new DeliveryReceiptRequest.Provider());
		// add XMPP Ping (XEP-0199)
		pm.addIQProvider("ping", "urn:xmpp:ping", new PingProvider());

		ServiceDiscoveryManager.setIdentityName(XMPP_IDENTITY_NAME);// 客户端名称
		ServiceDiscoveryManager.setIdentityType(XMPP_IDENTITY_TYPE);// 客户端类型
	}

	public SmackImpl(ClientConnService service) {
		this.myconn = XmppConnection.getConnection();
		this.myservice = service;
		this.contentResolver = myservice.getContentResolver();
		this.configapp = (ConfigApplication) myservice.getApplication();
	}

	@Override
	public boolean login(String account, String password, boolean isconn) {

		try {
			if (myconn == null) {
				// 网络连接没有建立。
				return false;
			}
			if (isconn) {
				// 断线重连标识符
			} else {
				if (myconn.isConnected()) {
					System.out.println("xmpp服务握手处于连接中！！");
				} else {
					System.out.println("xmpp服务握手断开了！！");
					myconn.connect();
				}
			}
			SmackConfiguration.setPacketReplyTimeout(PACKET_TIMEOUT);// 设置超时时间
			SmackConfiguration.setDefaultPingInterval(0);
			myconn.login(account, password, "XMPP");

			// 更新在线状态
			setStatusFromConfig();
		} catch (XMPPException e) {
			System.out.println("连接异常XMPPException:" + e.getMessage());
			myconn.disconnect();
			return false;
		}
		// 注册通讯录获取
		// 监听联系人动态变化
		//registerRosterListener();
		// 注册新的消息监听器。
		registerNewMessageListener();
		return true;
	}

	/**
	 * 获取新消息监听器
	 */
	private void registerNewMessageListener() {

		if (mPacketListener != null) {
			// 如果监听存在就删除监听
			myconn.removePacketListener(mPacketListener);
		}

		// 对消息进行过滤
		PacketTypeFilter filter = new PacketTypeFilter(Packet.class);
		mPacketListener = new PacketListener() {

			@Override
			public void processPacket(Packet packet) {
				 
				if (packet instanceof Message) {

					// 如果是消息类型
					Message msg = (Message) packet;
					System.out.println("接收到新消息" + msg.toXML());
					String chatMessage = msg.getBody();
					if (chatMessage != null) {
						//--
						Msg messs = Msg.analyseMsgBody(chatMessage);
						String msgMsg = messs.getMsg();
						// 收到的聊天信息。
						System.out.println("客户端发送:" + chatMessage);
						try {
							// 解析收到的消息
							// 解析成功是json字符串
							JSONObject json = new JSONObject(chatMessage);

							ContentValues values = new ContentValues();
							values.put("date", json.getString("date"));
							values.put("from_me", "0");
							values.put("jid", json.getString("sender"));
							values.put("message", json.getString("msg"));
							values.put("read", "0");
							values.put("pid", msg.getPacketID());
							values.put("user_jid", configapp.getUsername()
									+ "@www.taiji.com.cn");
							Uri uri = contentResolver.insert(
									Config.MyProvider.CHART_URI, values);
							System.out.println("插入的url地址是:" + uri.toString());
						} catch (JSONException e) {
							e.printStackTrace();
							// 解析不成功,不是json字符串
							System.out.println(chatMessage + "电脑发送的");
							return;
						}

					}

				}
				else{
					//Log.d("config", "其他类型:"+packet.toXML());
				}
			}
		};
		myconn.addPacketListener(mPacketListener, filter);
		// 对消息进行过滤
	}

	@Override
	public boolean logout() {
		if (myconn.isConnected()) {
			myconn.disconnect();
		}

		return false;
	}

	@Override
	public void setStatusFromConfig() {

		// Presence改变自己当前状态
		SharedPreferences sp = myservice.getSharedPreferences("config",
				Context.MODE_PRIVATE);
		boolean ishide = sp.getBoolean("hide_login", false);
		Presence presence = new Presence(Presence.Type.available);

		if (ishide) { // 隐身登陆
			System.out.println("隐身登陆");
			Mode mode = Mode.valueOf("xa");
			presence.setMode(mode);
			presence.setStatus("隐身");
			presence.setPriority(0);// 固定值
			myconn.sendPacket(presence);
		} else {
			System.out.println("在线登陆");
			// 在线登陆

			Mode mode = Mode.valueOf("available");
			presence.setMode(mode);
			presence.setStatus("在线");
			presence.setPriority(0);// 固定值
			myconn.sendPacket(presence);
		}

	}

	@Override
	public void sendMessage(String user, String message) {

	}

	@Override
	public void sendServerPing() {

	}

	@Override
	public String getNameForJID(String jid) {
		return null;
	}

	@Override
	public boolean isAuthenticated() {
		return false;
	}

	/**
	 * 初始化联系人。
	 */
	private void registerRosterListener() {

		mRoster = myconn.getRoster();
		mRosterListener = new RosterListener() {

			@Override
			public void presenceChanged(Presence presence) {
				System.out.println("初始化好友状态" + presence.toString());
				// 联系人状态改变，比如在线或离开、隐身之类
				// 当只有一个自己在线的时候不会触发该方法，如果有其他用户登录就会触发该方法。
				// 隐身，q我,忙碌，登录,都会触发此方法。
				String from = presence.getFrom();
				int index = from.lastIndexOf("/");
				String newfrom = from.substring(0, index);
				RosterEntry rosterEntry = mRoster.getEntry(newfrom);
				Collection<RosterGroup> groups = rosterEntry.getGroups();
				ContentValues values;
				for (RosterGroup rosterGroup : groups) {

					values = new ContentValues();
					values.put(RosterColumnName.JID, rosterEntry.getUser());
					//Log.d(Config.TAG, "用户在线状态:" + rosterEntry.getUser());
					values.put(RosterColumnName.Alias, rosterEntry.getName());
					System.out.println(rosterEntry.getName()
							+ presence.toString());
					values.put(RosterColumnName.Status_Mode, presence.getType()
							+ "");
				//	Log.d(Config.TAG, "用户在线状态:" + presence.getType() + "");
					values.put(RosterColumnName.Status_Message,
							presence.getStatus());
					values.put(RosterColumnName.Roster_Group,
							rosterGroup.getName());

					int code = contentResolver.update(
							Config.MyProvider.ROSTER_URI,
							values,
							RosterColumnName.JID + "=? and "
									+ RosterColumnName.Roster_Group + " =?",
							new String[] { rosterEntry.getUser(),
									rosterGroup.getName() });
					if (code == 0) {
						// 无此好友,将其添加到数据库里面。
						contentResolver.insert(Config.MyProvider.ROSTER_URI,
								values);
					}

				}

			}

			@Override
			// 匹配
			public void entriesUpdated(Collection<String> entries) {
			/*	// 一个人从组里面移除,或者被邀请进组里面去。
				// 先移除，后添加。
				ContentValues value = null;
				for (String entry : entries) {

					RosterEntry rosterEntry = mRoster.getEntry(entry);

					RosterEntry ros = mRoster.getEntry(rosterEntry.getUser());
					Collection<RosterGroup> groups = ros.getGroups();

					for (RosterGroup rosterGroup : groups) {
						value = new ContentValues();
						value.put("jid", rosterEntry.getUser());
						value.put("alias", rosterEntry.getName());
						value.put(RosterColumnName.Roster_Group,
								rosterGroup.getName());
						int code = contentResolver.delete(
								Config.MyProvider.ROSTER_URI,
								RosterColumnName.JID + " = ? and "
										+ RosterColumnName.Roster_Group
										+ " =? ",
								new String[] { rosterEntry.getUser(),
										rosterGroup.getName() });
						if (code == 0) {
							// 无此好友,添加好友到组。
							contentResolver.insert(
									Config.MyProvider.ROSTER_URI, value);
						}
					}

				}*/

			}

			@Override
			public void entriesDeleted(Collection<String> entries) {
			/*	if (0 != 0) {
					// 从组里面删除好友。
					for (String entry : entries) {
						int count = contentResolver.delete(
								Config.MyProvider.ROSTER_URI,
								RosterColumnName.JID + " = ?",
								new String[] { entry });

					}
				}*/
			}

			@Override
			// 匹配
			public void entriesAdded(Collection<String> entries) {
			/*	if (0 != 0) {
					ArrayList<ContentValues> list = new ArrayList<ContentValues>();
					ContentValues value = null;
					for (String entry : entries) {
						RosterEntry rosterEntry = mRoster.getEntry(entry);
						RosterEntry ros = mRoster.getEntry(rosterEntry
								.getUser());
						Collection<RosterGroup> groups = ros.getGroups();
						for (RosterGroup rosterGroup : groups) {
							Log.d(Config.TAG, rosterGroup.toString());

							value = new ContentValues();
							value.put("jid", rosterEntry.getUser());
							value.put("alias", rosterEntry.getName());
							value.put(RosterColumnName.Roster_Group,
									rosterGroup.getName());
							list.add(value);
						}

					}
					ContentValues[] cvs = new ContentValues[list.size()];
					int i = 0;
					for (ContentValues contentValues : list) {
						cvs[i++] = contentValues;
					}
					contentResolver.bulkInsert(Config.MyProvider.ROSTER_URI,
							cvs);
				}*/

			}
		};
		mRoster.addRosterListener(mRosterListener);
		// 将数据库好友清空。重新初始化。
		contentResolver.delete(Config.MyProvider.ROSTER_URI, null, null);
	}

	@Override
	public void stoplogin() {
		if (myconn.isConnected()) {// 首先判断是否还连接着服务器，需要先断开
			try {
				myconn.disconnect();
			} catch (Exception e) {
			}
		}
		if (mRosterListener != null) {
			mRoster.removeRosterListener(mRosterListener);
		}
		if (mPacketListener != null) {
			myconn.removePacketListener(mPacketListener);
		}
		XmppConnection.closeConnection();
	}
	/**

	 * @param body
	 * Json
	 */
	public static Msg analyseMsgBody(String jsonStr) {
		Msg msg = new Msg();
		
		try {
			JSONObject jsonObject = new JSONObject(jsonStr);
			msg.setUserid(jsonObject.getString(Msg.USERID));
			msg.setFrom(jsonObject.getString(Msg.FROM));
			msg.setMsg(jsonObject.getString(Msg.MSG_CONTENT));
			msg.setDate(jsonObject.getString(Msg.DATE));
			msg.setType(jsonObject.getString(Msg.MSG_TYPE));
			msg.setReceive(jsonObject.getString(Msg.RECEIVE_STAUTS));
			msg.setSender(jsonObject.getString(Msg.SENDER));
			msg.setReceiver(jsonObject.getString(Msg.RECEIVER));
			String type=jsonObject.getString(Msg.MSG_TYPE);
			if(!type.equals("normal")){
				msg.setTime(jsonObject.getString(Msg.TIME_REDIO));
				msg.setFilePath(jsonObject.getString(Msg.FIL_PAHT));
			}
			
		} catch (JSONException e1) {
			e1.printStackTrace();
		}finally{
			return msg;
		}
		
	}
}
