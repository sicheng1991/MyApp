package com.example.leetcode.medium;

/**
 * 给您两个非空链表，表示两个非负整数。这些数字以相反的顺序存储，每个节点都包含一个数字。将两个数字相加，并将其作为链表返回。
 * Created by yangzteL on 2018/5/3 0003.
 */

public class C2 {
    public static void main(String [] ar){
        ListNode l1 = new ListNode(5);
//        l1.next = new ListNode(4);
//        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
//        l2.next = new ListNode(6);
//        l2.next.next = new ListNode(4);

        addTwoNumbers(l1,l2);
    }
    public  static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int add = 0;
        ListNode node = new ListNode(0);
        ListNode node1 = node;
        while(l1 != null || l2 != null){
            int v1 = l1 != null ? l1.val:0;
            int v2 = l2 != null ? l2.val:0;
            node.next = new ListNode((v1 + v2 + add) % 10);
            add = (v1 + v2 + add) / 10;
            node = node.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if(add > 0){
            node.next = new ListNode(add);
        }
        return node1.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

