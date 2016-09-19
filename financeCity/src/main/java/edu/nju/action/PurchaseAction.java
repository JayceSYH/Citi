package edu.nju.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.nju.service.ExceptionsAndError.DataNotFoundException;
import edu.nju.service.POJO.NAVHistory;
import edu.nju.service.SearchService.SearchService;

@Controller
public class PurchaseAction extends BaseAction{
	@Autowired
	SearchService searchService;
	private String day;
	private String pid;
	private String[] dateArr;
	private double[] navArr;
	 public String[] getDateArr() {
		return dateArr;
	}

	public void setDateArr(String[] dateArr) {
		this.dateArr = dateArr;
	}

	public double[] getNavArr() {
		return navArr;
	}

	public void setNavArr(double[] navArr) {
		this.navArr = navArr;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String buy() {
		String propid= request.getParameter("propid");
		String proname= request.getParameter("proname");
		String protype= request.getParameter("protype");
		String proprice= request.getParameter("proprice");
		String priceType=request.getParameter("priceType");
		request.setAttribute("propid", propid);
		request.setAttribute("proname", proname);
		request.setAttribute("protype", protype);
		request.setAttribute("proprice", proprice);
		request.setAttribute("priceType", priceType);
		 return SUCCESS;
	 }

	public String buyAction() {
		String value = request.getParameter("submitValue");
		String pid = request.getParameter("propid");
		//TODO
		System.out.println(value + " " + pid);
		request.setAttribute("tipMessage", "购买成功！");
		return SUCCESS;
	}
	 
	 public String getHistory(){
		 try {
			 NAVHistory[] arr=searchService.getFundValueHistory(Integer.parseInt(pid),Integer.parseInt(day));
			 dateArr=new String[arr.length];
			 navArr=new double[arr.length];
			 for(int i=0;i<arr.length;i++){
				 dateArr[i]=arr[i].getDate();
				 navArr[i]=arr[i].getNAV();
			 }
			 System.out.println("leth "+dateArr.length);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return SUCCESS;
	 }
	 
	 public String soldAction(){
		 String pid = request.getParameter("hiddenId");
		 session.put("tipMessage", "已卖出！");
		 System.out.println(pid);
		 return SUCCESS;
	 }
}
