package com.excel.web.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 设备货道
 * 
 * @author zhaoss on 2016年12月1日
 */
public class DeviceAisle implements Serializable {

	private Long id;
	/**
	 * 货道号
	 */
	private Integer aisleNum;
	/**
	 * 产品ID
	 */
	private Long productId;
	/**
	 * 零售价
	 */
	private Double price;
	/**
	 * 库存
	 */
	private Integer stock;
	/**
	 * 库存提醒
	 */
	private Integer stockRemind;
	/**
	 * 设备ID
	 */
	private Long deviceId;

	/**
	 * 货柜ID
	 */
	private Long cabinetId;
	/**
	 * 货道容量
	 */
	private Integer capacity;
	/**
	 * 销量
	 */
	private Integer sales;
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
	 * 商品名称
	 */
	private String productName;
	/**
	 * 商品编码
	 */
	private String productCode;
	/**
	 * 商品图片
	 */
	private String pic;
	/**
	 * 厂商
	 */
	private String firm;
	/**
	 * 商品图片
	 */
	private String images;
	/**
	 * 销售额
	 */
	private Double salesVolume;
	/**
	 * 类别
	 */
	private Integer plantType;
	/**
	 * 销售额
	 */
	private Double priceOne;
	/**
	 * 销售额
	 */
	private Double priceTwo;
	
	/**
	 * 应补货数量
	 */
	private Integer supplementNo;
	
	/**
	 * 设备地址
	 */
	private String address;

	/**
	 * 设备编号
	 */
	private String code;
	
	private Integer beverageMachine;

	private String cabinetNo;
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPriceOne() {
		return priceOne;
	}

	public void setPriceOne(Double priceOne) {
		this.priceOne = priceOne;
	}

	public Double getPriceTwo() {
		return priceTwo;
	}

	public void setPriceTwo(Double priceTwo) {
		this.priceTwo = priceTwo;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getFirm() {
		return firm;
	}

	public void setFirm(String firm) {
		this.firm = firm;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Double getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(Double salesVolume) {
		this.salesVolume = salesVolume;
	}

	public Integer getPlantType() {
		return plantType;
	}

	public void setPlantType(Integer plantType) {
		this.plantType = plantType;
	}

	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	public Integer getAisleNum() {
		return aisleNum;
	}

	public void setAisleNum(Integer aisleNum) {
		this.aisleNum = aisleNum;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getStockRemind() {
		return stockRemind;
	}

	public void setStockRemind(Integer stockRemind) {
		this.stockRemind = stockRemind;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
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
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName
	 *            the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the supplementNo
	 */
	public Integer getSupplementNo() {
		return supplementNo;
	}

	/**
	 * @param supplementNo the supplementNo to set
	 */
	public void setSupplementNo(Integer supplementNo) {
		this.supplementNo = supplementNo;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the beverageMachine
	 */
	public Integer getBeverageMachine() {
		return beverageMachine;
	}

	/**
	 * @param beverageMachine the beverageMachine to set
	 */
	public void setBeverageMachine(Integer beverageMachine) {
		this.beverageMachine = beverageMachine;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	public Long getCabinetId() {
		return cabinetId;
	}

	public void setCabinetId(Long cabinetId) {
		this.cabinetId = cabinetId;
	}

	public String getCabinetNo() {
		return cabinetNo;
	}

	public void setCabinetNo(String cabinetNo) {
		this.cabinetNo = cabinetNo;
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
		DeviceAisle other = (DeviceAisle) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}