package com.howard.www.business.domain;

import java.util.Comparator;

public class GoodsEntityComparator implements Comparator<GoodsEntity> {

	public int compare(GoodsEntity goodsOne, GoodsEntity goodsTwo) {
	    String positionOne=goodsOne.getGoodsShelfPosition();
	    String positionTwo=goodsTwo.getGoodsShelfPosition();
	    
	    /**
	     * -1为降序1就是升序
	     */
	    
	    int comparatorPoint= positionOne.compareTo(positionTwo);
	    if(comparatorPoint>0){
	    	return 1;
	    }
	    if(comparatorPoint<0){
	    	return -1;
	    }
		return 0;
	}

}
