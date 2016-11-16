package com.itpersion.myimclient.domain;

import org.json.JSONException;
import org.json.JSONObject;



/**
 * 
 * @author tj
 *	消息封装�?
 */
 
public class Msg {
	String userid;
	String msg;
	String date;
	String from;
	String type  =TYPE[2];
	String receive;//
	String time;//
	String filePath;
	String sender;//消息发�?�?
	String receiver;//消息接受者（群或者对方的jid�?
	
	public static final String USERID ="userid";
	public static final String MSG_CONTENT ="msg";//
	public static final String DATE ="date";
	public static final String FROM ="from";
	public static final String MSG_TYPE ="type";
	public static final String RECEIVE_STAUTS="receive";//̬  
	public static final String TIME_REDIO="time";
	public static final String FIL_PAHT="filePath";
	public static final String SENDER="sender";
	public static final String RECEIVER="receiver";
	
	public static final String[] STATUS={"success","refused","fail","wait"};
	public static final String[] TYPE= {"record","photo","normal","picture","mix","video"};
	public static final String[] FROM_TYPE= {"IN","OUT"};

	public Msg(){}
	public Msg(String userid, String msg, String date, String from, String sender,
			String receiver) {
		this.userid = userid;
		this.msg = msg;
		this.date = date;
		this.from = from;
		this.sender = sender;
		this.receiver = receiver;
	}
	

//	public Msg(String userid, String msg, String date, String from,
//			String type, String receive) {
//		super();
//		this.userid = userid;
//		this.msg = msg;
//		this.date = date;
//		this.from = from;
//		this.type = type;
//		this.receive = receive;
//	}

	public Msg(String userid, String msg, String date, String from,
			String type, String receive, String sender,
			String receiver) {
		super();
		this.userid = userid;
		this.msg = msg;
		this.date = date;
		this.from = from;
		this.type = type;
		this.receive = receive;
		this.sender = sender;
		this.receiver = receiver;
	}
	public Msg(String userid, String msg, String date, String from,
			String type, String receive, String time, String filePath,
			String sender, String receiver) {
		super();
		this.userid = userid;
		this.msg = msg;
		this.date = date;
		this.from = from;
		this.type = type;
		this.receive = receive;
		this.time = time;
		this.filePath = filePath;
		this.sender = sender;
		this.receiver = receiver;
	}
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReceive() {
		return receive;
	}

	public void setReceive(String receive) {
		this.receive = receive;
	}

	public static String[] getStatus() {
		return STATUS;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	@Override
	public String toString() {
		return "{userid:" + userid + ", msg:" + msg + ", date:" + date
				+ ", from:" + from + ", type:" + type + ", receive:" + receive
				+ ", time:" + time + ", filePath:" + filePath + ", sender:"
				+ sender + ", receiver:" + receiver + "}";
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
			//值守应�?系统
			//String zsyjsys= StringUtil.getJidByName("zsyjsys");
			
			
		/*	if(jsonObject.getString(Msg.RECEIVER).equals(zsyjsys)){
				FinalHttp fh = new FinalHttp();
				fh.addHeader("Accept-Charset", "UTF-8");
				AjaxParams params = new AjaxParams();
				params.put("msg",msg.toJson(msg));
				fh.post(Constant.ZSYJ, params, new AjaxCallBack<String>() {
					@Override
					public void onLoading(long count, long current) {
						super.onLoading(count, current);
					}
					@Override
					public void onSuccess(String t) {
						super.onSuccess(t);
						if(t!=null){
						}
					}
				});
			}*/
			
		} catch (JSONException e1) {
			e1.printStackTrace();
		}finally{
			return msg;
		}
		
	}
	
	
	/**
	 * ��json 
	 */
	public static  String  toJson(Msg msg){
		JSONObject jsonObject=new JSONObject();
		String jsonStr="";
		try {
			jsonObject.put(Msg.USERID, msg.getUserid()+"");
			jsonObject.put(Msg.MSG_CONTENT, msg.getMsg()+"");
			jsonObject.put(Msg.DATE, msg.getDate()+"");
			jsonObject.put(Msg.FROM, msg.getFrom()+"");
			jsonObject.put(Msg.MSG_TYPE, msg.getType()+"");
			jsonObject.put(Msg.RECEIVE_STAUTS, msg.getReceive()+"");
			jsonObject.put(Msg.TIME_REDIO, msg.getTime());
			jsonObject.put(Msg.FIL_PAHT, msg.getFilePath());
			jsonObject.put(Msg.SENDER, msg.getSender());
			jsonObject.put(Msg.RECEIVER, msg.getReceiver());
			jsonStr= jsonObject.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			return jsonStr;
		}
	}
	
}