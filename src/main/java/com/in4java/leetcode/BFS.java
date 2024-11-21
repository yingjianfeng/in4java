package com.in4java.leetcode;

import com.sun.istack.NotNull;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author: yingjf
 * @date: 2024/11/20 09:18
 * @description: 广度优先算法  横向遍历
 * https://blog.csdn.net/EngineerofAI/article/details/120590420
 */
public class BFS {
    public static class Node implements Comparable<Node> {

        private String name;

        private TreeSet<Node> set = new TreeSet<>();//有序的集合

        public Node() {
        }

        public Node(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Set<Node> getSet() {
            return set;
        }

        public void setSet(TreeSet<Node> set) {
            this.set = set;
        }

        @Override
        public int compareTo(@NotNull Node o) {//排序规则
            if (name.hashCode() > o.getName().hashCode()) {
                return 1;
            }
            return 0;
        }
    }

    public Node init() {//初始化一个图及其节点
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");
        Node nodeG = new Node("G");
        Node nodeH = new Node("H");
        Node nodeI = new Node("I");
        nodeA.getSet().add(nodeB);
        nodeA.getSet().add(nodeC);
        nodeA.getSet().add(nodeI);
        nodeB.getSet().add(nodeD);
        nodeB.getSet().add(nodeE);
        nodeC.getSet().add(nodeF);
        nodeC.getSet().add(nodeG);
        nodeD.getSet().add(nodeH);
        nodeE.getSet().add(nodeH);
        return nodeA;
    }

    public void visite(Node node) {//访问每个节点
        System.out.print(node.getName() + " ");
    }

    public void bfs(Node start) {
        Queue<Node> queue = new LinkedList<>();//存储访问的节点
        Queue<Node> visite = new LinkedList<>();//存储访问过得节点
        queue.add(start);//起始节点添加到队列
        visite.add(start);//标识为访问过
        while (!queue.isEmpty()) {
            Node node = queue.poll();//队列头结点出队
            visite(node);
            Set<Node> set = node.getSet();//获取所有的直接关联的节点
            Iterator<Node> iterator = set.iterator();
            while (iterator.hasNext()) {
                Node next = iterator.next();
                if (!visite.contains(next)) {//不包含说明没有没有被访问
                    queue.add(next);
                    visite.add(next);
                }
            }
        }
    }

    public static void main(String[] args) {
        BFS bfs = new BFS();
        bfs.bfs(bfs.init());
    }


}
