package com.lineaje.assessment.utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.lineaje.assessment.model.Member;
import com.lineaje.assessment.model.MemberData;

public class Utils {

	public Utils() {
	}
	
	
	public List<MemberData> getMemberData(List<Member> members) {
		List<MemberData> memeberDataList = new ArrayList<>();
		getList(members, memeberDataList);
		
		return memeberDataList;
		
	}


	private void getList(List<Member> members, List<MemberData> memeberDataList) {
		if(members.isEmpty() || null == members) return;
		
		for(Member member : members) {
			int age = Integer.parseInt(member.getDeathYear()) - Integer.parseInt(member.getBirthYear());
			if(age > 0) {
				MemberData m = new MemberData(member.getName(), member.getBirthYear(), member.getDeathYear());
				memeberDataList.add(m);
				getList(member.getMembers(), memeberDataList);
			}
		}
	}
	
	
	public static void sort(List<MemberData> list) {
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
		}
	}

}
