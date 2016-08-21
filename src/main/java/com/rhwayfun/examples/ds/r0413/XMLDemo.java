package com.rhwayfun.examples.ds.r0413;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Created by rhwayfun on 16-4-13.
 */
public class XMLDemo {

    public static void main(String[] args){
        Person person = new Person(10,"Tom");
        XStream xStream = new XStream(new DomDriver());
        //设置Person类的别名
        xStream.alias("person", Person.class);
        String personXML = xStream.toXML(person);
        System.out.println(personXML);

        Person person1 = (Person) xStream.fromXML(personXML);
        System.out.println(person1.getId() + "-->" + person1.getName());
    }
}
