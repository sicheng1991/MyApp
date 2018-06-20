package com.example.myutils.uitls;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 *
 * Created by Longwj on 2017/10/10.
 */

public class Bencode {
    public static Object decode(byte[] data){
        ByteBuffer buffer = ByteBuffer.wrap(data);
        return decode(buffer);
    }
    public static Object decode(ByteBuffer buffer){
        Stack<Object> endStack = new Stack<Object>();
        while (buffer.hasRemaining()){
            byte ch = buffer.get();
            if(ch >= '0' & ch <= '9'){
                buffer.position(buffer.position() - 1);
                //string
                byte[] lenByte = readUntil(buffer, ':');
                int len = Integer.parseInt(new String(lenByte));
                lenByte = new byte[len];
                buffer.get(lenByte);
                String str = new String(lenByte);
                if(endStack.empty()){
                    endStack.push(new LinkedList<Object>());
                }
                setStackVal(endStack, str);
                //继续执行下一次
                continue;
            }
            if(ch == 'i'){
                //字符串处理
                byte[] temp = readUntil(buffer, 'e');
                Long val = Long.parseLong(new String(temp));
                if(endStack.empty()){
                    endStack.push(new LinkedList<Object>());
                }
                setStackVal(endStack, val);
                continue;
            }
            if(ch == 'l'){
                //列表处理
                endStack.push(new LinkedList<Object>());
                continue;
            }
            if(ch == 'd'){
                //字典类型开始解码
                endStack.push(new LinkedHashMap<Object, Object>());
                continue;
            }
            if(ch == 'e'){
                Object last = endStack.pop();
                if(endStack.empty()){
                    return last;
                }
                setStackVal(endStack, last);
            }
        }
        return endStack.empty() ? null : endStack.pop();
    }
    private static byte[] readUntil(ByteBuffer buff, char end){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while (true){
            byte ch = buff.get();
            if(ch == end){
                break;
            }
            baos.write(ch);
        }
        return baos.toByteArray();
    }
    private static void setStackVal(Stack<Object> stack, Object val){
        Object prev = stack.peek();
        if(prev instanceof List){
            ((List)prev).add(val);
        }else if(prev instanceof Map){
            Pair<Object, Object> entry = new Pair<Object, Object>(val, null);
            stack.push(entry);
        }else if(prev instanceof Pair){
            Pair<Object, Object> entry = (Pair<Object, Object>)stack.pop();
            entry.setVal(val);
            prev = stack.peek();
            ((Map<Object, Object>)prev).put(entry.getKey(), entry.getVal());
        }
    }
    static class Pair<K,V>{
        private K key;
        private V val;
        public Pair(K k, V v){
            this.key = k;
            this.val = v;
        }
        public K getKey() {
            return key;
        }
        public void setKey(K key) {
            this.key = key;
        }
        public V getVal() {
            return val;
        }
        public void setVal(V val) {
            this.val = val;
        }
    }
}
