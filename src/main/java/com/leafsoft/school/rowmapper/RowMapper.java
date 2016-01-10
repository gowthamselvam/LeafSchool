package com.leafsoft.school.rowmapper;

import java.math.BigInteger;
import java.util.Map;

import com.leafsoft.school.model.OrgDetail;
import com.leafsoft.school.model.OrgUser;

public class RowMapper {

	public static OrgUser setOrgUserRow(Map<String, Object> row) {
		OrgUser orguser = new OrgUser();
		orguser.setCreatetime(row.get("createdtime")!= null ? BigInteger.valueOf((long) row.get("createdtime")) : new BigInteger("0"));
		orguser.setDefaultorgid(row.get("defaultorgid") != null ? (Integer)row.get("defaultorgid") : -1);
		orguser.setEmail(row.get("email")!=null ? (String)row.get("email") : "");
		orguser.setLid(row.get("lid") != null ? (Integer)row.get("lid") : -1);
		orguser.setLuid(row.get("luid") != null ? (Integer)row.get("luid") : -1);
		orguser.setUsername(row.get("username")!= null ? (String)row.get("username") : "");
		return orguser;
	}
	
	public static OrgDetail setOrgDetailRow(Map<String, Object> row) {
		OrgDetail orgdetail = new OrgDetail();
		orgdetail.setAddress(row.get("address") != null ? (String)row.get("address") : "");
		orgdetail.setCity(row.get("city")!= null ? (String)row.get("city") : "");
		orgdetail.setCountry(row.get("country") !=null ? (String)row.get("country") : "");
		orgdetail.setCreatedtime(row.get("createdtime") != null ? BigInteger.valueOf((long) row.get("createdtime")) : new BigInteger("-1"));
		orgdetail.setCurrencycode(row.get("currencycode") != null ? (String)row.get("currencycode") : "");
		orgdetail.setDateformat(row.get("dateformat") != null ? (String)row.get("dateformat") : "");
		orgdetail.setOrgid(row.get("orgid") !=null ? (Integer)row.get("orgid") : -1);
		orgdetail.setOrgname(row.get("orgname") != null ? (String)row.get("orgname") : "");
		orgdetail.setState(row.get("state") !=null ? (String)row.get("state") : "");
		orgdetail.setStatus(row.get("state") != null ? Byte.valueOf(row.get("status").toString()) : 0);
		orgdetail.setTimetype(row.get("timetype")!=null ? (String)row.get("timetype") : "GMT");
		orgdetail.setZipcode(row.get("zipcode") !=null ? (String)row.get("zipcode") : "");
		return orgdetail;
	}
	
}
