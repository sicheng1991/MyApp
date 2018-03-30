package com.chimu.myapp.test;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Longwj on 2018/3/30 0030.
 */

public class ShopHelper {

    /**
     *
     * @param oldList             买货：接口的接口数据
     * @param newList             买货：本地数据库数据
     * @return    放的商品
     */
    public static List<GoodsInfo> addGoods(List<GoodsInfo> oldList, List<GoodsInfo> newList){
        return buyGoods(newList,oldList);
    }

    /**
     *
     * @param oldList             买货：本地数据库数据
     * @param newList             买货：接口的接口数据
     * @return    买的商品
     */
    public static List<GoodsInfo> buyGoods(List<GoodsInfo> oldList, List<GoodsInfo> newList){
        List<GoodsInfo> buyGoods = new ArrayList<>();
        if(oldList == null || oldList.size() == 0){//本地数据为空
            if(newList != null && newList.size() > 0){
                for(GoodsInfo info : newList){
                    GoodsInfo cloneGood = info.clone();//需要oldList的数据的话，这里就不用深复制
                    cloneGood.setEpcNum(-info.getEpcNum());
                    buyGoods.add(cloneGood);
                }
            }
            return buyGoods;
        }

        if(oldList == null || oldList.size() == 0){//买完所有商品
            return newList;
        }

        setContains(oldList,newList);

        for(GoodsInfo oldGood : oldList){
            if(oldGood.isContains()){
                for(GoodsInfo newGood : newList){
//                    if(TextUtils.equals(oldGood.getEpc(),newGood.getEpc())){
                      if(oldGood.getEpc().equals(newGood.getEpc())){
                        buyGoods.add(new GoodsInfo(oldGood.getEpc(),oldGood.getEpcNum() - newGood.getEpcNum()));
                        break;
                    }
                }
            }else {
                buyGoods.add(new GoodsInfo(oldGood.getEpc(),oldGood.getEpcNum() - 0));
            }

        }
        for(GoodsInfo newGood : newList){
            if(!newGood.isContains()){
                buyGoods.add(new GoodsInfo(newGood.getEpc(), 0 - newGood.getEpcNum()));
            }
        }

        return buyGoods;
    }

    /**
     * 集合在对面集合是否存在
     * @param listA
     * @param listB
     */
    private static void setContains(List<GoodsInfo> listA, List<GoodsInfo> listB){
        if(listA == null || listB == null){
            throw new IllegalArgumentException("list can not be null");
        }
        for(GoodsInfo goodA : listA){
            for(GoodsInfo goodB : listB){
//                if(TextUtils.equals(goodA.getEpc(),goodB.getEpc())){
                if(goodA.getEpc().equals(goodB.getEpc())){
                    goodA.setContains(true);
                    goodB.setContains(true);
                    System.out.println(goodA.toString() + goodB.toString());
                }
            }
        }
    }

}
