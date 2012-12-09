// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.seforge.monitor.domain;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.seforge.monitor.domain.Constraint;

privileged aspect Constraint_Roo_Json {
    
    public String Constraint.toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
    public static Constraint Constraint.fromJsonToConstraint(String json) {
        return new JSONDeserializer<Constraint>().use(null, Constraint.class).deserialize(json);
    }
    
    public static String Constraint.toJsonArray(Collection<Constraint> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static Collection<Constraint> Constraint.fromJsonArrayToConstraints(String json) {
        return new JSONDeserializer<List<Constraint>>().use(null, ArrayList.class).use("values", Constraint.class).deserialize(json);
    }
    
}
