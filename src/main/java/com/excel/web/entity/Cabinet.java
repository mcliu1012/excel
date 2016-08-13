package com.excel.web.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 货柜
 * 
 */
public class Cabinet implements Serializable {

	private Long id;
	/**
	 * 设备ID
	 */
	private Long deviceId;

	/**
	 * 货柜编号
	 */
	private String cabinetNo;

	/**
	 * 货柜类型
	 */
	private Integer type;
	
	
	/**
	 * 货道数量
	 */
	private Integer aisleCount;
	/**
	 * 创建人
	 */
	private Long createUser;
	/**
	 * 创建时间
	 */
	private Timestamp createTime;
	/**
	 * 修改人
	 */
	private Long updateUser;
	/**
	 * 修改时间
	 */
	private Timestamp updateTime;
	
	/**
	 * 型号
	 */
	private String model;
	
	/**
	 * 出厂编号
	 */
	private String factoryNo;
	
	/**
	 * 出厂日期
	 */
	private Timestamp factoryTime;
	
	private List<DeviceAisle> deviceAisles;
	
	public void addDeviceAisles(DeviceAisle deviceAisle) {
	    if (null == this.deviceAisles)
	        this.deviceAisles = new ArrayList<DeviceAisle>();
	    this.deviceAisles.add(deviceAisle);
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public String getCabinetNo() {
		return cabinetNo;
	}

	public void setCabinetNo(String cabinetNo) {
		this.cabinetNo = cabinetNo;
	}

	public Integer getAisleCount() {
		return aisleCount;
	}

	public void setAisleCount(Integer aisleCount) {
		this.aisleCount = aisleCount;
	}

	public Long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getFactoryNo() {
		return factoryNo;
	}

	public void setFactoryNo(String factoryNo) {
		this.factoryNo = factoryNo;
	}

	public Timestamp getFactoryTime() {
		return factoryTime;
	}

	public void setFactoryTime(Timestamp factoryTime) {
		this.factoryTime = factoryTime;
	}

	public List<DeviceAisle> getDeviceAisles() {
        return deviceAisles;
    }

    public void setDeviceAisles(List<DeviceAisle> deviceAisles) {
        this.deviceAisles = deviceAisles;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cabinet other = (Cabinet) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}