package com.manager.task.tasks;

public class User {

	private long id;
	private String mobileNumber;
	private String email;
	private int alertService, smsService, mailService, alarmService;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAlertService() {
		return alertService;
	}

	public void setAlertService(int alertService) {
		this.alertService = alertService;
	}

	public int getSmsService() {
		return smsService;
	}

	public void setSmsService(int smsService) {
		this.smsService = smsService;
	}

	public int getMailService() {
		return mailService;
	}

	public void setMailService(int mailService) {
		this.mailService = mailService;
	}

	public int getAlarmService() {
		return alarmService;
	}

	public void setAlarmService(int alarmService) {
		this.alarmService = alarmService;
	}

}
