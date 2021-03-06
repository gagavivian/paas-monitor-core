// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.seforge.monitor.domain;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.seforge.monitor.domain.Metric;
import org.seforge.monitor.domain.ResourceGroup;
import org.seforge.monitor.domain.ResourcePrototype;

privileged aspect Metric_Roo_Finder {
    
    public static TypedQuery<Metric> Metric.findMetricsByResourceGroupAndResourcePrototype(ResourceGroup resourceGroup, ResourcePrototype resourcePrototype) {
        if (resourceGroup == null) throw new IllegalArgumentException("The resourceGroup argument is required");
        if (resourcePrototype == null) throw new IllegalArgumentException("The resourcePrototype argument is required");
        EntityManager em = Metric.entityManager();
        TypedQuery<Metric> q = em.createQuery("SELECT o FROM Metric AS o WHERE o.resourceGroup = :resourceGroup AND o.resourcePrototype = :resourcePrototype", Metric.class);
        q.setParameter("resourceGroup", resourceGroup);
        q.setParameter("resourcePrototype", resourcePrototype);
        return q;
    }
    
}
