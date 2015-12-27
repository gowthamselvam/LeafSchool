<%@page import="com.leafsoft.util.JSONUtil"%>
<%
out.print(JSONUtil.getInstance().getDebugJson().getJSONObject("1").getJSONObject("userInfo"));
%>