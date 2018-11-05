package com.bemeal.web.taste;

import java.util.*;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bemeal.web.cmm.Pagination;
import com.bemeal.web.mbr.MemberMapper;


@RestController
public class TasteCtrl {
	private static final Logger logger = LoggerFactory.getLogger(TasteCtrl.class);
	@Autowired Taste tst;
	@Autowired TasteMapper tstMapper;
	@Autowired MemberMapper mbrMapper;
	@Autowired HashMap<String,Object> tmap;
	@Autowired Pagination page;
	
	@Transactional
	@GetMapping("/chart/{id}") //chart
	public Map<String,Object> chart(@PathVariable String id){
		Function<String, Map<String,Object>> f=x->{
			tmap.clear();
			tmap.put("area", tstMapper.chartArea(x));
			tmap.put("ingre", tstMapper.chartIngre(x));
			tmap.put("brand", tstMapper.chartBrand(x));
			tmap.put("menu", tstMapper.chartMenu(x));
			return tmap;
		};
		return f.apply(id);
	}
	
	@PostMapping("/cart/post") //cart 등록
	public int postCart(@RequestBody Map<String, Object> p){
		logger.info("id {} itemSeq {} quantity {}",p);
		tmap.clear();
		tmap.putAll(p);
		int result = tstMapper.postTaste(tmap);
		return result;
	}
	@GetMapping("/taste/list/{id}/{flag}") //장바구니 등록
	public ArrayList<Map<String, Object>>listCart(@PathVariable String id,
												@PathVariable String flag){
		tmap.clear();
		tmap.put("id", id);
		tmap.put("flag", flag);
		ArrayList<Map<String, Object>> tlist = tstMapper.listCart(tmap);
		System.out.println("장바구니 tlist: "+tlist);
		return tlist;
	}
	@PostMapping("/cart/delete") //장바구니 삭제
	public int deleteCart(@RequestBody Map<String, Object> p){
			tmap.put("delList", p.get("delList"));
			int result = tstMapper.deleteCart(tmap);
			logger.info("들어온여러개의값 {}, 리턴값 {}",p.get("delList"),result);
		return result;
	}
 
    @Transactional
    @PostMapping("/pay/post")  //선물하기, 구매하기
    public int postPay(@RequestBody Map<String, Object> p){
          tmap.clear();           
          if(p.get("toId")!=null) {//선물하기
         	  logger.info("toID :  {}",p.get("toId"));
         	 tmap.put("flag", "gift");
         	 tstMapper.postGift(p);
           }else {//구매하기
        	   tstMapper.postPay(p);     
        	   tmap.put("flag", "buy");
           };
           tmap.put("purchaseSeq", p.get("purchaseSeq").toString());
           System.out.println("p :"+p);
          tmap.putAll(p);
               System.out.println("tmap : "+tmap);
          int result = tstMapper.postTastePay(tmap);
          //delList가 있으면 장바구니에서 삭제됨
          if(tmap.get("delList")!=null) {
        	  System.out.println("delList : "+tmap.get("delList"));
        	  int resultDel = tstMapper.deleteCart(tmap);
        	  System.out.println(resultDel);
          }
          return result; 
    } 
    
    @Transactional
	@PostMapping("/purchase/payhis") //구매함 리스트, 검색
	public Map<String, Object> searchList(@RequestBody HashMap<String, Object> p){
			System.out.println("/purchase/payhis들어옴");
		tmap.clear();
		tmap.putAll(p);
		p.put("count", tstMapper.countTaste(p));
		p.put("pageNum", p.get("pageNo"));
		p.put("pageSize", 5);
		p.put("blockSize", 5);
		page.excute(p);
		page.setBeginRow(page.getBeginRow()-1);
		tmap.put("page",page);
			logger.info("page :  {}",tmap.get("page"));
			System.out.println("tmap : "+tmap);
		ArrayList<Map<String, Object>> tlist = tstMapper.listPayHis(tmap);
		tmap.put("tlist",tlist);
		System.out.println("맵: "+tmap);
		return tmap;
	}
    @Transactional
   	@GetMapping("/purchase/gift/{id}/{state}/{pageNo}") //선물함 리스트
   	public Map<String, Object> giftList(@PathVariable String id,
   										@PathVariable String state,
   										@PathVariable String pageNo){
   			System.out.println("/purchase/gift들어옴");
   		tmap.clear();
   		logger.info("state :  {}",state);
   		tmap.put("id", id);
		tmap.put("flag", "gift");
		tmap.put("state", state);
		tmap.put("count", tstMapper.countGift(tmap));
		System.out.println("count "+tmap.get("count"));
		tmap.put("pageNum", pageNo);
   		tmap.put("pageSize", 12);
   		tmap.put("blockSize", 5);
   		page.excute(tmap);
   		page.setBeginRow(page.getBeginRow()-1);
   		tmap.put("page",page);
   		logger.info("page :  {}",tmap.get("page"));
		ArrayList<Map<String, Object>> tlist = tstMapper.listGift(tmap);
   		tmap.put("tlist",tlist);
   		System.out.println("맵: "+tmap);
   		return tmap;
   	}

}
