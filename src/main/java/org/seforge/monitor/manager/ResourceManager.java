package org.seforge.monitor.manager;

import java.io.IOException;
import java.util.List;

import org.seforge.monitor.domain.Resource;
import org.seforge.monitor.exception.NotMonitoredException;

public interface ResourceManager {
	public List<Resource> getAllVims();
	
	public List<Resource> getAllPhyms();
	
	public List<Resource> getPhymsByGroup(int groupId);
	
	public List<Resource> getAppServersByGroup(int groupId);
	
	public Integer addNewServer(String ip, String jmxUrl, String path, String serviceName, String groupId, String protoytpe) throws IOException, NotMonitoredException;
	
	public Integer addApache(String ip, String port, String path, String serviceName, String groupId, String prototype) throws IOException, NotMonitoredException;
	
	public void addApacheApp(String id, String dir, String appType);
	
	public Integer addIis(String ip, String groupId) throws IOException, NotMonitoredException;


	public void deleteServer(String id) throws NumberFormatException, IOException;	
	
}
