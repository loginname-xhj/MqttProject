package com.itpersion.myimclient.domain;

import android.graphics.drawable.Drawable;

public class MyDefaultMessage {
	public Drawable message_icon;
	public String message_text;
	public String message_time;
	public String message_unread;
    public String message_fromer;
    
	public Drawable getMessage_icon() {
		return message_icon;
	}

	public void setMessage_icon(Drawable message_icon) {
		this.message_icon = message_icon;
	}


	public String getMessage_text() {
		return message_text;
	}

	public void setMessage_text(String message_text) {
		this.message_text = message_text;
	}

	public String getMessage_time() {
		return message_time;
	}

	public void setMessage_time(String message_time) {
		this.message_time = message_time;
	}

	public String getMessage_unread() {
		return message_unread;
	}

	public void setMessage_unread(String message_unread) {
		this.message_unread = message_unread;
	}

	public String getMessage_fromer() {
		return message_fromer;
	}

	public void setMessage_fromer(String message_fromer) {
		this.message_fromer = message_fromer;
	}

	@Override
	public String toString() {
		return "MyDefaultMessage [message_icon=" + message_icon
				+ ", message_text=" + message_text + ", message_time="
				+ message_time + ", message_unread=" + message_unread
				+ ", message_fromer=" + message_fromer + "]";
	}

}
