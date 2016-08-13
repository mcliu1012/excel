package excel;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.StringUtils;

import com.excel.util.ExcelUtil;
import com.excel.web.controller.BaseActionController;
import com.excel.web.entity.Device;

public class Test extends BaseActionController {
	
	public static void main(String[] args) {
//      String configFilePath = "E:\\workspace\\excel\\src\\main\\resources" + File.separator + "template" + File.separator + "config" + File.separator + "student.xml";
//    String srcReadFilePath = "E:\\workspace\\excel\\src\\main\\resources" + File.separator + "template" + File.separator + "temp" + File.separator + "学生信息.xls";
//    
//    Map<String, Object> beanParams = new HashMap<String, Object>();
//    List<Student> students = new ArrayList<Student>();
//    beanParams.put("students", students);
//    readExcel(configFilePath, beanParams, srcReadFilePath);
//    
//    System.out.println("ID\t  name\t  subject\t  score");
//    for(Student stu:students){
//        System.out.println(stu.getId()+"\t  "+stu.getName()+"\t  "+stu.getSubject()+"\t  "+stu.getScore());
//    }
//      String configFilePath = "E:\\workspace\\excel\\src\\main\\resources" + File.separator + "template" + File.separator + "config" + File.separator + "device.xml";
//      String srcReadFilePath = "E:\\workspace\\excel\\src\\main\\resources" + File.separator + "template" + File.separator + "temp" + File.separator + "test.xlsx";
    String configFilePath = "F:\\gitRepository\\excel\\src\\main\\resources" + File.separator + "template" + File.separator + "config" + File.separator + "device.xml";
    String srcReadFilePath = "F:\\gitRepository\\excel\\src\\main\\resources" + File.separator + "template" + File.separator + "temp" + File.separator + "test.xlsx";
      
      Map<String, Object> beanParams = new HashMap<String, Object>();
      List<Device> devices = new ArrayList<Device>();
      beanParams.put("devices", devices);
      ExcelUtil.readExcel(configFilePath, beanParams, srcReadFilePath);
      
      for(Device dev:devices){
          if (!StringUtils.isEmpty(dev.getDevNo()) ) {
              System.out.println(dev.getDevNo()+"\t  "+dev.getTypeStr()+"\t  "+dev.getModel()+"\t  "+dev.getCabinetNo()+"\t  "+dev.getFactoryNo()+"\t  "+dev.getFactoryTimeStr());
          }
      }
      
      
      
      
//    devices = initDevices();
      
      // 进行分组
      Map<String ,List<Device>> map = group(devices, new GroupBy<String>() {
          @Override
          public String groupby(Object obj) {
              Device d = (Device)obj ;
              return d.getDevNo() ;   // 分组依据为课程ID
          }
      }) ;
      
      System.out.println("--------------------------");
      for (Map.Entry<String ,List<Device>> entry : map.entrySet()) {  
        
          System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
          
          if ("777".equals(entry.getKey())) {
              List<Device> finalDevices = entry.getValue();
              
              // 按货柜号升序排序
              Collections.sort(finalDevices, new Comparator<Device>() {
                  public int compare(Device arg0, Device arg1) {
                      return arg0.getCabinetNo().compareTo(arg1.getCabinetNo());
                  }
              });
              // 按型号进行分组
              Map<String ,List<Device>> modelMap = group(finalDevices, new GroupBy<String>() {
                  @Override
                  public String groupby(Object obj) {
                      Device d = (Device)obj ;
                      return d.getModel() ;   // 分组依据为型号
                  }
              });
              
              for (Map.Entry<String ,List<Device>> modelEntry : modelMap.entrySet()) {  
                  System.out.println("Key = " + modelEntry.getKey() + ", Value = " + modelEntry.getValue());  
              }
              
              // 基础校验
              Set<Device> set = new HashSet<Device>();
              for(Device dev : finalDevices)
                   set.add(dev);
              
              
              
              System.out.println(set);
              
              for (Device device : set) {
                  System.out.println(device);
              }
              
              
          }
        
      }
      
      
      
      
      
      
      
      
      
      
      
      
      // 构造初始化数据
//    List<Device> finalDevices = new ArrayList<Device>();
//    List<Cabinet> finalCabinets = new ArrayList<Cabinet>();
//    List<DeviceAisle> finalDeviceAisles = new ArrayList<DeviceAisle>();
//    for (Device dev : devices) {
//        if (StringUtils.isEmpty(dev.getDevNo()) || StringUtils.isEmpty(dev.getModel()) || StringUtils.isEmpty(dev.getCabinetNo())
//                || StringUtils.isEmpty(dev.getFactoryNo()) || StringUtils.isEmpty(dev.getFactoryTimeStr()))
//            throw new Exception("数据不完整，请上传完整的文件。");
//        
//        // 校验设备编号是否已存在
////          Device dbDevice = findDeviceByDevNo(dev.getDevNo());
////          if (null != dbDevice)
////              throw new Exception("导入失败，设备编号【"+ dev.getDevNo() +"】已存在");
//        
//        // 构造设备信息
//        Device finalDevice = createDevice(dev);
//        
//        Cabinet finalCabinet = new Cabinet();
//        finalCabinet.setCabinetNo(dev.getCabinetNo());
//        finalCabinet.setAisleCount(getAisleCountByModel(dev.getModel()));
//        finalCabinet.setModel(dev.getModel());
//        finalCabinet.setFactoryNo(dev.getFactoryNo());
//        finalCabinet.setFactoryTime(new Timestamp(DateUtil.getDate(dev.getFactoryTimeStr()).getTime()));
//        finalCabinet.setCreateUser(1L);
//        finalCabinet.setCreateTime(new Timestamp(System.currentTimeMillis()));
//        finalDevice.addCainets(finalCabinet);
//        
//        
//        finalDevices.add(finalDevice);
//    }
	}
	
	
	/**
     * 构建初始化设备信息
     * @param dev
     * @param user
     * @return
     */
    public static Device createDevice(Device dev) {
        Device finalDevice = new Device();
        finalDevice.setDevNo(dev.getDevNo());
        finalDevice.setNatrue(0);// 自营
        finalDevice.setState(0);
        finalDevice.setOrgId(1L);
        finalDevice.setCreateUser(1L);
        finalDevice.setType(getDeviceTypeByModel(dev.getModel()));
        finalDevice.setPointId(0L);
        return finalDevice;
    }
    
    /**
     * 根据货柜型号取到设备类型
     * @param model
     * @return
     */
    public static Integer getDeviceTypeByModel(String model) {
        switch(model) {
            case "CVM-PC21PC42":
                return 1;// 饮料机
            case "CVM-PC12PC42":
                return 2;// 小型饮料机
            case "CVM-KZGPC23.6":
                return 3;// 中控机
            case "CVM-FD48WXT":
                return 4;// 弹簧机
            case "CVM-SPG64":
                return 5;// 64门格子柜
            case "CVM-SPG40":
                return 6;// 40门格子柜
            default:
                return -1;
        }
    }

    public static Integer getAisleCountByModel(String model) {
        switch(model) {
            case "CVM-PC21PC42":
                return 10;// 饮料机
            case "CVM-PC12PC42":
                return 21;// 小型饮料机
            case "CVM-KZGPC23.6":
                return 0;// 中控机
            case "CVM-FD48WXT":
                return 48;// 弹簧机
            case "CVM-SPG64":
                return 64;// 64门格子柜
            case "CVM-SPG40":
                return 40;// 40门格子柜
            default:
                return -1;
        }
    }
    
    public static List<Device> initDevices() {
        List<Device> devices = new ArrayList<Device>();
        Device device1 = new Device();
        device1.setDevNo("111");
        device1.setTypeStr("智能饮料机（黑色定制）");
        device1.setModel("CVM-PC21PC42");
        device1.setCabinetNo("1");
        device1.setFactoryNo("AAA");
        device1.setFactoryTimeStr("2016-07-08");
        devices.add(device1);

        Device device2 = new Device();
        device1.setDevNo("222");
        device1.setTypeStr("小型智能饮料机（黑色定制）");
        device1.setModel("CVM-PC12PC42");
        device1.setCabinetNo("1");
        device1.setFactoryNo("BBB");
        device1.setFactoryTimeStr("2016-07-08");
        devices.add(device2);
        
        Device device3 = new Device();
        device1.setDevNo("333");
        device1.setTypeStr("智能中控柜（黑色）");
        device1.setModel("CVM-KZGPC23.6");
        device1.setCabinetNo("1");
        device1.setFactoryNo("CCC");
        device1.setFactoryTimeStr("2016-07-08");
        devices.add(device3);
        
        Device device4 = new Device();
        device1.setDevNo("444");
        device1.setTypeStr("综合机辅机（黑色）");
        device1.setModel("CVM-FD48WXT");
        device1.setCabinetNo("1");
        device1.setFactoryNo("DDD");
        device1.setFactoryTimeStr("2016-07-08");
        devices.add(device4);
        
        Device device5 = new Device();
        device1.setDevNo("555");
        device1.setTypeStr("64门商品柜");
        device1.setModel("CVM-SPG64");
        device1.setCabinetNo("1");
        device1.setFactoryNo("EEE");
        device1.setFactoryTimeStr("2016-07-08");
        devices.add(device5);
        
        Device device6 = new Device();
        device1.setDevNo("666");
        device1.setTypeStr("40门商品柜");
        device1.setModel("CVM-SPG40");
        device1.setCabinetNo("1");
        device1.setFactoryNo("FFF");
        device1.setFactoryTimeStr("2016-07-08");
        devices.add(device6);
        
        Device device7 = new Device();
        device1.setDevNo("777");
        device1.setTypeStr("智能中控柜（黑色）");
        device1.setModel("CVM-KZGPC23.6");
        device1.setCabinetNo("1");
        device1.setFactoryNo("GGG");
        device1.setFactoryTimeStr("2016-07-08");
        devices.add(device7);
        
        Device device8 = new Device();
        device1.setDevNo("777");
        device1.setTypeStr("综合机辅机（黑色）");
        device1.setModel("CVM-FD48WXT");
        device1.setCabinetNo("2");
        device1.setFactoryNo("HHH");
        device1.setFactoryTimeStr("2016-07-08");
        devices.add(device8);
        
        Device device9 = new Device();
        device1.setDevNo("777");
        device1.setTypeStr("64门商品柜");
        device1.setModel("CVM-SPG64");
        device1.setCabinetNo("2");
        device1.setFactoryNo("III");
        device1.setFactoryTimeStr("2016-07-08");
        devices.add(device9);

        Device device10 = new Device();
        device1.setDevNo("777");
        device1.setTypeStr("综合机辅机（黑色）");
        device1.setModel("CVM-FD48WXT");
        device1.setCabinetNo("4");
        device1.setFactoryNo("JJJ");
        device1.setFactoryTimeStr("2016-07-08");
        devices.add(device10);
        
        Device device11 = new Device();
        device1.setDevNo("777");
        device1.setTypeStr("64门商品柜");
        device1.setModel("CVM-SPG64");
        device1.setCabinetNo("5");
        device1.setFactoryNo("KKK");
        device1.setFactoryTimeStr("2016-07-08");
        devices.add(device11);
        
        Device device12 = new Device();
        device1.setDevNo("888");
        device1.setTypeStr("智能饮料机（黑色定制）");
        device1.setModel("CVM-PC21PC42");
        device1.setCabinetNo("1");
        device1.setFactoryNo("LLL");
        device1.setFactoryTimeStr("2016-07-08");
        devices.add(device12);
        
        Device device13 = new Device();
        device1.setDevNo("999");
        device1.setTypeStr("小型智能饮料机（黑色定制）");
        device1.setModel("CVM-PC12PC42");
        device1.setCabinetNo("1");
        device1.setFactoryNo("MMM");
        device1.setFactoryTimeStr("2016-07-08");
        devices.add(device13);
        
        Device device14 = new Device();
        device1.setDevNo("999");
        device1.setTypeStr("40门商品柜");
        device1.setModel("CVM-SPG40");
        device1.setCabinetNo("2");
        device1.setFactoryNo("NNN");
        device1.setFactoryTimeStr("2016-07-08");
        devices.add(device14);

        Device device15 = new Device();
        device1.setDevNo("999");
        device1.setTypeStr("40门商品柜");
        device1.setModel("CVM-SPG40");
        device1.setCabinetNo("3");
        device1.setFactoryNo("OOO");
        device1.setFactoryTimeStr("2016-07-08");
        devices.add(device15);
        
        Device device16 = new Device();
        device1.setDevNo("999");
        device1.setTypeStr("64门商品柜");
        device1.setModel("CVM-SPG64");
        device1.setCabinetNo("4");
        device1.setFactoryNo("PPP");
        device1.setFactoryTimeStr("2016-07-08");
        devices.add(device16);
        
        return devices;
    }
    
    /**
     * 分組依據接口，用于集合分組時，獲取分組依據
     * @author  ZhangLiKun
     * @title   GroupBy
     * @date    2013-4-23
     */
    public interface GroupBy<T> {
        T groupby(Object obj) ;
    }
    
    /**
     * 
     * @param colls
     * @param gb
     * @return
     */
    public static final <T extends Comparable<T> ,D> Map<T ,List<D>> group(Collection<D> colls ,GroupBy<T> gb){
        if(colls == null || colls.isEmpty()) {
            System.out.println("分組集合不能為空!");
            return null ;
        }
        if(gb == null) {
            System.out.println("分組依據接口不能為Null!");
            return null ;
        }
        Iterator<D> iter = colls.iterator() ;
        Map<T ,List<D>> map = new HashMap<T, List<D>>() ;
        while(iter.hasNext()) {
            D d = iter.next() ;
            T t = gb.groupby(d) ;
            if(map.containsKey(t)) {
                map.get(t).add(d) ;
            } else {
                List<D> list = new ArrayList<D>() ;
                list.add(d) ;
                map.put(t, list) ;
            }
        }
        return map ;
    }
	
	
	
}
