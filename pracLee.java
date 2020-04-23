package pracGoGo;

import java.util.Scanner;

class PhoneInfo{
	String name;
	String phoneNumber;
	
	public PhoneInfo(String name, String num) {
		
		this.name = name;
		phoneNumber = num;
	}
	
	public void showPhoneInfo() {
		System.out.println("name :"+name);
		System.out.println("num : "+phoneNumber);
	}
}

class PhoneUnivInfo extends PhoneInfo{
	String major;
	int year;
	public PhoneUnivInfo(String name, String num, String major, int year) {
		super(name, num);
		this.major = major;
		this.year = year;
	}
	public void showPhoneInfo() {
		super.showPhoneInfo();
		System.out.println("major :"+major);
		System.out.println("year : "+year);
	}
	
	
}
class PhoneCompanyInfo extends PhoneInfo{
	String company;

	public PhoneCompanyInfo(String name, String num, String company) {
		super(name, num);
		this.company = company;
	}
	public void showPhoneInfo() {
		super.showPhoneInfo();
		System.out.println("company : "+company);
	}
}
class PhoneBookManager{
	final int MAX_CNT=100;
	PhoneInfo[] infoStorage = new PhoneInfo[MAX_CNT];
	int curCnt=0;
	
	private PhoneInfo readFriendInfo() {
		System.out.println("name : ");
		String name =MenuViewer.scan.nextLine();
		System.out.println("phoneNumber : ");
		String phone = MenuViewer.scan.nextLine();
		return new PhoneInfo(name, phone);
	}
	private PhoneInfo readUnivFriendInfo() {
		System.out.println("name : ");
		String name = MenuViewer.scan.nextLine();		
		System.out.println("phone : ");
		String phone = MenuViewer.scan.nextLine();		
		System.out.println("major : ");
		String major = MenuViewer.scan.nextLine();
		System.out.println("year : ");
		int year = MenuViewer.scan.nextInt();
		MenuViewer.scan.nextLine();
		return new PhoneUnivInfo(name, phone, major, year);
	}
	private PhoneInfo readCompanyInfo() {
		System.out.println("name : ");
		String name = MenuViewer.scan.nextLine();
		System.out.println("phone : ");
		String phone = MenuViewer.scan.nextLine();
		System.out.println("company : ");
		String company = MenuViewer.scan.nextLine();
		return new PhoneCompanyInfo(name, phone, company);
	}
	public void inputData() {
		System.out.println("starting data input..");
		System.out.println("1.general  2.university  3.company");
		System.out.print(">select >>");
		int choice = MenuViewer.scan.nextInt();
		MenuViewer.scan.nextLine();
		PhoneInfo info  =null;
		switch(choice) {
		case 1: 
			info = readFriendInfo();
			break;
		case 2:
			info =readUnivFriendInfo();
			break;
		case 3: 
			info = readCompanyInfo();
			break;
		}
		infoStorage[curCnt++]=info;
		System.out.println("inputing data complete");
	}
	public void searchData() {
		System.out.println("starting search data..");
		System.out.print("name :");
		String name = MenuViewer.scan.nextLine();
		
		int dataIdx = search(name);
		if(dataIdx<0) {
			System.out.println("data is not exist..\n");
		}else {
			infoStorage[dataIdx].showPhoneInfo();
			System.out.println("data found.. \n");
		}
	}
	public void deleteData() {
		System.out.println("starting delete data..");
		System.out.println("name : ");
		String name = MenuViewer.scan.nextLine();
		
		int dataIdx  =search(name);
		if(dataIdx<0) {
			System.out.println("data is not exist..\n");
						
		}else {
			for(int idx = dataIdx;idx<(curCnt-1);idx++) {
				infoStorage[idx]=infoStorage[idx+1];
				curCnt--;
				System.out.println("deleting data is complete..");
			}
		}
	}
	private int search(String name) {
		for(int idx=0;idx<curCnt;idx++) {
			PhoneInfo curInfo = infoStorage[idx];
			if(name.compareTo(curInfo.name)==0) {
				return idx;
			}
		}
		return -1;
	}
}
class MenuViewer{
	public static Scanner scan = new Scanner(System.in);
	
	public static void showMenu() {
		System.out.println("please select..");
		System.out.println("1. data input");
		System.out.println("2. data search");
		System.out.println("3. data delete");
		System.out.println("4. program shutdown");
		System.out.print("select : ");
	}
}

public class pracLee {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PhoneBookManager manager = new PhoneBookManager();
		int choice;
		while(true) {
			MenuViewer.showMenu();
			choice=MenuViewer.scan.nextInt();
			MenuViewer.scan.nextLine();
			switch(choice) {
			case 1:
				manager.inputData();
				break;
			case 2:
				manager.searchData();
				break;
			case 3:
				manager.deleteData();
				break;
			case 4:
				System.out.println("shutdown program..");
				return;
			}
		}
	}

}
