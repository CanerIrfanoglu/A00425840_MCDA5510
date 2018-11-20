<%@page contentType="text/html;charset=UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<HTML>
<HEAD>
<TITLE>Result</TITLE>
</HEAD>
<BODY>
<H1>Result</H1>

<jsp:useBean id="sampleTrxnWebServiceProxyid" scope="session" class="com.caner.mcda5510.service.TrxnWebServiceProxy" />
<%
if (request.getParameter("endpoint") != null && request.getParameter("endpoint").length() > 0)
sampleTrxnWebServiceProxyid.setEndpoint(request.getParameter("endpoint"));
%>

<%
String method = request.getParameter("method");
int methodID = 0;
if (method == null) methodID = -1;

if(methodID != -1) methodID = Integer.parseInt(method);
boolean gotMethod = false;

try {
switch (methodID){ 
case 2:
        gotMethod = true;
        java.lang.String getEndpoint2mtemp = sampleTrxnWebServiceProxyid.getEndpoint();
if(getEndpoint2mtemp == null){
%>
<%=getEndpoint2mtemp %>
<%
}else{
        String tempResultreturnp3 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getEndpoint2mtemp));
        %>
        <%= tempResultreturnp3 %>
        <%
}
break;
case 5:
        gotMethod = true;
        String endpoint_0id=  request.getParameter("endpoint8");
            java.lang.String endpoint_0idTemp = null;
        if(!endpoint_0id.equals("")){
         endpoint_0idTemp  = endpoint_0id;
        }
        sampleTrxnWebServiceProxyid.setEndpoint(endpoint_0idTemp);
break;
case 10:
        gotMethod = true;
        com.caner.mcda5510.service.TrxnWebService getTrxnWebService10mtemp = sampleTrxnWebServiceProxyid.getTrxnWebService();
if(getTrxnWebService10mtemp == null){
%>
<%=getTrxnWebService10mtemp %>
<%
}else{
%>
<TABLE>
<TR>
<TD COLSPAN="3" ALIGN="LEFT">returnp:</TD>
</TABLE>
<%
}
break;
case 15:
        gotMethod = true;
        String trxnID_1id=  request.getParameter("trxnID18");
        int trxnID_1idTemp  = Integer.parseInt(trxnID_1id);
        String name_2id=  request.getParameter("name20");
            java.lang.String name_2idTemp = null;
        if(!name_2id.equals("")){
         name_2idTemp  = name_2id;
        }
        String cardNumber_3id=  request.getParameter("cardNumber22");
            java.lang.String cardNumber_3idTemp = null;
        if(!cardNumber_3id.equals("")){
         cardNumber_3idTemp  = cardNumber_3id;
        }
        String unitPrice_4id=  request.getParameter("unitPrice24");
        double unitPrice_4idTemp  = Double.parseDouble(unitPrice_4id);
        String qty_5id=  request.getParameter("qty26");
        int qty_5idTemp  = Integer.parseInt(qty_5id);
        String expDate_6id=  request.getParameter("expDate28");
            java.lang.String expDate_6idTemp = null;
        if(!expDate_6id.equals("")){
         expDate_6idTemp  = expDate_6id;
        }
        java.lang.String createTransaction15mtemp = sampleTrxnWebServiceProxyid.createTransaction(trxnID_1idTemp,name_2idTemp,cardNumber_3idTemp,unitPrice_4idTemp,qty_5idTemp,expDate_6idTemp);
if(createTransaction15mtemp == null){
%>
<%=createTransaction15mtemp %>
<%
}else{
        String tempResultreturnp16 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(createTransaction15mtemp));
        %>
        <%= tempResultreturnp16 %>
        <%
}
break;
case 30:
        gotMethod = true;
        String trxnID_7id=  request.getParameter("trxnID33");
        int trxnID_7idTemp  = Integer.parseInt(trxnID_7id);
        String name_8id=  request.getParameter("name35");
            java.lang.String name_8idTemp = null;
        if(!name_8id.equals("")){
         name_8idTemp  = name_8id;
        }
        String cardNumber_9id=  request.getParameter("cardNumber37");
            java.lang.String cardNumber_9idTemp = null;
        if(!cardNumber_9id.equals("")){
         cardNumber_9idTemp  = cardNumber_9id;
        }
        String unitPrice_10id=  request.getParameter("unitPrice39");
        double unitPrice_10idTemp  = Double.parseDouble(unitPrice_10id);
        String qty_11id=  request.getParameter("qty41");
        int qty_11idTemp  = Integer.parseInt(qty_11id);
        String expDate_12id=  request.getParameter("expDate43");
            java.lang.String expDate_12idTemp = null;
        if(!expDate_12id.equals("")){
         expDate_12idTemp  = expDate_12id;
        }
        java.lang.String updateTransaction30mtemp = sampleTrxnWebServiceProxyid.updateTransaction(trxnID_7idTemp,name_8idTemp,cardNumber_9idTemp,unitPrice_10idTemp,qty_11idTemp,expDate_12idTemp);
if(updateTransaction30mtemp == null){
%>
<%=updateTransaction30mtemp %>
<%
}else{
        String tempResultreturnp31 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(updateTransaction30mtemp));
        %>
        <%= tempResultreturnp31 %>
        <%
}
break;
case 45:
        gotMethod = true;
        String trxnID_13id=  request.getParameter("trxnID68");
        int trxnID_13idTemp  = Integer.parseInt(trxnID_13id);
        com.caner.mcda5510.transaction.Transaction getTransaction45mtemp = sampleTrxnWebServiceProxyid.getTransaction(trxnID_13idTemp);
if(getTransaction45mtemp == null){
%>
<%=getTransaction45mtemp %>
<%
}else{
%>
<TABLE>
<TR>
<TD COLSPAN="3" ALIGN="LEFT">returnp:</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">createdBy:</TD>
<TD>
<%
if(getTransaction45mtemp != null){
java.lang.String typecreatedBy48 = getTransaction45mtemp.getCreatedBy();
        String tempResultcreatedBy48 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typecreatedBy48));
        %>
        <%= tempResultcreatedBy48 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">id:</TD>
<TD>
<%
if(getTransaction45mtemp != null){
%>
<%=getTransaction45mtemp.getId()
%><%}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">quantity:</TD>
<TD>
<%
if(getTransaction45mtemp != null){
%>
<%=getTransaction45mtemp.getQuantity()
%><%}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">expDate:</TD>
<TD>
<%
if(getTransaction45mtemp != null){
java.lang.String typeexpDate54 = getTransaction45mtemp.getExpDate();
        String tempResultexpDate54 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typeexpDate54));
        %>
        <%= tempResultexpDate54 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">nameOnCard:</TD>
<TD>
<%
if(getTransaction45mtemp != null){
java.lang.String typenameOnCard56 = getTransaction45mtemp.getNameOnCard();
        String tempResultnameOnCard56 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typenameOnCard56));
        %>
        <%= tempResultnameOnCard56 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">unitPrice:</TD>
<TD>
<%
if(getTransaction45mtemp != null){
%>
<%=getTransaction45mtemp.getUnitPrice()
%><%}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">creditCard:</TD>
<TD>
<%
if(getTransaction45mtemp != null){
java.lang.String typecreditCard60 = getTransaction45mtemp.getCreditCard();
        String tempResultcreditCard60 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typecreditCard60));
        %>
        <%= tempResultcreditCard60 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">totalPrice:</TD>
<TD>
<%
if(getTransaction45mtemp != null){
%>
<%=getTransaction45mtemp.getTotalPrice()
%><%}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">createdOn:</TD>
<TD>
<%
if(getTransaction45mtemp != null){
java.lang.String typecreatedOn64 = getTransaction45mtemp.getCreatedOn();
        String tempResultcreatedOn64 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typecreatedOn64));
        %>
        <%= tempResultcreatedOn64 %>
        <%
}%>
</TD>
<TR>
<TD WIDTH="5%"></TD>
<TD COLSPAN="2" ALIGN="LEFT">cardNumber:</TD>
<TD>
<%
if(getTransaction45mtemp != null){
java.lang.String typecardNumber66 = getTransaction45mtemp.getCardNumber();
        String tempResultcardNumber66 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(typecardNumber66));
        %>
        <%= tempResultcardNumber66 %>
        <%
}%>
</TD>
</TABLE>
<%
}
break;
case 70:
        gotMethod = true;
        String trxnID_14id=  request.getParameter("trxnID73");
        int trxnID_14idTemp  = Integer.parseInt(trxnID_14id);
        boolean removeTransaction70mtemp = sampleTrxnWebServiceProxyid.removeTransaction(trxnID_14idTemp);
        String tempResultreturnp71 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(removeTransaction70mtemp));
        %>
        <%= tempResultreturnp71 %>
        <%
break;
case 75:
        gotMethod = true;
        java.lang.String getAllTransactions75mtemp = sampleTrxnWebServiceProxyid.getAllTransactions();
if(getAllTransactions75mtemp == null){
%>
<%=getAllTransactions75mtemp %>
<%
}else{
        String tempResultreturnp76 = org.eclipse.jst.ws.util.JspUtils.markup(String.valueOf(getAllTransactions75mtemp));
        %>
        <%= tempResultreturnp76 %>
        <%
}
break;
}
} catch (Exception e) { 
%>
Exception: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.toString()) %>
Message: <%= org.eclipse.jst.ws.util.JspUtils.markup(e.getMessage()) %>
<%
return;
}
if(!gotMethod){
%>
result: N/A
<%
}
%>
</BODY>
</HTML>