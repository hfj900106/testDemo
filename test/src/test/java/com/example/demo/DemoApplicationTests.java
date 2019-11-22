package com.example.demo;

import com.example.demo.algorithm.Graph;
import com.example.demo.test.Subject;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {

    }

    @Test
    @SneakyThrows
    public void genericity() {
        List<Integer> list = new ArrayList<>();
        list.add(12);
        list.getClass().getMethod("add", Object.class).invoke(list, "abc");
        System.out.println("===========" + list.get(1));
    }

    @Test
    public void observer() {
        Subject subject = new Subject();
//        new Observer1(subject);
//        new Observer2(subject);
        subject.setState(12);
    }

    @Test
    public void mapTest() {
        System.out.println(Integer.MAX_VALUE);
    }

//    @Test
//    public void testComponentScan() {
//        AnnotationConfigApplicationContext applicationContext2 = new AnnotationConfigApplicationContext(DemoApplication.class);
//        String[] names = applicationContext2.getBeanDefinitionNames();
//        for (String name : names) {
//            System.out.println(name);
//        }
//    }

    @Test
    public void linkedList() {

        /**
         * Collections 测试RandomAccess随机访问速度
         */
        List<String> al = new ArrayList<String>();
        List<String> ll = new LinkedList<String>();
        for (int i = 0; i < 100000; i++) {
            al.add(String.valueOf(i));
            ll.add(String.valueOf(i));
        }
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < al.size(); i++) {
            al.get(i);
        }
        System.out.println("count:" + (System.currentTimeMillis() - startTime));
        startTime = System.currentTimeMillis();
        for (int i = 0; i < ll.size(); i++) {
            ll.get(i);
        }
        System.out.println("count:" + (System.currentTimeMillis() - startTime));
        startTime = System.currentTimeMillis();
        for (Iterator<String> it = ll.iterator(); it.hasNext(); ) {
            it.next();
        }
        System.out.println("count:" + (System.currentTimeMillis() - startTime));


    }

    @Test
    public void linkedHashMapTest(){
        Map<String,String > map = new LinkedHashMap();
        for(int i =10;i>0;i--){
            map.put(i+"",i+"str");
        }
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String, String> next = iterator.next();
            System.out.println(next.getKey()+":"+ next.getValue());
        }
    }

    @Test
    public void hashMapTest(){
        Map map = new HashMap();
        for(int i =10;i>0;i--){
            map.put(i,i);
        }
        Iterator iterator = map.keySet().iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    public void setTest() {
        Set set = new HashSet();
        set.add(1);
        set.add(2);
        set.add(3);
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }


    }

    @Test
    public void dijkstraTest(){
        Graph graph = new Graph(9);
        graph.addEdge(0,1,1);
        graph.addEdge(0,2,2);
        graph.addEdge(1,5,5);
        graph.addEdge(1,3,3);
        graph.addEdge(2,3,3);
        graph.addEdge(5,6,6);
        graph.addEdge(3,6,6);
        graph.addEdge(6,7,7);
        graph.dijkstra(0,3);
    }

}
