package com.excel.web.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 设备
 * 
 * @author zhaoss on 2016年12月1日
 */
public class Device implements Serializable {

	private Long id;
	/**
	 * 设备编号
	 */
	private String devNo;
	/**
	 * 设备地址
	 */
	private String address;
	/**
	 * 设备性质
	 */
	private Integer natrue;
	/**
	 * 设备状态
	 */
	private Integer state;
	/**
	 * 所属机构
	 */
	private Long orgId;

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
	 * 所属人名称
	 */
	private String orgName;
	/**
	 * 销售额
	 */
	private Double salePrice;
	
	/**
	 * 销售数量
	 * */
	private Long saleNumber;
	
	/**
	 * 设备类型  1：饮料机  2：弹簧机
	 */
	private Integer type;

	/**
	 * 点位ID
	 */
	private Long pointId;
	
	private List<DeviceAisle> deviceAisles;

	/**
	 * 点位地址
	 */
	private String pointAddress;
	
	/**
	 * 型号
	 */
	private String model;
	
	/**
	 * 出厂编号
	 */
	private String factoryNo;
	
	/**
	 * 设备类型，excel导入用
	 */
	private String typeStr;

	/**
	 * 出厂日期，excel导入用
	 */
	private String factoryTimeStr;

	/**
	 * 货柜编号，excel导入用
	 */
	private String cabinetNo;
	
	private List<Cabinet> cabinets;
	
	public void addCainets(Cabinet cabinet) {
	    if (this.cabinets == null)
            this.cabinets = new ArrayList<Cabinet>();
	    this.cabinets.add(cabinet);
	} 

	public List<DeviceAisle> getDeviceAisles() {
		return deviceAisles;
	}

	public void setDeviceAisles(List<DeviceAisle> deviceAisles) {
		this.deviceAisles = deviceAisles;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public String getDevNo() {
		return devNo;
	}

	public void setDevNo(String devNo) {
		this.devNo = devNo;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getNatrue() {
		return natrue;
	}

	public void setNatrue(Integer natrue) {
		this.natrue = natrue;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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

	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}
	

	/**
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * @param orgName
	 *            the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	
	/**
	 * @return the saleNumber
	 */
	public Long getSaleNumber() {
		return saleNumber;
	}

	/**
	 * @param saleNumber the saleNumber to set
	 */
	public void setSaleNumber(Long saleNumber) {
		this.saleNumber = saleNumber;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getPointId() {
		return pointId;
	}

	public void setPointId(Long pointId) {
		this.pointId = pointId;
	}
	
	public void initDefaultValue() {
		if (null == this.pointId)
			this.pointId = 0L;
	}

	public String getPointAddress() {
		return pointAddress;
	}

	public void setPointAddress(String pointAddress) {
		this.pointAddress = pointAddress;
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

	public String getTypeStr() {
		return typeStr;
	}

	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}

	public String getFactoryTimeStr() {
		return factoryTimeStr;
	}

	public void setFactoryTimeStr(String factoryTimeStr) {
		this.factoryTimeStr = factoryTimeStr;
	}

	public String getCabinetNo() {
		return cabinetNo;
	}

	public void setCabinetNo(String cabinetNo) {
		this.cabinetNo = cabinetNo;
	}

	public List<Cabinet> getCabinets() {
        return cabinets;
    }

    public void setCabinets(List<Cabinet> cabinets) {
        this.cabinets = cabinets;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cabinetNo == null) ? 0 : cabinetNo.hashCode());
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
		Device other = (Device) obj;
		if (cabinetNo == null) {
			if (other.cabinetNo != null)
				return false;
		} else if (!cabinetNo.equals(other.cabinetNo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}