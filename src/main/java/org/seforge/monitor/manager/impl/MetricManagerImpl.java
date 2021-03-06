package org.seforge.monitor.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seforge.monitor.domain.Metric;
import org.seforge.monitor.domain.MetricTemplate;
import org.seforge.monitor.domain.ResourceGroup;
import org.seforge.monitor.domain.ResourcePrototype;
import org.seforge.monitor.hqapi.HQProxy;
import org.seforge.monitor.manager.MetricManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetricManagerImpl implements MetricManager{
	
	@Autowired
	private HQProxy hqProxy;

	@Override
	public void saveAndUpdateMetrics(List<Metric> metrics) {
		// TODO First, save these metrics to our database;
		// Second, call hqproxy and update metric in hqserver
		for(Metric metric : metrics){
			Integer id = metric.getId();			
			if(id!=null){
				Metric pmetric = Metric.findMetric(id);
				if(!metric.isEnabled())
					pmetric.remove();
				else{
					pmetric.setInterval(metric.getInterval());
					pmetric.merge();
				}				
			}else{
				metric.setEnabled(metric.isEnabled());				
				metric.setInterval(metric.getInterval());
				metric.persist();
			}
						
		}
		hqProxy.syncMetrics(metrics);
		
	}

	
	public List<Metric> getMetricsByResourcePrototypeAndGroup(ResourcePrototype resourcePrototype, ResourceGroup resourceGroup){
		List<MetricTemplate> templates;
		List<Metric> enabledMetrics;
		if(resourcePrototype.getId() == 18){
			templates = new ArrayList();
			enabledMetrics = new ArrayList();
			for( int i =20; i<=24; i++){
				ResourcePrototype rpt = ResourcePrototype.findResourcePrototype(i);
				templates.addAll(rpt.getMetricTemplates());
				enabledMetrics.addAll(Metric.findMetricsByResourceGroupAndResourcePrototype(resourceGroup, rpt).getResultList());
			}			
		}if(resourcePrototype.getId() == 19){
			templates = new ArrayList();
			enabledMetrics = new ArrayList();
			for( int i =25; i<=27; i++){
				ResourcePrototype rpt = ResourcePrototype.findResourcePrototype(i);
				templates.addAll(rpt.getMetricTemplates());
				enabledMetrics.addAll(Metric.findMetricsByResourceGroupAndResourcePrototype(resourceGroup, rpt).getResultList());
			}			
		}
		else{
			templates = resourcePrototype.getMetricTemplates();
			enabledMetrics = Metric.findMetricsByResourceGroupAndResourcePrototype(resourceGroup, resourcePrototype).getResultList();
		}		 
		  
		Map<String, Metric> metricMap = new HashMap<String, Metric>();
		for(Metric m: enabledMetrics){
			String name = m.getMetricTemplate().getName();
			metricMap.put(name, m);
		}
		for(MetricTemplate t : templates){
			if(!metricMap.containsKey(t.getName())){
				Metric m = new Metric();
				m.setMetricTemplate(t);
				m.setEnabled(false);
				m.setResourceGroup(resourceGroup);
				m.setResourcePrototype(t.getResourcePrototype());
				m.setInterval(t.getDefaultInterval());
				enabledMetrics.add(m);
			}
		}
		return enabledMetrics;
		
	}
	
	
	public List<Metric> getMetricsByResourcePrototypeAndGroup(ResourcePrototype resourcePrototype, ResourceGroup resourceGroup, int start, int limit){
		List result = getMetricsByResourcePrototypeAndGroup(resourcePrototype, resourceGroup);		
		int total = result.size();
		if(start+limit < total)
			return result.subList(start, start + limit);
		else
			return result.subList(start, total);
	}
	
	public List<Metric> getEnabledMetrics(ResourcePrototype resourcePrototype, ResourceGroup resourceGroup, int start, int limit){
		System.out.println(resourcePrototype.getName());
		System.out.println(resourcePrototype.getId());
		System.out.println(resourceGroup.getGroupOwner().getName());
		List<Metric> enabledList = Metric.findMetricsByResourceGroupAndResourcePrototype(resourceGroup, resourcePrototype).getResultList();			
		if(start+limit < enabledList.size())
			return enabledList.subList(start, start+limit);
		else
			return enabledList.subList(start, enabledList.size());
	}
	
	public int getEnabledMetricCount(ResourcePrototype resourcePrototype, ResourceGroup resourceGroup){
		List<Metric> enabled = Metric.findMetricsByResourceGroupAndResourcePrototype(resourceGroup, resourcePrototype).getResultList();		
		return enabled.size();
	}


}
