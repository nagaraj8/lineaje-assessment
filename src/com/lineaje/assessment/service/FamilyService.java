/**
 * 
 */
package com.lineaje.assessment.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import com.lineaje.assessment.model.Member;
import com.lineaje.assessment.model.MemberData;

/**
 * @author HegdeNagaraj
 *
 */
public class FamilyService {
	
	
	private static Logger logger = Logger.getLogger(FamilyService.class.getName());
	
	private static List<MemberData> list = null;
	
	private static int birthYear = 999999;
	private static int deathYear = 0;
	private static int sumOfAllAges = 0;
	
	private static int familyLine = 0;
	

	public static void individualFamilyLinesWithShortestAndLongest(List<Member> members) {
		int shorter = 99999;
		int longer = 0;
		for(int i=0;i<members.size();i++) {
			List<MemberData> memberDataList = new ArrayList<>();
			System.out.println();
			logger.info("Printing family tree for family: "+(i+1));
			System.out.println("Printing family tree for family: "+(i+1));
			
			System.out.println("Name: "+members.get(i).getName()+" Birth Year: "+members.get(i).getBirthYear()+" Death Year: "+members.get(i).getDeathYear());
			getFamilyTree(memberDataList, members.get(i).getMembers());
			for(MemberData memberData : memberDataList) {
				System.out.println("Name: "+memberData.getName()+" Birth Year: "+memberData.getBirthYear()+" Death Year: "+memberData.getDeathYear());
			}
			memberDataList = null;
			shorter = Math.min(familyLine, shorter);
			longer = Math.max(longer, familyLine);
			familyLine = 1;
		}
		
		System.out.println("Shortest Family line is: "+shorter);
		
		System.out.println("Longest Family line is: "+longer);
		System.out.println();
	}
	
	
	private static void getFamilyTree(List<MemberData> memberDataList, List<Member> members) {
		if(members.isEmpty() || members == null) return;
		for(Member m : members) {
			int age = Integer.parseInt(m.getDeathYear()) - Integer.parseInt(m.getBirthYear());
			if(age < 0) {
				logger.warning("The age is invalid and this member: "+m.getName()+" cannot be considered further!!");
				continue;
			}
			MemberData memberData = new MemberData(m.getName(), m.getBirthYear(), m.getDeathYear());
			memberDataList.add(memberData);
			getFamilyTree(memberDataList, m.getMembers());
		}
		
		familyLine++;
	}


	public static void printAllFamilyMembersAndTheirAge(List<Member> members) {
		if(members.isEmpty() || null == members) return;
		
		for(Member member : members) {
			int age = Integer.parseInt(member.getDeathYear()) - Integer.parseInt(member.getBirthYear());
			if(age < 0) {
				logger.warning("The age is invalid and this member: "+member.getName()+" cannot be considered further!!");
				continue;
			}
			System.out.println("Name: "+member.getName()+" and Age: "+ age);
			printAllFamilyMembersAndTheirAge(member.getMembers());
		}
	}
	
	public static void orderAgeOfFamilyMembers(List<Member> members) {
		list = new ArrayList<>();
		sort(members, list);
		if (list != null && !list.isEmpty()) {
			Collections.sort(list, new Comparator<MemberData>() {
	            @Override
	            public int compare(MemberData m1, MemberData m2) {
	            	int age1 = Integer.parseInt(m1.getDeathYear()) - Integer.parseInt(m1.getBirthYear());
	            	int age2 = Integer.parseInt(m2.getDeathYear()) - Integer.parseInt(m2.getBirthYear());
	            	if(age1 < age2) return -1;
	            	if(age2 < age1) return 1;
	                return 0;
	            }
	        });
			
			logger.info("After ordering in ascending order");
			for(MemberData m : list) {
				int age = Integer.parseInt(m.getDeathYear()) - Integer.parseInt(m.getBirthYear());
				System.out.println("Name: "+m.getName()+" and Age: "+age);
			}
			
        }
		System.out.println();
	}


	private static void sort(List<Member> members, List<MemberData> list) {
		
		if(members.isEmpty() || null == members) return;
		
		for(Member member : members) {
			int age = Integer.parseInt(member.getDeathYear()) - Integer.parseInt(member.getBirthYear());
			if(age < 0) {
				logger.warning("The age is invalid and this member: "+member.getName()+" cannot be considered further!!");
				continue;
			}
			MemberData m = new MemberData(member.getName(), member.getBirthYear(), member.getDeathYear());
			list.add(m);
			sort(member.getMembers(), list);
		}
		
	}
	
	
	public static void lineajeRange(List<Member> members) {
		range(members);
		System.out.println("Birth Year: "+birthYear);
		System.out.println("Death Year: "+deathYear);
		System.out.println("Lineaje Range: "+(deathYear - birthYear));
		System.out.println();
	}
	
	private static void range(List<Member> members) {
		if(members.isEmpty() || null == members) return;
		
		for(Member member : members) {
			
			int age = Integer.parseInt(member.getDeathYear()) - Integer.parseInt(member.getBirthYear());
			if(age < 0) {
				logger.warning("The age is invalid and this member: "+member.getName()+" cannot be considered further!!");
				continue;
			}
			birthYear = Math.min(birthYear, Integer.parseInt(member.getBirthYear()));
			deathYear = Math.max(deathYear, Integer.parseInt(member.getDeathYear()));
			
			range(member.getMembers());
			
		}
	}
	
	public static void meanAgeOfLineaje(List<Member> members) {
		findMean(members);
		int mean = sumOfAllAges/list.size();
		System.out.println("Mean Age of Lineaje: "+mean);
		System.out.println();
	}


	public static void findMean(List<Member> members) {
		if(members.isEmpty() || null == members) return;
		
		for(Member member : members) {
			int age = Integer.parseInt(member.getDeathYear()) - Integer.parseInt(member.getBirthYear());
			if(age < 0) {
				logger.warning("The age is invalid and this member: "+member.getName()+" cannot be considered further!!");
				continue;
			}
			
			sumOfAllAges += age;
			findMean(member.getMembers());
		}
	}
	
	public static void findMedian(List<Member> members) {
		System.out.println();
		if(members.isEmpty() || null == members) return;
		int median = 0;
		if(list.size()%2 == 0) {
			MemberData member1 = list.get(list.size()/2);
			MemberData member2 = list.get((list.size()/2)-1);
			int age1 = Integer.parseInt(member1.getDeathYear()) - Integer.parseInt(member1.getBirthYear());
			int age2 = Integer.parseInt(member2.getDeathYear()) - Integer.parseInt(member2.getBirthYear());
			median =  (age1+age2)/2;
		} else {
			MemberData member = list.get(list.size()/2);
			median = Integer.parseInt(member.getDeathYear()) - Integer.parseInt(member.getBirthYear());
		}
		System.out.println("The median age of this lineaje is: "+median);
		System.out.println();
	}
	
	public static void findIQR() {
		int mid = getMid(0, list.size());
		
		MemberData member1 = list.get(getMid(0, mid));
		
		MemberData member2 = list.get(getMid(mid+1, list.size()));
		
		int age1 = Integer.parseInt(member1.getDeathYear()) - Integer.parseInt(member1.getBirthYear());
		int age2 = Integer.parseInt(member2.getDeathYear()) - Integer.parseInt(member2.getBirthYear());
		
		System.out.println("IQR value is: "+(age2 - age1));
		System.out.println();
		
	}


	private static int getMid(int i, int j) {
		int n = j - i + 1;
	    n = (n + 1) / 2 - 1;
	    return n + i;
	}
	
	public static void findYoungestAndLongestDied() {
		
		MemberData member1 = list.get(0);
		
		MemberData member2 = list.get(list.size()-1);
		
		int age1 = Integer.parseInt(member1.getDeathYear()) - Integer.parseInt(member1.getBirthYear());
		int age2 = Integer.parseInt(member2.getDeathYear()) - Integer.parseInt(member2.getBirthYear());
		
		list = null;
		familyLine = 0;
		sumOfAllAges = 0;
		int birthYear = 999999;
		int deathYear = 0;
		System.out.println("Youngest Death, Name: "+member1.getName()+" and age: "+age1);
		
		System.out.println("Longest Death, Name: "+member2.getName()+" and age: "+age2);
		System.out.println();
		
	}
}
