package com.itpersion.myimclient.activity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketIDFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smackx.Form;
import org.jivesoftware.smackx.FormField;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smackx.packet.MUCAdmin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itpersion.myimclient.R;
import com.itpersion.myimclient.adapter.AddressBookAdapter;
import com.itpersion.myimclient.asmack.XmppConnection;
import com.itpersion.myimclient.imp.NetWorkStatusCallback;
import com.lidroid.xutils.db.annotation.Id;

/**
 * 通讯录
 * 
 * @author 机器人编写
 * 
 */
public class AddressBookActivity extends Activity implements
		NetWorkStatusCallback {
	private RelativeLayout rl_addressbook_top;
	private TextView title_name_tv;
	private ExpandableListView elv_addressbook_content;
	private MultiUserChat muc;
	private XMPPConnection connection;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addressbook);
		initView();
	try {
		  
		//Message.Type.groupchat
			muc.revokeMembership("");
		   muc = new MultiUserChat(XmppConnection.getConnection(),
				"");
			muc.kickParticipant("", "");
			
			Message newMessage = new Message("", Message.Type.normal);
			// XMPPConnection.login("", "", "");
			connection = new XMPPConnection("");
			muc.grantMembership("xuhj@www.taiji.com.cn");
			muc.invite("xuhj@www.taiji.com.cn", "helloworld");
			muc.join("");
			muc.isJoined();
			muc.getAdmins();
			// muc.banUsers(jids);
			// muc.grantVoice(nickname);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化组件。
	 */
	@SuppressLint("NewApi")
	private void initView() {

		rl_addressbook_top = (RelativeLayout) findViewById(R.id.rl_addressbook_top);
		title_name_tv = (TextView) rl_addressbook_top
				.findViewById(R.id.title_name_tv);
		title_name_tv.setText("联系人");
		elv_addressbook_content = (ExpandableListView) findViewById(R.id.elv_addressbook_content);
		elv_addressbook_content.setAdapter(new AddressBookAdapter(
				getApplicationContext()));
		elv_addressbook_content.setGroupIndicator(null);
		elv_addressbook_content
				.setOnGroupClickListener(new OnGroupClickListener() {

					@Override
					public boolean onGroupClick(ExpandableListView parent,
							View v, int groupPosition, long id) {
						if (parent.isGroupExpanded(groupPosition)) {
							parent.collapseGroup(groupPosition);
						} else {
							parent.expandGroup(groupPosition, false);
						}
						return true;
					}
				});
		elv_addressbook_content.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {

			}
		});

	}

	@Override
	public void NetWork_conn_Error() {

	}

	@Override
	public void NetWork_conn_Sucess() {

	}

	private void send(String jid, String affiliation, String reason) {
		MUCAdmin iq = new MUCAdmin();
		iq.setTo("groupname");
		iq.setType(IQ.Type.SET);
		// Set the new affiliation.
		MUCAdmin.Item item = new MUCAdmin.Item("none", "none");
		item.setJid(jid);
		if (reason != null)
			item.setReason(reason);
		iq.addItem(item);

		// Wait for a response packet back from the server.
		PacketFilter responseFilter = new PacketIDFilter(iq.getPacketID());
		PacketCollector response = connection
				.createPacketCollector(responseFilter);
		// Send the change request to the server.
		connection.sendPacket(iq);
		// Wait up to a certain number of seconds for a reply.
		IQ answer = (IQ) response.nextResult(SmackConfiguration
				.getPacketReplyTimeout());
		// Stop queuing results
		response.cancel();

		if (answer == null) {
			//throw new XMPPException("No response from server.");
		} else if (answer.getError() != null) {
			//throw new XMPPException(answer.getError());
		}
	}
}
