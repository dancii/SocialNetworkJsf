package com.socialnetwork.dao;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-11-29T13:28:14")
@StaticMetamodel(Wallpost.class)
public class Wallpost_ { 

    public static volatile SingularAttribute<Wallpost, Integer> toid;
    public static volatile SingularAttribute<Wallpost, Date> date;
    public static volatile SingularAttribute<Wallpost, Integer> id;
    public static volatile SingularAttribute<Wallpost, String> message;
    public static volatile SingularAttribute<Wallpost, Integer> fromid;

}