// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.seforge.monitor.domain;

import org.seforge.monitor.domain.Metric;
import org.seforge.monitor.domain.MetricTemplate;
import org.seforge.monitor.domain.ResourceGroup;
import org.seforge.monitor.domain.ResourcePrototype;

privileged aspect Metric_Roo_JavaBean {
    
    public Integer Metric.getId() {
        return this.id;
    }
    
    public void Metric.setId(Integer id) {
        this.id = id;
    }
    
    public Long Metric.getInterval() {
        return this.interval;
    }
    
    public void Metric.setInterval(Long interval) {
        this.interval = interval;
    }
    
    public boolean Metric.isEnabled() {
        return this.enabled;
    }
    
    public void Metric.setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    public ResourcePrototype Metric.getResourcePrototype() {
        return this.resourcePrototype;
    }
    
    public void Metric.setResourcePrototype(ResourcePrototype resourcePrototype) {
        this.resourcePrototype = resourcePrototype;
    }
    
    public ResourceGroup Metric.getResourceGroup() {
        return this.resourceGroup;
    }
    
    public void Metric.setResourceGroup(ResourceGroup resourceGroup) {
        this.resourceGroup = resourceGroup;
    }
    
    public MetricTemplate Metric.getMetricTemplate() {
        return this.metricTemplate;
    }
    
    public void Metric.setMetricTemplate(MetricTemplate metricTemplate) {
        this.metricTemplate = metricTemplate;
    }
    
}
