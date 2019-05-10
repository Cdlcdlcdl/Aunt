package entity;

import java.sql.Timestamp;


/**
 * Ordering entity. @author MyEclipse Persistence Tools
 */

public class Ordering implements java.io.Serializable {

	// Fields

	private String orderId;
	private Worker worker;
	private User user;
	private Servicetype servicetype;
	private String orderName;
	private String address;
	private Double longitude;
	private Double latitude;

	public void setPredictTime(String predictTime) {
		this.predictTime = predictTime;
	}

	private String predictTime;
	private Timestamp startTime;
	private Timestamp addTime;
	private Timestamp modifyTime;

	// Constructors

	/** default constructor */
	public Ordering() {
	}

	public Ordering(Servicetype servicetype, String predictTime,Worker worker, User user){
		this.servicetype = servicetype;
		this.predictTime = predictTime;
		this.worker = worker;
		this.user = user;
	}

	/** minimal constructor */
	public Ordering(Worker worker, User user, Servicetype servicetype,
			String address, Double longitude, Double latitude,
			String predictTime, Timestamp startTime, Timestamp modifyTime) {
		this.worker = worker;
		this.user = user;
		this.servicetype = servicetype;
		this.address = address;
		this.longitude = longitude;
		this.latitude = latitude;
		this.predictTime = predictTime;
		this.startTime = startTime;
		this.modifyTime = modifyTime;
	}

	/** full constructor */
	public Ordering(Worker worker, User user, Servicetype servicetype,
			String orderName, String address, Double longitude,
			Double latitude, String predictTime, Timestamp startTime,
			Timestamp addTime, Timestamp modifyTime) {
		this.worker = worker;
		this.user = user;
		this.servicetype = servicetype;
		this.orderName = orderName;
		this.address = address;
		this.longitude = longitude;
		this.latitude = latitude;
		this.predictTime = predictTime;
		this.startTime = startTime;
		this.addTime = addTime;
		this.modifyTime = modifyTime;
	}

	// Property accessors

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public Worker getWorker() {
		return this.worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}


	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public Servicetype getServicetype() {
		return this.servicetype;
	}

	public void setServicetype(Servicetype servicetype) {
		this.servicetype = servicetype;
	}


	public String getOrderName() {
		return this.orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}


	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}


	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}


	public String getPredictTime() {
		return this.predictTime;
	}



	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}


	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}


	public Timestamp getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

}